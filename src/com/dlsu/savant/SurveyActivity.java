package com.dlsu.savant;

import java.io.IOException;
import java.util.ArrayList;

import database.SavantDatabaseHandler;
import database.SurveyDatabaseHandler;
import objects.SurveyItemAdaptiveCapacity;
import objects.SurveyItemSensitivity;
import objects.SurveyType;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;


public class SurveyActivity extends Activity implements OnSeekBarChangeListener, OnTouchListener {

	private SavantDatabaseHandler databaseHandler;	
	private SurveyItemSensitivity surveyItemsSensitivity[];
	private SurveyItemAdaptiveCapacity surveyItemsAdaptiveCapacity[];
	private int answers[];
	private String[] valueDescriptions;
	private String[] valueImages;
	private int currentItemIndex;
	
	private SurveyType type;
	private int siteId;
	
	private String currentImage;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_survey);
		
		Bundle extras = getIntent().getExtras();
		if (extras.getString("type").equals("sensitivity")) {
			type = SurveyType.SURVEY_TYPE_SENSITIVITY;
			//((TextView)findViewById(R.id.titlebar_title)).setText("Sensitivity Survey");
			setTitle("Sensitivity Survey");
		}
		else {
			type = SurveyType.SURVEY_TYPE_ADAPTIVE_CAPACITY;
			//((TextView)findViewById(R.id.titlebar_title)).setText("Adaptive Capacity Survey");
			setTitle("Adaptive Capacity Survey");
			((ImageView)findViewById(R.id.survey_frequency_slider_label_image_view)).setImageResource(R.drawable.label_bottom_adpt_cpcty);
		}
		siteId = extras.getInt("id");
				
		if (type == SurveyType.SURVEY_TYPE_ADAPTIVE_CAPACITY) {
			((SeekBar)findViewById(R.id.survey_frequency_slider)).setMax(3);
		}
		
		initializeDatabase();
		initializeSurvey();
		initializeHandler();
		reloadSurveyItem();
	}
	
	private void initializeDatabase() {
		databaseHandler = new SavantDatabaseHandler(this);
		
		try {
			databaseHandler.createDatabase();
		}
		catch (IOException e) {
			throw new Error("Unable to create database");
		}
	}
	
	private void initializeSurvey() {
		databaseHandler.openDataBase();
		if (type == SurveyType.SURVEY_TYPE_SENSITIVITY) {
			ArrayList <SurveyItemSensitivity> items = databaseHandler.getAllSurveyItemsSensitivity();
			surveyItemsSensitivity = new SurveyItemSensitivity[items.size()];
			for (int i = 0; i < items.size(); i++) {
				surveyItemsSensitivity[i] = items.get(i);
			}
		}
		else {
			ArrayList <SurveyItemAdaptiveCapacity> items = databaseHandler.getAllSurveyItemsAdaptiveCapacity();
			surveyItemsAdaptiveCapacity = new SurveyItemAdaptiveCapacity[items.size()];
			for (int i = 0; i < items.size(); i++) {
				surveyItemsAdaptiveCapacity[i] = items.get(i);
			}
		}
		databaseHandler.close();
		
		currentItemIndex = 0;
		if (type == SurveyType.SURVEY_TYPE_SENSITIVITY) {
			valueDescriptions = new String[5];
			valueImages = new String[5];
		}
		else {
			valueDescriptions = new String[4];
			valueImages = new String[4];
		}
		
		if (type == SurveyType.SURVEY_TYPE_SENSITIVITY) {
			answers = new int[surveyItemsSensitivity.length];
		}
		else {
			answers = new int[surveyItemsAdaptiveCapacity.length];
		}
		
		for (int i = 0; i < answers.length; i++) {
			answers[i] = 0;
		}
	}
	
	private void initializeHandler() {
		((SeekBar)findViewById(R.id.survey_frequency_slider)).setOnSeekBarChangeListener(this);
		((SeekBar)findViewById(R.id.survey_frequency_slider)).setOnTouchListener(this);
	}
	
	private void reloadSurveyItem() {
		if (type == SurveyType.SURVEY_TYPE_SENSITIVITY) {
			((TextView)findViewById(R.id.category_label_text_view)).setText(surveyItemsSensitivity[currentItemIndex].getCategory());
			((TextView)findViewById(R.id.main_question_text_view)).setText(surveyItemsSensitivity[currentItemIndex].getMainQuestion());
			((TextView)findViewById(R.id.sub_question_text_view)).setText(surveyItemsSensitivity[currentItemIndex].getSubQuestion());
			valueDescriptions[0] = "1: " + surveyItemsSensitivity[currentItemIndex].getValue1Desc();
			valueDescriptions[1] = "2: " + surveyItemsSensitivity[currentItemIndex].getValue2Desc();
			valueDescriptions[2] = "3: " + surveyItemsSensitivity[currentItemIndex].getValue3Desc();
			valueDescriptions[3] = "4: " + surveyItemsSensitivity[currentItemIndex].getValue4Desc();
			valueDescriptions[4] = "5: " + surveyItemsSensitivity[currentItemIndex].getValue5Desc();
			valueImages[0] = surveyItemsSensitivity[currentItemIndex].getImage1();
			valueImages[1] = surveyItemsSensitivity[currentItemIndex].getImage2();
			valueImages[2] = surveyItemsSensitivity[currentItemIndex].getImage3();
			valueImages[3] = surveyItemsSensitivity[currentItemIndex].getImage4();
			valueImages[4] = surveyItemsSensitivity[currentItemIndex].getImage5();
			
			((ScrollView)findViewById(R.id.survey_scroll_view)).scrollTo(0, 0);
			((SeekBar)findViewById(R.id.survey_frequency_slider)).setProgress(answers[currentItemIndex]);
	        ((ImageView)findViewById(R.id.surevy_item_picture_border)).setVisibility(View.GONE);
			
			String uri = getImageUriOfCurrentProgress(answers[currentItemIndex]);
			String[] image = uri.split("/");
			this.currentImage = image[image.length-1];
			int imageResource = getResources().getIdentifier(uri, null, getPackageName());
	        ((ImageView)findViewById(R.id.survey_item_image_view)).setImageDrawable(getResources().getDrawable(imageResource));
	        ((ImageView)findViewById(R.id.survey_item_image_view)).setVisibility(View.GONE);
	        
			((TextView)findViewById(R.id.answer_information_text_view)).setText(valueDescriptions[answers[currentItemIndex]]);
			
			setTitle("Sensitivity Survey (" + (currentItemIndex + 1) + "/" + answers.length + ")");
		}
		else {
			((TextView)findViewById(R.id.category_label_text_view)).setText(surveyItemsAdaptiveCapacity[currentItemIndex].getCategory());
			((TextView)findViewById(R.id.main_question_text_view)).setText(surveyItemsAdaptiveCapacity[currentItemIndex].getMainQuestion());
			((TextView)findViewById(R.id.sub_question_text_view)).setText(surveyItemsAdaptiveCapacity[currentItemIndex].getSubQuestion());
			valueDescriptions[0] = "2: " + surveyItemsAdaptiveCapacity[currentItemIndex].getValue2Desc();
			valueDescriptions[1] = "3: " + surveyItemsAdaptiveCapacity[currentItemIndex].getValue3Desc();
			valueDescriptions[2] = "4: " + surveyItemsAdaptiveCapacity[currentItemIndex].getValue4Desc();
			valueDescriptions[3] = "5: " + surveyItemsAdaptiveCapacity[currentItemIndex].getValue5Desc();
			valueImages[0] = surveyItemsAdaptiveCapacity[currentItemIndex].getImage2();
			valueImages[1] = surveyItemsAdaptiveCapacity[currentItemIndex].getImage3();
			valueImages[2] = surveyItemsAdaptiveCapacity[currentItemIndex].getImage4();
			valueImages[3] = surveyItemsAdaptiveCapacity[currentItemIndex].getImage5();
			
			((ScrollView)findViewById(R.id.survey_scroll_view)).scrollTo(0, 0);
			((SeekBar)findViewById(R.id.survey_frequency_slider)).setProgress(answers[currentItemIndex]);
			((ImageView)findViewById(R.id.surevy_item_picture_border)).setVisibility(View.GONE);
			
			String uri = getImageUriOfCurrentProgress(answers[currentItemIndex]);
			String[] image = uri.split("/");
			this.currentImage = image[image.length-1];
			int imageResource = getResources().getIdentifier(uri, null, getPackageName());
	        ((ImageView)findViewById(R.id.survey_item_image_view)).setImageDrawable(getResources().getDrawable(imageResource));
	        ((ImageView)findViewById(R.id.survey_item_image_view)).setVisibility(View.GONE);
	        
			((TextView)findViewById(R.id.answer_information_text_view)).setText(valueDescriptions[answers[currentItemIndex]]);
			
			setTitle("Adaptive Capacity Survey (" + (currentItemIndex + 1) + "/" + answers.length + ")");
		}
	}
	
	public void goNext(View view) {
		currentItemIndex++;
		if (type == SurveyType.SURVEY_TYPE_SENSITIVITY) {
			if (currentItemIndex >= surveyItemsSensitivity.length) {
				currentItemIndex = surveyItemsSensitivity.length - 1;
				
				exitSurvey();
			}
			else {
				reloadSurveyItem();
			}
		}
		else {
			if (currentItemIndex >= surveyItemsAdaptiveCapacity.length) {
				currentItemIndex = surveyItemsAdaptiveCapacity.length - 1;
				
				exitSurvey();
			}
			else {
				reloadSurveyItem();
			}
		}
	}
	
	public void goPrevious(View view) {
		currentItemIndex--;
		if (currentItemIndex < 0) {
			currentItemIndex = 0;
		}
		else {
			reloadSurveyItem();
		}
	}
	
	private void saveAnswersToDatabase() {
		if (type == SurveyType.SURVEY_TYPE_SENSITIVITY) {
			float result = computeSensitivityScore();
			SurveyDatabaseHandler handler = new SurveyDatabaseHandler(this);
			handler.openDataBase();
			handler.saveSensitivitySurveyAnswers(siteId, answers, result);
			handler.close();
		}
		else {
			float result = computeAdaptiveCapacityScore();
			SurveyDatabaseHandler handler = new SurveyDatabaseHandler(this);
			handler.openDataBase();
			handler.saveAdaptiveCapacitySurveyAnswers(siteId, answers, result);
			handler.close();
		}
	}
	
	public float computeSensitivityScore() {
		
		/*float result = 0;
		
		for (int i = 0; i < answers.length; i++) {
			result += answers[i] + 1;
		}*/
		
		float coastalHabitatSum = 0 ;
		for (int i = 0; i < 6; i++) {
			coastalHabitatSum += (answers[i]+1) ;
		}
		float coastalHabitatAve = coastalHabitatSum/6;
		
		
		float fishAndFisheriesSum = 0;
		for (int i = 6; i < 11; i++) {
			fishAndFisheriesSum += (answers[i]+1) ;
		}
		float fishAndFisheriesAve = fishAndFisheriesSum/5;
		
		float coastalIntegritySum = 0;
		for (int i = 11; i < 15; i++) {
			coastalIntegritySum += (answers[i]+1) ;
		}
		float coastalIntegrityAve = coastalIntegritySum/4;
		
		return (coastalHabitatAve+fishAndFisheriesAve+coastalIntegrityAve)/ 3;
	}
	
	public float computeAdaptiveCapacityScore() {
		/*float result = 0;
		for (int i = 0; i < answers.length; i++) {
			result += answers[i] + 1;
		}*/
		
		float coastalHabitatSum = 0 ;
		for (int i = 0; i < 14; i++) {
			coastalHabitatSum += (answers[i]+2) ;
		}
		float coastalHabitatAve = coastalHabitatSum/14;
		
		float fishAndFisheriesSum = 0;
		for (int i = 14; i < 19; i++) {
			fishAndFisheriesSum += (answers[i]+2) ;
		}
		float fishAndFisheriesAve = fishAndFisheriesSum/5;
		
		float coastalIntegrityAve = answers[19]+2;
		
		float humanActivitySum = 0;
		for (int i = 20; i < 24; i++) {
			humanActivitySum += (answers[i]+2);
		}
		float humanActivityAve = humanActivitySum/4;
		
		return   (coastalHabitatAve+fishAndFisheriesAve+coastalIntegrityAve+humanActivityAve)/ 4;
	}
	
	private void exitSurvey() {
		AlertDialog.Builder builder = new AlertDialog.Builder(SurveyActivity.this);
		builder.setTitle("The survey cannot be edited once it is submitted. Submit your answers?");
		builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,	int id) {
				saveAnswersToDatabase();
				finish();
			}
		});
		builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,	int id) {
			}
		});
		AlertDialog dialog = builder.create();
		dialog.show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.survey, menu);
		return true;
	}

	@Override
	public void onProgressChanged(SeekBar arg0, int progress, boolean arg2) {
		TextView target = (TextView)findViewById(R.id.answer_information_text_view);
		target.setText(valueDescriptions[progress]);
		answers[currentItemIndex] = progress;
		
		
		String uri = getImageUriOfCurrentProgress(progress);
		int imageResource = getResources().getIdentifier(uri, null, getPackageName());
		
		String[] image = uri.split("/");
		this.currentImage = image[image.length-1];
		
		if(!currentImage.equalsIgnoreCase("image_not_found")){
			((ImageView)findViewById(R.id.surevy_item_picture_border)).setVisibility(View.VISIBLE);
			
			((ImageView)findViewById(R.id.survey_item_image_view)).setImageDrawable(getResources().getDrawable(imageResource));
	        ((ImageView)findViewById(R.id.survey_item_image_view)).setVisibility(View.VISIBLE);
        }
		else
		{
			((ImageView)findViewById(R.id.surevy_item_picture_border)).setVisibility(View.GONE);
			((ImageView)findViewById(R.id.survey_item_image_view)).setVisibility(View.GONE);
		}
	}
	
	public String getImageUriOfCurrentProgress(int progress) {
		String uri = "drawable/";
		if (type == SurveyType.SURVEY_TYPE_SENSITIVITY) {
			if (progress == 0) {
				uri += surveyItemsSensitivity[currentItemIndex].getImage1();
				((ImageView)findViewById(R.id.surevy_item_picture_border)).setImageResource(R.drawable.picture_border_1);
			}
			else if (progress == 1) {
				uri += surveyItemsSensitivity[currentItemIndex].getImage2();
				((ImageView)findViewById(R.id.surevy_item_picture_border)).setImageResource(R.drawable.picture_border_2);
			}
			else if (progress == 2) {
				uri += surveyItemsSensitivity[currentItemIndex].getImage3();
				((ImageView)findViewById(R.id.surevy_item_picture_border)).setImageResource(R.drawable.picture_border_3);
			}
			else if (progress == 3) {
				uri += surveyItemsSensitivity[currentItemIndex].getImage4();
				((ImageView)findViewById(R.id.surevy_item_picture_border)).setImageResource(R.drawable.picture_border_4);
			}
			else if (progress == 4) {
				uri += surveyItemsSensitivity[currentItemIndex].getImage5();
				((ImageView)findViewById(R.id.surevy_item_picture_border)).setImageResource(R.drawable.picture_border_5);
			}
		}
		else {
			if (progress == 0) {
				uri += surveyItemsAdaptiveCapacity[currentItemIndex].getImage2();
				((ImageView)findViewById(R.id.surevy_item_picture_border)).setImageResource(R.drawable.picture_border_adpt_cpcty_2);
			}
			else if (progress == 1) {
				uri += surveyItemsAdaptiveCapacity[currentItemIndex].getImage3();
				((ImageView)findViewById(R.id.surevy_item_picture_border)).setImageResource(R.drawable.picture_border_adpt_cpcty_3);
			}
			else if (progress == 2) {
				uri += surveyItemsAdaptiveCapacity[currentItemIndex].getImage4();
				((ImageView)findViewById(R.id.surevy_item_picture_border)).setImageResource(R.drawable.picture_border_adpt_cpcty_4);
			}
			else if (progress == 3) {
				uri += surveyItemsAdaptiveCapacity[currentItemIndex].getImage5();
				((ImageView)findViewById(R.id.surevy_item_picture_border)).setImageResource(R.drawable.picture_border_adpt_cpcty_5);
			}
		}
		return uri;
	}

	@Override
	public void onStartTrackingTouch(SeekBar arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStopTrackingTouch(SeekBar arg0) {
		// TODO Auto-generated method stub
		// Remove Popup
		((ImageView)findViewById(R.id.survey_item_image_view)).setVisibility(View.GONE);
		((ImageView)findViewById(R.id.surevy_item_picture_border)).setVisibility(View.GONE);
	}

	@Override
	public boolean onTouch(View arg0, MotionEvent arg1) {
		// TODO Auto-generated method stub
		// Popup
		
		if(currentImage != null && !currentImage.equalsIgnoreCase("image_not_found"))
		{
			((ImageView)findViewById(R.id.surevy_item_picture_border)).setVisibility(View.VISIBLE);
			((ImageView)findViewById(R.id.survey_item_image_view)).setVisibility(View.VISIBLE);
		}
		
		
		return false;
	}
}
