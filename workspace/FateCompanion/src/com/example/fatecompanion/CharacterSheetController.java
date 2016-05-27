package com.example.fatecompanion;

import java.util.ArrayList;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class CharacterSheetController {
	
	private static CharacterSheetController instance = null;
	
	private Context appContext;
	
	private DbHelper dbHelper;
	
	private SQLiteDatabase database;
	
	private ArrayList<CharacterSheetID> characterSheetIDs;
	
	
	public CharacterSheetController( Context applicationContext )
	{
		this.appContext = applicationContext;
		this.dbHelper = new DbHelper( this.appContext );
		this.database = this.dbHelper.getWritableDatabase();
		
		this.characterSheetIDs = new ArrayList<CharacterSheetID>();
		
		//populate the CharacterSheetID-List
		ArrayList<Long> characterSheetIDIDs = FateDBUtils.loadCharacterSheetIDIDs( this.database );
		for ( Long csIDID : characterSheetIDIDs )
		{
			CharacterSheetID newCharacterSheetID = new CharacterSheetID ( 1L, 1L );
			
			if ( newCharacterSheetID.loadFromDB( csIDID, this.database ) )
			{
				characterSheetIDs.add( newCharacterSheetID );
			} else
			{
				Toast.makeText(appContext, "CharacterSheetID with ID " + Long.toString(csIDID) + " could not be loaded.", Toast.LENGTH_SHORT).show();
			}
		}
	}
	
	public static synchronized CharacterSheetController getInstance(Context applicationContext)
	{
		if ( instance == null)
		{
			instance = new CharacterSheetController( applicationContext );
		}
		
		return instance;
	}
	
	public CharacterSheetID getCharacterSheetIDByID( Long characterID, Long campaignID )
	{
		CharacterSheetID temp = null;
		
		//search the CharacterSheetID-List
		for ( CharacterSheetID characterSheetID : characterSheetIDs )
		{
			if( characterSheetID.getCharacterID().equals( characterID )
					&& characterSheetID.getCampaignID().equals( campaignID ) )
			{
				temp = characterSheetID;
				break;
			}
		}
		
		return temp;
	}
	
	public CharacterSheetID getCharacterSheetIDByID( Long characterSheetIDID )
	{
		
		
		return null;
	}
}
