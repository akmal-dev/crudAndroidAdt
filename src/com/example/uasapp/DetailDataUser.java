package com.example.uasapp;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DetailDataUser extends Activity {
	protected Cursor cursor;
	Database dbhelper;
	EditText id,nama_depan,nama_belakang,username,password;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail_data_user);
		dbhelper= new Database(this);
		
		id = (EditText)findViewById(R.id.editText1);
		nama_depan = (EditText)findViewById(R.id.editText2);
		nama_belakang = (EditText)findViewById(R.id.editText3);
		username = (EditText)findViewById(R.id.editText4);
		password= (EditText)findViewById(R.id.editText5);
		
		SQLiteDatabase db = dbhelper.getReadableDatabase();
		cursor = db.rawQuery("select * from login where username ='"+getIntent().getStringExtra("username")+"' ", null);
		cursor.moveToFirst();
		
		if (cursor.getCount()>0) {
			id.setText(cursor.getString(0).toString());
			nama_depan.setText(cursor.getString(1).toString());
			nama_belakang.setText(cursor.getString(2).toString());
			username.setText(cursor.getString(3).toString());
			password.setText(cursor.getString(4).toString());
		}
	}
	
	public void Dashboard (View view){
		Intent i = new Intent (DetailDataUser.this, Dashboard.class);
		startActivity(i);
	}
	
	public void TampilUser (View view){
		Intent i = new Intent (DetailDataUser.this, DataUser.class);
		startActivity(i);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detail_data_user, menu);
		return true;
	}

}
