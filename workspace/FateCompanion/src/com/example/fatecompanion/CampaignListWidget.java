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
	
	private static final int PADDING_NAME = 0;
	private static final int PADDING_INFO = 10;
	
	private Long campaignID;
	
	public CampaignListWidget(Context context, Long campaignID)
	{
		this(context, null, campaignID);
	}

	public CampaignListWidget(Context context, AttributeSet attrs, Long campaignID) 
	{
		super(context, attrs);
		
		this.setOrientation( LinearLayout.HORIZONTAL );
		//this.setPadding( 0, 0, 0, 10 );
		
		this.campaignID = campaignID;
		
		Campaign campaign;
		
		campaign = CampaignController.getInstance().getCampaignByID( campaignID );
		
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
		
		LinearLayout campInfo = new LinearLayout( context );
		
		campInfo.setOrientation( LinearLayout.VERTICAL );
		campInfo.setPadding( 0, 10, 0, 20 ); // 10 more at the bottom for symmetry
		
		/* generate color depending on the campaign name */
		
		// default color if there is a problem with the name
		
		int color = Color.rgb( 120, 120, 120 );
		
		// seed a RNG with the campaignID
		
		Random gen = new Random( this.campaignID );
			
		color = Color.rgb( gen.nextInt( 256 ), gen.nextInt( 256 ), gen.nextInt( 256 ));
		
		// set the color of the widget
		
		colorSpace.setColorFilter( color );
		
		/* set up the textviews */
		
		// campaign name
		
		TextView campName = new TextView( context );
		campName.setPadding( PADDING_NAME, 0, 0, 0 );
		campName.setTextAppearance(context, android.R.style.TextAppearance_Large);
		campName.setTypeface( Typeface.SERIF, Typeface.NORMAL );
		campName.setText( campaign.getName() );
		
		// campaign description
		
		TextView campDesc = new TextView( context );
		campDesc.setPadding( PADDING_INFO, 0, 0, 0 );
		campDesc.setMaxLines( 3 );
		campDesc.setText( campaign.getDescription() );
		
		// campaign system
		
		TextView campSys = new TextView( context );
		campSys.setPadding( PADDING_INFO, 0, 0, 0 );
		campSys.setText( campaign.getSystem().toString() );
		
		// campaign last played
		
		TextView campLast = new TextView( context );
		//campLast.setTextColor( color );
		campLast.setPadding( PADDING_INFO, 0, 0, 0 );
		
		SimpleDateFormat df = new SimpleDateFormat( "E dd.MM.yyyy hh:mm:ss", Locale.US );
		
		Date lastPlayed = campaign.getLastPlayed();
		
		if ( lastPlayed.getTime() != 0L )
			campLast.setText( df.format( lastPlayed ) );
		else
			campLast.setText( "Not played yet" );
		
		campInfo.addView( campName );
		campInfo.addView( campDesc );
		campInfo.addView( campSys  );
		campInfo.addView( campLast );
		
		this.addView( colorSpace );
		this.addView( campInfo );
		
		int bgcolor = color - (224<<24);
		
		this.setBackgroundColor( bgcolor );
	}
	
	public Long getCampaignID()
	{
		return this.campaignID;
	}
}
