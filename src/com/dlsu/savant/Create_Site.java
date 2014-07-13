package com.dlsu.savant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Create_Site extends ActionBarActivity {
	
	public final static String EXTRA_MESSAGE = "com.dlsu.savant.MESSAGE";
	Button createSite;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create__site);
		
		//Intent intent = getIntent();
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		createSite = (Button)findViewById(R.id.startSurvey);
		createSite.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Create_Site.this, View_Site.class);
				EditText site = (EditText)findViewById(R.id.editSiteName);
				EditText mun = (EditText)findViewById(R.id.editMunicipality);
				EditText prov = (EditText)findViewById(R.id.editProvince);
				String siteName = site.getText().toString();
				String munName = mun.getText().toString();
				String provName = prov.getText().toString();
				intent.putExtra(EXTRA_MESSAGE, siteName);
				intent.putExtra(EXTRA_MESSAGE, munName);
				intent.putExtra(EXTRA_MESSAGE, provName);
				startActivity(intent);
				
			}
		});
	}
}
