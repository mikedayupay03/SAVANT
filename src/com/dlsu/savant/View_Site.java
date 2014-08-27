package com.dlsu.savant;


import objects.ScoreIdentifier;
import objects.Site;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import database.SurveyDatabaseHandler;

public class View_Site extends Activity implements OnClickListener{
	
	private SurveyDatabaseHandler surveyDB;
	private Site site;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_view_site_details);	
		
		
		initializeDatabase();
		initializeData();
		viewDetails();
	}

	private void viewDetails() {
		((TextView)findViewById(R.id.titlebar_title)).setText(site.getSiteName()+" Details");
		((TextView)findViewById(R.id.nameOfSite)).setText(site.getSiteName());
		((TextView)findViewById(R.id.nameOfMun)).setText(site.getSiteMun() + ", " + site.getSiteProvince());
		((TextView)findViewById(R.id.nameOfDate)).setText(site.getDateCreated());
		
		((TextView)findViewById(R.id.exposureScore)).setText(site.getExposureScore());
		((TextView)findViewById(R.id.exposureScore)).setBackgroundResource(ScoreIdentifier.identifyScoreColor(site.getExposureScore()));
		((TextView)findViewById(R.id.exposureSubTitle)).setText(ScoreIdentifier.identifyScoreCategory(site.getExposureScore()));
		
		((RelativeLayout)findViewById(R.id.exposureContainer)).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(View_Site.this, SurveyAnswersActivity.class);
				intent.putExtra("id", site.getId());
				intent.putExtra("type", "exposure");
				startActivity(intent);
			}
		});
		
		if (site.hasAdaptiveCapacityScore() && site.hasSensitivityScore()) {
			String score = site.getAveScore() + "";
			((TextView)findViewById(R.id.ave_Score)).setText(score.substring(0, score.indexOf(".")+2));
			((TextView)findViewById(R.id.ave_Score)).setBackgroundResource(ScoreIdentifier.identifyScoreColor(site.getAveScore()));
		}
		
		if(site.hasSensitivityScore()){
			String score = site.getSensitivityScore()+"";
			((TextView)findViewById(R.id.sensitivityScore)).setText(score.substring(0, score.indexOf(".") + 2));
			((TextView)findViewById(R.id.sensitivityScore)).setBackgroundResource(ScoreIdentifier.identifyScoreColor(site.getSensitivityScore()));
			((TextView)findViewById(R.id.sensitivitySubTitle)).setText(ScoreIdentifier.identifyScoreCategory(site.getSensitivityScore()));
			((ImageView)findViewById(R.id.site_details_sensitivity_survey_button)).setImageResource(R.drawable.next);
			((RelativeLayout)findViewById(R.id.sensitivityContainer)).setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(View_Site.this, SurveyAnswersActivity.class);
					intent.putExtra("id", site.getId());
					intent.putExtra("type", "sensitivity");
					startActivity(intent);
				}
			});
		}else
		{
			((RelativeLayout)findViewById(R.id.sensitivityContainer)).setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(View_Site.this, SurveyActivity.class);
					intent.putExtra("id", site.getId());
					intent.putExtra("type", "sensitivity");
					startActivityForResult(intent, 1);
				}
			});
		}
		
		if (site.hasAdaptiveCapacityScore()) {
			String score = site.getAdaptiveCapacityScore()+"";
			((TextView)findViewById(R.id.adaptiveCapacityScore)).setText(score.substring(0, score.indexOf(".")+2));
			((TextView)findViewById(R.id.adaptiveCapacityScore)).setBackgroundResource(ScoreIdentifier.identifyScoreColor(site.getAdaptiveCapacityScore()));
			((TextView)findViewById(R.id.adaptiveCapacitySubTitle)).setText(ScoreIdentifier.identifyScoreCategory(site.getAdaptiveCapacityScore()));
			((ImageView)findViewById(R.id.site_details_adaptive_capacity_survey_button)).setImageResource(R.drawable.next);
			((RelativeLayout)findViewById(R.id.adaptiveCapacityContainer)).setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(View_Site.this, SurveyAnswersActivity.class);
					intent.putExtra("id", site.getId());
					intent.putExtra("type", "adaptive");
					startActivity(intent);
				}
			});
		}else {
			((RelativeLayout)findViewById(R.id.adaptiveCapacityContainer)).setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(View_Site.this, SurveyActivity.class);
					intent.putExtra("id", site.getId());
					intent.putExtra("type", "adaptive");
					startActivityForResult(intent, 1);
				}
			});
		}
	}

	private void initializeDatabase() {
		// TODO Auto-generated method stub
		surveyDB = new SurveyDatabaseHandler(this);
		try {
			surveyDB.createDatabase();
		}
		catch (Exception ex) {
			
		}
	}
	
	private void initializeData(){
		surveyDB.openDataBase();
		Bundle extras = getIntent().getExtras();
		site = surveyDB.getSite(extras.getInt("id"));
		surveyDB.close();
		setTitle(site.getSiteName() + "Details");
		//((TextView)findViewById(R.id.titlebar_title)).setText(site.getSiteName()+" Details");
	}
	
	protected void onActivityResult (int requestCode, int resultCode, Intent data) {
		initializeData();
		viewDetails();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}
