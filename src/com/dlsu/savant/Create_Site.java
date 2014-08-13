package com.dlsu.savant;

import objects.Site;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class Create_Site extends ActionBarActivity {
	
	public final static String EXTRA_MESSAGE = "com.dlsu.savant.MESSAGE";

	// final static String EXTRA_MESSAGE = "com.dlsu.savant.MESSAGE";

	private ImageButton createSitebtn;
	private EditText input_site_name;
	private EditText input_mun_name;
	private EditText input_prov_name;
	
	Intent intent;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_site);
		
		setTitle("Create New Site");
		
		intent = new Intent(Create_Site.this, View_Site.class);
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
		});
	}
}
