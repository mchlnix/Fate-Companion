package com.example.fatecompanion.model;

import java.util.ArrayList;


public class CharacterSheetFateCore extends CharacterSheet {
	
	private final int REFRESH_UPPER_LIMIT = 5; // TODO: Figure out if right
	
	private Long campaignID;
	private Long characterID;
	
	private RPGSystem system;
	
	private CharacterSheetID characterSheetID;
	
	private String name;
	private String description;
	private int refresh;
	
	private String highConcept;
	private String trouble;
	private String aspect1;
	private String aspect2;
	private String aspect3;
	
	private ArrayList<String> extras; // if there is a set limit of extras, turn into a String[]
	private ArrayList<String> stunts; // if there is a set limit of stunts, turn into a String[]
	
	private int stressPhysical;
	private int stressMental;
	
	private String consequence2;
	private String consequence4;
	private String consequence6;
	
	private String[] skillsSuperb;
	private String[] skillsGreat;
	private String[] skillsGood;
	private String[] skillsFair;
	private String[] skillsAverage;
	
	public CharacterSheetFateCore()
	{
		this.system = RPGSystem.FateCore;
		
		this.name = "";
		this.description = "";
		this.refresh = 0;
		
		this.highConcept = "";
		this.trouble = "";
		this.aspect1 = "";
		this.aspect2 = "";
		this.aspect3 = "";
		
		this.extras = new ArrayList<String>();
		this.stunts = new ArrayList<String>();
		
		this.stressPhysical = 0;
		this.stressMental = 0;
		
		this.consequence2 = "";
		this.consequence4 = "";
		this.consequence6 = "";
		
		this.skillsSuperb = new String[5];
		this.skillsGreat = new String[5];
		this.skillsGood = new String[5];
		this.skillsFair = new String[5];
		this.skillsAverage = new String[5];
	}

	public boolean updateValues( Long campaignID, Long characterID, 
			String name, String description, int refresh, 
			String highConcept, String trouble, String aspect1, 
			String aspect2, String aspect3, ArrayList<String> extras,
			ArrayList<String> stunts, int stressPhysical, int stressMental,
			String consequence2, String consequence4, String consequence6,
			String[] skillsSuperb, String[] skillsGreat, String[] skillsGood,
			String[] skillsFair, String[] skillsAverage
			)
	{
		this.campaignID = campaignID;
		this.characterID = characterID;
		
		this.characterSheetID = new CharacterSheetID( campaignID, characterID );
		
		this.name = name;
		this.description = description;
		this.refresh = 0;
		
		this.highConcept = highConcept;
		this.trouble = trouble;
		this.aspect1 = aspect1;
		this.aspect2 = aspect2;
		this.aspect3 = aspect3;
		
		this.extras = extras;
		this.stunts = stunts;
		
		this.stressPhysical = stressPhysical;
		this.stressMental = stressMental;
		
		this.consequence2 = consequence2;
		this.consequence4 = consequence4;
		this.consequence6 = consequence6;
		
		this.skillsSuperb = skillsSuperb;
		this.skillsGreat = skillsGreat;
		this.skillsGood = skillsGood;
		this.skillsFair = skillsFair;
		this.skillsAverage = skillsAverage;
		
		return this.saveToDB();
	}
	
	public boolean loadFromDB( Long campaignID, Long characterID )
	{
		CharacterSheetID characterSheetID = new CharacterSheetID(campaignID, characterID);
		
		/*
		 * TODO: load from DB using the ID
		 */
		
		return false;
	}
	
	private boolean saveToDB()
	{
		/*
		 * TODO: save to database
		 */
		return false;
	}
	
	public int validateName( String name )
	{
		/*
		 *  0 - all fine
		 *  1 - name is empty, except for maybe whitespace 
		 *  
		 *  TODO: Check for word chars with regex
		 */
		
		if ( name.trim().isEmpty() )
			return 1;
		
		return 0;
	}

	public String getName() {
		return name;
	}
	
	public int validateDescription( String description )
	{
		/*
		 * 0 - all fine
		 * 1 - description is empty except for maybe whitespace
		 * 
		 * TODO: Check for word chars with regex
		 */
		
		if ( description.trim().isEmpty() )
			return 1;
		
		return 0;
	}

	public String getDescription() {
		return description;
	}
	
	public int validateRefresh( String refresh )
	{
		/*
		 * 0 - all fine
		 * 1 - refresh is not a number
		 * 2 - refresh is too high
		 * 3 - refresh is negative
		 * 
		 * TODO: All cases covered?
		 */
		
		int _refresh;
		
		try
		{
			_refresh = Integer.parseInt( refresh );
		}
		catch( NumberFormatException e )
		{
			return 1;
		}
		
		if ( _refresh > REFRESH_UPPER_LIMIT )
			return 2;
		
		if ( _refresh < 0 )
			return 3;
		
		return 0;
	}

