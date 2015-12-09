package com.example.fatecompanion;

public final class DatabaseContract {

	//Empty Constructor, because it should not be instantiated
	public DatabaseContract() {}
	
	
	public static abstract class CharacterEntry {
		public static final String TABLE_NAME = "Character";
		public static final String COLUMN_CHARACTER_ID = "characterid";
		public static final String COLUMN_NAME = "charactername";
		public static final String COLUMN_DESCRIPTION = "characterdescription";
	}
	
	public static abstract class CampaignEntry {
		public static final String TABLE_NAME = "Campaign";
		public static final String COLUMN_CAMPAIGN_ID = "campaignid";
		public static final String COLUMN_NAME = "campaignname";
		public static final String COLUMN_DESCRIPTION = "campaigndescription";
		public static final String COLUMN_SYSTEM = "campaignsystem";
		public static final String COLUMN_LAST_PLAYED = "campaignlastplayed";
		public static final String COLUMN_CHARACTER = "campaigncharacter";
	}
}
