package com.example.fatecompanion;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

public class CharacterSheetFateAcceleratedView extends Activity {

	private long characterID;
	private long campaignID;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_character_sheet_fate_accelerated_view);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		/*getMenuInflater().inflate(R.menu.character_sheet_fate_accelerated_view,
				menu);*/
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onStart()
	{
		super.onStart();
		
		( (LinearLayout) this.findViewById( R.id.LinearLayout1 ) ).removeAllViews();
		
		Intent intent = this.getIntent();
		
		this.characterID = intent.getLongExtra("characterID", 0L);
		this.campaignID = intent.getLongExtra("campaignID", 0L);
		
		this.setTitle( CharacterController.getInstance(getApplicationContext()).getCharacterByID( this.characterID ).getName()
				+ " in " + CampaignController.getInstance(getApplicationContext()).getCampaignByID(this.campaignID).getName() );
		
		//1) find the correct CharacterSheetID
		
	}
}
