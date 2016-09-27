package com.rock.analyzecase;

import android.app.TabActivity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost;
public class MainActivity extends TabActivity {
	private static final String TAG="MainActivity";
	public static int FLAG_FOR_STUDY=9;
	public static TabHost mTabHost;
	private RadioGroup main_radioGroup;
	private RadioButton study_button;
	/*
	private RadioButton home_button;
	private Button institution_button;
	private Button case_button;
	private Button mine_button;
	*/
	
	public static final String TAB_HOME = "HOME_ACTIVITY";
	public static final String TAB_STUDY= "STUDY_ACTIVITY";
	public static final String TAB_CASE = "CASE_ACTIVITY";
	public static final String TAB_INSTITUTION= "INSTITUTION_ACTIVITY";
	public static final String TAB_MINE = "MINE_ACTIVITY";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main_page);
		findViewById();
		initView();
	}

	private void findViewById() {
		
		mTabHost = getTabHost();
		main_radioGroup=(RadioGroup)findViewById(R.id.main_button_group);
		study_button=(RadioButton)findViewById(R.id.main_study_page);
		/*
		home_button=(RadioButton)findViewById(R.id.main_home_page);
		institution_button=(RadioButton)findViewById(R.id.main_institution_page);
		case_button=(RadioButton)findViewById(R.id.main_case_page);
		mine_button=(RadioButton)findViewById(R.id.main_mine_page);
		*/
		
	}

	private void initView() {
		
		Intent i_home = new Intent(this, HomeTabActivity.class);
		Intent i_study = new Intent(this, StudyTabActivity.class);
		Intent i_institution = new Intent(this, InstitutionTabActivity.class);
		Intent i_case = new Intent(this, CaseTabActivity.class);
		Intent i_mine = new Intent(this, MineTabActivity.class);
		
		mTabHost.addTab(mTabHost.newTabSpec(TAB_HOME).setIndicator(TAB_HOME)
				.setContent(i_home));
		mTabHost.addTab(mTabHost.newTabSpec(TAB_STUDY)
				.setIndicator(TAB_STUDY).setContent(i_study));
		mTabHost.addTab(mTabHost.newTabSpec(TAB_INSTITUTION)
				.setIndicator(TAB_INSTITUTION).setContent(i_institution));
		mTabHost.addTab(mTabHost.newTabSpec(TAB_CASE).setIndicator(TAB_CASE)
				.setContent(i_case));
		mTabHost.addTab(mTabHost.newTabSpec(TAB_MINE)
				.setIndicator(TAB_MINE).setContent(i_mine));

		Intent intent=this.getIntent();
		int flag=intent.getIntExtra("flag", 0);
		if(flag==0){
			mTabHost.setCurrentTabByTag(TAB_HOME);
		}else if(flag==1){
			FLAG_FOR_STUDY=1;
			mTabHost.setCurrentTabByTag(TAB_STUDY);
			Log.i(TAG, "FLAG="+FLAG_FOR_STUDY);
			study_button.setChecked(true);
		}else if(flag==2){
			FLAG_FOR_STUDY=2;
			mTabHost.setCurrentTabByTag(TAB_STUDY);
			Log.i(TAG, "FLAG="+FLAG_FOR_STUDY);
			study_button.setChecked(true);
		}
		
		main_radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				switch(checkedId){
				case R.id.main_home_page:
					mTabHost.setCurrentTabByTag(TAB_HOME);
					break;
				case R.id.main_study_page:
					mTabHost.setCurrentTabByTag(TAB_STUDY);
					break;
				case R.id.main_institution_page:
					mTabHost.setCurrentTabByTag(TAB_INSTITUTION);
					break;
				case R.id.main_case_page:
					mTabHost.setCurrentTabByTag(TAB_CASE);
					break;
				case R.id.main_mine_page:
					mTabHost.setCurrentTabByTag(TAB_MINE);
					break;
				}
			}
		});
	}
	
	@Override
	public void onResume(){
		super.onResume();
		Log.i(TAG, TAG+" onResume");
	}

}
