package com.dlsu.savant;

import java.io.IOException;
import java.util.ArrayList;

import database.SavantDatabaseHandler;
import database.SurveyDatabaseHandler;

import objects.Site;
import objects.SiteSuggestionAdapter;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Create_Site extends ActionBarActivity implements OnItemClickListener, OnClickListener {
	
	public final static String EXTRA_MESSAGE = "com.dlsu.savant.MESSAGE";

	private ImageButton createSitebtn;	
	SavantDatabaseHandler handler;
	SurveyDatabaseHandler surveyHandler;
	Intent intent;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_site);
		
		setTitle("Create New Site");
		
		initDatabase();
		initData();
		
		createSitebtn = (ImageButton)findViewById(R.id.startSurveybtn);
		createSitebtn.setOnClickListener(this);
		
		/*intent = new Intent(this, View_Site.class);
		input_site_name = (EditText)findViewById(R.id.editSiteName);
		input_mun_name = (EditText)findViewById(R.id.editMunicipality);
		input_prov_name = (EditText)findViewById(R.id.editProvince);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		createSitebtn = (ImageButton)findViewById(R.id.startSurveybtn);
		createSitebtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Site siteObject = new Site();

				String siteName = input_site_name.getText().toString();
				String munName = input_mun_name.getText().toString();
				String provName = input_prov_name.getText().toString();
				intent.putExtra(EXTRA_MESSAGE, siteName);
				intent.putExtra(EXTRA_MESSAGE, munName);
				intent.putExtra(EXTRA_MESSAGE, provName);
				startActivity(intent);

				siteObject.setSiteName(input_site_name.getText().toString());
				siteObject.setSiteMun(input_mun_name.getText().toString());
				siteObject.setSiteProvince(input_prov_name.getText().toString());
				intent.putExtra("SiteObject", siteObject);
				startActivityForResult(intent, 1);
							
			}
		});*/
	}
	
	private void initDatabase(){
		handler = new SavantDatabaseHandler(this);
		try{
			handler.createDatabase();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	private void initData() {
		
		handler.openDataBase();
		ArrayList <Site> site = handler.getAllSuggestedSites();
		handler.close();
		Site[] data = new Site[site.size()];
		for (int i = 0; i < site.size(); i++) {
			data[i] = site.get(i);
		}
		SiteSuggestionAdapter adapter = new SiteSuggestionAdapter(this, R.layout.site_suggestion, data);
		((AutoCompleteTextView)findViewById(R.id.editSiteName)).setAdapter(adapter);
		((AutoCompleteTextView)findViewById(R.id.editSiteName)).setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		Site target = ((Site)arg0.getItemAtPosition(arg2));
		String name = target.getSiteName();
		String municipality = target.getSiteMun();
		String province = target.getSiteProvince();
		
		((AutoCompleteTextView)findViewById(R.id.editSiteName)).setText(name);
		((AutoCompleteTextView)findViewById(R.id.editMunicipality)).setText(municipality);
		((AutoCompleteTextView)findViewById(R.id.editProvince)).setText(province);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		String siteName = ((TextView)findViewById(R.id.editSiteName)).getText().toString().trim();
		String munName = ((TextView)findViewById(R.id.editMunicipality)).getText().toString().trim();
		String provName = ((TextView)findViewById(R.id.editProvince)).getText().toString().trim();
		
		handler.openDataBase();
		
		if(siteName.equals("") || munName.equals("") || provName.equals(""))
		{
			Toast.makeText(this, "Please complete the missing fields", Toast.LENGTH_SHORT).show();
		}
		else if (!handler.checkSiteSuggestionExists( siteName,  munName,  provName) )
		{
			Toast.makeText(this, "Sorry site does not belong from the suggested sites", Toast.LENGTH_LONG).show();
		}
		else{
			createSite();
		}
		
		handler.close();
	}

	private void createSite() {
		// TODO Auto-generated method stub
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Add this site to the database?");
		builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,	int id) {
				int newId = saveSiteToDatabase();
				finish();
				Intent nextActivity = new Intent(Create_Site.this, View_Site.class);
				nextActivity.putExtra("id", newId);
				startActivity(nextActivity);
			}
		});
		builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,	int id) {
			}
		});
		AlertDialog dialog = builder.create();
		dialog.show();
	}
	
	private int saveSiteToDatabase() {
		String siteName = ((TextView)findViewById(R.id.editSiteName)).getText().toString().trim();
		String municipality = ((TextView)findViewById(R.id.editMunicipality)).getText().toString().trim();
		String province = ((TextView)findViewById(R.id.editProvince)).getText().toString().trim();
		
		//SavantDatabaseHandler savantDb = new SavantDatabaseHandler(this);
		handler.openDataBase();
		int sugg_site_id = handler.getSiteSuggestionId(siteName, municipality, province);
		String[] scores = handler.getExposureScores(sugg_site_id);
		handler.close();
		
		surveyHandler = new SurveyDatabaseHandler(this);
		surveyHandler.openDataBase();
		surveyHandler.saveSite(siteName, municipality, province);
		int id = surveyHandler.getLatestSiteId();
		surveyHandler.saveExposureScores(id,scores);
		surveyHandler.close();
		
		return id;
	}
	
}
