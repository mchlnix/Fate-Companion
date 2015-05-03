package com.example.fatecompanion;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CharacterListWidget extends LinearLayout {
	
	private Long characterID;
	
	public CharacterListWidget(Context context, Long characterID)
	{
		this(context, null, characterID);
	}

	public CharacterListWidget(Context context, AttributeSet attrs, Long characterID) 
	{
		super(context, attrs);
		
		this.setOrientation( LinearLayout.HORIZONTAL );
		
		this.characterID = characterID;
		
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
		
		/* set up character information list */
		
		LinearLayout charInfo = new LinearLayout( context );
		
		charInfo.setOrientation( LinearLayout.VERTICAL );
		charInfo.setPadding( 0, 0, 0, 10 );
		
		/* generate color depending on the character name */
		
		// default color if there is a problem with the name
		
		int color = Color.rgb( 120, 120, 120 );
		
		// seed a RNG with the characterID
		
		Random gen = new Random( this.characterID );
		
		color = Color.rgb( gen.nextInt( 256 ), gen.nextInt( 256 ), gen.nextInt( 256 ));
		
		// set the color of the widget
		
		colorSpace.setColorFilter( color );
		
		/* set up the textviews */
		
		// character name
		
		TextView charName = new TextView( context );
		charName.setTextAppearance(context, android.R.style.TextAppearance_Large);
		charName.setTypeface( Typeface.SERIF, Typeface.NORMAL);
		charName.setText( character.getName() );
		
		// character description
		
		TextView charDesc = new TextView( context );
		charDesc.setText( character.getDescription() );
		
		// character last played
		
		TextView charLast = new TextView( context );
		charLast.setTextColor( color );
		
		SimpleDateFormat df = new SimpleDateFormat( "E dd.MM.yyyy hh:mm:ss", Locale.US );
		
		Date lastPlayed = CharacterController.getInstance().getLastPlayed( characterID );
		
		if ( lastPlayed.getTime() != 0L )
			charLast.setText( df.format( lastPlayed ) );
		else
			charLast.setText( "Not in play" );
		
		// put it all together
		
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
