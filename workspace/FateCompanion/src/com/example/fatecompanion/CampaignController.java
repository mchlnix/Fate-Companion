package com.example.fatecompanion;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.fatecompanion.DatabaseContract.CampaignEntry;

import android.content.Context;
import android.database.Cursor;
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
		ArrayList<Long> campaignIDs = loadCampaignIDs();
		
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
			// Shouldn't happen, since the characterCache should be complete
			
			/*
			 * TODO: Error handling.
			 */
			
			Campaign newCampaign = new Campaign();
			
			if ( newCampaign.loadFromDB( campaignID, database ) )
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
			this.campaignCache.put( campaignID, newCampaign );
		}
		
		return this.campaignCache.get( campaignID ).updateValues(name, description, system, characterID, campaignID, database);
	}
	
	public boolean playCampaign( Long campaignID )
	{
		return this.getCampaignByID( campaignID ).updateLastPlayed( database );
	}
	
	private ArrayList<Long> loadCampaignIDs()
	{
		ArrayList<Long> campaignIDs = new ArrayList<Long>();
		
		//queries every campaignID into a cursor
		String[] projection = {CampaignEntry.COLUMN_NAME_CAMPAIGN_ID};
		Cursor c = this.database.query(CampaignEntry.TABLE_NAME, projection, null, null, null, null, null);
			
		//iterate over cursor and populate campaignIDs
		while ( c.moveToNext() )
		{
			campaignIDs.add( c.getLong ( c.getColumnIndex( CampaignEntry.COLUMN_NAME_CAMPAIGN_ID ) ) );
		}
						
		c.close();
				
		return campaignIDs;
	}
}
