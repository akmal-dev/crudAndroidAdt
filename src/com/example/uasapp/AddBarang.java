package com.example.uasapp;


import java.util.List;


import com.example.uasapp.Barang;


import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class AddBarang extends Activity implements OnItemSelectedListener {
	Spinner spinner;
	protected Cursor cursor;
	Database dbhelper;
	Button btnSimpan;
	EditText kode,nama,jenis,stok,harga_beli,harga_jual;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_barang);
		
		dbhelper = new Database(this);
		
		kode = (EditText)findViewById(R.id.editText1);
		nama = (EditText)findViewById(R.id.editText2);
		stok = (EditText)findViewById(R.id.editText4);
		harga_beli = (EditText)findViewById(R.id.editText5);
		harga_jual = (EditText)findViewById(R.id.editText6);
		spinner = (Spinner) findViewById(R.id.spinner1);
		spinner.setOnItemSelectedListener(this);
		loadSpinnerData();
		
		btnSimpan = (Button)findViewById(R.id.btnSimpan);
		btnSimpan.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SQLiteDatabase db  = dbhelper.getReadableDatabase();
				db.execSQL("insert into barang (kode_barang,nama_barang,jenis_barang,stok,harga_beli,harga_jual) values " +
						"('"+kode.getText().toString()+"', '"+nama.getText().toString()+"', '"+spinner.getSelectedItem().toString()+"', '"+stok.getText().toString()+"','"+harga_beli.getText().toString()+"', '"+harga_jual.getText().toString()+"')");
				Toast.makeText(getApplicationContext(), "Berhasil Disimpan", Toast.LENGTH_LONG).show();
				Barang.ma.RefreshList();
				finish();
			}
		});
	}
	
	private void loadSpinnerData() {
		// TODO Auto-generated method stub
		dbhelper = new Database(this);
		List<String> lables = dbhelper.getAllLabels();

		// Creating adapter for spinner
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, lables);

		// Drop down layout style - list view with radio button
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// attaching data adapter to spinner
		spinner.setAdapter(dataAdapter);
	}

	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_barang, menu);
		return true;
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}
	
	public void Dashboard (View view){
		Intent i = new Intent (AddBarang.this, Dashboard.class);
		startActivity(i);
	}
	
	public void TampilBarang (View view){
		Intent i = new Intent (AddBarang.this, Barang.class);
		startActivity(i);
	}

}
