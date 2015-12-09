package com.example.fatecompanion;

import java.util.ArrayList;

import com.example.fatecompanion.DatabaseContract.*;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


//FateDBUtils provides static utility functionality for the database used (NO WRITE ACCESS)
public final class FateDBUtils {
	
	//Empty Constructor, because it should not be instantiated
	public FateDBUtils() {};
	
	
	//loads every characterID from the database and returns them in an ArrayList
	public static ArrayList<Long> loadCharacterIDs( SQLiteDatabase database )
	{
		ArrayList<Long> characterIDs = new ArrayList<Long>();
		
		//queries every character_id into a cursor
		String[] projection = {CharacterEntry.COLUMN_CHARACTER_ID};
		Cursor c = database.query( CharacterEntry.TABLE_NAME, projection, null, null, null, null, null );
				
		//iterate over cursor and populate characterIDs
		while ( c.moveToNext() )
		{
			characterIDs.add( c.getLong ( c.getColumnIndex( CharacterEntry.COLUMN_CHARACTER_ID ) ) );
		}
		
		c.close();
		
		return characterIDs;
	}
	
	//loads every campaignID from the database and returns them in an ArrayList
	public static ArrayList<Long> loadCampaignIDs( SQLiteDatabase database )
	{
		ArrayList<Long> campaignIDs = new ArrayList<Long>();
		
		//queries every campaignID into a cursor
		String[] projection = {CampaignEntry.COLUMN_CAMPAIGN_ID};
		Cursor c = database.query( CampaignEntry.TABLE_NAME, projection, null, null, null, null, null );
			
		//iterate over cursor and populate campaignIDs
		while ( c.moveToNext() )
		{
			campaignIDs.add( c.getLong ( c.getColumnIndex( CampaignEntry.COLUMN_CAMPAIGN_ID ) ) );
		}
						
		c.close();
				
		return campaignIDs;
	}

}
