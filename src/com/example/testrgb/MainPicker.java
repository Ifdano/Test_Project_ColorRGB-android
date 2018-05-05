package com.example.testrgb;

import android.app.Activity;
import android.os.Bundle;

import android.view.View;

import android.view.View.OnTouchListener;
import android.view.MotionEvent;

import android.widget.Button;
import android.widget.TextView;
import android.widget.SeekBar;
import android.widget.LinearLayout;

import android.widget.SeekBar.OnSeekBarChangeListener;

import android.graphics.Color;

import android.content.Context;
import android.content.SharedPreferences;

public class MainPicker extends Activity implements OnTouchListener, OnSeekBarChangeListener{
	
	//set key's for save values
	public static final String KEY_PICKER = "picker";
	
	public static final String TEMP_SEEK1 = "tempSeek1";
	public static final String TEMP_SEEK2 = "tempSeek2";
	public static final String TEMP_SEEK3 = "tempSeek3";
	
	public static final String TEMP_PROGRESS1 = "tempProgress1";
	public static final String TEMP_PROGRESS2 = "tempProgress2";
	public static final String TEMP_PROGRESS3 = "tempProgress3";
	
	private LinearLayout layoutPickerMain;
	
	private LinearLayout layoutPicker1;
	private LinearLayout layoutPicker2;
	private LinearLayout layoutPicker3;
	
	private SeekBar seekPicker1;
	private SeekBar seekPicker2;
	private SeekBar seekPicker3;
	
	private Button buttonPickerBack;
	
	private TextView textPicker;
	
	private String tempSeek1;
	private String tempSeek2;
	private String tempSeek3;
	private String tempSeekMain;
	
	private int tempProgress1;
	private int tempProgress2;
	private int tempProgress3;
	
	private SharedPreferences rgbSettings; 
	
	protected void onCreate(Bundle saves){
		super.onCreate(saves);
		setContentView(R.layout.layout_picker);
		
		init();
	}
	
	//initialization widgets and others
	public void init(){
		
		//set main KEY settings
		rgbSettings = getSharedPreferences(KEY_PICKER, Context.MODE_PRIVATE);
		
		layoutPickerMain = (LinearLayout)findViewById(R.id.layoutPicker);
		
		layoutPicker1 = (LinearLayout)findViewById(R.id.layoutPicker1);
		layoutPicker2 = (LinearLayout)findViewById(R.id.layoutPicker2);
		layoutPicker3 = (LinearLayout)findViewById(R.id.layoutPicker3);
		
		seekPicker1 = (SeekBar)findViewById(R.id.seekPicker1);
		seekPicker2 = (SeekBar)findViewById(R.id.seekPicker2);
		seekPicker3 = (SeekBar)findViewById(R.id.seekPicker3);
		
		buttonPickerBack = (Button)findViewById(R.id.buttonPickerBack);
		
		textPicker = (TextView)findViewById(R.id.textPicker);
		
		//set all Listeners
		buttonPickerBack.setOnTouchListener(this);
		
		seekPicker1.setOnSeekBarChangeListener(this);
		seekPicker2.setOnSeekBarChangeListener(this);
		seekPicker3.setOnSeekBarChangeListener(this);
		
		//set default values
		tempSeek1 = "00";
		tempSeek2 = "00";
		tempSeek3 = "00";
		tempSeekMain = "";
		
		seekPicker1.setProgress(0);
		seekPicker2.setProgress(0);
		seekPicker3.setProgress(0);
		
		tempProgress1 = 0;
		tempProgress2 = 0;
		tempProgress3 = 0;
	}
	
	protected void onPause(){
		super.onPause();
		
		//save values
		SharedPreferences.Editor editor = rgbSettings.edit();
		
		editor.putString(TEMP_SEEK1, tempSeek1);
		editor.putString(TEMP_SEEK2, tempSeek2);
		editor.putString(TEMP_SEEK3, tempSeek3);
		
		editor.putInt(TEMP_PROGRESS1, tempProgress1);
		editor.putInt(TEMP_PROGRESS2, tempProgress2);
		editor.putInt(TEMP_PROGRESS3, tempProgress3);
		
		editor.apply();
	}
	
	protected void onStop(){
		super.onStop();
		
		//save values
		SharedPreferences.Editor editor = rgbSettings.edit();
		
		editor.putString(TEMP_SEEK1, tempSeek1);
		editor.putString(TEMP_SEEK2, tempSeek2);
		editor.putString(TEMP_SEEK3, tempSeek3);
		
		
		editor.putInt(TEMP_PROGRESS1, tempProgress1);
		editor.putInt(TEMP_PROGRESS2, tempProgress2);
		editor.putInt(TEMP_PROGRESS3, tempProgress3);
		
		editor.apply();
	}
	
	protected void onDestroy(){
		super.onDestroy();
		
		//save values
		SharedPreferences.Editor editor = rgbSettings.edit();
		
		editor.putString(TEMP_SEEK1, tempSeek1);
		editor.putString(TEMP_SEEK2, tempSeek2);
		editor.putString(TEMP_SEEK3, tempSeek3);
			
		editor.putInt(TEMP_PROGRESS1, tempProgress1);
		editor.putInt(TEMP_PROGRESS2, tempProgress2);
		editor.putInt(TEMP_PROGRESS3, tempProgress3);
		
		editor.apply();
	}
	
