package com.dlsu.savant;

import java.io.IOException;

import objects.Site;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import database.SurveyDatabaseHandler;

public class ListSitesActivity extends Activity implements OnItemClickListener{
	
	private SurveyDatabaseHandler surveyDB;
	private Site[] siteList;
	private SiteAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_list_sites);
		
		initializeDatabase();
		((ListView)findViewById(R.id.listSites)).setOnItemClickListener(this);
	}
	
	private void initializeDatabase()
	{
		surveyDB = new SurveyDatabaseHandler(this);
		try {
			surveyDB.createDatabase();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_sites, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}else if(id == R.id.addSite){
			Intent newSite = new Intent(this, Create_Site.class);
			startActivity(newSite);
			return true;
		}else if(id == R.id.action_search){
			
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		Intent nextActivity = new Intent(this, View_Site.class);
		nextActivity.putExtra("id", ((Site)arg0.getItemAtPosition(arg2)).getId());
		startActivity(nextActivity);
	}
	
}
