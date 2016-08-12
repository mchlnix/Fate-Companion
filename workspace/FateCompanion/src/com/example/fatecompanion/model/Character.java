package com.example.fatecompanion.model;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.fatecompanion.database.DatabaseContract.CharacterEntry;
import com.example.fatecompanion.util.FateDBUtils;


public class Character {
	
	private Long id;
	private String name;
	private String description;
	private ArrayList<Long> campaigns;
	private HashMap<RPGSystem, CharacterSheet> templateSheets;
	private HashMap<Long, CharacterSheet> sheets;
	
	
	public Character()
	{
		this.id = 1L;
		this.name = "";
		this.description = "";

		this.campaigns = new ArrayList<Long>();
		this.templateSheets = new HashMap<RPGSystem, CharacterSheet>();
		this.sheets = new HashMap<Long, CharacterSheet>();
	}
	
	public boolean updateValues( String name, String description, Long characterID , SQLiteDatabase db )
	{
		this.id = characterID;
		this.name = name;
		this.description = description;
		
		return this.saveToDB(db);
	}

	public boolean addTemplateSheet( CharacterSheet sheet, RPGSystem system , SQLiteDatabase db )
	{
		this.templateSheets.put( system, sheet );
		
		return this.saveToDB(db);
	}
	
	public boolean addCharacterSheet( CharacterSheet sheet, Long campaignID , SQLiteDatabase db )
	{
		if ( ! this.campaigns.contains( campaignID ) )
			this.campaigns.add( campaignID );

		this.sheets.put( campaignID, sheet );
		
		return this.saveToDB(db);
	}
	
	public boolean removeCampaign( Long campaignID , SQLiteDatabase db )
	{
		this.sheets.remove( campaignID );
		
		return this.saveToDB(db);
	}
	
	//loads a specified character out of the DB and sets the properties
	public boolean loadFromDB( Long characterID , SQLiteDatabase database )
	{
		String[] projection = {CharacterEntry.COLUMN_CHARACTER_ID, CharacterEntry.COLUMN_NAME, CharacterEntry.COLUMN_DESCRIPTION};
		String selection = CharacterEntry.COLUMN_CHARACTER_ID + " = " + characterID.toString();
		
		Cursor c = database.query(CharacterEntry.TABLE_NAME, projection, selection, null, null, null, null);
		
		c.moveToFirst();
		this.id = c.getLong( c.getColumnIndex( CharacterEntry.COLUMN_CHARACTER_ID ) );
		this.name = c.getString( c.getColumnIndex( CharacterEntry.COLUMN_NAME ) );
		this.description = c.getString( c.getColumnIndex( CharacterEntry.COLUMN_DESCRIPTION ) );
		
		if (this.id.equals( c.getLong( c.getColumnIndex( CharacterEntry.COLUMN_CHARACTER_ID ) ) )
				&& this.name.equals( c.getString( c.getColumnIndex( CharacterEntry.COLUMN_NAME ) ) )
				&& this.description.equals( c.getString( c.getColumnIndex( CharacterEntry.COLUMN_DESCRIPTION ) ) ) )
		{
			c.close();
			return true;
		}
		
		c.close();
		return false;
	}
	
	private boolean saveToDB( SQLiteDatabase database )
	{	
		if ( validateName( getName() ) == 1 )
		{
			//invalid name
			return false;
		}
		
		if ( validateDescription( getDescription() ) == 1 )
		{
			//invalid description
			return false;
		}
		
		ContentValues values = new ContentValues();
		values.put( CharacterEntry.COLUMN_CHARACTER_ID, getID() );
		values.put( CharacterEntry.COLUMN_NAME, getName() );
		values.put( CharacterEntry.COLUMN_DESCRIPTION, getDescription() );
		
		//Check whether characterID is already in the DB (update) or needs to be added (insert)
		ArrayList<Long> checklist = FateDBUtils.loadCharacterIDs(database);
		if ( checklist.contains( getID() ) )
		{
			database.update(CharacterEntry.TABLE_NAME, values, CharacterEntry.COLUMN_CHARACTER_ID + " = " + getID().toString(), null);
			return true;
		} else
		{
			if ( database.insert( CharacterEntry.TABLE_NAME, null, values) != -1)
			{	
				return true;
			}
		}
		
		//TODO can't save? Error Handling
		return false;
	}

	public Long getID() 
	{
		return this.id;
	}

	public static int validateName( String name )
	{
		/*
		 * 0 - all clear
		 * 1 - empty string
		 */
		
		if ( name.trim().isEmpty() )
			return 1;
		
		return 0;
	}

	public String getName() 
	{
		return this.name;
	}
	
	public static int validateDescription( String description )
	{
		/*
		 * 0 - all clear
		 * 1 - empty string
		 */
		
		if ( description.trim().isEmpty() )
			return 1;
		
		return 0;
	}

	public String getDescription() 
	{
		return this.description;
	}

	public ArrayList<Long> getCampaigns()
	{
		return this.campaigns;
	}
	
	public String toString()
	{
		return "CharacterID: " + this.id.toString() + "; Name: " + this.name +
				"; Description: " + this.description;
	}
}