	protected void onStart(){
		super.onStart();
		
		//get saved values
		if(rgbSettings.contains(TEMP_SEEK1)){
			tempSeek1 = rgbSettings.getString(TEMP_SEEK1, "00");
			layoutPicker1.setBackgroundColor(Color.parseColor("#" + tempSeek1 + "0000"));
		}
		
		if(rgbSettings.contains(TEMP_SEEK2)){
			tempSeek2 = rgbSettings.getString(TEMP_SEEK2, "00");
			layoutPicker2.setBackgroundColor(Color.parseColor("#00" + tempSeek2 + "00"));
		}
		
		if(rgbSettings.contains(TEMP_SEEK3)){
			tempSeek3 = rgbSettings.getString(TEMP_SEEK3, "00");
			layoutPicker3.setBackgroundColor(Color.parseColor("#0000" + tempSeek3));
		}
		
		if(rgbSettings.contains(TEMP_PROGRESS1)){
			tempProgress1 = rgbSettings.getInt(TEMP_PROGRESS1, 0);
			seekPicker1.setProgress(tempProgress1);
		}
		
		if(rgbSettings.contains(TEMP_PROGRESS2)){
			tempProgress2 = rgbSettings.getInt(TEMP_PROGRESS2, 0);
			seekPicker2.setProgress(tempProgress2);
		}
		
		if(rgbSettings.contains(TEMP_PROGRESS3)){
			tempProgress3 = rgbSettings.getInt(TEMP_PROGRESS3, 0);
			seekPicker3.setProgress(tempProgress3);
		}
		
		//set saved values
		tempSeekMain = tempSeek1 + tempSeek2 + tempSeek3;
		textPicker.setText("#" + tempSeekMain);
		layoutPickerMain.setBackgroundColor(Color.parseColor("#" + tempSeekMain));
		
	}
	
	protected void onResume(){
		super.onResume();
		
		//get saved values
		if(rgbSettings.contains(TEMP_SEEK1)){
			tempSeek1 = rgbSettings.getString(TEMP_SEEK1, "00");
			layoutPicker1.setBackgroundColor(Color.parseColor("#" + tempSeek1 + "0000"));
		}
		
		if(rgbSettings.contains(TEMP_SEEK2)){
			tempSeek2 = rgbSettings.getString(TEMP_SEEK2, "00");
			layoutPicker2.setBackgroundColor(Color.parseColor("#00" + tempSeek2 + "00"));
		}
		
		if(rgbSettings.contains(TEMP_SEEK3)){
			tempSeek3 = rgbSettings.getString(TEMP_SEEK3, "00");
			layoutPicker3.setBackgroundColor(Color.parseColor("#0000" + tempSeek3));
		}
		
		if(rgbSettings.contains(TEMP_PROGRESS1)){
			tempProgress1 = rgbSettings.getInt(TEMP_PROGRESS1, 0);
			seekPicker1.setProgress(tempProgress1);
		}
		
		if(rgbSettings.contains(TEMP_PROGRESS2)){
			tempProgress2 = rgbSettings.getInt(TEMP_PROGRESS2, 0);
			seekPicker2.setProgress(tempProgress2);
		}
		
		if(rgbSettings.contains(TEMP_PROGRESS3)){
			tempProgress3 = rgbSettings.getInt(TEMP_PROGRESS3, 0);
			seekPicker3.setProgress(tempProgress3);
		}
		
		//set saved values
		tempSeekMain = tempSeek1 + tempSeek2 + tempSeek3;
		textPicker.setText("#" + tempSeekMain);
		layoutPickerMain.setBackgroundColor(Color.parseColor("#" + tempSeekMain));
		
	}
	
	//realize onTouchListener method
	public boolean onTouch(View view, MotionEvent event){
		
		//back to main menu
		if(event.getAction() == MotionEvent.ACTION_DOWN)
			if(view.getId() == R.id.buttonPickerBack)
				finish();
		
		return false;
	}
	
	//realize SeekBarChangeListener
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
		
		//get values from progress and convert to hexadecimal
		if(seekBar == seekPicker1){
			tempProgress1 = progress;
			tempSeek1 = getHexadecimal(tempProgress1);
			layoutPicker1.setBackgroundColor(Color.parseColor("#" + tempSeek1 + "0000"));
		}
		
		if(seekBar == seekPicker2){
			tempProgress2 = progress;
			tempSeek2 = getHexadecimal(tempProgress2);
			layoutPicker2.setBackgroundColor(Color.parseColor("#00" + tempSeek2 + "00"));
		}
		
		if(seekBar == seekPicker3){
			tempProgress3 = progress;
			tempSeek3 = getHexadecimal(tempProgress3);
			layoutPicker3.setBackgroundColor(Color.parseColor("#0000" + tempSeek3));
		}
		
		//set main color
		tempSeekMain = tempSeek1 + tempSeek2 + tempSeek3;
		textPicker.setText("#" + tempSeekMain);
		layoutPickerMain.setBackgroundColor(Color.parseColor("#" + tempSeekMain));
		
	}
	
	public void onStartTrackingTouch(SeekBar seekBar){}
	
	public void onStopTrackingTouch(SeekBar seekBar){
		tempSeekMain = tempSeek1 + tempSeek2 + tempSeek3;
		textPicker.setText("#" + tempSeekMain);
		layoutPickerMain.setBackgroundColor(Color.parseColor("#" + tempSeekMain));
	}
	
	//method for convert int to hexadecimal
	public String getHexadecimal(int tempValue){
		
		String tempS;
		int tempN;
		String[] valueHex = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};
		
		tempS = "";
		tempN = tempValue;
		
		if(tempN < 15){
			tempS += "0";
			tempS += valueHex[tempN];
		}else{
			tempS += valueHex[(int)(tempN/16)];
			tempS += valueHex[tempN%16];
		}
		
		return tempS;
		
	}
}