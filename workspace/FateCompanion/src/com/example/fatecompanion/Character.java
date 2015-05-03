package com.example.fatecompanion;

import java.util.ArrayList;
import java.util.HashMap;


public class Character {
	
	private Long id;
	private String name;
	private String description;
	private ArrayList<Long> campaigns;
	private HashMap<RPGSystem, CharacterSheet> templateSheets;
	private HashMap<Long, CharacterSheet> sheets;
	
	public Character()
	{
		this.id = 1L;
		this.name = "";
		this.description = "";

		this.campaigns = new ArrayList<Long>();
		this.templateSheets = new HashMap<RPGSystem, CharacterSheet>();
		this.sheets = new HashMap<Long, CharacterSheet>();
	}
	
	public boolean updateValues( String name, String description, Long characterID )
	{
		this.id = characterID;
		this.name = name;
		this.description = description;
		
		return this.saveToDB();
	}

	public boolean addTemplateSheet( CharacterSheet sheet, RPGSystem system )
	{
		this.templateSheets.put( system, sheet );
		
		return this.saveToDB();
	}
	
	public boolean addCharacterSheet( CharacterSheet sheet, Long campaignID )
	{
		if ( ! this.campaigns.contains( campaignID ) )
			this.campaigns.add( campaignID );

		this.sheets.put( campaignID, sheet );
		
		return this.saveToDB();
	}
	
	public boolean removeCampaign( Long campaignID )
	{
		this.sheets.remove( campaignID );
		
		return this.saveToDB();
	}
	
	public boolean loadFromDB( Long characterID )
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

	public Long getID() 
	{
		return this.id;
	}

	public static int validateName( String name )
	{
		/*
		 * 0 - all clear
		 * 1 - empty string
		 */
		
		if ( name.trim().isEmpty() )
			return 1;
		
		return 0;
	}

	public String getName() 
	{
		return this.name;
	}
	
	public static int validateDescription( String description )
	{
		/*
		 * 0 - all clear
		 * 1 - empty string
		 */
		
		if ( description.trim().isEmpty() )
			return 1;
		
		return 0;
	}

	public String getDescription() 
	{
		return this.description;
	}

	public ArrayList<Long> getCampaigns()
	{
		return this.campaigns;
	}
	
	public String toString()
	{
		return "CharacterID: " + this.id.toString() + "; Name: " + this.name +
				"; Description: " + this.description;
	}
}
