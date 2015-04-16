package com.example.fatecompanion;

public abstract class CharacterSheet {
	
	private String name;
	private String description;
	private RPGSystem system;
	
	public String getName()
	{
		return this.name;
	}
	
	public String getDescription()
	{
		return this.description;
	}
	
	public void setName( String name )
	{
		this.name = name;
	}
	
	public void setDescription( String description )
	{
		this.description = description;
	}

	public RPGSystem getSystem() 
	{
		return this.system;
	}

	public void setSystem(RPGSystem system) 
	{
		this.system = system;
	}
}
