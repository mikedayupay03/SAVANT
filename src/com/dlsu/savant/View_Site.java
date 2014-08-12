package com.dlsu.savant;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class View_Site extends Activity {
		
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view__site);
		

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
		

		
		
	}
}
