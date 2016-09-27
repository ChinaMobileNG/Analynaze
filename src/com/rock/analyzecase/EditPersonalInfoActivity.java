package com.rock.analyzecase;

import java.io.File;
import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NavUtils;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class EditPersonalInfoActivity extends Activity {
	private ImageView headImage = null;
	private static final int nicknameRequestCode=1;
	private static final int genderRequestCode=3;
	private static final int majorRequestCode=5;
	private static final int classRequestCode=7;
	private static final int REQUEST_CODE_ASK_CALL_PHONE=100;
	public static  final String GETRESULT="result";
	private RelativeLayout nickname_layout;
	private RelativeLayout gender_layout;
	private RelativeLayout major_layout;
	private RelativeLayout klass_layout;
	private RelativeLayout portrait_layout;
	private TextView nickname;
	private TextView gender;
	private TextView major;
	private TextView klass;
	private String tempResult;
	public SharedPreferences sharedPreferences;
	private Editor editor;
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		//this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.mine_edit_info);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		File sd=Environment.getExternalStorageDirectory();
		boolean read=sd.canRead();
		boolean write=sd.canWrite();
		Log.i("BOOLEAN", read+"***"+write);
		
		nickname_layout=(RelativeLayout)findViewById(R.id.mine_edit_nickname);
		gender_layout=(RelativeLayout)findViewById(R.id.mine_edit_gender);
		major_layout=(RelativeLayout)findViewById(R.id.mine_edit_major);
		klass_layout=(RelativeLayout)findViewById(R.id.mine_edit_class);
		portrait_layout=(RelativeLayout)findViewById(R.id.mine_edit_portrait);
		nickname=(TextView)findViewById(R.id.nickname);
		gender=(TextView)findViewById(R.id.gender);
		major=(TextView)findViewById(R.id.major);
		klass=(TextView)findViewById(R.id.klass);
		headImage=(ImageView)findViewById(R.id.setting_head_portrait);
		
		sharedPreferences=getSharedPreferences("myInfo", MODE_PRIVATE);
		editor=sharedPreferences.edit();
		
		loadIInfo();
		
		nickname_layout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(EditPersonalInfoActivity.this,EditInputTextActivity.class);
				startActivityForResult(intent, nicknameRequestCode);
			}
		});
		gender_layout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(EditPersonalInfoActivity.this,EditInputTextActivity.class);
				startActivityForResult(intent, genderRequestCode);
			}
		});
		major_layout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(EditPersonalInfoActivity.this,EditInputTextActivity.class);
				startActivityForResult(intent, majorRequestCode);
			}
		});
		klass_layout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(EditPersonalInfoActivity.this,EditInputTextActivity.class);
				startActivityForResult(intent, classRequestCode);
			}
		});
		
		portrait_layout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (Build.VERSION.SDK_INT >= 23) {
		            int checkWriteExStoragePermission = ContextCompat.checkSelfPermission(
		            		getApplicationContext(),Manifest.permission.WRITE_EXTERNAL_STORAGE);
		            if(checkWriteExStoragePermission != PackageManager.PERMISSION_GRANTED){
		                ActivityCompat.requestPermissions(getParent(), 
		                		new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
		                		REQUEST_CODE_ASK_CALL_PHONE);
		                return;
		            }
		           openGallery();
				}else{
					openGallery();
				}
			}
        }); 
		sendResult();
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == nicknameRequestCode && resultCode == 2)
        {
            tempResult = data.getStringExtra(GETRESULT);
            nickname.setText(tempResult);
            saveInfo("nickname", tempResult);
        }else if(requestCode == genderRequestCode && resultCode == 2){
        	 tempResult = data.getStringExtra(GETRESULT);
             gender.setText(tempResult);
             saveInfo("gender", tempResult);
        }else if(requestCode == majorRequestCode && resultCode == 2){
	       	 tempResult = data.getStringExtra(GETRESULT);
	         major.setText(tempResult);
	         saveInfo("major", tempResult);
	    }else if(requestCode == classRequestCode && resultCode == 2){
		   	 String tempResult = data.getStringExtra(GETRESULT);
		     klass.setText(tempResult);
	         saveInfo("klass", tempResult);
	    }else if(requestCode==10){  
          try {  
              Uri selectedImage = data.getData();  
              saveInfo("head_portrait_uri", selectedImage.toString());
              SharedPreferences temp=getSharedPreferences("myInfo", MODE_PRIVATE);
              setPortrait(temp.getString("head_portrait_uri", ""));
          } catch (Exception e) {  
              // TODO: handle exception   
              e.printStackTrace();  
          }  
	    }

    }
	
	private void saveInfo(String target,String content){
		editor.putString(target, content);
		editor.commit();
	}
	
	private void loadIInfo(){
		SharedPreferences myInformation=getSharedPreferences("myInfo", MODE_PRIVATE);
		String myNickname=myInformation.getString("nickname", "昵称");
		String myGender=myInformation.getString("gender", "性别");
		String myMajor=myInformation.getString("major", "专业");
		String myKlass=myInformation.getString("klass", "班级");
		
		nickname.setText(myNickname);
		gender.setText(myGender);
		major.setText(myMajor);
		klass.setText(myKlass);
		headImage.setBackgroundResource(R.drawable.setting_portrait);
		
	}
	
	private void sendResult(){
		Intent intent=new Intent();
		intent.putExtra(com.rock.analyzecase.MineTabActivity.GETRESULTOFNICKNAME, 
				nickname.getText().toString());
		intent.putExtra(com.rock.analyzecase.MineTabActivity.GETRESULTOFMAJOR, 
				major.getText().toString());
		intent.putExtra(com.rock.analyzecase.MineTabActivity.GETRESULTOFCLASS, 
				klass.getText().toString());
		setResult(100, intent);
	}

	public void openGallery(){
		 Intent getImage = new Intent(Intent.ACTION_GET_CONTENT);  
         getImage.addCategory(Intent.CATEGORY_OPENABLE);  
         getImage.setType("image/jpeg");  
         startActivityForResult(getImage, 10);  
	}
	
	//设置头像
	private void setPortrait(String uri){
		Uri portraitUri=Uri.parse(uri);
		 String[] filePathColumn = { MediaStore.Images.Media.DATA };  
         for(int i=0;i<filePathColumn.length;i++)
       	  Log.i("图片绝对路径",filePathColumn[i]+" "+portraitUri);

         Cursor cursor = getContentResolver().query(portraitUri,  
                 filePathColumn, null, null, null);  
         cursor.moveToFirst();  

         int columnIndex = cursor.getColumnIndex(filePathColumn[0]);  
         String picturePath = cursor.getString(columnIndex);  
         cursor.close();  
         
      // 第一次解析将inJustDecodeBounds设置为true，来获取图片大小  
         final BitmapFactory.Options options = new BitmapFactory.Options();  
         options.inJustDecodeBounds = true;  
         BitmapFactory.decodeFile(picturePath, options);
         // 调用上面定义的方法计算inSampleSize值  
         options.inSampleSize = 
         		calculateInSampleSize(options,headImage.getWidth(),headImage.getHeight()); 
         // 使用获取到的inSampleSize值再次解析图片  
         options.inJustDecodeBounds = false;  
         Bitmap portrait=BitmapFactory.decodeFile(picturePath, options); 
         headImage.setImageBitmap(portrait);  
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
		    }  
		    return inSampleSize;  
		}  
		
		@Override
		public boolean onOptionsItemSelected(MenuItem item){
			switch(item.getItemId()){
			case android.R.id.home:
				if(NavUtils.getParentActivityName(getParent())!=null){
					NavUtils.navigateUpFromSameTask(getParent());
				}
				return true;
			default:
				return super.onOptionsItemSelected(item);
			}
		}
}
