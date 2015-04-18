package com.example.fatecompanion;

public class CharacterSheetID {
	
	private Long campaignID;
	private Long characterID;
	
	public CharacterSheetID( Long campaignID, Long characterID )
	{
		this.campaignID = campaignID;
		this.characterID = characterID;
	}
	
	public boolean equals( CharacterSheetID characterSheetID )
	{
		return this.campaignID == characterSheetID.campaignID &&
				this.characterID == characterSheetID.characterID;
	}
}
