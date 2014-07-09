package com.dlsu.savant;


import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class View_Site extends Activity {
	
	TextView nameOfSite;
	//Initialize the list of view site
	String[] viewSScoreOpt =  new String []
			{ "Exposure", "Sensitivity", "Adaptive Capacity"};

	private ListView listScore;
	private ArrayAdapter<String> arrayAdapter1;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view__site);
		
		listScore = (ListView)findViewById(R.id.listscoreopt);
		nameOfSite = (TextView)findViewById(R.id.nameOfSite);
		nameOfSite.setText("Leron");
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		arrayAdapter1 = new ArrayAdapter<String>(this, R.layout.list_options,viewSScoreOpt);
		listScore.setAdapter(arrayAdapter1);
	}
}
