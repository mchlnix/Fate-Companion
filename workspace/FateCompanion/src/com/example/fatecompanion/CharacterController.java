package com.example.fatecompanion;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class CharacterController {
	
	private static CharacterController instance = null;

	private HashMap<Long, Character> characterCache;
	
	private Context appContext;
	
	private DbHelper dbHelper;
	
	private SQLiteDatabase database;
	
	private CharacterController()
	{
		// Get every characterID from the DB, then populate the cache
		
		this.characterCache = new HashMap<Long, Character>();
		
		ArrayList<Long> characterIDs = new ArrayList<Long>();
		
		/*
		 * TODO: load characterIDs from DB and save them into `characterIDs`
		 */
		
		for ( Long characterID : characterIDs)
		{
			Character newChar = new Character();
			
			if ( newChar.loadFromDB( characterID , database ) )
				this.characterCache.put( characterID, newChar );
			else
			{
				/*
				 * TODO: Error handling. 
				 */
			}
		}
	}
	
	public static synchronized CharacterController getInstance(Context applicationContext)
	{
		// synchronized, because of wikipedia
		
		if (instance == null)
		{
			instance = new CharacterController();
			instance.appContext = applicationContext;
			instance.dbHelper = new DbHelper(instance.appContext);
			instance.database = instance.dbHelper.getWritableDatabase();
		}
		
		return instance;
	}
	
	public ArrayList<Long> getAllCharacterIDs()
	{
		// Might as well do it this way, won't be 100+ characters
		// If performance becomes an issue, just fetch them from the db
		
		return new ArrayList<Long>( this.characterCache.keySet() );
	}
	
	public Character getCharacterByID( Long characterID )
	{
		if ( ! this.characterCache.containsKey( characterID ) )
		{
			// Shouldn't happen, since the characterCache should be complete
			
			/*
			 * TODO: Error handling.
			 */
			
			Character newChar = new Character();
			
			if ( newChar.loadFromDB( characterID , database ) )
				this.characterCache.put( characterID, newChar );
			else
			{
				/*
				 * TODO: Error handling.
				 */
			}
			
			// if not successful, this function returns null
		}
		
		return this.characterCache.get( characterID );
	}
	
	public ArrayList<Long> getCampaignIDsByCharacterID( Long characterID )
	{
		return this.characterCache.get( characterID ).getCampaigns();
	}
	
	public boolean saveCharacter( Long characterID, String name, String description ) 
	{
		// This method makes or edits a character, depending on, 
		// if the id is already in the characterCache
		// characterCache needs to be always up to date (and complete)
		
		name = name.trim();
		description = description.trim();
		
		if ( ! this.characterCache.containsKey( characterID ) )
		{
			Character newChar = new Character();
			
			if ( newChar.loadFromDB( characterID , database ) )
				this.characterCache.put( characterID, newChar );
			else
			{
				/*
				 * TODO: Error handling.
				 */
				this.characterCache.put( characterID, newChar );
			}
		}
		
		return this.characterCache.get( characterID ).updateValues( name, description, characterID , database );
	}
	
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
			Date temp = CampaignController.getInstance().getCampaignByID( campaignID ).getLastPlayed();
			
			if ( temp.after( date ) )
				date = temp;
		}
		
		return date;
	}
	
}
