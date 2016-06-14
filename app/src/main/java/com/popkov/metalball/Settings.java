package com.popkov.metalball;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Typeface;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


public class Settings extends Activity implements OnClickListener  {
	
	TextView vibrateOnOff,musicOnOff,soundOnOff;
	Boolean vibrateBool,musicBool,soundBool;
	EditText cardSize,colors;
	SharedPreferences mySharedPreferences;
	boolean quick,game;
	private SoundPool sounds;
	private int sExplosion;
	Vibrator vibr;
	boolean bazMusic;

	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.settings);
	        
	        Intent intent = getIntent();
			quick = intent.getBooleanExtra("quick", false);
			game = intent.getBooleanExtra("Game", false);
	        
	        final LinearLayout sound = (LinearLayout)findViewById(R.id.sound);
	        final TextView soundTXT = (TextView)findViewById(R.id.soundTXT);
	        soundOnOff = (TextView)findViewById(R.id.soundOnOff);
	        soundTXT.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/tuschtouch3.ttf"));
	        soundOnOff.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/tuschtouch3.ttf"));
	        sound.setOnClickListener(this);
	        soundTXT.setOnClickListener(this);
	        soundOnOff.setOnClickListener(this);
	        
	        final LinearLayout music = (LinearLayout)findViewById(R.id.music);
	        final TextView musicTXT = (TextView)findViewById(R.id.musicTXT);
	        musicOnOff = (TextView)findViewById(R.id.musicOnOff);
	        musicTXT.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/tuschtouch3.ttf"));
	        musicOnOff.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/tuschtouch3.ttf"));
	        music.setOnClickListener(this);
	        musicTXT.setOnClickListener(this);
	        musicOnOff.setOnClickListener(this);
	        
	        final LinearLayout vibrate = (LinearLayout)findViewById(R.id.vibrate);
	        final TextView vibrateTXT = (TextView)findViewById(R.id.vibrateTXT);
	        vibrateOnOff = (TextView)findViewById(R.id.vibrateOnOff);
	        vibrateTXT.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/tuschtouch3.ttf"));
	        vibrateOnOff.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/tuschtouch3.ttf"));
	        vibrate.setOnClickListener(this);
	        vibrateTXT.setOnClickListener(this);
	        vibrateOnOff.setOnClickListener(this);
	        

	        
	        final Button Save = (Button)findViewById(R.id.Save);
	        final Button Cancel = (Button)findViewById(R.id.Cancel);
	        Save.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/tuschtouch3.ttf"));
	        Cancel.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/tuschtouch3.ttf"));
	        Save.setOnClickListener(this);
	        Cancel.setOnClickListener(this);
	       
	        
	        mySharedPreferences = getSharedPreferences("SETTINGS_COLOR", Context.MODE_PRIVATE);
	        bazMusic = mySharedPreferences.getBoolean("MUSIC", true);
	        soundBool = mySharedPreferences.getBoolean("SAUND", true);
	        musicBool = mySharedPreferences.getBoolean("MUSIC", true);
	        vibrateBool = mySharedPreferences.getBoolean("VIBRATE", true);
	        
	        soundOnOff.setText(settingOnOff(soundBool));
	        musicOnOff.setText(settingOnOff(musicBool));
	        vibrateOnOff.setText(settingOnOff(vibrateBool));
        
	        cardSize.setText(""+mySharedPreferences.getInt("CART",6));
	        colors.setText(""+mySharedPreferences.getInt("COLORS",4));
	        
	       // sounds = new SoundPool(10, AudioManager.STREAM_MUSIC,0);
	       // sExplosion = sounds.load(this, R.raw.boom, 1);
	        
	        vibr = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
	        
	 	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.sound  :
			soundBool = !soundBool;
			soundOnOff.setText(settingOnOff(soundBool));
			soundOnOff();
			break;
		case R.id.soundTXT  :
			soundBool = !soundBool;
			soundOnOff.setText(settingOnOff(soundBool));
			soundOnOff();
			break;
		case R.id.soundOnOff :
			soundBool = !soundBool;
			soundOnOff.setText(settingOnOff(soundBool));
			soundOnOff();
			break;
		case R.id.music:
			musicBool = !musicBool;
			musicOnOff.setText(settingOnOff(musicBool));
			musicOnOff();
			break;
		case R.id.musicTXT:
			musicBool = !musicBool;
			musicOnOff.setText(settingOnOff(musicBool));
			musicOnOff();
			break;
		case R.id.musicOnOff:
			musicBool = !musicBool;
			musicOnOff.setText(settingOnOff(musicBool));
			musicOnOff();
			break;
		case R.id.vibrate :
			vibrateBool = !vibrateBool;
			vibrateOnOff.setText(settingOnOff(vibrateBool));
			
			break;
		case R.id.vibrateTXT :
			vibrateBool = !vibrateBool;
			vibrateOnOff.setText(settingOnOff(vibrateBool));
			
			break;
		case R.id.vibrateOnOff :
			vibrateBool = !vibrateBool;
			vibrateOnOff.setText(settingOnOff(vibrateBool));
			
			break;
		case R.id.Save:
			Editor editor = mySharedPreferences.edit();
			int k = Integer.parseInt(cardSize.getText().toString());
			int c = Integer.parseInt(colors.getText().toString());
			int n = mySharedPreferences.getInt("CART",6);
			if (!(k==n)) {
				
				editor.putInt("ENUMERATOR", 0);
	        	
	       
	        	editor.putLong("CART", 0l);
				for (int i = 0; i < k; i++) {
	        		for (int j = 0; j < k; j++) {
	        			editor.remove("CART_SIZE"+i+j);
	    			}	
				} 
				
				for (int i = 0; i < k; i++) {
	        		for (int j = 0; j < k; j++) {
	        			editor.putInt("CART_SIZE"+i+j, 0);
	    			}	
				} 	
				
			}

			editor.putInt("COLORS", c);
        	editor.putInt("CART", k);
        	editor.putBoolean("SAUND", soundBool);
        	editor.putBoolean("MUSIC", musicBool);
        	editor.putBoolean("VIBRATE", vibrateBool);
        	editor.apply();
        	
        	if (game) {
        		Intent intent = new Intent();
        		if (quick) {
        			intent.putExtra("action", "Game");
				}else {
					intent.putExtra("action", "quickGame");
				}
        		setResult(RESULT_OK, intent);
			}
        	
        	
			finish();
			break;
		case R.id.Cancel:
			if (bazMusic!=musicBool) {
				if (bazMusic) {
					startService(new Intent(this, FonMusic.class));
				} else {
					stopService(new Intent(this, FonMusic.class));	
				}
			}
			
			if (game) {
        		Intent intent = new Intent();
        		if (quick) {
        			intent.putExtra("action", 1);
				}else {
					intent.putExtra("action", 2);
				}
        		setResult(RESULT_OK, intent);
			}
			finish();
			break;
		}
			
			
		}
	
	
	@Override
	public void onBackPressed() {
		if (bazMusic!=musicBool) {
			if (bazMusic) {
				startService(new Intent(this, FonMusic.class));
			} else {
				stopService(new Intent(this, FonMusic.class));	
			}
		}
		
		if (game) {
    		Intent intent = new Intent();
    		if (quick) {
    			intent.putExtra("action", 1);
			}else {
				intent.putExtra("action", 2);
			}
    		setResult(RESULT_OK, intent);
		}
		finish();
	}
	
	public void  musicOnOff() {
		if (musicBool) {
			startService(new Intent(this, FonMusic.class));
		} else {
			stopService(new Intent(this, FonMusic.class));	
		}
	}
	
	public void  soundOnOff() {
		if (soundBool) {
			sounds.play(sExplosion, 1.0f, 1.0f, 0, 0, 1.0f);
		} 
	}
	
	public void  vibrateOnOff() {
		if (vibrateBool) {
			vibr.vibrate(1500l);
		} 
	}
	
	public int  settingOnOff(Boolean b) {
		if (b) {
        	return R.string.setting_ON;
		} else {
			return R.string.setting_OFF;
		}
	}

}
