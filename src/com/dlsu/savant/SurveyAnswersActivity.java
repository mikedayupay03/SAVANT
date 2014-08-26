package com.dlsu.savant;

import java.util.ArrayList;

import database.SavantDatabaseHandler;
import database.SurveyDatabaseHandler;
import objects.SurveyItem;
import objects.SurveyItemAdapter;
import objects.SurveyItemAdaptiveCapacity;
import objects.SurveyItemSensitivity;
import objects.SurveyType;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;

public class SurveyAnswersActivity extends Activity {

	private SurveyDatabaseHandler surveyDB;
	private SavantDatabaseHandler savantDB;
	private int siteId;
	private SurveyType type;
	private String[] surveyItemTextList;
	private SurveyItem[] surveyItemList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_survey_answers);
		
		Bundle extras = getIntent().getExtras();
		siteId = extras.getInt("id");
		String type = extras.getString("type");
		
		if (type.equals("sensitivity")) {
			this.type = SurveyType.SURVEY_TYPE_SENSITIVITY;
			((TextView)findViewById(R.id.titlebar_title)).setText("Sensitivity Survey");
			//setTitle("Sensitivity Survey");
		}
		else if(type.equals("adaptive")) {
			this.type = SurveyType.SURVEY_TYPE_ADAPTIVE_CAPACITY;
			((TextView)findViewById(R.id.titlebar_title)).setText("Adaptive Capacity Survey");
			//setTitle("Adaptive Capacity Survey");
		}
		else
		{
			this.type = SurveyType.SURVEY_TYPE_EXPOSURE;
			((TextView)findViewById(R.id.titlebar_title)).setText("Exposure Survey");
			//setTitle("Exposure Survey");
		}
		
		initializeDatabase();
		initializeData();
		reloadList();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.view_survey_answers, menu);
		return true;
	}
	
	private void initializeDatabase() {
		surveyDB = new SurveyDatabaseHandler(this);
		savantDB = new SavantDatabaseHandler(this);
		try {
			surveyDB.createDatabase();
		}
		catch (Exception ex) {
			
		}
	}
	
	private void initializeData() {
		savantDB.openDataBase();
		if (type == SurveyType.SURVEY_TYPE_SENSITIVITY) {
			ArrayList <SurveyItemSensitivity> results = savantDB.getAllSurveyItemsSensitivity();
			surveyItemTextList = new String[results.size()];
			for (int i = 0; i < results.size(); i++) {
				surveyItemTextList[i] = results.get(i).getSubQuestion();
			}
		}
		else if (type == SurveyType.SURVEY_TYPE_ADAPTIVE_CAPACITY) {
			ArrayList <SurveyItemAdaptiveCapacity> results = savantDB.getAllSurveyItemsAdaptiveCapacity();
			surveyItemTextList = new String[results.size()];
			for (int i = 0; i < results.size(); i++) {
				surveyItemTextList[i] = results.get(i).getSubQuestion();
			}
		}
		else{
			surveyItemTextList = savantDB.getAllSurveyItemsExposure();
		} 
		savantDB.close();
		
		surveyDB.openDataBase();
		ArrayList <SurveyItem> surveys = surveyDB.getAllSurveyAnswers(siteId, type, surveyItemTextList);
		surveyItemList = new SurveyItem[surveys.size()];
		for (int i = 0; i < surveys.size(); i++) {
			surveyItemList[i] = surveys.get(i);
		}
		surveyDB.close();
	}
	
	private void reloadList() {
		SurveyItemAdapter adapter = new SurveyItemAdapter(this, R.layout.list_survey, surveyItemList);
		((ListView)findViewById(R.id.list_survey_answers_list_view)).setAdapter(adapter);
	}

}
