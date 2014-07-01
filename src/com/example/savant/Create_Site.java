package com.example.savant;

import android.app.Activity;
import android.os.Bundle;

public class Create_Site extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create__site);
		
		//Intent intent = getIntent();
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}
}
