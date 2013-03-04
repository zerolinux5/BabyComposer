package com.zerolinux5.babycomposer;

import java.util.ArrayList;
import java.util.List;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import android.os.Bundle;
import android.os.Vibrator;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener{
	static private final String LOG_TAG = "MainActivity";
	SensorManager sensorManager = null;
	
	//for the music player
	private SoundPool soundPool;
	private int soundIDa;
	private int soundIDb;
	private int soundIDc;
	private int soundIDd;
	private int soundIDe;
	private int soundIDf;
	private int soundIDg;
	boolean loaded = false;
	int timera;
	int timerb;
	int timerc;
	int timerd;
	int timere;
	int timerf;
	int timerg;
	ArrayList<String> NoteList = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		
		// Set the hardware buttons to control the music
	    this.setVolumeControlStream(AudioManager.STREAM_MUSIC);    
	    // Load the sound
	    soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
	    soundPool.setOnLoadCompleteListener(new OnLoadCompleteListener() {
	      @Override
	      public void onLoadComplete(SoundPool soundPool, int sampleId,
	          int status) {
	        loaded = true;
	      }
	    });
	    soundIDa = soundPool.load(this, R.raw.a, 1);
	    soundIDb = soundPool.load(this, R.raw.b, 1);
	    soundIDc = soundPool.load(this, R.raw.c, 1);
	    soundIDd = soundPool.load(this, R.raw.d, 1);
	    soundIDe = soundPool.load(this, R.raw.e, 1);
	    soundIDf = soundPool.load(this, R.raw.f, 1);
	    soundIDg = soundPool.load(this, R.raw.g, 1);
	    timera = 0;
	    timerb = 0;
	    timerc = 0;
	    timerd = 0;
	    timere = 0;
	    timerf = 0;
	    timerg = 0;
	    
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
	    synchronized (this) {
	        switch (event.sensor.getType()){
	            case Sensor.TYPE_ORIENTATION:
	            	Log.d(LOG_TAG, "x: "+event.values[0]+" y: "+event.values[1]+" z: "+event.values[2]);
	            	TextView tv1 = (TextView) findViewById(R.id.textView1);
	            	TextView tv3 = (TextView) findViewById(R.id.textView7);
	            	Button tv2 = (Button) findViewById(R.id.button1);
	            	
	            	if(event.values[1] < -113){
	            		tv1.setText(R.string.none);
	            		tv2.setText(R.string.none);
	            		tv3.setText(R.string.G);
	            		tv2.setVisibility(View.INVISIBLE);
	            	}
	            	if (event.values[1] < -107 && event.values[1] > -113){
	            		tv1.setText(R.string.none);
	            		tv2.setText(R.string.G);
	            		tv3.setText(R.string.F);
	            		tv2.setVisibility(View.VISIBLE);
	            		timera++;
	            		if (timera == 25){
	            			timera =0;
		            		setUpMusic(7);
	            		}
	            		timerReset(1);
	            	}
	            	if (event.values[1] < -97 && event.values[1] > -103){
	            		tv1.setText(R.string.G);
	            		tv2.setText(R.string.F);
	            		tv3.setText(R.string.E);
	            		timerb++;
	            		if (timerb == 25){
		            		timerb =0;
		            		setUpMusic(6);
	            		}
	            		timerReset(2);
	            	}
	            	if (event.values[1] < -87 && event.values[1] > -93){
	            		tv1.setText(R.string.F);
	            		tv2.setText(R.string.E);
	            		tv3.setText(R.string.D);
	            		timerc++;
	            		if (timerc == 25){	 
		            		timerc =0;
		            		setUpMusic(5);
	            		}
	            		timerReset(3);
	            	}
	            	if (event.values[1] < -77 && event.values[1] > -83){
	            		tv1.setText(R.string.E);
	            		tv2.setText(R.string.D);
	            		tv3.setText(R.string.C);
	            		timerd++;
	            		if (timerd == 25){  
		            		timerd =0;
		            		setUpMusic(4);	
	            		}
	            		timerReset(4);
	            	}
	            	if (event.values[1] < -67 && event.values[1] > -73){
	            		tv1.setText(R.string.D);
	            		tv2.setText(R.string.C);
	            		tv3.setText(R.string.B);
	            		timere++;
	            		if (timere == 25){ 
		            		timere =0;
		            		setUpMusic(3);	
	            		}
	            		timerReset(5);
	            	}
	            	if (event.values[1] < -57 && event.values[1] > -63){
	            		tv1.setText(R.string.C);
	            		tv2.setText(R.string.B);
	            		tv3.setText(R.string.A);
	            		timerf++;
	            		if (timerf == 25){
		            		timerf =0;
		            		setUpMusic(2);	
	            		}
	            		timerReset(6);
	            	}
	            	if (event.values[1] < -47 && event.values[1] > -53){
	            		tv1.setText(R.string.B);
	            		tv2.setText(R.string.A);
	            		tv3.setText(R.string.none);
	            		tv2.setVisibility(View.VISIBLE);
	            		timerg++;
	            		if (timerg == 25){
		            		timerg =0;
		            		setUpMusic(1);	
	            		}
	            		timerReset(7);
	            	}
	            	if(event.values[1] > -47){
	            		tv1.setText(R.string.A);
	            		tv2.setText(R.string.none);
	            		tv3.setText(R.string.none);
	            		tv2.setVisibility(View.INVISIBLE);
	            	}
	            break;
	 
	        }
	    }
	 }

	@Override
	 protected void onResume() {
	    super.onResume();
	    sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), sensorManager.SENSOR_DELAY_GAME);
	    sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), sensorManager.SENSOR_DELAY_GAME);
	    timera = 0;
	    timerb = 0;
	    timerc = 0;
	    timerd = 0;
	    timere = 0;
	    timerf = 0;
	    timerg = 0;
	}
	
	@Override
	 protected void onStop() {
	    super.onStop();
	    sensorManager.unregisterListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER));
	    sensorManager.unregisterListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION));
	 }
	
	private void setUpMusic(int value){
		Log.d(LOG_TAG, "Succeded");
	      // Getting the user sound settings
	      AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
	      float actualVolume = (float) audioManager
	          .getStreamVolume(AudioManager.STREAM_MUSIC);
	      float maxVolume = (float) audioManager
	          .getStreamMaxVolume(AudioManager.STREAM_MUSIC);
	      float volume = actualVolume / maxVolume;
	      // Is the sound loaded already?
	      if (loaded) {
	    	  switch (value){
	    	  	case 1:
	    	  		soundPool.play(soundIDa, volume, volume, 1, 0, 1f);
	    	  		break;
	    	  	case 2:
	    	  		soundPool.play(soundIDb, volume, volume, 1, 0, 1f);
	    	  		break;
	    	  	case 3:
	    	  		soundPool.play(soundIDc, volume, volume, 1, 0, 1f);
		    		break;
	    	  	case 4:
	    	  		soundPool.play(soundIDd, volume, volume, 1, 0, 1f);
		    		break;
	    	  	case 5:
	    	  		soundPool.play(soundIDe, volume, volume, 1, 0, 1f);
		    		break;
	    	  	case 6:
	    	  		soundPool.play(soundIDf, volume, volume, 1, 0, 1f);
		    		break;
	    	  	case 7:
	    	  		soundPool.play(soundIDg, volume, volume, 1, 0, 1f);
		    		break;
	    	  }
	    	  
	      }
	}
	
	private void timerReset(int nonReset){
		switch (nonReset){
		case 1:
			timerb = 0;
			timerc = 0;
			timerd = 0;
			timere = 0;
			timerf = 0;
			timerg = 0;
			break;
		case 2:
			timera = 0;
			timerc = 0;
			timerd = 0;
			timere = 0;
			timerf = 0;
			timerg = 0;
			break;
		case 3:
			timera = 0;
			timerb = 0;
			timerd = 0;
			timere = 0;
			timerf = 0;
			timerg = 0;
			break;
		case 4:
			timera = 0;
			timerb = 0;
			timerc = 0;
			timere = 0;
			timerf = 0;
			timerg = 0;
			break;
		case 5:
			timera = 0;
			timerb = 0;
			timerc = 0;
			timerd = 0;
			timerf = 0;
			timerg = 0;
			break;
		case 6:
			timera = 0;
			timerb = 0;
			timerc = 0;
			timerd = 0;
			timere = 0;
			timerg = 0;
			break;
		case 7:
			timera = 0;
			timerb = 0;
			timerc = 0;
			timerd = 0;
			timere = 0;
			timerf = 0;
			break;
		}
	}
	
	public void saveNote(View v){
		Button b = (Button)v;
	    String buttonText = b.getText().toString();
		NoteList.add(buttonText);
		// Get instance of Vibrator from current Context
		Vibrator confirmationVibrate = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		// Vibrate for 300 milliseconds
		confirmationVibrate.vibrate(300);

	}
	
	public void playBack(View v){
	    sensorManager.unregisterListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER));
	    sensorManager.unregisterListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION));
		for (int i = 0; i < NoteList.size(); i++){
			String playNote = NoteList.get(i);
			Log.d(LOG_TAG, NoteList.get(i));
			if (playNote.equals("A")){
				Log.d(LOG_TAG, "why not playing?");
				setUpMusic(1);
			}
			if (playNote.equals("B")){
				setUpMusic(2);
			}
			if (playNote.equals("C")){
				setUpMusic(3);
			}
			if (playNote.equals("D")){
				setUpMusic(4);
			}
			if (playNote.equals("E")){
				setUpMusic(5);
			}
			if (playNote.equals("F")){
				setUpMusic(6);
			}
			if (playNote.equals("G")){
				setUpMusic(7);
			}
			try {Thread.sleep(500);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
		}
	    sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), sensorManager.SENSOR_DELAY_GAME);
	    sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), sensorManager.SENSOR_DELAY_GAME);
	}
	
	public void reset(View v){
		NoteList.clear();
	}

}
