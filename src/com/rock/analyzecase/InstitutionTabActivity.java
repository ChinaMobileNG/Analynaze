package com.rock.analyzecase;

import java.util.Properties;

import com.widgets.analyzecase.MyPropertyUtils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebSettings.ZoomDensity;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class InstitutionTabActivity extends Activity {
	private static final String TAG="InstitutionActivity";
	private RadioGroup institution_group;
	private WebView institution_web;
	private ProgressDialog progressBar;
	private  AlertDialog alertDialog;
	private String dianxinUrl;
	private String computerUrl;
	private String dianqiUrl;
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.institution_activity);
		
		Properties properties=MyPropertyUtils.getProperties(getApplicationContext());
		
		dianxinUrl=properties.getProperty("dianxinUrl");
		dianqiUrl=properties.getProperty("dianqiUrl");
		computerUrl=properties.getProperty("computerUrl");
		institution_group=(RadioGroup)findViewById(R.id.institution_button_group);
		institution_web=(WebView)findViewById(R.id.institution_loadWebView);
		
		alertDialog = new AlertDialog.Builder(this).create();  
		
		//初始显示第一个tab里面的内容
		loadWeb(dianxinUrl);
		
		institution_group.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				switch(checkedId){
				case R.id.baidu_page:
					loadWeb(dianxinUrl);
					break;
				case R.id.csdn_page:
					loadWeb(computerUrl);
					break;
				case R.id.cnblogs_page:
					loadWeb(dianqiUrl);
					break;
				default:
					break;
				}
			}
		});
	}
	
	private void loadWeb(String url){
		institution_web.setWebViewClient(new WebViewClient(){
	           @Override
	        public boolean shouldOverrideUrlLoading(WebView view, String url) {
	            // TODO Auto-generated method stub
	               //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
	             view.loadUrl(url);
	            return true;
	        }
	           @Override
	        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error){   
	                  //handler.cancel(); 默认的处理方式，WebView变成空白页   
	                  handler.proceed();//接受证书   
	                 //handleMessage(Message msg); 其他处理   
	           }
	           @Override
				public void onPageStarted(WebView view,String url,Bitmap favicon){
	        	   if(progressBar==null)
			               progressBar = ProgressDialog.show(InstitutionTabActivity.this, "", "Loading,please wait...");
				}
	           @Override
	           public void onPageFinished(WebView view,String url){
	        	   if(progressBar.isShowing()){
	        		   progressBar.dismiss();
	        		   progressBar=null;
	        	   }
	           }
	           @SuppressWarnings("deprecation")
			@Override
	           public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {  
	                Log.e(TAG, "Error: " + description);   
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
		
		
		WebSettings webSettings=institution_web.getSettings();
		webSettings.setJavaScriptCanOpenWindowsAutomatically(true);  
		webSettings.setUseWideViewPort(true);//关键点  
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
		  
		DisplayMetrics metrics = new DisplayMetrics();  
		  getWindowManager().getDefaultDisplay().getMetrics(metrics);  
		  int mDensity = metrics.densityDpi;  
		  Log.d("maomao", "densityDpi = " + mDensity);  
		  if (mDensity == 240) {   
		   webSettings.setDefaultZoom(ZoomDensity.FAR);  
		  } else if (mDensity == 160) {  
		     webSettings.setDefaultZoom(ZoomDensity.MEDIUM);  
		  } else if(mDensity == 120) {  
		   webSettings.setDefaultZoom(ZoomDensity.CLOSE);  
		  }else if(mDensity == DisplayMetrics.DENSITY_XHIGH){  
		   webSettings.setDefaultZoom(ZoomDensity.FAR);   
		  }else if (mDensity == DisplayMetrics.DENSITY_TV){  
		   webSettings.setDefaultZoom(ZoomDensity.FAR);   
		  }else{  
		      webSettings.setDefaultZoom(ZoomDensity.MEDIUM);  
		  }  
		  
		  Log.i(TAG, "current url: "+url);
		institution_web.loadUrl(url);
	}

}
