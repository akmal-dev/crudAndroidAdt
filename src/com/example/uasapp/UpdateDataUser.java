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

public class UpdateDataUser extends Activity {
	protected Cursor cursor;
	Database dbhelper;
	Button btnUpdate;
	EditText id,nama_depan,nama_belakang,username,password;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update_data_user);
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
		btnUpdate = (Button)findViewById(R.id.btnUpdate);
		btnUpdate.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SQLiteDatabase db = dbhelper.getWritableDatabase();
				db.execSQL("update login set nama_depan='"+nama_depan.getText().toString()+"',nama_belakang='"+nama_belakang.getText().toString()+"',username='"+username.getText().toString()+"', password='"+password.getText().toString()+"' where id= '"+id.getText().toString()+"' ");
				Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
				DataUser.ma.RefreshList();
				finish();
			}
		});
	}
	
	public void Dashboard (View view){
		Intent i = new Intent (UpdateDataUser.this, Dashboard.class);
		startActivity(i);
	}
	
	public void TampilUser (View view){
		Intent i = new Intent (UpdateDataUser.this, DataUser.class);
		startActivity(i);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.update_data_user, menu);
		return true;
	}

}
