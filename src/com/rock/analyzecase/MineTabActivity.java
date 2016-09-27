package com.rock.analyzecase;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MineTabActivity extends Activity {
	private static final String TAG="MINEACTIVITY";
	public static final String GETRESULTOFNICKNAME="nickname";
	public static final String GETRESULTOFMAJOR="major";
	public static final String GETRESULTOFCLASS="class";
	private static final int MINETABREQUESTCODE=5;
	private String result_nickname;
	private String result_major;
	private String result_klass;
	private RelativeLayout portrait_layout;
	private LinearLayout setting_layout;
	private LinearLayout signin_layout;
	private RelativeLayout about_layout;
	private TextView myName;
	private TextView myMajor;
	private TextView myClass;
	private SharedPreferences mine_info;
	private Editor mine_info_editor;
	private ImageView mine_portrait;
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mine_activity);
		
		myName=(TextView)findViewById(R.id.mine_name);
		myMajor=(TextView)findViewById(R.id.mine_major);
		myClass=(TextView)findViewById(R.id.mine_class);
		
		mine_portrait=(ImageView)findViewById(R.id.mine_head_portrait);
		/*
		 * 这个方法只是在onCreate后面执行了，但是我需要的是在执行oncreate的时候就能获得数据
		 *  ViewTreeObserver vto2 = mine_portrait.getViewTreeObserver();   
		    vto2.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {  
		        @Override   
		        public void onGlobalLayout() {  
                    mine_portrait.getViewTreeObserver().removeOnGlobalLayoutListener(this);
		    		portrait_width=mine_portrait.getWidth();
		    		portrait_height=mine_portrait.getHeight(); 
		    		Log.i(TAG, "初始化portrait宽和高(在方法里面)："+portrait_width+" "+portrait_height);
		        }   
		    });
    		Log.i(TAG, "初始化portrait宽和高(在方法外面)："+portrait_width+" "+portrait_height);
		 * 
		 */
		
		mine_info=getApplicationContext().getSharedPreferences("mine_info", MODE_PRIVATE);
		mine_info_editor=mine_info.edit();
		
		loadInfo();
		
		portrait_layout=(RelativeLayout)findViewById(R.id.mine_page_portrait);
		portrait_layout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				loadActivity(EditPersonalInfoActivity.class);
			}
		});
		
		setting_layout=(LinearLayout)findViewById(R.id.setting_layout);
		setting_layout.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				loadActivity(EditPersonalInfoActivity.class);
			}
		});
		
		signin_layout=(LinearLayout)findViewById(R.id.signin_layout);
		signin_layout.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				loadActivity(SignInActivity.class);
			}
			
		});
		
		about_layout=(RelativeLayout)findViewById(R.id.about_layout);
		about_layout.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				loadActivity(CaseTabActivity.class);
			}
		});
	}
	
	private void loadActivity(@SuppressWarnings("rawtypes") Class target){
		Intent intent=new Intent(this,target);
		startActivityForResult(intent, MINETABREQUESTCODE);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == MINETABREQUESTCODE && resultCode == 100)
        {
        	
            result_nickname = data.getStringExtra(GETRESULTOFNICKNAME);
            result_major = data.getStringExtra(GETRESULTOFMAJOR);
            result_klass = data.getStringExtra(GETRESULTOFCLASS);
            
            myName.setText(result_nickname);
            myMajor.setText(result_major);
            myClass.setText(result_klass);
        }
    }
	
	private void saveInfo(){
		SharedPreferences sp=getSharedPreferences("myInfo", MODE_PRIVATE);
		mine_info_editor.putString("mine_name", sp.getString("nickname", ""));
		mine_info_editor.putString("mine_major", sp.getString("major", ""));
		mine_info_editor.putString("mine_class", sp.getString("klass", ""));
		mine_info_editor.putString("mine_portrait_uri", sp.getString("head_portrait_uri", ""));
		mine_info_editor.commit();
	}
	private void loadInfo(){
		SharedPreferences share=getSharedPreferences("mine_info", MODE_PRIVATE);
		myName.setText(share.getString("mine_name", ""));
		myMajor.setText(share.getString("mine_major", ""));
		myClass.setText(share.getString("mine_class", ""));
		try {  
			String uri=share.getString("mine_portrait_uri", "");
			String picturePath=getPicturePath(uri);
            mine_portrait.setImageBitmap(decodeSampledBitmapFromFile(picturePath, 
            		/*mine_portrait.getWidth(),mine_portrait.getHeight()*/195,195));
        } catch (Exception e) {  
            // TODO: handle exception   
            e.printStackTrace();  
        }  
	}
	
	//根据存储在sharePreference中的图片文件的地址（uri）获取string形式的绝对地址
	private String getPicturePath(String uri){
        Uri selectedImage =Uri.parse(uri);
        String[] filePathColumn = { MediaStore.Images.Media.DATA };  

        Cursor cursor = getContentResolver().query(selectedImage,  
                filePathColumn, null, null, null);  
        cursor.moveToFirst();  

        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);  
        String picturePath = cursor.getString(columnIndex);  
        cursor.close();
		return picturePath;
	}
	
	//计算适应imageview尺寸的inSampleSize
	public static int calculateInSampleSize(BitmapFactory.Options options,  
	        int reqWidth, int reqHeight) {  
	    // 源图片的高度和宽度  
	    final int height = options.outHeight;  
	    final int width = options.outWidth; 
	    int inSampleSize = 1;  
	    if (height > reqHeight || width > reqWidth) {  
	        // 计算出实际宽高和目标宽高的比率  
	        final int heightRatio = Math.round((float) height / (float) reqHeight);  
	        final int widthRatio = Math.round((float) width / (float) reqWidth);  
	        // 选择宽和高中最小的比率作为inSampleSize的值，这样可以保证最终图片的宽和高  
	        // 一定都会大于等于目标的宽和高。  
	        inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;  
	        Log.i(TAG, "压缩比为："+inSampleSize);
	    }  
	    return inSampleSize;  
	}  
	
	//从文件中压缩图片
	private Bitmap decodeSampledBitmapFromFile(String picturePath,
			int requestWidth,int requestHeight){
		// 第一次解析将inJustDecodeBounds设置为true，来获取图片大小  
        final BitmapFactory.Options options = new BitmapFactory.Options();  
        options.inJustDecodeBounds = true;  
        BitmapFactory.decodeFile(picturePath, options);
        // 调用上面定义的方法计算inSampleSize值  
        options.inSampleSize = 
        		calculateInSampleSize(options,requestWidth,requestHeight); 
        // 使用获取到的inSampleSize值再次解析图片  
        options.inJustDecodeBounds = false;  
        Bitmap portrait=BitmapFactory.decodeFile(picturePath, options); 
		return portrait;
	}
	
	@Override
	public void onPause(){
		super.onPause();
		saveInfo();
	}
	@Override
	public void onResume(){
		super.onResume();
		saveInfo();
		loadInfo();
	}
}
