package com.example.uasapp;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UpdateBarang extends Activity implements OnItemSelectedListener {
	Spinner spinner;
	protected Cursor cursor;
	Database dbhelper;
	Button btnUpdate;
	EditText kode,nama,jenis,stok,harga_beli,harga_jual;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update_barang);
		
		dbhelper= new Database(this);
		kode = (EditText)findViewById(R.id.editText1);
		nama = (EditText)findViewById(R.id.editText2);
		jenis = (EditText)findViewById(R.id.editText3);
		stok = (EditText)findViewById(R.id.editText4);
		harga_beli = (EditText)findViewById(R.id.editText5);
		harga_jual = (EditText)findViewById(R.id.editText6);
		spinner = (Spinner) findViewById(R.id.spinner1);
		spinner.setOnItemSelectedListener(this);
		loadSpinnerData();
		
		SQLiteDatabase db = dbhelper.getReadableDatabase();
		cursor = db.rawQuery("select * from barang where nama_barang ='"+getIntent().getStringExtra("nama_barang")+"' ", null);
		cursor.moveToFirst();
		
		if (cursor.getCount()>0) {
			kode.setText(cursor.getString(0).toString());
			nama.setText(cursor.getString(1).toString());
			spinner.setSelected(true);
			stok.setText(cursor.getString(3).toString());
			harga_beli.setText(cursor.getString(4).toString());
			harga_jual.setText(cursor.getString(5).toString());
		}
		
		btnUpdate = (Button)findViewById(R.id.btnUpdate);
		btnUpdate.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SQLiteDatabase db = dbhelper.getWritableDatabase();
				db.execSQL("update barang set nama_barang='"+nama.getText().toString()+"',jenis_barang='"+spinner.getSelectedItem().toString()+"',stok='"+stok.getText().toString()+"', harga_beli='"+harga_beli.getText().toString()+"', harga_jual='"+harga_jual.getText().toString()+"' where kode_barang= '"+kode.getText().toString()+"' ");
				Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
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

	public void Dashboard (View view){
		Intent i = new Intent (UpdateBarang.this, Dashboard.class);
		startActivity(i);
	}
	
	public void TampilBarang (View view){
		Intent i = new Intent (UpdateBarang.this, Barang.class);
		startActivity(i);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.update_barang, menu);
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

}
