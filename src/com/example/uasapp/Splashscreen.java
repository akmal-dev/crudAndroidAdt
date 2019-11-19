package com.example.uasapp;

import android.os.Bundle;
import android.content.Intent;
import android.app.Activity;
import android.view.Menu;
import android.view.Window;
import android.os.Handler;
import android.view.WindowManager;

public class Splashscreen extends Activity {
	
	//set waktu splash
	private static int splashInterval = 4000;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_splashscreen);
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Intent i = new Intent(Splashscreen.this, MainActivity.class);
				startActivity(i);
				
				//jeda slesai splash
				this.finish();
			}

			private void finish() {
				// TODO Auto-generated method stub
				
			}
		},splashInterval);
		
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splashscreen, menu);
		return true;
	}

}
