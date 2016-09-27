package com.rock.analyzecase;

import java.util.Properties;

import com.widgets.analyzecase.GeneralWebPageActivity;
import com.widgets.analyzecase.MyPropertyUtils;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

public class StudyTabActivity extends GeneralWebPageActivity {
	private static final String TAG="StudyTabActivity";
	@Override
	protected void onCreate(android.os.Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.general_webpage_activity);
		
		webSetting();
		
		alertDialog = new AlertDialog.Builder(this).create();  
	};
	@Override
	public void onPause(){
		Log.i(TAG, "onPause called");
		MainActivity.FLAG_FOR_STUDY=9;
		super.onPause();
	}
	@Override
	protected void onStop() {
		Log.i(TAG, "onStop called");
		super.onStop();
	};
	@Override
	public void onDestroy(){
		Log.i(TAG, "onDestroy called");
		super.onDestroy();
	}
	@Override
	public void onStart(){
		super.onStart();
		Log.i(TAG, "onStart called");
	}
	/**
	 * 因为StudyTabActivity这一模块可能由多个其他模块启动（homePage中的4个link），为了在不同模块启动会进入不同对应页面中
	 * 这里在onResume中进行了区分，目的达到了，但是也会增加系统开销，每次回到StudyTabActivity这里都会重新进行页面的加载，
	 * 并没有使用到webView的缓存优势
	 * 
	 */
	@Override
	public void onResume(){
		super.onResume();
		Properties properties = MyPropertyUtils.getProperties(getApplicationContext());
		if(MainActivity.FLAG_FOR_STUDY==1){
			setUrl(properties.getProperty("csdn"));
		}else{
			setUrl(properties.getProperty("cnblogs"));
		}
		loadWeb(myUrl);
		Log.i(TAG, "onResume called");
	}

	@Override
	 public boolean onKeyDown(int keyCode, KeyEvent event) {
		super.onKeyDown(keyCode, event);
		if (keyCode == KeyEvent.KEYCODE_BACK
               && event.getRepeatCount() == 0) {
			openActivity(MainActivity.class,2);
           return true;
       }
       return super.onKeyDown(keyCode, event);
	 }
	
	/**
	 * 启动相关activity
	 *这里的flag表示从homePage中的某个link对应页面返回的的MainActivity
	 *2代表case_link,4代表media_link,6代表visual_link,8代表xxx_link
	 * @param ActivityClass
	 */
		private void openActivity(Class ActivityClass,int flag){
			Intent intent=new Intent(this,ActivityClass);
			Bundle bundle=new Bundle();
			bundle.putInt("flag", flag);
			intent.putExtras(bundle);
			startActivity(intent);
		}
	           
}
