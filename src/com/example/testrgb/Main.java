package com.example.testrgb;

import android.app.Activity;
import android.os.Bundle;

import android.view.View;

import android.view.View.OnTouchListener;
import android.view.MotionEvent;

import android.widget.Button;

import android.content.Intent;

public class Main extends Activity implements OnTouchListener{
	
	private Button buttonPicker;
	private Button buttonSearch;
	private Button buttonInfo;
	
	private Intent intent;
	
	protected void onCreate(Bundle saves){
		super.onCreate(saves);
		setContentView(R.layout.activity_main);
		
		init();
	}
	
	//initialization widgets
	public void init(){
		
		buttonPicker = (Button)findViewById(R.id.buttonMainPicker);
		buttonSearch = (Button)findViewById(R.id.buttonMainSearch);
		buttonInfo = (Button)findViewById(R.id.buttonMainInfo);
		
		//set TouchListener's
		buttonPicker.setOnTouchListener(this);
		buttonSearch.setOnTouchListener(this);
		buttonInfo.setOnTouchListener(this);
		
	}
	
	//realize onTouchListener method
	public boolean onTouch(View view, MotionEvent event){
		
		if(event.getAction() == MotionEvent.ACTION_DOWN){
			
			if(view.getId() == R.id.buttonMainPicker)
				intent = new Intent(this, MainPicker.class);
		
			if(view.getId() == R.id.buttonMainSearch)
				intent = new Intent(this, MainSearch.class);
			
			if(view.getId() == R.id.buttonMainInfo)
				intent = new Intent(this, MainInfo.class);
				
			startActivity(intent);	
			
		}
		
		return false;
	}
	
}