	public int getRefresh() {
		return refresh;
	}
	
	public int validateHighConcept( String highConcept )
	{
		/*
		 * 0 - all fine
		 * 1 - high concept is empty except for maybe whitespace
		 * 
		 * TODO: Check for word chars with regex
		 */
		
		if ( highConcept.trim().isEmpty() )
			return 1;
		
		return 0;
	}

	public String getHighConcept() {
		return highConcept;
	}
	
	public int validateTrouble( String trouble )
	{
		/*
		 * 0 - all fine
		 * 1 - trouble is empty except for maybe whitespace
		 * 
		 * TODO: Check for word chars with regex
		 */
		
		if ( trouble.trim().isEmpty() )
			return 1;
		
		return 0;
	}

	public String getTrouble() {
		return trouble;
	}
	
	public int validateAspects( String[] aspects )
	{
		// TODO
		
		return 0;
	}

	public String getAspect1() {
		return aspect1;
	}
	
	public String getAspect2() {
		return aspect2;
	}
	
	public String getAspect3() {
		return aspect3;
	}

	public int validateExtras( ArrayList<String> extras )
	{
		/*
		 * 0 - all fine
		 * 1 - at least on of the extras is empty (can't happen?)
		 * 
		 * TODO: check for several other factors
		 */
		
		for ( String extra : extras )
			if ( extra.trim().isEmpty() )
				return 1;
				
		return 0;
	}
	public ArrayList<String> getExtras() {
		return extras;
	}

	public int validateStunts( ArrayList<String> stunts )
	{
		/*
		 * 0 - all fine
		 * 1 - at least on of the stunts is empty (can't happen?)
		 * 
		 * TODO: check for several other factors
		 */
		
		for ( String stunt : stunts )
			if ( stunt.trim().isEmpty() )
				return 1;
		
		return 0;
	}
	
	public ArrayList<String> getStunts() {
		return stunts;
	}

	public int validateStressPhysical( String stressPhysical )
	{
		// can be set automatically?
		
		return 0;
	}
	
	public int getStressPhysical() {
		return stressPhysical;
	}
	
	public int validateStressMental( String stressMental )
	{
		// can be set automatically?
		
		return 0;
	}

	public int getStressMental() {
		return stressMental;
	}
	
	public int validateConsequence2( String consequence2 )
	{
		/*
		 * 0 - all is fine
		 * 
		 * TODO: Anything to consider?
		 */
		
		return 0;
	}

	public String getConsequence2() {
		return consequence2;
	}
	
	public int validateConsequence4( String consequence4 )
	{
		/*
		 * 0 - all is fine
		 * 
		 * TODO: Anything to consider?
		 */
		
		return 0;
	}

	public String getConsequence4() {
		return consequence4;
	}
	
	public int validateConsequence6( String consequence6 )
	{
		/*
		 * 0 - all is fine
		 * 
		 * TODO: Anything to consider?
		 */
		
		return 0;
	}

	public String getConsequence6() {
		return consequence6;
	}
	
	public int validateSkillsSuperb( String[] skillsSuperb )
	{
		/*
		 * 0 - all fine
		 * 1 - too many skills
		 * 2 - not enough skills
		 * 
		 * TODO: Anything else to consider?
		 */
		
		return 0;
	}

	public String[] getSkillsSuperb() {
		return skillsSuperb;
	}
	
	public int validateSkillsGreat( String[] skillsGreat )
	{
		/*
		 * 0 - all fine
		 * 1 - too many skills
		 * 2 - not enough skills
		 * 
		 * TODO: Anything else to consider?
		 */
		
		return 0;
	}

	public String[] getSkillsGreat() {
		return skillsGreat;
	}
	
	public int validateSkillsGood( String[] skillsGood )
	{
		/*
		 * 0 - all fine
		 * 1 - too many skills
		 * 2 - not enough skills
		 * 
		 * TODO: Anything else to consider?
		 */
		
		return 0;
	}

	public String[] getSkillsGood() {
		return skillsGood;
	}
	
	public int validateSkillsFair( String[] skillsFair )
	{
		/*
		 * 0 - all fine
		 * 1 - too many skills
		 * 2 - not enough skills
		 * 
		 * TODO: Anything else to consider?
		 */
		
		return 0;
	}

	public String[] getSkillsFair() {
		return skillsFair;
	}
	
	public int validateSkillsAverage( String[] skillsAverage )
	{
		/*
		 * 0 - all fine
		 * 1 - too many skills
		 * 2 - not enough skills
		 * 
		 * TODO: Anything else to consider?
		 */
		
		return 0;
	}

	public String[] getSkillsAverage() {
		return skillsAverage;
	}
}
