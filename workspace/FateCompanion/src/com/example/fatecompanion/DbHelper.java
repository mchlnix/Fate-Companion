package com.example.fatecompanion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
		String createCharacterTable = "CREATE TABLE " + DatabaseContract.CharacterEntry.TABLE_NAME + " (" + DatabaseContract.CharacterEntry.COLUMN_NAME_CHARACTER_ID + " INTEGER PRIMARY KEY," + DatabaseContract.CharacterEntry.COLUMN_NAME_NAME + " TEXT," + DatabaseContract.CharacterEntry.COLUMN_NAME_DESCRIPTION + " TEXT" + " )";
		db.execSQL(createCharacterTable);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}
	
	
	

}
