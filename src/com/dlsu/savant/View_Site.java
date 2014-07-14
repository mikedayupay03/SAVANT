package com.dlsu.savant;


import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class View_Site extends Activity {
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view__site);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		Site siteObj = (Site) getIntent().getSerializableExtra("SiteObject");
		TextView siteName = (TextView)findViewById(R.id.nameOfSite);
		TextView munName = (TextView)findViewById(R.id.nameOfMun);
		siteName.setText(siteObj.getSiteName());
		munName.setText(siteObj.getSiteMun()+", "+ siteObj.getSiteProvince());
		
	}
}
