package com.example.fatecompanion;

import java.util.Date;

public class Campaign {

	private Long id;
	private String name;
	private String description;
	private RPGSystem system;
	
	private Date lastPlayed;
	private Long character;
	
	public Campaign( String name, String description, RPGSystem system, Long characterID )
	{
		this.id = System.currentTimeMillis();
		this.name = name;
		this.description = description;
		this.system = system;
		
		this.lastPlayed = new Date();
		this.character = characterID;
	}
	
	private void updateLastPlayed()
	{
		this.lastPlayed = new Date();
	}
	
	private boolean saveToDB()
	{
		return false;
	}
	
}
