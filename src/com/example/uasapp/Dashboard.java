package com.example.uasapp;

import android.os.Bundle;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class Dashboard extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dashboard);

	}
	
	public void TampilBarang (View view){
		Intent i = new Intent (Dashboard.this, Barang.class);
		startActivity(i);
	}
	
	public void TampilKategori (View view){
		Intent i = new Intent(Dashboard.this, Kategori.class);
		startActivity(i);
	}
	public void TampilUser (View view){
		Intent i = new Intent(Dashboard.this, DataUser.class);
		startActivity(i);
	}
	
	public void TampilTeam (View view){
		Intent i = new Intent(Dashboard.this, Team.class);
		startActivity(i);
	}
	
	
	public void Logout(View view){
		Intent i = new Intent (Dashboard.this, MainActivity.class);
		startActivity(i);
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.dashboard, menu);
		return true;
	}

}
