package com.example.fatecompanion.controller;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.fatecompanion.database.DbHelper;
import com.example.fatecompanion.model.Campaign;
import com.example.fatecompanion.model.RPGSystem;
import com.example.fatecompanion.util.FateDBUtils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class CampaignController {
	
	private static CampaignController instance = null;
	
	private HashMap<Long, Campaign> campaignCache;
	
	private Context appContext;
	
	private DbHelper dbHelper;
	
	private SQLiteDatabase database;
	
	private CampaignController(Context applicationContext)
	{
		this.appContext = applicationContext;
		this.dbHelper = new DbHelper(this.appContext);
		this.database = this.dbHelper.getWritableDatabase();
		
		this.campaignCache = new HashMap<Long, Campaign>();
	
		//load all campaignIDs from the DB
		ArrayList<Long> campaignIDs = FateDBUtils.loadCampaignIDs(database);
		
		//populate Cache
		for ( Long campaignID : campaignIDs)
		{
			Campaign newCampaign = new Campaign();
			
			if ( newCampaign.loadFromDB( campaignID, database ) )
				this.campaignCache.put( campaignID, newCampaign );
			else
			{
				Toast.makeText(appContext, "Campaign with ID " + Long.toString(campaignID) + " could not be loaded.", Toast.LENGTH_SHORT).show();
			}
		}
	}
	
	public static synchronized CampaignController getInstance(Context applicationContext)
	{
		// synchronized, because of wikipedia
		
		if ( instance == null )
		{
			instance = new CampaignController(applicationContext);
		}
		
		return instance;
	}
	
	
	public Campaign getCampaignByID( Long campaignID )
	{	
		if ( ! this.campaignCache.containsKey( campaignID ) )
		{	
			//load all characterIDs and check database for the specific ID
			ArrayList<Long> campaignIDs = FateDBUtils.loadCampaignIDs(database);
			
			if ( ! campaignIDs.contains( campaignID ) )
			{
				//can't do anything, so User is advised to return to previous list
				Toast.makeText(appContext, "Please return to previous list and try again.", Toast.LENGTH_LONG).show();
				
				return null;
			} else
			{
				//reload the Cache, completely
				for ( Long campID : campaignIDs)
				{
					Campaign newCamp = new Campaign();
					
					if ( newCamp.loadFromDB( campID , database ) )
						this.campaignCache.put( campID, newCamp );
					else
					{
						Toast.makeText(appContext, "Campaign with ID " + Long.toString(campID) + " could not be loaded.", Toast.LENGTH_SHORT).show();
					}
				}
			}
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
			this.campaignCache.put( campaignID, newCampaign );
		}
		
		return this.campaignCache.get( campaignID ).updateValues(name, description, system, characterID, campaignID, database);
	}
	
	public boolean playCampaign( Long campaignID )
	{
		return this.getCampaignByID( campaignID ).updateLastPlayed( database );
	}
}
