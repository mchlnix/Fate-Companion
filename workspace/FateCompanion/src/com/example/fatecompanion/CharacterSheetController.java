package com.example.fatecompanion;

import java.util.HashMap;

public class CharacterSheetController {
	
	HashMap<CharacterSheetID, CharacterSheet> characterSheetCache;
	
	public CharacterSheetController()
	{
		this.characterSheetCache = new HashMap<CharacterSheetID, CharacterSheet>();
	}
	
	public boolean addCharacterSheet( Long campaignID, Long characterID, CharacterSheet characterSheet )
	{
		this.characterSheetCache.put( new CharacterSheetID( campaignID, characterID ), characterSheet );
		
		//TODO: Fix this Max!
		//return CharacterController.getInstance().getCharacterByID( characterID ).addCharacterSheet( characterSheet, campaignID );
		return false;
	}
	
	public boolean addTemplateCharacterSheet( Long characterID, CharacterSheet sheet )
	{
		//TODO: Fix this Max!
		//return CharacterController.getInstance().getCharacterByID( characterID )
		//.addTemplateSheet( sheet, sheet.getSystem() );
		return false;
	}
	
	public CharacterSheet getCharacterSheet( Long campaignID, Long characterID )
	{
		return this.characterSheetCache.get( new CharacterSheetID( campaignID, characterID ) );
	}
}
