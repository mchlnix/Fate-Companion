package com.example.fatecompanion;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CampaignListWidget extends LinearLayout {
	
	public CampaignListWidget(Context context, Long campaignID)
	{
		this(context, null, campaignID);
	}

	public CampaignListWidget(Context context, AttributeSet attrs, Long campaignID) 
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
}
