package com.example.fatecompanion.view.create;

import com.example.fatecompanion.R;
import com.example.fatecompanion.R.id;
import com.example.fatecompanion.R.layout;
import com.example.fatecompanion.controller.CharacterController;
import com.example.fatecompanion.model.Character;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CharacterCreateView extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_character_create_view);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		/*getMenuInflater().inflate(R.menu.character_create_view, menu);*/
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
	
	public void saveCharacter( View v )
	{
		Long characterID  = System.currentTimeMillis();
		String name = ( (EditText) this.findViewById( R.id.editText1) ).getText().toString();
		String description = ( (EditText) this.findViewById( R.id.editText2) ).getText().toString();
		
		if ( Character.validateName( name ) != 0 )
		{
			( (EditText) this.findViewById( R.id.editText1) ).requestFocus();
			return;
		}
		
		if ( Character.validateDescription( description ) != 0 )
		{
			( (EditText) this.findViewById( R.id.editText2) ).requestFocus();
			return;
		}
		
		if ( ! CharacterController.getInstance(getApplicationContext()).saveCharacter(characterID, name, description) )
		{
			Toast.makeText(this, "Character could not be saved. Unknown error.", Toast.LENGTH_SHORT).show();
		}
		
		this.finish();
	}
}
