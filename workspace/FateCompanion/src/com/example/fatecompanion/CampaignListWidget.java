package com.example.fatecompanion;

import android.content.Context;
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
		
		this.campaignID = campaignID;
		
		this.setOrientation( LinearLayout.HORIZONTAL );
		
		ImageView colorSpace = new ImageView(context);
		
		colorSpace.setLayoutParams( new ViewGroup.LayoutParams( 
				ViewGroup.LayoutParams.WRAP_CONTENT, 
				ViewGroup.LayoutParams.MATCH_PARENT ) );
		
		colorSpace.setMinimumWidth( 60 ); // 60 - padding = 50
		
		colorSpace.setPadding(0, 0, 10, 0);
		
		colorSpace.setImageResource( R.drawable.widget_shape );
		
		this.addView( colorSpace );
		
		LinearLayout charInfo = new LinearLayout( context );
		
		charInfo.setOrientation( LinearLayout.VERTICAL );
		
		TextView charName = new TextView( context );
		charName.setTextAppearance(context, android.R.style.TextAppearance_Large);
		charName.setTypeface( Typeface.SERIF, Typeface.NORMAL);
		TextView charDesc = new TextView( context );
		TextView charLast = new TextView( context );
		
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
		
		charName.setText( campaign.getName() );
		charDesc.setText( campaign.getDescription() );
		charLast.setText( campaign.getLastPlayed().toString() ); //TODO: format correctly
		
		charInfo.addView( charName );
		charInfo.addView( charDesc );
		charInfo.addView( charLast );
		
		this.addView( charInfo );
	}
	
	public Long getCampaignID()
	{
		return this.campaignID;
	}
}
