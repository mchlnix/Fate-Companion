package com.example.fatecompanion;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class CampaignListView extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campaign_list_view);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.campaign_list_view, menu);
        return true;
    }
    
}
