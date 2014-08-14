package com.dlsu.savant;


import objects.ScoreIdentifier;
import objects.Site;
import android.app.Activity;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.Window;
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
		setTitle(site.getSiteName() + "Details");
		
		initializeDatabase();
		viewDetails();
	}

	private void viewDetails() {
		((TextView)findViewById(R.id.nameOfSite)).setText(site.getSiteName());
		((TextView)findViewById(R.id.nameOfMun)).setText(site.getSiteMun() + ", " + site.getSiteProvince());
		((TextView)findViewById(R.id.nameOfDate)).setText(site.getDateCreated());
		
		((TextView)findViewById(R.id.exposureSubTitle)).setText(site.getExposureScore());
		((TextView)findViewById(R.id.exposureSubTitle)).setBackgroundResource(ScoreIdentifier.identifyScoreColor(site.getExposureScore()));
		((TextView)findViewById(R.id.exposureScore)).setText(ScoreIdentifier.identifyScoreCategory(site.getExposureScore()));
		
		((RelativeLayout)findViewById(R.id.exposureContainer)).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				
			}
		});
		
		if (site.hasAdaptiveCapacityScore() && site.hasSensitivityScore()) {
			String score = site.getAveScore() + "";
			((TextView)findViewById(R.id.ave_Score)).setText(score.substring(0, score.indexOf(".")+2));
			((TextView)findViewById(R.id.ave_Score)).setBackgroundResource(ScoreIdentifier.identifyScoreColor(site.getAveScore()));
		}
		
		if(site.hasSensitivityScore()){
			String score = site.getSensitivityScore()+"";
			((TextView)findViewById(R.id.sensitivitySubTitle)).setText(score.substring(0, score.indexOf(".") + 2));
			((TextView)findViewById(R.id.sensitivityScore)).setBackgroundResource(ScoreIdentifier.identifyScoreColor(site.getSensitivityScore()));
			((TextView)findViewById(R.id.sensitivityScore)).setText(ScoreIdentifier.identifyScoreCategory(site.getSensitivityScore()));
			//((ImageView)findViewById(R.id.site_details_sensitivity_survey_button)).setImageResource(R.drawable.next);
			((RelativeLayout)findViewById(R.id.sensitivityContainer)).setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					
				}
			});
		}else
		{
			((RelativeLayout)findViewById(R.id.sensitivityContainer)).setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					
				}
			});
		}
		
		if (site.hasAdaptiveCapacityScore()) {
			String score = site.getAdaptiveCapacityScore()+"";
			((TextView)findViewById(R.id.adaptiveCapacitySubTitle)).setText(score.substring(0, score.indexOf(".")+2));
			((TextView)findViewById(R.id.adaptiveCapacityScore)).setBackgroundResource(ScoreIdentifier.identifyScoreColor(site.getAdaptiveCapacityScore()));
			((TextView)findViewById(R.id.adaptiveCapacityScore)).setText(ScoreIdentifier.identifyScoreCategory(site.getAdaptiveCapacityScore()));
			//((ImageView)findViewById(R.id.site_details_adaptive_capacity_survey_button)).setImageResource(R.drawable.next);
			((RelativeLayout)findViewById(R.id.adaptiveCapacityContainer)).setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					
				}
			});
		}else {
			((RelativeLayout)findViewById(R.id.adaptiveCapacityContainer)).setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					
				}
			});
		}
	}

	private void initializeDatabase() {
		// TODO Auto-generated method stub
		
	}
	
	private void initializeData(){
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}
