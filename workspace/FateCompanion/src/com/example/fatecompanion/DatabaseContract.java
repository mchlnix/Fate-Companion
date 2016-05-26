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
	
	public static abstract class CharacterSheetIDEntry {
		public static final String TABLE_NAME = "CharacterSheetID";
		public static final String COLUMN_CHARACTERSHEET_ID_ID = "charactersheetidid";
		public static final String COLUMN_CHARACTER = "characterid";
		public static final String COLUMN_CAMPAIGN = "campaignid";
	}
	
	public static abstract class CharacterSheetFAEEntry {
		public static final String TABLE_NAME = "CharacterSheetFateAccelerated";
		public static final String COLUMN_NAME = "charactername";
		public static final String COLUMN_DESCRIPTION = "characterdescription";
		public static final String COLUMN_SYSTEM = "charactersystem";
		public static final String COLUMN_HIGHCONCEPT = "characterhighconcept";
		public static final String COLUMN_TROUBLE = "charactertrouble";
		public static final String COLUMN_ASPECT1 = "characteraspect1";
		public static final String COLUMN_ASPECT2 = "characteraspect2";
		public static final String COLUMN_ASPECT3 = "characteraspect3";
		public static final String COLUMN_STUNT1 = "characterstunt1";
		public static final String COLUMN_STUNT2 = "characterstunt2";
		public static final String COLUMN_STUNT3 = "characterstunt3";
		public static final String COLUMN_STUNT4 = "characterstunt4";
		public static final String COLUMN_STUNT5 = "characterstunt5";
		public static final String COLUMN_CAREFUL = "characterapproachcareful";
		public static final String COLUMN_CLEVER = "characterapproachclever";
		public static final String COLUMN_FLASHY = "characterapproachflashy";
		public static final String COLUMN_FORCEFUL = "characterapproachforceful";
		public static final String COLUMN_QUICK = "characterapproachquick";
		public static final String COLUMN_SNEAKY = "characterapproachsneaky";
		public static final String COLUMN_STRESS1 = "characterstress1";
		public static final String COLUMN_STRESS2 = "characterstress2";
		public static final String COLUMN_STRESS3 = "characterstress3";
		public static final String COLUMN_CONSEQUENCE1 = "characterconsequence1";
		public static final String COLUMN_CONSEQUENCE2 = "characterconsequence2";
		public static final String COLUMN_CONSEQUENCE3 = "characterconsequence3";
		public static final String COLUMN_REFRESH = "characterrefresh";
		public static final String COLUMN_FATEPOINTS = "characterfatepoints";
		public static final String COLUMN_CHARACTERSHEETID = "charactersheetid";
	}
}
