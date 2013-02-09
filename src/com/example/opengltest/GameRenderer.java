package com.example.opengltest;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView.Renderer;
import android.util.Log;

public class GameRenderer implements Renderer {
	
	private Background backgroundLayerOne = new Background();
	private Background backgroundLayerTwo = new Background();
	
	private Spaceship spaceship = new Spaceship();

	private float bgLayer1Scrolled;
	private float bgLayer2Scrolled;
		
	private int width = 0;
	private int height = 0;
	
	private long loopStart = 0;
	private long loopEnd = 0;
	private long loopRunTime = 0;
	


	@Override
	public void onDrawFrame(GL10 gl) {
		loopStart = System.currentTimeMillis();
		// TODO Auto-generated method stub
		try {
			if (loopRunTime < GameEngine.GAME_THREAD_FPS_SLEEP) {
				Thread.sleep(GameEngine.GAME_THREAD_FPS_SLEEP - loopRunTime);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);	
		
		scrollBGLayer1(gl);
		scrollBGLayer2(gl);
		
		movePlayer(gl);
		//All other game drawing will be called here
		
		//gl.glEnable(GL10.GL_BLEND); 
	    //gl.glBlendFunc(GL10.GL_ONE, GL10.GL_ONE); 
		loopEnd = System.currentTimeMillis();
		loopRunTime = loopEnd - loopStart;
	}
	
	private void movePlayer(GL10 gl) {
		float scaleX = 1f;
		float scaleY = 1f;
		if (this.width != 0) {
			scaleX = (float) spaceship.width / (float) this.width;
		}
		if (this.height != 0) {
			scaleY = (float) spaceship.height / (float) this.height;
		}
		gl.glMatrixMode(GL10.GL_MODELVIEW);
	    gl.glLoadIdentity();
	    gl.glPushMatrix();
	    gl.glScalef(scaleX, scaleY, 1f);
	    GameEngine.playerYPosition += ((float) GameEngine.playerAction) * 0.02f;
	    gl.glTranslatef(0f, GameEngine.playerYPosition, 0f);

	    spaceship.draw(gl);
	    gl.glPopMatrix();
	    gl.glLoadIdentity();
	}
	
	private void scrollBGLayer1(GL10 gl) {
		if (bgLayer1Scrolled > Float.MAX_VALUE) {
			bgLayer1Scrolled = 0f;
		}
		
	    gl.glMatrixMode(GL10.GL_MODELVIEW);
	    gl.glLoadIdentity();
	    gl.glPushMatrix();
	    gl.glScalef(1f, 1f, 1f);
	    gl.glTranslatef(0f, 0f, 0f);
    
		gl.glMatrixMode(GL10.GL_TEXTURE);
		gl.glLoadIdentity();
	    gl.glTranslatef(bgLayer1Scrolled, 0.0f, 0.0f); 
	   
	    backgroundLayerOne.draw(gl);
	    gl.glPopMatrix();
	    bgLayer1Scrolled += GameEngine.SCROLL_BACKGROUND_1;
	    gl.glLoadIdentity();
	}
	
	private void scrollBGLayer2(GL10 gl) {
	    gl.glMatrixMode(GL10.GL_MODELVIEW);
	    gl.glLoadIdentity();
	    gl.glPushMatrix();
	    gl.glScalef(0.25f, 0.5f, 1f);
	    if (bgLayer2Scrolled > 5f) {
	    	bgLayer2Scrolled = 0f;
	    }

	    gl.glTranslatef(4f-bgLayer2Scrolled, 0f, 0f);
	   
	    backgroundLayerTwo.draw(gl);
	    gl.glPopMatrix();
	    bgLayer2Scrolled += GameEngine.SCROLL_BACKGROUND_2;
	    gl.glLoadIdentity();
	}
	
	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		gl.glViewport(0, 0, width, height);
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glOrthof(0f, 1f, 0f, 1f, -1f, 1f);
		Log.v("Surface changed; width:height = ", +width+":"+height);
		this.width = width;
		this.height = height;
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		gl.glEnable(GL10.GL_TEXTURE_2D);
		gl.glClearDepthf(1.0f);
		gl.glEnable(GL10.GL_DEPTH_TEST);
		gl.glDepthFunc(GL10.GL_LEQUAL);
		gl.glEnable(GL10.GL_BLEND);
		gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		backgroundLayerOne.loadTexture(gl, GameEngine.BACKGROUND_LAYER_ONE, GameEngine.context);
		backgroundLayerTwo.loadTexture(gl, GameEngine.BACKGROUND_LAYER_TWO, GameEngine.context);
		spaceship.loadTexture(gl, GameEngine.SPACESHIP, GameEngine.context);
	}

}
