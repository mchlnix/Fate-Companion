package com.example.fatecompanion.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.fatecompanion.database.DatabaseContract.CampaignEntry;
import com.example.fatecompanion.database.DatabaseContract.CharacterEntry;
import com.example.fatecompanion.database.DatabaseContract.CharacterSheetFAEEntry;
import com.example.fatecompanion.database.DatabaseContract.CharacterSheetIDEntry;

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
		
		//Create-Statement for Character Table
		String createCharacterTable = 
				"CREATE TABLE " + CharacterEntry.TABLE_NAME 
				+ " (" 
				+ CharacterEntry.COLUMN_CHARACTER_ID 	+ " INTEGER PRIMARY KEY," 
				+ CharacterEntry.COLUMN_NAME 			+ " TEXT," 
				+ CharacterEntry.COLUMN_DESCRIPTION 	+ " TEXT" 
				+ " )";
		db.execSQL(createCharacterTable);
		
		//Create-Statement for Campaign Table
		String createCampaignTable =  
				"CREATE TABLE " + CampaignEntry.TABLE_NAME 
				+ " (" 
				+ CampaignEntry.COLUMN_CAMPAIGN_ID 	+ " INTEGER PRIMARY KEY," 
				+ CampaignEntry.COLUMN_NAME 		+ " TEXT," 
				+ CampaignEntry.COLUMN_DESCRIPTION 	+ " TEXT," 
				+ CampaignEntry.COLUMN_SYSTEM 		+ " TEXT," 
				+ CampaignEntry.COLUMN_LAST_PLAYED 	+ " INTEGER, " 
				+ CampaignEntry.COLUMN_CHARACTER 	+ " INTEGER references " 
				+ CharacterEntry.TABLE_NAME + "(" + CharacterEntry.COLUMN_CHARACTER_ID + ")" 
				+ ")";
		db.execSQL(createCampaignTable);
		
		//Create-Statement for CharacterSheetID Table
		String createCharacterSheetIDTable = 
				"CREATE TABLE " + CharacterSheetIDEntry.TABLE_NAME
				+ " ("
				+ CharacterSheetIDEntry.COLUMN_CHARACTERSHEET_ID_ID	+ " INTEGER PRIMARY KEY,"
				+ CharacterSheetIDEntry.COLUMN_CHARACTER			+ " INTEGER NOT NULL,"
				+ CharacterSheetIDEntry.COLUMN_CAMPAIGN				+ " INTEGER NOT NULL"
				+ ")";
		db.execSQL(createCharacterSheetIDTable);
		
		//Create-Statement for CharacterSheet Fate Accelerated Table
		String createCharacterSheetFAETable =
				"CREATE TABLE " + CharacterSheetFAEEntry.TABLE_NAME
				+ " ("
				+ CharacterSheetFAEEntry.COLUMN_NAME 		+ " TEXT,"
				+ CharacterSheetFAEEntry.COLUMN_DESCRIPTION + " TEXT,"
				+ CharacterSheetFAEEntry.COLUMN_SYSTEM 		+ " TEXT,"
				+ CharacterSheetFAEEntry.COLUMN_HIGHCONCEPT + " TEXT,"
				+ CharacterSheetFAEEntry.COLUMN_TROUBLE 	+ " TEXT,"
				+ CharacterSheetFAEEntry.COLUMN_ASPECT1		+ " TEXT,"
				+ CharacterSheetFAEEntry.COLUMN_ASPECT2		+ " TEXT,"
				+ CharacterSheetFAEEntry.COLUMN_ASPECT3		+ " TEXT,"
				+ CharacterSheetFAEEntry.COLUMN_STUNT1		+ " TEXT,"
				+ CharacterSheetFAEEntry.COLUMN_STUNT2		+ " TEXT,"
				+ CharacterSheetFAEEntry.COLUMN_STUNT3		+ " TEXT,"
				+ CharacterSheetFAEEntry.COLUMN_STUNT4		+ " TEXT,"
				+ CharacterSheetFAEEntry.COLUMN_STUNT5		+ " TEXT,"
				+ CharacterSheetFAEEntry.COLUMN_CAREFUL		+ " INTEGER,"
				+ CharacterSheetFAEEntry.COLUMN_CLEVER		+ " INTEGER,"
				+ CharacterSheetFAEEntry.COLUMN_FLASHY		+ " INTEGER,"
				+ CharacterSheetFAEEntry.COLUMN_FORCEFUL	+ " INTEGER,"
				+ CharacterSheetFAEEntry.COLUMN_QUICK		+ " INTEGER,"
				+ CharacterSheetFAEEntry.COLUMN_SNEAKY		+ " INTEGER,"
				+ CharacterSheetFAEEntry.COLUMN_STRESS1		+ " INTEGER,"
				+ CharacterSheetFAEEntry.COLUMN_STRESS2		+ " INTEGER,"
				+ CharacterSheetFAEEntry.COLUMN_STRESS3		+ " INTEGER,"
				+ CharacterSheetFAEEntry.COLUMN_CONSEQUENCE1 + " TEXT,"
				+ CharacterSheetFAEEntry.COLUMN_CONSEQUENCE2 + " TEXT,"
				+ CharacterSheetFAEEntry.COLUMN_CONSEQUENCE3 + " TEXT,"
				+ CharacterSheetFAEEntry.COLUMN_REFRESH		+ " INTEGER,"
				+ CharacterSheetFAEEntry.COLUMN_FATEPOINTS	+ " INTEGER,"
				+ CharacterSheetFAEEntry.COLUMN_CHARACTERSHEETID	+ " INTEGER NOT NULL,"
				+ " PRIMARY KEY (" + CharacterSheetFAEEntry.COLUMN_CHARACTERSHEETID + "),"
				+ " FOREIGN KEY (" + CharacterSheetFAEEntry.COLUMN_CHARACTERSHEETID + ") REFERENCES " + CharacterSheetIDEntry.TABLE_NAME + "(" + CharacterSheetIDEntry.COLUMN_CHARACTERSHEET_ID_ID + ")"
				+ ")";
		db.execSQL(createCharacterSheetFAETable);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}
	
	
	

}
