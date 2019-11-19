package com.example.uasapp;

import com.example.uasapp.Kategori;

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

public class AddKategori extends Activity {
	protected Cursor cursor;
	Database dbhelper;
	Button btnSimpan;
	EditText id,nama;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_kategori);
		dbhelper = new Database(this);
		
		id = (EditText)findViewById(R.id.editText1);
		nama = (EditText)findViewById(R.id.editText2);
		
		btnSimpan=(Button)findViewById(R.id.btnSimpan);
		btnSimpan.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				SQLiteDatabase db=dbhelper.getReadableDatabase();
				db.execSQL("insert into kategori(id_kategori,nama_kategori) values "+ 
				"('"+id.getText().toString()+"','"+nama.getText().toString()+"')");
				Toast.makeText(getApplicationContext(), "Berhasil Disimpan", Toast.LENGTH_LONG).show();
				Kategori.ma.RefreshList();
				finish();
			}
		});
	}
	
	public void Dashboard (View view){
		Intent i = new Intent (AddKategori.this, Dashboard.class);
		startActivity(i);
	}
	
	public void TampilKategori (View view){
		Intent i = new Intent (AddKategori.this, Kategori.class);
		startActivity(i);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_kategori, menu);
		return true;
	}

}
