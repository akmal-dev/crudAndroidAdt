package com.example.uasapp;

import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.database.Cursor;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper{
	public static final String Nama="Uas.db";
	public static final Integer Versi=1;

	public Database(Context context) {
		super(context, Nama, null, Versi);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String sql = "create table login (id integer primary key, nama_depan text null, nama_belakang text null, username text null, password text null)";
		db.execSQL(sql);
		sql = "create table kategori (id_kategori integer primary key,nama_kategori text null)";
		db.execSQL(sql);
		sql = "create table barang(kode_barang text primary key, nama_barang text, jenis_barang text, stok integer, harga_beli integer,harga_jual integer )";
		db.execSQL(sql);
		
		sql = "insert into login (id,nama_depan,nama_belakang,username,password) values(1,'Jaya','Prana', 'admin','admin123')";
		db.execSQL(sql);
		sql= "insert into kategori (id_kategori,nama_kategori) values (1,'Snack')";
		db.execSQL(sql);
		sql= "insert into kategori (id_kategori,nama_kategori) values (2,'Makanan')";
		db.execSQL(sql);
		sql= "insert into kategori (id_kategori,nama_kategori) values (3,'Minuman')";
		db.execSQL(sql);
		sql = "insert into barang values ('BR001','Beng-Beng','Snack',10,24500,28000)";
		db.execSQL(sql);
		sql = "insert into barang values ('BR002','Top','Snack',5,14500,18000)";
		db.execSQL(sql);
		sql = "insert into barang values ('BR003','Mie Sedaap','Makanan',2,21500,28000)";
		db.execSQL(sql);
		sql = "insert into barang values ('BR004','Indomie','Makanan',6,17500,19000)";
		db.execSQL(sql);
		sql = "insert into barang values ('BR005','Aqua','Minuman',7,24000,25000)";
		db.execSQL(sql);
		sql = "insert into barang values ('BR006','Le Minerale','Minuman',15,22500,25000)";
		db.execSQL(sql);
		sql = "insert into barang values ('BR007','Vit','Minuman',8,21500,24000)";
		db.execSQL(sql);
		sql = "insert into barang values ('BR008','Ades','Minuman',4,23000,27000)";
		db.execSQL(sql);
		sql = "insert into barang values ('BR009','Coca-cola','Minuman',6,18500,22000)";
		db.execSQL(sql);
		sql = "insert into barang values ('BR010','Sprite','Minuman',10,17500,19000)";
		db.execSQL(sql);
	}
	

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
	public void insertLabel (String label){
		SQLiteDatabase db = this.getWritableDatabase();
    	
    	ContentValues values = new ContentValues();
    	values.put("nama_kategori", label);
    	 
    	// Inserting Row
        db.insert("kategori", null, values);
        db.close(); //
		
	}
	public List<String> getAllLabels(){
    	List<String> labels = new ArrayList<String>();
    	
        // Select All Query
        String selectQuery = "SELECT  * FROM kategori" ;
     
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
     
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	labels.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        
        // closing connection
        cursor.close();
        db.close();
    	
    	// returning lables
    	return labels;
    }

}
