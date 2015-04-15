package com.example.fatecompanion;

import java.util.Date;

public class Campaign {

	private Long id;
	private String name;
	private String description;
	private RPGSystem system;
	
	private Date lastPlayed;
	private Long character;
	
	public Campaign()
	{
		this.id = 1L;
		this.name = "";
		this.description = "";
		
		this.system = null;
		this.lastPlayed = new Date();
		this.lastPlayed.setTime( 0 );
		this.character = 1L;
	}
	
	public boolean updateValues( String name, String description, RPGSystem system, Long characterID, Long campaignID )
	{
		this.id = campaignID;
		this.name = name;
		this.description = description;
		this.system = system;
		
		this.lastPlayed = new Date();
		this.character = characterID;
		
		return this.saveToDB();
	}
	
	public boolean updateLastPlayed()
	{
		this.lastPlayed = new Date();
		
		return this.saveToDB();
	}
	
	public boolean loadFromDB( Long campaignID )
	{
		// TODO: load from DB using the parameter ID
		// and set the properties
		
		return false;
	}
	
	private boolean saveToDB()
	{
		/*
		 * TODO: save to DB
		 */
		
		return false;
	}

	public String toString()
	{
		return "CampaignID: " + this.id.toString() + "; System: " + this.system + 
				"; Name: " + this.name + "; Description" + this.description;
	}
}
