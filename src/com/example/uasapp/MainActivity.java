package com.example.uasapp;



import android.os.Bundle;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	TextView judul,txtusername,txtpassword;
	EditText edituser,editpass;
	Button login;
	Database dbhelper;
	Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        dbhelper = new Database(this);
        
        edituser = (EditText)findViewById(R.id.editText1);
        editpass = (EditText)findViewById(R.id.editText2);
        
        //font
        txtusername = (TextView)findViewById(R.id.textView2);
        Typeface customfont1=Typeface.createFromAsset(getAssets(),"font/nucleo-outline.ttf");
        txtusername.setTypeface(customfont1);
        txtpassword = (TextView)findViewById(R.id.textView3);
        customfont1=Typeface.createFromAsset(getAssets(),"font/nucleo-outline.ttf");
        txtpassword.setTypeface(customfont1);
  
    }
    public void cekLogin (View view){
    	String user = edituser.getText().toString();
    	String pass = editpass.getText().toString();
    	
    	SQLiteDatabase db= dbhelper.getReadableDatabase();
    	String sql = "Select * from login where username='"+user+"' and password='"+pass+"'  ";
    	cursor= db.rawQuery(sql, null);
    	
    	if (cursor.getCount()>0) {
    		Intent i = new Intent (MainActivity.this, Dashboard.class);
			startActivity(i);
    		
		}
    	else{
    		Toast.makeText(getApplicationContext(), "Gagal Login", Toast.LENGTH_LONG).show();
    	}
    	finish();
    }
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
