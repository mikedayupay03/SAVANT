package com.dlsu.savant;

import objects.Site;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import database.SurveyDatabaseHandler;

public class ListSitesActivity extends Activity {
	
	private SurveyDatabaseHandler surveyDBHandler;
	private Site[] siteList;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_sites);
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
}
