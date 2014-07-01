package com.example.savant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Splash_screen extends Activity {
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Thread timer = new Thread(){
        	public void run(){
        		try{
        			sleep(5000);
        			
        		} catch(InterruptedException e){
        			e.printStackTrace();
        		}finally{
        			Intent openMain = new Intent("com.example.savant.MainActivity");
        			startActivity(openMain);
        		}
        	}
        };
    }
}
