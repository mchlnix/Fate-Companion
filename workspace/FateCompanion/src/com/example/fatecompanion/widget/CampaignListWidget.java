package com.example.fatecompanion.widget;


import android.content.Context;

public class CampaignListWidget extends FateListWidget {
	
	private long campaignID;
	
	public CampaignListWidget(Context context, long campID ) {
		super(context);
		
		this.campaignID = campID;
	}

	public Long getCampaignID()
	{
		return this.campaignID;
	}
}
