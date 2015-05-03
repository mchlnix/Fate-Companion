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

public class CampaignListWidget extends LinearLayout {
	
	private Long campaignID;
	
	public CampaignListWidget(Context context, Long campaignID)
	{
		this(context, null, campaignID);
	}

	public CampaignListWidget(Context context, AttributeSet attrs, Long campaignID) 
	{
		super(context, attrs);
		
		this.setOrientation( LinearLayout.HORIZONTAL );
		
		this.campaignID = campaignID;
		
		Campaign campaign;
		
		if ( campaignID != 0L )
		{
			campaign = CampaignController.getInstance().getCampaignByID( campaignID );
		}
		else //delete if real data is available
		{
			campaign = new Campaign();
			campaign.updateValues( "DebugName", "DebugDescription", RPGSystem.FateCore, 0L, 0L );
		}
		
		ImageView colorSpace = new ImageView(context);
		
		colorSpace.setLayoutParams( new ViewGroup.LayoutParams( 
				ViewGroup.LayoutParams.WRAP_CONTENT, 
				ViewGroup.LayoutParams.MATCH_PARENT ) );
		
		// set the width manually, otherwise it would be 1px or as wide as the screen
		
		colorSpace.setMinimumWidth( 60 ); // 60 - padding = 50
		
		colorSpace.setPadding(0, 0, 10, 0);
		
		// what should be shown? color is changed further down (setColorFilter)
		
		colorSpace.setImageResource( R.drawable.widget_shape );
		
		/* set up campaign information list */
		
		LinearLayout charInfo = new LinearLayout( context );
		
		charInfo.setOrientation( LinearLayout.VERTICAL );
		charInfo.setPadding( 0, 0, 0, 10 );
		
		/* generate color depending on the character name */
		
		// default color if there is a problem with the name
		
		int color = Color.rgb( 120, 120, 120 );
		
		// seed a RNG with the character name
		
		String tempName = campaign.getName().replaceAll( "[^a-zA-Z]","" );
		
		Random gen = new Random( Long.parseLong( tempName, 36 ) );
			
		color = Color.rgb( gen.nextInt( 256 ), gen.nextInt( 256 ), gen.nextInt( 256 ));
		
		// set the color of the widget
		
		colorSpace.setColorFilter( color );
		
		/* set up the textviews */
		
		// campaign name
		
		TextView charName = new TextView( context );
		charName.setTextAppearance(context, android.R.style.TextAppearance_Large);
		charName.setTypeface( Typeface.SERIF, Typeface.NORMAL );
		charName.setText( campaign.getName() );
		
		// campaign description
		
		TextView charDesc = new TextView( context );
		charDesc.setText( campaign.getDescription() );
		
		// campaign system
		
		TextView charSys = new TextView( context );
		charSys.setText( campaign.getSystem().toString() );
		
		// campaign last played
		
		TextView charLast = new TextView( context );
		charLast.setTextColor( color );
		
		SimpleDateFormat df = new SimpleDateFormat( "E dd.MM.yyyy hh:mm:ss", Locale.US );
		
		Date lastPlayed = campaign.getLastPlayed();
		
		if ( lastPlayed.getTime() != 0L )
			charLast.setText( df.format( lastPlayed ) );
		else
			charLast.setText( "Not played yet" );
		
		charInfo.addView( charName );
		charInfo.addView( charDesc );
		charInfo.addView( charSys  );
		charInfo.addView( charLast );
		
		this.addView( colorSpace );
		this.addView( charInfo );
	}
	
	public Long getCampaignID()
	{
		return this.campaignID;
	}
}
