package com.example.fatecompanion;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CharacterListWidget extends LinearLayout {
	
	public CharacterListWidget(Context context, Long characterID)
	{
		this(context, null, characterID);
	}

	public CharacterListWidget(Context context, AttributeSet attrs, Long characterID) 
	{
		super(context, attrs);
		
		this.setOrientation( LinearLayout.HORIZONTAL );
		
		ImageView colorSpace = new ImageView(context);
		colorSpace.setMinimumWidth(50);
		colorSpace.setMinimumHeight(100);
		colorSpace.setPadding(10, 0, 10, 0);
		
		colorSpace.setAdjustViewBounds( true );
		colorSpace.setScaleType( ScaleType.CENTER_CROP);
		
		colorSpace.setBackgroundColor( Color.rgb( 255, 255, 0) );
		
		this.addView( colorSpace );
		
		LinearLayout charInfo = new LinearLayout( context );
		
		charInfo.setOrientation( LinearLayout.VERTICAL );
		
		TextView charName = new TextView( context );
		charName.setTextAppearance(context, android.R.style.TextAppearance_Large);
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
}
