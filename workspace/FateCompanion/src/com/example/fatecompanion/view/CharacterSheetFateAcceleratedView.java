package com.example.fatecompanion.view;

import com.example.fatecompanion.R;
import com.example.fatecompanion.controller.CampaignController;
import com.example.fatecompanion.controller.CharacterController;
import com.example.fatecompanion.controller.CharacterSheetController;
import com.example.fatecompanion.model.CharacterSheetID;
import com.example.fatecompanion.view.create.CharacterSheetFateAcceleratedCreateView;

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
		
		//1) find the correct CharacterSheetID
		CharacterSheetID currentCharacterSheetID = CharacterSheetController.getInstance(getApplicationContext()).getCharacterSheetIDByID(characterID, campaignID);
		
		//2) check whether there is already a CharacterSheet
		if ( currentCharacterSheetID == null )
		{
			goToCharacterSheetCreate( characterID, campaignID );
		} else
		{
			//3) TODO create all the fields
		}
	}
	
	/*
	 * We use this.finish() at the bottom to remove this instance of 
	 * FateAcceleratedCharacterSheetView from the activity history.
	 * Since it is empty we won't need it in case of a "back" from the 
	 * following creation view.
	 * If the creation was finished, then (by removing the creation view, 
	 * by calling finish() there as well) we automatically jump back over 
	 * both views to the campaign view. 
	 * 
	 */
	
	public void goToCharacterSheetCreate( Long characterID, Long campaignID )
	{
		Intent intent = new Intent(this, CharacterSheetFateAcceleratedCreateView.class);
		intent.putExtra( "characterID", characterID);
		intent.putExtra( "campaignID", campaignID);
		
		this.startActivity( intent );
		this.finish();
	}
}
