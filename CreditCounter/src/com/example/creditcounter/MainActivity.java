package com.example.creditcounter;

import android.support.v7.app.ActionBarActivity;
import android.view.Window;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

public class MainActivity extends ActionBarActivity {

	MediaPlayer mp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		mp = MediaPlayer.create(this, R.raw.ring);
		mp.start();
		
		Thread timer = new Thread(){
			public void run() {
				try {
					sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					Intent intent =new Intent();
					intent.setClass(MainActivity.this, Show.class);
					startActivity(intent);
				}
			}
		};
		timer.start();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		mp.release();
		finish();
	}
}
