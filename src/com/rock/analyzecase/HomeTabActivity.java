package com.rock.analyzecase;

import java.util.ArrayList;
import java.util.List;

import com.adapter.analyzecase.CaseListAdapter;
import com.assistclass.analyzecase.CaseItem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

public class HomeTabActivity extends Activity {
	private RadioGroup home_button_group;
	private static final String TAG="HomeActivity";
	private List<CaseItem> case_list=new ArrayList<CaseItem>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_activity);
		
		//int maxMemory=(int)(Runtime.getRuntime().maxMemory()/1024/1024);
		//Log.i(TAG, "The max memory is: "+maxMemory+"M");
		
		initCase();
		
		CaseListAdapter adapter=
				new CaseListAdapter(HomeTabActivity.this, R.layout.listview_item, case_list,getResources());
		
		ListView list = (ListView) findViewById(R.id.home_page_listview); 
		home_button_group=(RadioGroup)findViewById(R.id.home_button_group);
		
		list.setAdapter(adapter);
		home_button_group.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				/**
				 * 这里根据在homePage的点击事件，来强行连接到mianPage中对应的子模块A的子模块B
				 * 目的是为了使观感上看起来较为统一，为了从子模块B返回到A（注意：上一步实际上并没有建立子模块A，所以现在内存中
				 * 是不存在A的），会在对应子模块返回事件中再次强行连接到mainPage中的子模块A
				 * 
				 */
				switch(checkedId){
				case R.id.case_link:
					Log.i(TAG, "case_link被点击了");
					openActivity(MainActivity.class,1);
					break;
				case R.id.media_link:
					Log.i(TAG, "media_link被点击了");
					break;
				case R.id.visual_link:
					break;
				case R.id.xxx_link:
					break;
				}
				
			}
		});
         
        //添加点击 
        list.setOnItemClickListener(new OnItemClickListener() { 
 
            @Override 
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, 
                    long arg3) { 
                Toast.makeText(getApplicationContext(), "第"+arg2+"个", Toast.LENGTH_SHORT).show();
            } 
        }); 
    } 
	
	//初始化定制列表项所有item
	private void initCase(){
		CaseItem item1=new CaseItem(R.drawable.dlutlabel, "aaaaaaaa");
		case_list.add(item1);
		CaseItem item2=new CaseItem(R.drawable.dlutlabel, "ddddddd");
		case_list.add(item2);
		CaseItem item3=new CaseItem(R.drawable.dlutlabel, "rrrrrrrrrr");
		case_list.add(item3);
		CaseItem item4=new CaseItem(R.drawable.dlutlabel, "tttttttttt");
		case_list.add(item4);
		CaseItem item5=new CaseItem(R.drawable.dlutlabel, "uuuuuuuuu");
		case_list.add(item5);
		CaseItem item6=new CaseItem(R.drawable.dlutlabel, "kkkkkkkkk");
		case_list.add(item6);
		CaseItem item7=new CaseItem(R.drawable.dlutlabel, "wwwwwwww");
		case_list.add(item7);
		CaseItem item8=new CaseItem(R.drawable.dlutlabel, "fddgfbgbgfbfgbgf");
		case_list.add(item8);
	}
	/**
	 * 启动相关activity
	 *这里的flag表示从homePage中的某个link启动的MainActivity
	 *1代表case_link,3代表media_link,5代表visual_link,7代表xxx_link
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
