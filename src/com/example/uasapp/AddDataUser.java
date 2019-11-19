package com.example.uasapp;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class AddDataUser extends Activity {
	protected Cursor cursor;
	Database dbhelper;
	Button btnSimpan;
	EditText id,nama_depan,nama_belakang,username,password;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_data_user);
		
		dbhelper = new Database(this);
		
		id = (EditText)findViewById(R.id.editText1);
		nama_depan = (EditText)findViewById(R.id.editText2);
		nama_belakang = (EditText)findViewById(R.id.editText3);
		username = (EditText)findViewById(R.id.editText4);
		password= (EditText)findViewById(R.id.editText5);
		
		btnSimpan = (Button)findViewById(R.id.btnSimpan);
		btnSimpan.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SQLiteDatabase db  = dbhelper.getReadableDatabase();
				db.execSQL("insert into login (id,nama_depan,nama_belakang,username,password) values " +
						"('"+id.getText().toString()+"','"+nama_depan.getText().toString()+"','"+nama_belakang.getText().toString()+"','"+username.getText().toString()+"','"+password.getText().toString()+"')");
				Toast.makeText(getApplicationContext(), "Berhasil Disimpan", Toast.LENGTH_LONG).show();
				DataUser.ma.RefreshList();
				finish();
			}
		});
	}
	
	public void Dashboard (View view){
		Intent i = new Intent (AddDataUser.this, Dashboard.class);
		startActivity(i);
	}
	
	public void TampilUser (View view){
		Intent i = new Intent (AddDataUser.this, DataUser.class);
		startActivity(i);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_data_user, menu);
		return true;
	}

}
