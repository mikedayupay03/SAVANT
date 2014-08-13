package com.dlsu.savant;


import objects.Site;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

public class MainActivity extends Activity {

	private ListView listsite;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Site site_data[] = new Site[]{
        		new Site("Leron"),
        		new Site("Leron")
        		
        };
        
        listsite = (ListView)findViewById(R.id.listSites);
        SiteAdapter adapter = new SiteAdapter(this, R.layout.list_sites, site_data);
        listsite.setAdapter(adapter);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.main_activity_actions, menu);
    	return super.onCreateOptionsMenu(menu);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch(item.getItemId()) {
    		case R.id.addSite:
    			Intent newSite = new Intent(this, Create_Site.class);
    			startActivity(newSite);
    			return true;
    		case R.id.action_search:
    			//openSearch();
    			return true;
    			
    		default:
    			return super.onOptionsItemSelected(item);
    	}
    }
}
