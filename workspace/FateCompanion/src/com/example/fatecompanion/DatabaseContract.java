package com.example.fatecompanion;

public final class DatabaseContract {

	//Empty Constructor, because it should not be instantiated
	public DatabaseContract() {}
	
	
	public static abstract class CharacterEntry {
		public static final String TABLE_NAME = "Character";
		public static final String COLUMN_NAME_CHARACTER_ID = "characterid";
		public static final String COLUMN_NAME_NAME = "charactername";
		public static final String COLUMN_NAME_DESCRIPTION = "characterdescription";
	}
	
	
}
