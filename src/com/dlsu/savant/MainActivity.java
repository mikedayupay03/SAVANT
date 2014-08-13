package com.dlsu.savant;


import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageButton;

public class MainActivity extends Activity {
	
	private ImageButton viewSiteBtn;
	private ImageButton createSiteBtn;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        
        setTitle("SAVANT");
        
    }
    
}
