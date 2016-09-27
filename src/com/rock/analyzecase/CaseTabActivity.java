package com.rock.analyzecase;

import java.util.Properties;

import com.widgets.analyzecase.GeneralWebPageActivity;
import com.widgets.analyzecase.MyPropertyUtils;

import android.app.AlertDialog;
import android.view.Window;

public class CaseTabActivity extends GeneralWebPageActivity {
	@Override
	protected void onCreate(android.os.Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.general_webpage_activity);
		webSetting();
		Properties properties=MyPropertyUtils.getProperties(getApplicationContext());
		alertDialog = new AlertDialog.Builder(this).create();  
		setUrl(properties.getProperty("bdimage"));
		loadWeb(myUrl);
	};
}
