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

public class UpdateKategori extends Activity {
	protected Cursor cursor;
	Database dbhelper;
	Button btnUpdate;
	EditText id,nama;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update_kategori);
		
		dbhelper= new Database(this);
		id = (EditText)findViewById(R.id.editText1);
		nama = (EditText)findViewById(R.id.editText2);
		
		SQLiteDatabase db = dbhelper.getReadableDatabase();
		cursor = db.rawQuery("select * from kategori where nama_kategori ='"+getIntent().getStringExtra("nama_kategori")+"' ", null);
		cursor.moveToFirst();
		
		if (cursor.getCount()>0) {
			id.setText(cursor.getString(0).toString());
			nama.setText(cursor.getString(1).toString());
		}
		
		btnUpdate = (Button)findViewById(R.id.btnUpdate);
		btnUpdate.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SQLiteDatabase db = dbhelper.getWritableDatabase();
				db.execSQL("update kategori set nama_kategori='"+nama.getText().toString()+"' where id_kategori= '"+id.getText().toString()+"' ");
				Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
				Kategori.ma.RefreshList();
				finish();
			}
		});
	}
	public void Dashboard (View view){
		Intent i = new Intent (UpdateKategori.this, Dashboard.class);
		startActivity(i);
	}
	
	public void TampilKategori (View view){
		Intent i = new Intent (UpdateKategori.this, Kategori.class);
		startActivity(i);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.update_kategori, menu);
		return true;
	}

}
