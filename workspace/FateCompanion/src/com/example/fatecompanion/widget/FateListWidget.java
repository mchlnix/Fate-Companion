package com.example.fatecompanion.widget;

import java.util.Random;

import com.example.fatecompanion.R;
import com.example.fatecompanion.R.drawable;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FateListWidget extends LinearLayout {
	
	private static final int PADDING_TITLE_RIGHT  = 0;
	private static final int PADDING_TITLE_TOP    = 0;
	private static final int PADDING_TITLE_LEFT   = 0;
	private static final int PADDING_TITLE_BOTTOM = 0;
	
	private static final int PADDING_TEXT_LIST_LEFT   =  0;
	private static final int PADDING_TEXT_LIST_TOP    = 10;
	private static final int PADDING_TEXT_LIST_RIGHT  =  0;
	private static final int PADDING_TEXT_LIST_BOTTOM = 20;
	
	private static final int PADDING_INFO_ENTRY_LEFT   = 10;
	private static final int PADDING_INFO_ENTRY_TOP    =  0;
	private static final int PADDING_INFO_ENTRY_RIGHT  =  0;
	private static final int PADDING_INFO_ENTRY_BOTTOM =  0;
	
	private static final int PADDING_IMAGE_LEFT   =  0;
	private static final int PADDING_IMAGE_TOP    =  0;
	private static final int PADDING_IMAGE_RIGHT  = 10;
	private static final int PADDING_IMAGE_BOTTOM =  0;
	
	private static final int DEFAULT_COLOR_RED   = 120;
	private static final int DEFAULT_COLOR_GREEN = 120;
	private static final int DEFAULT_COLOR_BLUE  = 120;
	
	private static final int COLOR_OFFSET_ALPHA = 24; //Bits
	private static final int COLOR_VALUE_ALPHA = 32; //Between 0 - 256, 0 meaning transparent
	
	private static final int MINIMUM_WIDTH_IMAGE = 60;
	
	private static final String DEFAULT_TITLE = "";
	
	private TextView title;
	private LinearLayout infoBox;
	private ImageView colorSpace;
	
	public FateListWidget(Context context) {
		super(context);
		
		this.setOrientation( LinearLayout.HORIZONTAL );
		
		this.colorSpace = new ImageView( context );
		this.colorSpace.setLayoutParams( new ViewGroup.LayoutParams( 
				ViewGroup.LayoutParams.WRAP_CONTENT, 
				ViewGroup.LayoutParams.MATCH_PARENT ) );
		// set the width manually, otherwise it would be 1px or as wide as the screen
		this.colorSpace.setMinimumWidth( MINIMUM_WIDTH_IMAGE ); // 60 - padding = 50
		this.colorSpace.setPadding( PADDING_IMAGE_LEFT, 
									PADDING_IMAGE_TOP, 
									PADDING_IMAGE_RIGHT, 
									PADDING_IMAGE_BOTTOM );
		// what should be shown? color is changed further down (setColorFilter)
		this.colorSpace.setImageResource( R.drawable.widget_shape );
		
		this.title = new TextView( context );
		this.title.setPadding( PADDING_TITLE_LEFT, 
							   PADDING_TITLE_TOP, 
							   PADDING_TITLE_RIGHT, 
							   PADDING_TITLE_BOTTOM );
		this.title.setTextAppearance(context, android.R.style.TextAppearance_Large);
		this.title.setTypeface( Typeface.SERIF, Typeface.NORMAL );
		this.title.setText( DEFAULT_TITLE );
		
		this.infoBox = new LinearLayout( context );
		this.infoBox.setOrientation( LinearLayout.VERTICAL );
		
		LinearLayout textList = new LinearLayout( context );
		textList.setOrientation( LinearLayout.VERTICAL );
		textList.setPadding( PADDING_TEXT_LIST_LEFT, 
							 PADDING_TEXT_LIST_TOP, 
							 PADDING_TEXT_LIST_RIGHT, 
							 PADDING_TEXT_LIST_BOTTOM );
		textList.addView( this.title );
		textList.addView( this.infoBox );
		
		this.addView( this.colorSpace );
		this.addView( textList );
	}
	
	//Sets the title string to `title`
	
	public void setTitle( String title ) {
		
		this.title.setText( title );
	}
	
	//Sets the title string to its default value
	
	public void clearTitle() {
		
		this.title.setText( DEFAULT_TITLE );
	}
	
	//Adds a text view containing `infoText` to the list of information under the title
	
	public void addInfo( String infoText ) {
		
		TextView newChild = new TextView( this.getContext() );
		newChild.setPadding( PADDING_INFO_ENTRY_LEFT, 
				 			 PADDING_INFO_ENTRY_TOP, 
				 			 PADDING_INFO_ENTRY_RIGHT, 
				 			 PADDING_INFO_ENTRY_BOTTOM );
		newChild.setMaxLines( 3 );
		newChild.setText( infoText );
		
		this.infoBox.addView( newChild );
	}
	
	//Adds `view` to the list of information under the title
	
	public void addInfoTextView( TextView view ) {
		
		this.infoBox.addView( view );
	}
	
	//Changes the string in the info Element at `index` to ìnfoText`
	//Invalid indices are ignored
	
	public void setInfoAt( int index, String infoText ) {
		
		//index out of range
		if ( index < 0 || index > this.infoBox.getChildCount()-1 )
			return;
		
		TextView toChange = (TextView) this.infoBox.getChildAt( index );
		
		toChange.setText( infoText );
		
		//TODO: Test if the text changes, meaning, if we get a copy or a reference from getChildAt
	}
	
	//Removes the element at `index` from the list of information under the title. 
	//Negative indices remove the last element
	//Invalid indices are ignored
	
	public void removeInfoAt( int index ) {
		
		if ( this.infoBox.getChildCount() == 0 || index > this.infoBox.getChildCount()-1 )
			return;
		
		if ( index < 0 )
			index = this.infoBox.getChildCount() - 1;
		
		this.infoBox.removeViewAt( index );
		
		//TODO: Check what happens with invalid indices
	}
	
	//Removes all elements in info
	
	public void clearInfo() {
		
		this.infoBox.removeAllViews();
	}
	
	//sets color of the ImageView directly
	
	public void setColor( int color ) {
		
		this.colorSpace.setColorFilter( color );
	}
	
	//sets ImageView to a random color based on the given seed
	
	public void setRandomColor( long seed ) {

		Random gen = new Random( seed );

		// default color if there is a problem with the seed
		int color = Color.rgb( DEFAULT_COLOR_RED, DEFAULT_COLOR_GREEN, DEFAULT_COLOR_BLUE );
		
		color = Color.rgb( gen.nextInt( 256 ), gen.nextInt( 256 ), gen.nextInt( 256 ));
		
		this.colorSpace.setColorFilter( color );
		
		this.setBackgroundColor( color );
	}
	
	//sets ImageView to a random color using the current unix time
		
	public void setRandomColor() {
		
		this.setRandomColor( System.currentTimeMillis() );
	}
	
	//Sets the background color of the widget 
	
	public void setBackgroundColor( int color ) {
		
		int alphaDelta = 256 - COLOR_VALUE_ALPHA;
		
		int bgcolor = color - (alphaDelta<<COLOR_OFFSET_ALPHA);
		
		super.setBackgroundColor( bgcolor );
	}
}
