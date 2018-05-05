package com.example.testrgb;

import android.app.Activity;
import android.os.Bundle;

import android.view.View;

import android.view.View.OnTouchListener;
import android.view.MotionEvent;

import android.widget.Button;

public class MainInfo extends Activity implements OnTouchListener{
	
	private Button buttonInfoBack;
	
	protected void onCreate(Bundle saves){
		super.onCreate(saves);
		setContentView(R.layout.layout_info);
		
		init();
	}
	
	//initialization widgets
	public void init(){
		
		buttonInfoBack = (Button)findViewById(R.id.buttonInfoBack);
		buttonInfoBack.setOnTouchListener(this);
		
	}
	
	//realize onTouchListener method
	public boolean onTouch(View view, MotionEvent event){
		
		//back to main menu
		if(event.getAction() == MotionEvent.ACTION_DOWN)
			if(view.getId() == R.id.buttonInfoBack)
				finish();
		
		return false;
	}
	
}