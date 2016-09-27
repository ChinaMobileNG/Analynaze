package com.widgets.analyzecase;

import com.rock.analyzecase.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebSettings.LayoutAlgorithm;

public class GeneralWebPageActivity extends Activity{
	public String myUrl;
	private static final String TAG="StudyActivity";
	public ProgressWebView general_web;
	//public ProgressBar myProgressBar;
	public ProgressDialog progressBar;
	public  AlertDialog alertDialog;
	private int count=0;
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
	}
	
	public void webSetting(){
		
		general_web=(ProgressWebView)findViewById(R.id.general_loadWebView);
		general_web.requestFocusFromTouch();
		//settings
		WebSettings webSettings=general_web.getSettings();
		//webSettings.setPluginState();  设置是否支持插件
		webSettings.setUseWideViewPort(true);//设置此属性，可任意比例缩放
		webSettings.setLoadWithOverviewMode(true);
		webSettings.setJavaScriptCanOpenWindowsAutomatically(true);  
		webSettings.setUseWideViewPort(true);//关键点  
		//设置 缓存模式 
		webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);  
		// 开启 DOM storage API 功能 
		webSettings.setDomStorageEnabled(true); 
		/**  
		 * 用WebView显示图片，可使用这个参数 设置网页布局类型： 1、LayoutAlgorithm.NARROW_COLUMNS ：  
		 * 适应内容大小 2、LayoutAlgorithm.SINGLE_COLUMN:适应屏幕，内容将自动缩放  
		 */  
		webSettings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);  
		webSettings.setDisplayZoomControls(false);  
		webSettings.setJavaScriptEnabled(true); // 设置支持javascript脚本  
		webSettings.setAllowFileAccess(true); // 允许访问文件  
		webSettings.setBuiltInZoomControls(true); // 设置显示缩放按钮  
		webSettings.setSupportZoom(true); // 支持缩放  
		webSettings.setLoadWithOverviewMode(true);  
		
		if(Build.VERSION.SDK_INT>=19){
			webSettings.setLoadsImagesAutomatically(true);
		}else{
			webSettings.setLoadsImagesAutomatically(false);
		}
		
		//setWebViewClient
		general_web.setWebViewClient(new WebViewClient(){
			
			@Override
			public void onPageFinished(WebView view, String url) {
			    if(!general_web.getSettings().getLoadsImagesAutomatically()) {
			        general_web.getSettings().setLoadsImagesAutomatically(true);
			    }
			}
			
	           @Override
	        public boolean shouldOverrideUrlLoading(WebView view, String url) {
	            // TODO Auto-generated method stub
	               //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
	             view.loadUrl(url);
	            return true;
	        }
	           
	           @Override
	        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error){
	                  handler.proceed();//接受证书   
	           }
	           @SuppressWarnings("deprecation")
			public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {  
	                Log.e(TAG, "Error: " + description);   
	                super.onReceivedError(view, errorCode, description, failingUrl);
	              //用javascript隐藏系统定义的404页面信息
	            	//String data = "Page NO FOUND！";
	            	//view.loadUrl("javascript:document.body.innerHTML=\"" + data + "\"");
	                alertDialog.setTitle("出错了");  
	                alertDialog.setIcon(R.drawable.problem);
	                alertDialog.setMessage("网络蜀黍好像开了点小差....");  
	                alertDialog.setButton("返回", new DialogInterface.OnClickListener() {  
	                    public void onClick(DialogInterface dialog, int which) {  
	                        return;  
	                    }  
	                });  
	                alertDialog.show();  
	            }  
	       });
	}

	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if(keyCode==KeyEvent.KEYCODE_BACK)
        {
            if(general_web.canGoBack())
            {
                general_web.goBack();//返回上一页面
                return true;
            }
            else
            {
                finish();//退出程序
            }
        }
        return super.onKeyDown(keyCode, event);
    }
	
	public void setUrl(String url) {
		// TODO Auto-generated method stub
		myUrl=url;
	}
	
	public void loadWeb(String url){
		Log.i(TAG, "current url is "+url);
		general_web.loadUrl(url);
	}
}
