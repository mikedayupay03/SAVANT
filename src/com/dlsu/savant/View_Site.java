package com.dlsu.savant;


import objects.Site;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class View_Site extends Activity {
	
	private Site site = new Site();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_site_details);	

		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		Intent intent = getIntent();
	
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
