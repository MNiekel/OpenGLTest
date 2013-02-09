package com.example.opengltest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splashscreen);
		
		new Handler().postDelayed(new Thread() {
			@Override
			public void run() {
				Intent menu = new Intent(SplashActivity.this, MenuActivity.class);
				SplashActivity.this.startActivity(menu);
				SplashActivity.this.finish();
				overridePendingTransition(R.layout.fadein, R.layout.fadeout);
			}
		}, GameEngine.GAME_THREAD_DELAY);
	}
}
