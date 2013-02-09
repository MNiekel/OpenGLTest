package com.example.opengltest;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class MusicManager extends Service {
	
	public static boolean isRunning = false;
	private MediaPlayer player;
	private static Context context;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	public IBinder onUnBind(Intent intent) {
		return null;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		context = this;
		setMusicOptions(this, GameEngine.LOOP_BG_MUSIC, GameEngine.R_VOLUME, GameEngine.L_VOLUME, GameEngine.BG_MUSIC);
	}
	
	@Override
	public void onDestroy() {
		player.stop();
		player.release();
	}
	
	private void setMusicOptions(Context context, boolean looped, int rVolume, int lVolume, int soundID) {
		player = MediaPlayer.create(context, soundID);
		player.setLooping(looped);
		player.setVolume(rVolume, lVolume);
	}
	
	public int onStartCommand(Intent intent, int flags, int startId) {
		try {
			player.start();
			isRunning = true;
		} catch (Exception e) {
			isRunning = false;
			player.stop();
		}
		return 1;
	}
	
	public void onStart(Intent intent, int startId) {
		/* not in book
		try {
			player.start();
			isRunning = true;
		} catch (Exception e) {
			isRunning = false;
			player.stop();
		}
		*/
	}
	
	public void onStop() {
		isRunning = false;
	}
	
	public void onPause() {
	}

	@Override
	public void onLowMemory() {
		player.stop();
	}
}
