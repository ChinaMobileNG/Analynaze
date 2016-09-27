package com.rock.analyzecase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.RelativeLayout;

public class SplashActivity extends Activity {
	private Button enterMainButton;
	private static final String TAG="SplashActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN,
		WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_splash);
		RelativeLayout rl_splash=(RelativeLayout)findViewById(R.id.rl_splash);
		AlphaAnimation aa=new AlphaAnimation(0.3f, 1.0f);
		aa.setDuration(2000);
		rl_splash.startAnimation(aa);
		enterMainButton=(Button)findViewById(R.id.enter_button);
		enterMainButton.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				loadMainUI();
				Log.i(TAG, "Button click_to_enter is clicked");
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void loadMainUI(){
		Intent mainIntent=new Intent(SplashActivity.this, MainActivity.class);
		startActivity(mainIntent);
		Log.e(TAG, "load完mainUI");
		finish();
	}
}
