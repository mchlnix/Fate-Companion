package com.example.fatecompanion;

import java.util.ArrayList;
import java.util.Date;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.example.fatecompanion.DatabaseContract.CampaignEntry;

public class Campaign {

	private Long id;
	private String name;
	private String description;
	private RPGSystem system;
	
	private Date lastPlayed;
	private Long character;
	
	public Campaign()
	{
		this.id = 1L;
		this.name = "";
		this.description = "";
		
		this.system = null;
		this.lastPlayed = new Date();
		this.lastPlayed.setTime( 0 );
		this.character = 1L;
	}
	
	public boolean updateValues( String name, String description, RPGSystem system, Long characterID, Long campaignID, SQLiteDatabase database )
	{
		this.id = campaignID;
		this.name = name;
		this.description = description;
		this.system = system;
		
		this.lastPlayed = new Date();
		this.character = characterID;
		
		return this.saveToDB( database );
	}
	
	public boolean updateLastPlayed( SQLiteDatabase database )
	{
		this.lastPlayed = new Date();
		
		return this.saveToDB( database );
	}
	
	public boolean loadFromDB( Long campaignID, SQLiteDatabase database )
	{
		// TODO: load from DB using the parameter ID
		// and set the properties
		
		return false;
	}
	
	private boolean saveToDB(SQLiteDatabase database)
	{
		ContentValues values = new ContentValues();
		values.put( CampaignEntry.COLUMN_CAMPAIGN_ID, getID() );
		values.put( CampaignEntry.COLUMN_NAME, getName() );
		values.put( CampaignEntry.COLUMN_DESCRIPTION, getDescription() );
		values.put( CampaignEntry.COLUMN_SYSTEM, getSystem().name() );
		values.put( CampaignEntry.COLUMN_LAST_PLAYED, getLastPlayed().getTime() );
		values.put( CampaignEntry.COLUMN_CHARACTER, getCharacter() );
		
		//Check whether characterID is already in the DB (update) or needs to be added (insert)
		ArrayList<Long> checklist = FateDBUtils.loadCampaignIDs(database);
		if ( checklist.contains( getID() ) )
		{
			database.update(CampaignEntry.TABLE_NAME, values, null, null);
			return true;
		} else
		{
			if ( database.insert( CampaignEntry.TABLE_NAME, null, values) != -1)
			{	
				return true;
			}
		}
		
		//TODO database can't save?
		return false;
	}

	public Long getID() {
		return id;
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

	public String getName() {
		return name;
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

	public String getDescription() {
		return description;
	}

	public RPGSystem getSystem() {
		return system;
	}

	public Date getLastPlayed() {
		return lastPlayed;
	}

	public Long getCharacter() {
		return character;
	}

	public String toString()
	{
		return "CampaignID: " + this.id.toString() + "; System: " + this.system + 
				"; Name: " + this.name + "; Description" + this.description;
	}
}
