package com.example.opengltest;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.content.Context;

public class GameActivity extends Activity {
	
	private GameView gameView;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		GameEngine.display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		gameView = new GameView(this);

		setContentView(gameView);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float x = event.getX();
		float y = event.getY();
		DisplayMetrics metrics = new DisplayMetrics();
		if (GameEngine.display != null) {
			GameEngine.display.getMetrics(metrics);
			Log.v("TouchEvent", "x:y = "+x+":"+y);
		}
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			if (y > (metrics.heightPixels / 2)) {
				GameEngine.playerAction = GameEngine.PLAYER_DOWN;
				Log.v("TouchEvent", "Moving down...");
			} else {
				GameEngine.playerAction = GameEngine.PLAYER_UP;
				Log.v("TouchEvent", "Moving up...");
			}
			break;
		case MotionEvent.ACTION_UP:
			GameEngine.playerAction = GameEngine.PLAYER_RELEASE;
			break;
		}
		return false;
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		gameView.onPause();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		gameView.onResume();
	}
}
