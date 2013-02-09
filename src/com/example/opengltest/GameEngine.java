package com.example.opengltest;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Display;
import android.view.View;

public class GameEngine {
	public static final int GAME_THREAD_DELAY = 4000; // msecs
	public static final int MENU_BUTTON_ALPHA = 0;
	public static final boolean HAPTIC_BUTTON_FEEDBACK = true;
	
	public static final int BG_MUSIC = R.raw.bgmusic;
	public static final int L_VOLUME = 100;
	public static final int R_VOLUME = 100;
	public static final boolean LOOP_BG_MUSIC = true;
	
	public static Context context;
	public static Thread musicThread;
	public static Display display;
	
	public static final int BACKGROUND_LAYER_ONE = R.drawable.backgroundstars;
	public static final int BACKGROUND_LAYER_TWO = R.drawable.debris;
	public static final int SPACESHIP = R.drawable.spaceship;
	public static final float SCROLL_BACKGROUND_1 = 0.002f;
	public static final float SCROLL_BACKGROUND_2 = 0.0005f;
	public static final int GAME_THREAD_FPS_SLEEP = (1000/60);
	
	public static final int PLAYER_UP = 1;
	public static final int PLAYER_DOWN = -1;
	public static final int PLAYER_RELEASE = 0;
	
	public static int playerAction = PLAYER_RELEASE;
	public static float playerYPosition = 0;

	/*Kill game and exit*/
	public boolean onExit(View v) {
		try {
			Intent bgmusic = new Intent(context, MusicManager.class);
			context.stopService(bgmusic);
			//musicThread.stop();
			return true;
		} catch (Exception e) {
			Log.v("PEngine: onExit", "Exception: " +e);
			return false;
		}
	}
}
