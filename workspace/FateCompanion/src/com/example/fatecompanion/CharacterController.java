package com.example.fatecompanion;

import java.util.ArrayList;
import java.util.HashMap;

public class CharacterController {

	private HashMap<Long, Character> characterCache;
	
	public CharacterController()
	{
		// Get every characterID from the DB, then populate the cache
		
		this.characterCache = new HashMap<Long, Character>();
		
		ArrayList<Long> characterIDs = new ArrayList<Long>();
		
		/*
		 * TODO: load characterIDs from DB and save them into `characterIDs`
		 */
		
		for ( Long characterID : characterIDs)
		{
			Character newChar = new Character();
			
			if ( newChar.loadFromDB( characterID ) )
				this.characterCache.put( characterID, newChar );
			else
			{
				/*
				 * TODO: Error handling. 
				 */
			}
		}
	}
	
	public ArrayList<Long> getAllCharacterIDs()
	{
		// Might as well do it this way, won't be 100+ characters
		// If performance becomes an issue, just fetch them from the db
		
		return new ArrayList<Long>( this.characterCache.keySet() );
	}
	
	public Character getCharacterByID( Long characterID )
	{
		if ( ! this.characterCache.containsKey( characterID ) )
		{
			// Shouldn't happen, since the characterCache should be complete
			
			/*
			 * TODO: Error handling.
			 */
			
			Character newChar = new Character();
			
			if ( newChar.loadFromDB( characterID ) )
				this.characterCache.put( characterID, newChar );
			else
			{
				/*
				 * TODO: Error handling.
				 */
			}
			
			// if not successful, this function returns null
		}
		
		return this.characterCache.get( characterID );
	}
	
	public ArrayList<Long> getCampaignIDsByCharacterID( Long characterID )
	{
		return this.characterCache.get( characterID ).getCampaigns();
	}
	
	public boolean saveCharacter( Long characterID, String name, String description ) 
	{
		// This method makes or edits a character, depending on, 
		// if the id is already in the characterCache
		// characterCache needs to be always up to date (and complete)
		
		if ( ! this.characterCache.containsKey( characterID ) )
		{
			Character newChar = new Character();
			
			if ( newChar.loadFromDB( characterID ) )
				this.characterCache.put( characterID, newChar );
			else
			{
				/*
				 * TODO: Error handling.
				 */
			}
		}
		
		return this.characterCache.get( characterID ).updateValues( name, description, characterID );
	}
	
	public void addCampaignToCharacter( Long campaignID, Long characterID )
	{
		// adds empty characterSheet, so that the character has the campaign
		
		this.characterCache.get( characterID ).addCharacterSheet( null, campaignID );
	}
	
}
