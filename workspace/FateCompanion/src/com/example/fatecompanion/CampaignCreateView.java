package com.example.fatecompanion;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class CampaignCreateView extends Activity {

	private Long characterID;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_campaign_create_view);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.campaign_create_view, menu);
		
		this.characterID = this.getIntent().getLongExtra( "characterID", 0L );
		
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
	
	public void saveCampaign( View v )
	{
		String name        = ( (EditText) findViewById( R.id.editText1 ) ).getText().toString();
		String description = ( (EditText) findViewById( R.id.editText2 ) ).getText().toString();
		String system      = (String) ( (Spinner)  findViewById( R.id.spinner1  ) ).getSelectedItem();
		system = system.replace( "[^a-zA-Z]", "").replace(" ", ""); // TODO: Find better mechanism
		
		Long campaignID = System.currentTimeMillis();
		
		if ( Campaign.validateName( name ) != 0 )
		{
			( (EditText) findViewById( R.id.editText1 ) ).requestFocus();
			return;
		}
		
		if ( Campaign.validateDescription( description ) != 0 )
		{
			( (EditText) findViewById( R.id.editText2 ) ).requestFocus();
			return;
		}
		
		if ( ! CampaignController.getInstance().saveCampaign( campaignID, 
															  this.characterID, 
															  name, 
															  description, 
															  RPGSystem.valueOf( system ) ) )
		{
			Toast.makeText(this, "Campaign could not be saved. Unknown error.", Toast.LENGTH_SHORT).show();
		}
		
		Intent intent = new Intent( this, CharacterSheetView.class );
		
		intent.putExtra( "characterID", this.characterID );
		intent.putExtra( "campaignID", campaignID );
			
		this.startActivity( intent );
	}
}
