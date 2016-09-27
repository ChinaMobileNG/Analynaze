package com.widgets.analyzecase;

import com.android.volley.toolbox.ImageLoader.ImageCache;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
/**
 * @author Rockfor
 * @version 1.0  2016.4.25
 *
 */
public class BitmapCache implements ImageCache {
	private LruCache<String, Bitmap> mCache;
	public BitmapCache() {
		// TODO Auto-generated constructor stub
		//将缓存区设置成10M大小
		int maxCache=10*1024*1024;
		mCache=new LruCache<String, Bitmap>(maxCache){
			@Override
			protected int sizeOf(String key,Bitmap bitmap){
				return bitmap.getRowBytes()*bitmap.getHeight();
			}
		};
	}

	@Override
	public Bitmap getBitmap(String url) {
		// TODO Auto-generated method stub
		return mCache.get(url);
	}

	@Override
	public void putBitmap(String url, Bitmap bitmap) {
		// TODO Auto-generated method stub
		mCache.put(url, bitmap);

	}
	

}
