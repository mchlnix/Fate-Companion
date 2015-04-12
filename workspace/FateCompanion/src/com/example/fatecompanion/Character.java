package com.example.fatecompanion;

import java.util.HashMap;
import java.util.LinkedList;


public class Character {
	
	private Long id;
	private String name;
	private String description;
	private LinkedList<Long> campaigns;
	private HashMap<RPGSystem, CharacterSheet> templateSheets;
	private HashMap<Long, CharacterSheet> sheets;
	
	public Character( String name, String description )
	{
		this.id = System.currentTimeMillis();
		this.name = name;
		this.description = description;

		this.campaigns = new LinkedList<Long>();
		this.templateSheets = new HashMap<RPGSystem, CharacterSheet>();
		this.sheets = new HashMap<Long, CharacterSheet>();
	}
	
	public void addTemplateSheet( CharacterSheet sheet, RPGSystem system )
	{
		this.templateSheets.put( system, sheet );
	}
	
	public void addCharacterSheet( CharacterSheet sheet, Long campaignID )
	{
		this.sheets.put( campaignID, sheet );
	}
	
	public void removeCampaign( Long campaignID )
	{
		this.sheets.remove( campaignID );
	}
	
	private boolean saveToDB()
	{
		return false;
	}
}
