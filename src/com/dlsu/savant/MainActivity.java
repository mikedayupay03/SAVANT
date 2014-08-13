package com.dlsu.savant;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

public class MainActivity extends Activity {
	
	private ImageButton viewSiteBtn;
	private ImageButton createSiteBtn;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        
        setTitle("SAVANT");
        
        viewSiteBtn = (ImageButton)findViewById(R.id.view_site_btn);
        createSiteBtn = (ImageButton)findViewById(R.id.create_new_site_btn);
        
        viewSiteBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent nextActivity = new Intent(MainActivity.this, ListSitesActivity.class);
				startActivity(nextActivity);
			}
		});
        
        createSiteBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent nextActivity = new Intent(MainActivity.this, Create_Site.class);
				startActivity(nextActivity);
			}
		});
        
    }
    
}
