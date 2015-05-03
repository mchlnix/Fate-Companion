package com.example.fatecompanion;

import java.util.ArrayList;
import java.util.HashMap;

public class CampaignController {
	
	private static CampaignController instance = null;
	
	private HashMap<Long, Campaign> campaignCache;
	
	private CampaignController()
	{
		// Get every campaignID from the DB, then populate the cache
		
		this.campaignCache = new HashMap<Long, Campaign>();
		
		ArrayList<Long> campaignIDs = new ArrayList<Long>();
		
		/*
		 * TODO: load campaignIDs from DB and save them into `campaignIDs`
		 */
		
		for ( Long campaignID : campaignIDs)
		{
			Campaign newCampaign = new Campaign();
			
			if ( newCampaign.loadFromDB( campaignID ) )
				this.campaignCache.put( campaignID, newCampaign );
			else
			{
				/*
				 * TODO: Error handling. 
				 */
			}
		}
	}
	
	public static synchronized CampaignController getInstance()
	{
		// synchronized, because of wikipedia
		
		if ( instance == null )
		{
			instance = new CampaignController();
		}
		
		return instance;
	}
	
	public Campaign getCampaignByID( Long campaignID )
	{
		if ( ! this.campaignCache.containsKey( campaignID ) )
		{
			// Shouldn't happen, since the characterCache should be complete
			
			/*
			 * TODO: Error handling.
			 */
			
			Campaign newCampaign = new Campaign();
			
			if ( newCampaign.loadFromDB( campaignID ) )
				this.campaignCache.put( campaignID, newCampaign );
			else
			{
				/*
				 * TODO: Error handling.
				 */
			}
			
			// if not successful, this function returns null
		}
		
		return this.campaignCache.get( campaignID ); 
	}
	
	public Boolean validateName( String name )
	{
		/*
		 * TODO: more checks
		 */
		
		return ! name.isEmpty();
	}
	
	public boolean saveCampaign( Long campaignID, Long characterID, String name, String description, RPGSystem system )
	{
		// This method makes or edits a campaign, depending on, 
		// if the id is already in the campaignCache
		// campaignCache needs to be always up to date (and complete)
		
		name = name.trim();
		description = description.trim();
		
		if ( ! this.campaignCache.containsKey( campaignID ) )
		{
			Campaign newCampaign = new Campaign();
			
			if ( newCampaign.loadFromDB( campaignID ) )
				this.campaignCache.put( campaignID, newCampaign );
			else
			{
				/*
				 * TODO: Error handling.
				 */
				this.campaignCache.put( campaignID, newCampaign );
			}
		}
		return this.campaignCache.get( campaignID ).updateValues(name, description, system, characterID, campaignID);
	}
	
	public boolean playCampaign( Long campaignID )
	{
		return this.getCampaignByID( campaignID ).updateLastPlayed();
	}
}
