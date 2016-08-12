package com.example.fatecompanion.widget;


import android.content.Context;

public class CharacterListWidget extends FateListWidget {
	
	private long characterID;
	
	public CharacterListWidget( Context context, Long charID ) {
		super(context);
		
		this.characterID = charID;
	}

	public Long getCharacterID()
	{
		return this.characterID;
	}
}
