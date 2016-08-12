package com.example.fatecompanion.view;

import com.example.fatecompanion.R;
import com.example.fatecompanion.view.create.CharacterSheetCreateView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class CharacterSheetView extends Activity {

	protected long characterID;
	protected long campaignID;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_character_sheet_view);
		
		Intent intent = this.getIntent();
		
		this.characterID = intent.getLongExtra("characterID", 0L);
		this.campaignID = intent.getLongExtra("campaignID", 0L);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		/*getMenuInflater().inflate(R.menu.character_sheet_view, menu);*/
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
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		
		Intent intent = new Intent(this, CharacterSheetCreateView.class);
		intent.putExtra( "characterID", characterID);
		intent.putExtra( "campaignID", campaignID);
		
		this.startActivity( intent );
		this.finish();
	}
	
	
}
