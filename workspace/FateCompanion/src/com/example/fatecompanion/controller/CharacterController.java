package com.example.fatecompanion.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.example.fatecompanion.database.DbHelper;
import com.example.fatecompanion.database.DatabaseContract.CampaignEntry;
import com.example.fatecompanion.model.Character;
import com.example.fatecompanion.util.FateDBUtils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class CharacterController {
	
	private static CharacterController instance = null;

	private HashMap<Long, Character> characterCache;
	
	private Context appContext;
	
	private DbHelper dbHelper;
	
	private SQLiteDatabase database;
	
	
	private CharacterController(Context applicationContext)
	{		
		//if problems occur - switch "this" for "instance"
		this.appContext = applicationContext;
		this.dbHelper = new DbHelper(this.appContext);
		this.database = this.dbHelper.getWritableDatabase();
		
		this.characterCache = new HashMap<Long, Character>();
		
		
		// Get every characterID from the DB
		ArrayList<Long> characterIDs = FateDBUtils.loadCharacterIDs(database);
		
		//populate characterCache
		for ( Long characterID : characterIDs)
		{
			Character newChar = new Character();
			
			if ( newChar.loadFromDB( characterID , database ) )
				this.characterCache.put( characterID, newChar );
			else
			{
				Toast.makeText(appContext, "Character with ID " + Long.toString(characterID) + " could not be loaded.", Toast.LENGTH_SHORT).show();
			}
		}
	}
	
	public static synchronized CharacterController getInstance(Context applicationContext)
	{
		// synchronized, because of wikipedia
		
		if (instance == null)
		{
			instance = new CharacterController(applicationContext);
		}
		
		return instance;
	}
	
	public ArrayList<Long> getAllCharacterIDs()
	{
		// Might as well do it this way, won't be 100+ characters
		// If performance becomes an issue, just fetch them from the db
		
		return new ArrayList<Long>( this.characterCache.keySet() );
	}
	
	//if not successful returns null
	public Character getCharacterByID( Long characterID )
	{
		if ( ! this.characterCache.containsKey( characterID ) )
		{	
			//load all characterIDs and check database for the specific ID
			ArrayList<Long> characterIDs = FateDBUtils.loadCharacterIDs(database);
			
			if ( ! characterIDs.contains( characterID ) )
			{
				//can't do anything, so User is advised to return to CharacterListView
				Toast.makeText(appContext, "Please return to list of characters and try again.", Toast.LENGTH_LONG).show();
				
				return null;
			} else
			{
				//reload the Cache, completely
				for ( Long charID : characterIDs)
				{
					Character newChar = new Character();
					
					if ( newChar.loadFromDB( charID , database ) )
						this.characterCache.put( charID, newChar );
					else
					{
						Toast.makeText(appContext, "Character with ID " + Long.toString(charID) + " could not be loaded.", Toast.LENGTH_SHORT).show();
					}
				}
			}
		}
		
		return this.characterCache.get( characterID );
	}
	
	public ArrayList<Long> getCampaignIDsByCharacterID( Long characterID )
	{
		ArrayList<Long> campaignIDs = new ArrayList<Long>();
		
		//queries every campaignID into a cursor
		String[] projection = {CampaignEntry.COLUMN_CAMPAIGN_ID};
		
		//where clause
		String where = CampaignEntry.COLUMN_CHARACTER + " = " + characterID.toString();

		Cursor c = database.query( CampaignEntry.TABLE_NAME, projection, where, null, null, null, null );
			
		//iterate over cursor and populate campaignIDs
		while ( c.moveToNext() )
		{
			campaignIDs.add( c.getLong ( c.getColumnIndex( CampaignEntry.COLUMN_CAMPAIGN_ID ) ) );
		}
						
		c.close();
				
		return campaignIDs;
	}
	
	// This method makes or edits a character, depending on, 
	// if the id is already in the characterCache
	// characterCache needs to be always up to date (and complete)
	public boolean saveCharacter( Long characterID, String name, String description ) 
	{	
		name = name.trim();
		description = description.trim();
		
		if ( ! this.characterCache.containsKey( characterID ) )
		{
			//new character is not yet in the cache, so it is added
			Character newChar = new Character();
			this.characterCache.put(characterID, newChar);
		}
		//else: character needs to be updated, which is called in the return-clause
		
		return this.characterCache.get( characterID ).updateValues( name, description, characterID , database );
	}
	
	//TODO
	public void addCampaignToCharacter( Long campaignID, Long characterID )
	{
		// adds empty characterSheet, so that the character has the campaign
		// TODO: Error handling, when no character found.
		// TODO: Change return type to boolean?
		this.characterCache.get( characterID ).addCharacterSheet( null, campaignID , database );
	}
	
	public Date getLastPlayed( Long characterID )
	{
		ArrayList<Long> list = getCampaignIDsByCharacterID( characterID );
		
		Date date = new Date();
		date.setTime( 0L );
		
		for ( Long campaignID : list )
		{
			Date temp = CampaignController.getInstance(appContext).getCampaignByID( campaignID ).getLastPlayed();
			
			if ( temp.after( date ) )
				date = temp;
		}
		
		return date;
	}
}
