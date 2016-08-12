package com.example.fatecompanion.model;


public class CharacterSheetFateAccelerated extends CharacterSheet {

	//every character starts with refresh 3
	private final int initialRefresh = 3;
	
	//refresh may never go below 1
	private final int minimalRefresh = 1;
	
	private int refresh;
	private int fatePoints;
	
	private String highConcept;
	private String trouble;
	private String aspect1;
	private String aspect2;
	private String aspect3;
	
	private String freeStunt1;
	private String freeStunt2;
	private String freeStunt3;
	
	//these stunts cost refresh
	private String stunt4;
	private String stunt5;
	
	private int approachCareful;
	private int approachClever;
	private int approachFlashy;
	private int approachForceful;
	private int approachQuick;
	private int approachSneaky;
	
	private boolean stress1;
	private boolean stress2;
	private boolean stress3;
	
	private String consequenceMild;
	private String consequenceModerate;
	private String consequenceSevere;
	
	
	
	public CharacterSheetFateAccelerated()
	{
		this.refresh = initialRefresh;
		this.fatePoints = initialRefresh;
		
		this.highConcept = "";
		this.trouble = "";
		this.aspect1 = "";
		this.aspect2 = "";
		this.aspect3 = "";
		
		this.freeStunt1 = "";
		this.freeStunt2 = "";
		this.freeStunt3 = "";
		this.stunt4 = "";
		this.stunt5 = "";
		
		this.approachCareful = 0;
		this.approachClever = 0;
		this.approachFlashy = 0;
		this.approachForceful = 0;
		this.approachQuick = 0;
		this.approachSneaky = 0;
		
		this.stress1 = false;
		this.stress2 = false;
		this.stress3 = false;
		
		this.consequenceMild = "";
		this.consequenceModerate = "";
		this.consequenceSevere = "";
	}
	
	public CharacterSheetFateAccelerated(String name, String description, RPGSystem system)
	{
		this();
		setName(name);
		setDescription(description);
		setSystem(system);
	}
	
	public boolean updateValues(
			int refresh, int fatePoints, String highConcept, String trouble,
			String aspect1, String aspect2, String aspect3, String freeStunt1,
			String freeStunt2, String freeStunt3, String stunt4, String stunt5,
			int approachCareful, int approachClever, int approachFlashy,
			int approachForceful, int approachQuick, int approachSneaky,
			boolean stress1, boolean stress2, boolean stress3, 
			String consequenceMild, String consequenceModerate, String consequenceSevere,
			String name, String description, RPGSystem system, CharacterSheetID csID)
	{
		this.refresh = refresh;
		this.fatePoints = fatePoints;
		
		this.highConcept = highConcept;
		this.trouble = trouble;
		this.aspect1 = aspect1;
		this.aspect2 = aspect2;
		this.aspect3 = aspect3;
		
		this.freeStunt1 = freeStunt1;
		this.freeStunt2 = freeStunt2;
		this.freeStunt3 = freeStunt3;
		this.stunt4 = stunt4;
		this.stunt5 = stunt5;
		
		this.approachCareful = approachCareful;
		this.approachClever = approachClever;
		this.approachFlashy = approachFlashy;
		this.approachForceful = approachForceful;
		this.approachQuick = approachQuick;
		this.approachSneaky = approachSneaky;
		
		this.stress1 = stress1;
		this.stress2 = stress2;
		this.stress3 = stress3;
		
		this.consequenceMild = consequenceMild;
		this.consequenceModerate = consequenceModerate;
		this.consequenceSevere = consequenceSevere;
		
		setName(name);
		setDescription(description);
		setSystem(system);
		
		return this.saveToDB();
	}
	
	
	public boolean loadFromDB()
	{
		//TODO: load from DB
		
		return false;
	}
	
	public boolean saveToDB()
	{
		//TODO: save to DB
		
		return false;
	}

	public int getInitialRefresh() {
		return initialRefresh;
	}

	public int getMinimalRefresh() {
		return minimalRefresh;
	}

	public int getRefresh() {
		return refresh;
	}

	public int getFatePoints() {
		return fatePoints;
	}

	public String getHighConcept() {
		return highConcept;
	}

	public String getTrouble() {
		return trouble;
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

	public String getFreeStunt1() {
		return freeStunt1;
	}

	public String getFreeStunt2() {
		return freeStunt2;
	}

	public String getFreeStunt3() {
		return freeStunt3;
	}

	public String getStunt4() {
		return stunt4;
	}

	public String getStunt5() {
		return stunt5;
	}

	public int getApproachCareful() {
		return approachCareful;
	}

	public int getApproachClever() {
		return approachClever;
	}

	public int getApproachFlashy() {
		return approachFlashy;
	}

	public int getApproachForceful() {
		return approachForceful;
	}

	public int getApproachQuick() {
		return approachQuick;
	}

	public int getApproachSneaky() {
		return approachSneaky;
	}

	public boolean isStress1() {
		return stress1;
	}

	public boolean isStress2() {
		return stress2;
	}

	public boolean isStress3() {
		return stress3;
	}

	public String getConsequenceMild() {
		return consequenceMild;
	}

	public String getConsequenceModerate() {
		return consequenceModerate;
	}

	public String getConsequenceSevere() {
		return consequenceSevere;
	}
}
