package com.dlsu.savant;


import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class View_Site extends Activity {
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view__site);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		Intent intent = getIntent();
		String siteName = intent.getStringExtra(Create_Site.EXTRA_MESSAGE);
		//String munName = intent.getStringExtra(Create_Site.EXTRA_MESSAGE);
		//String provName = intent.getStringExtra(Create_Site.EXTRA_MESSAGE);
		TextView site = (TextView)findViewById(R.id.nameOfSite);
		//TextView mun = (TextView)findViewById(R.id.nameOfMun);
		//TextView date = (TextView)findViewById(R.id.nameOfDate);
		
		site.setText(siteName);
		//mun.setText(munName +", " +provName);
		//date.setText(Calendar.DATE);
		
	}
}
