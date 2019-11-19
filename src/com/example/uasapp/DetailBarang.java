package com.example.uasapp;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.EditText;

public class DetailBarang extends Activity {
	
	protected Cursor cursor;
	Database dbhelper;
	EditText kode,nama,jenis,stok,harga_beli,harga_jual;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail_barang);
		
		dbhelper = new Database(this);
		
		kode = (EditText)findViewById(R.id.editText1);
		nama = (EditText)findViewById(R.id.editText2);
		jenis = (EditText)findViewById(R.id.editText3);
		stok = (EditText)findViewById(R.id.editText4);
		harga_beli = (EditText)findViewById(R.id.editText5);
		harga_jual = (EditText)findViewById(R.id.editText6);
		
		SQLiteDatabase db = dbhelper.getReadableDatabase();
		cursor = db.rawQuery("select * from barang where nama_barang='"+getIntent().getStringExtra("nama_barang")+"' ", null);
		cursor.moveToFirst();
		if (cursor.getCount()>0) {
			cursor.moveToPosition(0);
			kode.setText(cursor.getString(0).toString());
			nama.setText(cursor.getString(1).toString());
			jenis.setText(cursor.getString(2).toString());
			stok.setText(cursor.getString(3).toString());
			harga_beli.setText(cursor.getString(4).toString());
			harga_jual.setText(cursor.getString(5).toString());
		}
	}
	
	public void Dashboard (View view){
		Intent i = new Intent (DetailBarang.this, Dashboard.class);
		startActivity(i);
	}
	
	public void TampilBarang (View view){
		Intent i = new Intent (DetailBarang.this, Barang.class);
		startActivity(i);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detail_barang, menu);
		return true;
	}

}
