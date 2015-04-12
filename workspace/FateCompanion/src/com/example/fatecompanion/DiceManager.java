package com.example.fatecompanion;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class DiceManager {
	private static DiceResult[] possibilities = DiceResult.values();
	private static int amountOfPossibilities = DiceResult.values().length;
	private static Random random = new Random();
	
	public static List<DiceResult> getDiceRoll( int amount )
	{
		LinkedList<DiceResult> result = new LinkedList<DiceResult>();
		
		for ( int i = 0; i < amount; i++)
		{
			result.add( DiceManager.possibilities[ random.nextInt( DiceManager.amountOfPossibilities ) ] );
		}
		
		return result;
	}
	
}
