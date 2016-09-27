package com.adapter.analyzecase;

import java.util.List;

import com.assistclass.analyzecase.CaseItem;
import com.rock.analyzecase.R;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CaseListAdapter extends ArrayAdapter<CaseItem> {
	private static final String TAG="CaseListAdapter";
	private Resources resources;
	private int case_item_id;
	public CaseListAdapter(Context context, int resourceId, List<CaseItem> case_list,Resources res) {
		super(context, resourceId, case_list);
		case_item_id=resourceId;
		resources=res;
		Log.i(TAG, "resources:"+resources.toString());
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public View getView(int position,View convertView,ViewGroup parent){
		View view;
		ViewHolder viewHolder;
		CaseItem item=getItem(position);
		if(convertView==null){
			view=LayoutInflater.from(getContext()).inflate(case_item_id, null);
			viewHolder=new ViewHolder();
			//viewHolder.imageView=(ImageView)view.findViewById(R.id.listitem_imageview);
			viewHolder.textView=(TextView)view.findViewById(R.id.listitem_textview);
			view.setTag(viewHolder);
		}else{
			view=convertView;
			viewHolder=(ViewHolder)view.getTag();
		}
		/*  丢弃
		viewHolder.imageView.setImageBitmap(
				decodeSampledBitmapFromFile(
						//viewHolder.imageView.getWidth(),viewHolder.imageView.getHeight(),
						135,135,item.getCase_image_id()));
						*/
		viewHolder.textView.setText(item.getCase_description());
		return view;
		
	}
	
	class ViewHolder{
		//ImageView imageView;
		TextView textView;
	}
	
	//计算适应imageview尺寸的inSampleSize
		public static int calculateInSampleSize(BitmapFactory.Options options,  
		        int reqWidth, int reqHeight) {  
		    // 源图片的高度和宽度  
			Log.i(TAG, "所需宽和高："+reqWidth+" "+reqHeight);
		    final int height = options.outHeight;  
		    final int width = options.outWidth; 
		    Log.i(TAG, "资源宽和高："+width+" "+height);
		    int inSampleSize = 1;  
		    if (height > reqHeight || width > reqWidth) {  
		        // 计算出实际宽高和目标宽高的比率  
		        final int heightRatio = Math.round((float) height / (float) reqHeight);  
		        final int widthRatio = Math.round((float) width / (float) reqWidth);  
		        // 选择宽和高中最小的比率作为inSampleSize的值，这样可以保证最终图片的宽和高  
		        // 一定都会大于等于目标的宽和高。  
		        inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;  
		        Log.i(TAG, "计算压缩比为："+inSampleSize);
		    }  
		    return inSampleSize;  
		}  
		
		//从资源中中压缩图片
		private Bitmap decodeSampledBitmapFromFile(int requestWidth,int requestHeight,int resId){
			// 第一次解析将inJustDecodeBounds设置为true，来获取图片大小  
	        final BitmapFactory.Options options = new BitmapFactory.Options();  
	        options.inJustDecodeBounds = true;  
	        BitmapFactory.decodeResource(resources, resId, options);
	        // 调用上面定义的方法计算inSampleSize值  
	        options.inSampleSize = 
	        		calculateInSampleSize(options,requestWidth,requestHeight); 
	        // 使用获取到的inSampleSize值再次解析图片  
	        options.inJustDecodeBounds = false;  
	        Bitmap portrait=BitmapFactory.decodeResource(resources, resId, options); 
			return portrait;
		}

}
