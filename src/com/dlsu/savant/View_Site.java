package com.dlsu.savant;


import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class View_Site extends Activity {
	
<<<<<<< HEAD
	TextView nameOfSite;
	//Initialize the list of view site
	String[] viewSScoreOpt =  new String []
			{ "Exposure", "Sensitivity", "Adaptive Capacity"};
=======
>>>>>>> origin/master

	private ListView listScore;
	private ArrayAdapter<String> arrayAdapter1;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view__site);
		
<<<<<<< HEAD
		listScore = (ListView)findViewById(R.id.listscoreopt);
		nameOfSite = (TextView)findViewById(R.id.nameOfSite);
		nameOfSite.setText("Leron");
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		arrayAdapter1 = new ArrayAdapter<String>(this, R.layout.list_options,viewSScoreOpt);
		listScore.setAdapter(arrayAdapter1);
=======
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
		
>>>>>>> origin/master
	}
}
