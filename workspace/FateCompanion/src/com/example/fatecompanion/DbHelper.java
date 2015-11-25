package com.example.fatecompanion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.fatecompanion.DatabaseContract.*;
//imports CharacterEntry and CampaignEntry

public class DbHelper extends SQLiteOpenHelper {
	
	private static final String DATABASE_NAME = "FateCompanion.db";
	private static final int DATABASE_VERSION = 1; 
	private Context mContext;

	
	
	
	public DbHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		mContext = context;
	}

	//method is executed when DB is first created
	@Override
	public void onCreate(SQLiteDatabase db) {
		//
		String createCharacterTable = 
				"CREATE TABLE " + CharacterEntry.TABLE_NAME 
				+ " (" 
				+ CharacterEntry.COLUMN_NAME_CHARACTER_ID + " INTEGER PRIMARY KEY," 
				+ CharacterEntry.COLUMN_NAME_NAME + " TEXT," 
				+ CharacterEntry.COLUMN_NAME_DESCRIPTION + " TEXT" 
				+ " )";
		db.execSQL(createCharacterTable);
		
		String createCampaignTable =  
				"CREATE TABLE " + CampaignEntry.TABLE_NAME 
				+ " (" 
				+ CampaignEntry.COLUMN_NAME_CAMPAIGN_ID + " INTEGER PRIMARY KEY," 
				+ CampaignEntry.COLUMN_NAME_NAME + " TEXT," 
				+ CampaignEntry.COLUMN_NAME_DESCRIPTION + " TEXT," 
				+ CampaignEntry.COLUMN_NAME_SYSTEM + " TEXT," 
				+ CampaignEntry.COLUMN_NAME_LAST_PLAYED + " INTEGER, " 
				+ CampaignEntry.COLUMN_NAME_CHARACTER + " INTEGER references " 
				+ CharacterEntry.TABLE_NAME + "(" + CharacterEntry.COLUMN_NAME_CHARACTER_ID + ")" 
				+ ")";
		db.execSQL(createCampaignTable);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}
	
	
	

}
