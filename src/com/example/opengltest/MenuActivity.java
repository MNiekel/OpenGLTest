package com.example.opengltest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class MenuActivity extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menuscreen);
		
		GameEngine.musicThread = new Thread() {
			public void run() {
				Intent bgmusic = new Intent(getApplicationContext(), MusicManager.class);
				startService(bgmusic);
				GameEngine.context = getApplicationContext();
			}
		};
		
		GameEngine.musicThread.start();
		
		final GameEngine engine = new GameEngine();
		
		ImageButton start = (ImageButton)findViewById(R.id.startbuttonID);
		ImageButton quit = (ImageButton)findViewById(R.id.quitbuttonID);

		start.getBackground().setAlpha(GameEngine.MENU_BUTTON_ALPHA);
		start.setHapticFeedbackEnabled(GameEngine.HAPTIC_BUTTON_FEEDBACK);

		quit.getBackground().setAlpha(GameEngine.MENU_BUTTON_ALPHA);
		quit.setHapticFeedbackEnabled(GameEngine.HAPTIC_BUTTON_FEEDBACK);

		start.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent game = new Intent(getApplicationContext(), GameActivity.class);
				MenuActivity.this.startActivity(game);
			}
		});

		quit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				boolean clean = false;
				clean = engine.onExit(v);
				if (clean)
				{
					int pid = android.os.Process.myPid();
					android.os.Process.killProcess(pid);
				}
			}
		});
	}
}
