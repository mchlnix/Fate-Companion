package com.example.fatecompanion;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CharacterListView extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_character_list_view);
		
		ArrayList<Long> characterIDs = CharacterController.getInstance().getAllCharacterIDs();
		
		for ( Long ID : characterIDs )
		{
			( (LinearLayout) this.findViewById( R.id.LinearLayout1 ) )
				.addView( new CharacterListWidget( this, ID ) );
		}
		
		TextView derp = new TextView(this);
		derp.setText( "derp2" );
		
		// Test 0L is debug ID
		( (LinearLayout) this.findViewById( R.id.LinearLayout1 ) )
		.addView( new CharacterListWidget(this, 0L) );
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.character_list_view, menu);
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
}
