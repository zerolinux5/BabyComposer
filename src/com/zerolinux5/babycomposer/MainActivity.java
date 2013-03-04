package com.zerolinux5.babycomposer;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener {
	static private final String LOG_TAG = "MainActivity";
	SensorManager sensorManager = null;
	
	//for orientation values
	TextView outputX;
	TextView outputY;
	TextView outputZ;
	
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
	            	if (event.values[1] < -107 && event.values[1] > -113){
	            		timera++;
	            		if (timera == 30){
		            		setUpMusic(7);	      
		            		timera =0;
	            		}
	            		timerReset(1);
	            	}
	            	if (event.values[1] < -97 && event.values[1] > -103){
	            		timerb++;
	            		if (timerb == 30){
		            		setUpMusic(6);	 
		            		timerb =0;
	            		}
	            		timerReset(2);
	            	}
	            	if (event.values[1] < -87 && event.values[1] > -93){
	            		timerc++;
	            		if (timerc == 30){
		            		setUpMusic(5);	 
		            		timerc =0;
	            		}
	            		timerReset(3);
	            	}
	            	if (event.values[1] < -77 && event.values[1] > -83){
	            		timerd++;
	            		if (timerd == 30){
		            		setUpMusic(4);	  
		            		timerd =0;
	            		}
	            		timerReset(4);
	            	}
	            	if (event.values[1] < -67 && event.values[1] > -73){
	            		timere++;
	            		if (timere == 30){
		            		setUpMusic(3);	 
		            		timere =0;
	            		}
	            		timerReset(5);
	            	}
	            	if (event.values[1] < -57 && event.values[1] > -63){
	            		timerf++;
	            		if (timerf == 30){
		            		setUpMusic(2);	
		            		timerf =0;
	            		}
	            		timerReset(6);
	            	}
	            	if (event.values[1] < -47 && event.values[1] > -53){
	            		timerg++;
	            		if (timerg == 30){
		            		setUpMusic(1);	
		            		timerg =0;
	            		}
	            		timerReset(7);
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

}
