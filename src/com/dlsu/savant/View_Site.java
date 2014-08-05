package com.dlsu.savant;


import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class View_Site extends Activity {
	
<<<<<<< HEAD
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
=======
<<<<<<< HEAD
<<<<<<< HEAD
	TextView nameOfSite;
	//Initialize the list of view site
	String[] viewSScoreOpt =  new String []
			{ "Exposure", "Sensitivity", "Adaptive Capacity"};
=======
>>>>>>> origin/master
=======
>>>>>>> origin/master
>>>>>>> 26c8e203104469aaa4bf9f8b0d4e68e3fe046bef

	private ListView listScore;
	private ArrayAdapter<String> arrayAdapter1;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view__site);
		
<<<<<<< HEAD
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
=======
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
		
		
>>>>>>> origin/master
	}
}
