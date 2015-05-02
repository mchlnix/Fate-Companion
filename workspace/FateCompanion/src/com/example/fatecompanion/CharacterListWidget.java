package com.example.fatecompanion;

import java.io.UnsupportedEncodingException;
import java.util.Random;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class CharacterListWidget extends LinearLayout {
	
	private Long characterID;
	
	public CharacterListWidget(Context context, Long characterID)
	{
		this(context, null, characterID);
	}

	public CharacterListWidget(Context context, AttributeSet attrs, Long characterID) 
	{
		super(context, attrs);
		
		this.characterID = characterID;
		
		this.setOrientation( LinearLayout.HORIZONTAL );

		/* define the graphic */
		
		ImageView colorSpace = new ImageView(context);
		
		// make it as high as the other elements in the LinearLayout
		
		colorSpace.setLayoutParams( new ViewGroup.LayoutParams( 
				ViewGroup.LayoutParams.WRAP_CONTENT, 
				ViewGroup.LayoutParams.MATCH_PARENT ) );
		
		// set the width manually, otherwise it would be 1px or as wide as the screen
		
		colorSpace.setMinimumWidth( 60 ); // 60 - padding = 50
		
		colorSpace.setPadding(0, 0, 10, 0);
		
		// what should be shown? color is changed further down (setColorFilter)
		
		colorSpace.setImageResource( R.drawable.widget_shape );
		
		LinearLayout charInfo = new LinearLayout( context );
		
		charInfo.setOrientation( LinearLayout.VERTICAL );
		
		TextView charName = new TextView( context );
		charName.setTextAppearance(context, android.R.style.TextAppearance_Large);
		charName.setTypeface( Typeface.SERIF, Typeface.NORMAL);
		TextView charDesc = new TextView( context );
		TextView charLast = new TextView( context );
		
		Character character;
		
		if ( characterID != 0L )
		{
			character = CharacterController.getInstance().getCharacterByID( characterID );
		}
		else //delete if real data is available
		{
			character = new Character();
			character.updateValues( "DebugName", "DebugDescription", 0L );
		}
		
		/* generate color depending on the character name */
		
		// default color if there is a problem with the name
		
		int color = Color.rgb( 120, 120, 120 );
		
		// seed a RNG with the character name
		
		try {
			byte[] str = character.getName().getBytes( "US-ASCII" );
			
			String tempName = new String( str );
			
			Random gen = new Random( Long.parseLong( tempName, 36 ) );
			
			color = Color.rgb( gen.nextInt( 256 ), gen.nextInt( 256 ), gen.nextInt( 256 ));
			
		} 
		catch (NumberFormatException e) {}
		catch (UnsupportedEncodingException e) {}
		
		// set the color of the widget
		
		colorSpace.setColorFilter( color );
		
		charName.setText( character.getName() );
		charDesc.setText( character.getDescription() );
		charLast.setText( "Derp" ); // TODO: get last played date
		
		charInfo.addView( charName );
		charInfo.addView( charDesc );
		charInfo.addView( charLast );
		
		// add to LinearLayout
		this.addView( colorSpace );
		this.addView( charInfo );
	}
	
	public Long getCharacterID()
	{
		return this.characterID;
	}
}
