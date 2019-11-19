package com.example.uasapp;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.app.AlertDialog.Builder;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

public class DataUser extends Activity {
	public static DataUser ma;
	ImageView add;
	
	String[] daftar;
	ListView list;
	protected Cursor cursor;
	Database dbhelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_data_user);
		dbhelper = new Database(this);
		
		add = (ImageView)findViewById(R.id.add);
		add.setOnClickListener(new View.OnClickListener() {		
			@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent i = new Intent(DataUser.this, AddDataUser.class);
				startActivity(i);
		}
		});
		ma = this;
		dbhelper = new Database(this);
		RefreshList();
	}
	public void Dashboard (View view){
		Intent i = new Intent(DataUser.this, Dashboard.class);
		startActivity(i);
	}

	public void RefreshList() {
		// TODO Auto-generated method stub
		SQLiteDatabase db = dbhelper.getReadableDatabase();
		
		cursor = db.rawQuery("select * from login", null);
		daftar = new String[cursor.getCount()];
		cursor.moveToFirst();
		
		for(int i=0; i<cursor.getCount(); i++) {
			cursor.moveToPosition(i);
			daftar[i]= cursor.getString(3).toString();
		}
		list=(ListView)findViewById(R.id.listView1);
		list.setAdapter(new ArrayAdapter<Object>(this, android.R.layout.simple_list_item_1, daftar));
		list.setSelected(true);
		
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				String pilih = daftar[arg2];
				
				Intent i = new Intent(DataUser.this, DetailDataUser.class);
				i.putExtra("username", pilih);
				startActivity(i);
			}
		});
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				final String selection = daftar[arg2];
				
				final CharSequence[]dialogitem= {"Lihat","Update"};
				
				AlertDialog.Builder builder= new AlertDialog.Builder(DataUser.this);
				builder.setTitle("Pilihan");
				
				builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int item) {
						// TODO Auto-generated method stub
						switch (item) {
						case 0:
							Intent i  = new Intent(getApplicationContext(),DetailDataUser.class);
							i.putExtra("username", selection);
							startActivity(i);
							break;
							
						case 1:
							Intent in  = new Intent(getApplicationContext(),UpdateDataUser.class);
							in.putExtra("username", selection);
							startActivity(in);
						break;
						}
					}
				});
				builder.create().show();
			}
			
		});
		((ArrayAdapter)list.getAdapter()).notifyDataSetInvalidated();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.data_user, menu);
		return true;
	}

}
