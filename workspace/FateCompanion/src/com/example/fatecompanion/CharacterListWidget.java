package com.example.fatecompanion;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
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
		
		// what should be shown?
		
		colorSpace.setImageResource( R.drawable.widget_shape );
		
		// add to LinearLayout
		
		this.addView( colorSpace );
		
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
		
		charName.setText( character.getName() );
		charDesc.setText( character.getDescription() );
		charLast.setText( "Derp" );
		
		charInfo.addView( charName );
		charInfo.addView( charDesc );
		charInfo.addView( charLast );
		
		this.addView( charInfo );
	}
	
	public Long getCharacterID()
	{
		return this.characterID;
	}
}
