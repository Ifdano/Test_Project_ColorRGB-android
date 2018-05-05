package com.example.testrgb;

import android.app.Activity;
import android.os.Bundle;

import android.view.View;

import android.view.View.OnTouchListener;
import android.view.MotionEvent;

import android.widget.EditText;
import android.widget.Button;
import android.widget.LinearLayout;

import android.graphics.Color;

import android.content.Context;
import android.content.SharedPreferences;

public class MainSearch extends Activity implements OnTouchListener{
	
	//set key's for save values
	public static final String KEY_SEARCH = "search";
	
	public static final String TEMP_HINT = "tempHint";
	public static final String TEMP_TEXT_RGB = "tempRGB";
	
	private LinearLayout layoutSearch;
	
	private EditText editSearch;
	
	private Button buttonSearchBack;
	private Button buttonSearchCheck;
	
	private String tempEdit;
	private String tempHint;
	
	private SharedPreferences rgbSettings;
	
	protected void onCreate(Bundle saves){
		super.onCreate(saves);
		setContentView(R.layout.layout_search);
		
		init();
	}
	
	//initialization widgets and others
	public void init(){
		
		//set main KEY settings
		rgbSettings = getSharedPreferences(KEY_SEARCH, Context.MODE_PRIVATE);
		
		layoutSearch = (LinearLayout)findViewById(R.id.layoutSearch);
		
		editSearch = (EditText)findViewById(R.id.editSearch);
		
		buttonSearchBack = (Button)findViewById(R.id.buttonSearchBack);
		buttonSearchCheck = (Button)findViewById(R.id.buttonSearchCheck);
		
		//set all Listeners
		buttonSearchBack.setOnTouchListener(this);
		buttonSearchCheck.setOnTouchListener(this);
		
		//set default values
		tempEdit = "#000000";
		
	}
	
	protected void onPause(){
		super.onPause();
		
		//save values
		SharedPreferences.Editor editor = rgbSettings.edit();
		
		editor.putString(TEMP_HINT, tempHint);
		editor.putString(TEMP_TEXT_RGB, tempEdit);
		
		editor.apply();
	}
	
	protected void onStop(){
		super.onStop();
		
		//save values
		SharedPreferences.Editor editor = rgbSettings.edit();
		
		editor.putString(TEMP_HINT, tempHint);
		editor.putString(TEMP_TEXT_RGB, tempEdit);
		
		editor.apply();
	}
	
	protected void onDestroy(){
		super.onDestroy();
		
		//save values
		SharedPreferences.Editor editor = rgbSettings.edit();
		
		editor.putString(TEMP_HINT, tempHint);
		editor.putString(TEMP_TEXT_RGB, tempEdit);
		
		editor.apply();
	}
	
	protected void onStart(){
		super.onStart();
		
		//get and set saved values
		if(rgbSettings.contains(TEMP_HINT)){
			tempHint = rgbSettings.getString(TEMP_HINT, "");
			editSearch.setHint(tempHint);
			
			if(rgbSettings.contains(TEMP_TEXT_RGB)){
				
				if(tempHint.charAt(0) == '#'){
					tempEdit = rgbSettings.getString(TEMP_TEXT_RGB, "");
					editSearch.setText(tempEdit);
					layoutSearch.setBackgroundColor(Color.parseColor(tempEdit));
				}
				
				if(tempHint.charAt(0) == 'W'){
					tempEdit = rgbSettings.getString(TEMP_TEXT_RGB, "");
					editSearch.setText(tempEdit);
				}
				
			}
		}
		
	}
	
	protected void onResume(){
		super.onResume();

		//get and set saved values
		if(rgbSettings.contains(TEMP_HINT)){
			tempHint = rgbSettings.getString(TEMP_HINT, "");
			editSearch.setHint(tempHint);
			
			if(rgbSettings.contains(TEMP_TEXT_RGB)){
				
				if(tempHint.charAt(0) == '#'){
					tempEdit = rgbSettings.getString(TEMP_TEXT_RGB, "");
					editSearch.setText(tempEdit);
					layoutSearch.setBackgroundColor(Color.parseColor(tempEdit));
				}
				
				if(tempHint.charAt(0) == 'W'){
					tempEdit = rgbSettings.getString(TEMP_TEXT_RGB, "");
					editSearch.setText(tempEdit);
				}
				
			}
		}
		
	}
	
	//realize onTouchListener method
	public boolean onTouch(View view, MotionEvent event){
		
		if(event.getAction() == MotionEvent.ACTION_DOWN){
			
			//back to main menu
			if(view.getId() == R.id.buttonSearchBack)
				finish();
			
			//check code
			if(view.getId() == R.id.buttonSearchCheck){
				
				tempEdit = editSearch.getText().toString();
				
				if(tempEdit.length() == 7){
					
					if(tempEdit.charAt(0) == '#' && 
					(tempEdit.charAt(1)>='0' && tempEdit.charAt(1)<='9' || 
					tempEdit.charAt(1)>='A' && tempEdit.charAt(1)<='F') &&
					(tempEdit.charAt(2)>='0' && tempEdit.charAt(2)<='9' ||
					tempEdit.charAt(2)>='A' && tempEdit.charAt(2)<='F') &&
					(tempEdit.charAt(3)>='0' && tempEdit.charAt(3)<='9' ||
					tempEdit.charAt(3)>='A' && tempEdit.charAt(3)<='F') &&
					(tempEdit.charAt(4)>='0' && tempEdit.charAt(4)<='9' ||
					tempEdit.charAt(4)>='A' && tempEdit.charAt(4)<='F') &&
					(tempEdit.charAt(5)>='0' && tempEdit.charAt(5)<='9' ||
					tempEdit.charAt(5)>='A' && tempEdit.charAt(5)<='F') &&
					(tempEdit.charAt(6)>='0' && tempEdit.charAt(6)<='9' ||
					tempEdit.charAt(6)>='A' && tempEdit.charAt(6)<='F')
						){
					layoutSearch.setBackgroundColor(Color.parseColor(tempEdit));
					editSearch.setHint("#RGB");
					tempHint = "#RGB";
					
				}else{
					editSearch.setText("");
					editSearch.setHint("Wrong code");
					tempHint = "Wrong code";
				}
	
			}else{
				editSearch.setText("");
				editSearch.setHint("Wrong code");
				tempHint = "Wrong code";
			}
		
		  }
		
		}
		return false;
	}
	
}