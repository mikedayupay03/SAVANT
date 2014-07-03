package com.dlsu.savant;


import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class View_Site extends Activity {
	
	TextView nameOfSite;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view__site);
		
		nameOfSite = (TextView)findViewById(R.id.nameOfSite);
		nameOfSite.setText("Leron");
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}
}
