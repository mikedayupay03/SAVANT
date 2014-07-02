package com.example.savant;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class Create_Site extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create__site);
		
		//Intent intent = getIntent();
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}
}
