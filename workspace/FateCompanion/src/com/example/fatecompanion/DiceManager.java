package com.example.fatecompanion;

import java.util.Random;

public class DiceManager {
	private static DiceResult[] possibilities = DiceResult.values();
	private static int amountOfPossibilities = DiceResult.values().length;
	private static Random random = new Random();
	
	private DiceManager()
	{
		
	}
	
	public static DiceResult[] getDiceRoll( int amount )
	{
		DiceResult[] result = new DiceResult[amount];
		
		for ( int i = 0; i < amount; i++)
		{
			result[i] = DiceManager.possibilities[ random.nextInt( DiceManager.amountOfPossibilities ) ];
		}
		
		return result;
	}
	
}
