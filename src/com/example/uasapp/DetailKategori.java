package com.example.uasapp;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.EditText;

public class DetailKategori extends Activity {
	
	protected Cursor cursor;
	Database dbhelper;
	EditText id,nama;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail_kategori);
		
		dbhelper = new Database(this);
		
		id = (EditText)findViewById(R.id.editText1);
		nama = (EditText)findViewById(R.id.editText2);
		
		SQLiteDatabase db = dbhelper.getReadableDatabase();
		cursor = db.rawQuery("select * from kategori where nama_kategori='"+getIntent().getStringExtra("nama_kategori")+"' ", null);
		cursor.moveToFirst();
		if (cursor.getCount()>0) {
			cursor.moveToPosition(0);
			id.setText(cursor.getString(0).toString());
			nama.setText(cursor.getString(1).toString());
		}
	}
	
	public void Dashboard (View view){
		Intent i = new Intent (DetailKategori.this, Dashboard.class);
		startActivity(i);
	}
	
	public void TampilKategori (View view){
		Intent i = new Intent (DetailKategori.this, Kategori.class);
		startActivity(i);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detail_kategori, menu);
		return true;
	}

}
