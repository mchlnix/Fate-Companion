package com.example.fatecompanion;

import java.util.ArrayList;

import com.example.fatecompanion.DatabaseContract.CharacterSheetIDEntry;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class CharacterSheetID {
	
	private Long characterSheetIDID;
	
	private Long campaignID;
	private Long characterID;
	
	public CharacterSheetID( Long campaignID, Long characterID )
	{
		this.campaignID = campaignID;
		this.characterID = characterID;
		this.characterSheetIDID = 1L;
	}
	
	public boolean equals( CharacterSheetID characterSheetID )
	{
		return this.campaignID == characterSheetID.campaignID &&
				this.characterID == characterSheetID.characterID;
	}
	
	public boolean loadFromDB( Long characterID, Long campaignID, SQLiteDatabase database )
	{
		String[] projection = {
				CharacterSheetIDEntry.COLUMN_CHARACTERSHEET_ID_ID,
				CharacterSheetIDEntry.COLUMN_CHARACTER,
				CharacterSheetIDEntry.COLUMN_CAMPAIGN};
		String selection = CharacterSheetIDEntry.COLUMN_CHARACTER + " = " + characterID.toString() 
				+ " AND " + CharacterSheetIDEntry.COLUMN_CAMPAIGN + " = " + campaignID.toString();
		
		Cursor c = database.query(CharacterSheetIDEntry.TABLE_NAME, projection, selection, null, null, null, null);
		
		c.moveToFirst();
		this.characterSheetIDID	= c.getLong( c.getColumnIndex( CharacterSheetIDEntry.COLUMN_CHARACTERSHEET_ID_ID ) );
		this.characterID		= c.getLong( c.getColumnIndex( CharacterSheetIDEntry.COLUMN_CHARACTER ) );
		this.campaignID			= c.getLong( c.getColumnIndex( CharacterSheetIDEntry.COLUMN_CAMPAIGN ) );
		
		if ( this.characterSheetIDID.equals( c.getLong( c.getColumnIndex( CharacterSheetIDEntry.COLUMN_CHARACTERSHEET_ID_ID ) ) )
			&& this.characterID.equals( c.getLong( c.getColumnIndex( CharacterSheetIDEntry.COLUMN_CHARACTER ) ) )
			&& this.campaignID.equals( c.getLong( c.getColumnIndex( CharacterSheetIDEntry.COLUMN_CAMPAIGN ) ) ) )
		{
			c.close();
			return true;
		}
		
		c.close();
		return false;
	}
	
	public boolean saveToDB( SQLiteDatabase database )
	{
		ContentValues values = new ContentValues();
		
		values.put( CharacterSheetIDEntry.COLUMN_CHARACTERSHEET_ID_ID, getCharacterSheetIDID() );
		values.put( CharacterSheetIDEntry.COLUMN_CHARACTER, getCharacterID() );
		values.put( CharacterSheetIDEntry.COLUMN_CAMPAIGN, getCampaignID() );
		
		//check whether ID is already in the DB (update) or needs to be added (insert)
		ArrayList<Long> checklist = FateDBUtils.loadCharacterSheetIDIDs( database );
		
		if ( checklist.contains( getCharacterSheetIDID() ) )
		{
			database.update(CharacterSheetIDEntry.TABLE_NAME, 
					values, 
					CharacterSheetIDEntry.COLUMN_CHARACTERSHEET_ID_ID + " = " + getCharacterSheetIDID().toString(), 
					null);
			return true;
		} else
		{
			if ( database.insert(CharacterSheetIDEntry.TABLE_NAME, null, values) != -1 )
			{
				return true;
			}
		}
		
		
		return false;
	}

	public Long getCharacterSheetIDID() {
		return characterSheetIDID;
	}

	public void setCharacterSheetIDID(Long characterSheetIDID) {
		this.characterSheetIDID = characterSheetIDID;
	}

	public Long getCampaignID() {
		return campaignID;
	}

	public void setCampaignID(Long campaignID) {
		this.campaignID = campaignID;
	}

	public Long getCharacterID() {
		return characterID;
	}

	public void setCharacterID(Long characterID) {
		this.characterID = characterID;
	}
}
