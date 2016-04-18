package com.canning.todo.activities.splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.canning.todo.MainActivity;

import listTable.todo.com.todo.R;

public class SplashScreen extends Activity {

	// Splash screen timer
	private static int SPLASH_TIME_OUT = 3000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		Thread background = new Thread() {
			public void run() {

				try {
					// Thread will sleep for 5 seconds
					sleep(5*1000);

					// After 5 seconds redirect to another intent
					Intent i=new Intent(SplashScreen.this, MainActivity.class);
					startActivity(i);

					//Remove activity
					finish();

				} catch (Exception ignored) {

				}
			}
		};

		// start thread
		background.start();
	}

}
