package com.dlsu.savant;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class View_Site extends Activity {
	

	private ListView listsurvey;
	Site site = new Site();
	
	String[] surveyName = {
			"Exposure",
			"Sensitivity",
			"Adaptive Capacity"
	};
	
	Survey survey_data[] = new Survey[]{
			new Survey(site.getSiteExposure(), site.getSiteExposure()),
			new Survey(Double.toString(site.getSiteSens()), Double.toString(site.getSiteSens())),
			new Survey(Double.toString(site.getSiteAdaptCap()), Double.toString(site.getSiteAdaptCap()))
	};

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
		

		listScore = (ListView)findViewById(R.id.listScore);
		
		arrayAdapter1 = new ArrayAdapter<String>(this, R.layout.list_options,viewSScoreOpt);
		listScore.setAdapter(arrayAdapter1);

		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		Intent intent = getIntent();
		//String siteName = intent.getStringExtra(Create_Site.EXTRA_MESSAGE);
		//String munName = intent.getStringExtra(Create_Site.EXTRA_MESSAGE);
		//String provName = intent.getStringExtra(Create_Site.EXTRA_MESSAGE);
		//TextView site = (TextView)findViewById(R.id.nameOfSite);
		//TextView mun = (TextView)findViewById(R.id.nameOfMun);
		//TextView date = (TextView)findViewById(R.id.nameOfDate);
		
		//site.setText(siteName);
		//mun.setText(munName +", " +provName);
		//date.setText(Calendar.DATE);
	
		getActionBar().setDisplayHomeAsUpEnabled(true);
		Site siteObj = (Site) getIntent().getSerializableExtra("SiteObject");
		TextView siteName = (TextView)findViewById(R.id.nameOfSite);
		TextView munName = (TextView)findViewById(R.id.nameOfMun);
		TextView dateCreated = (TextView)findViewById(R.id.nameOfDate);
		siteName.setText(siteObj.getSiteName());
		munName.setText(siteObj.getSiteMun()+", "+ siteObj.getSiteProvince());
		dateCreated.setText(siteObj.getDateCreated());
		
		listsurvey = (ListView)findViewById(R.id.listScore);
		
		SurveyAdapter adapter = new SurveyAdapter(this, R.layout.list_scores, surveyName, survey_data);
		listsurvey.setAdapter(adapter);
		
		
	}
}
