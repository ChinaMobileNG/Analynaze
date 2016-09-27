package com.rock.analyzecase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Window;
import android.widget.EditText;

public class EditInputTextActivity extends Activity implements TextWatcher{
	private static final String TAG="EditInputTextActivity";
	private EditText editText;
	private String content;
	private Intent backintent;
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.edit_info_edittext);
		editText=(EditText)findViewById(R.id.edit_info);
		backintent=new Intent();
		editText.addTextChangedListener(this);
	}
	@Override
	public void beforeTextChanged(CharSequence s, int start, int count, int after) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void afterTextChanged(Editable s) {
		// TODO Auto-generated method stub
		content=editText.getText().toString();
		backintent.putExtra(com.rock.analyzecase.EditPersonalInfoActivity.GETRESULT, content);
		setResult(2, backintent);
	}
	//这个对话框暂时没有使用
	/*
	private void showDialog(){  
        AlertDialog dialog;  
        AlertDialog.Builder builder = new AlertDialog.Builder(this);  
        builder.setTitle("消息").setIcon(R.drawable.dlutlabel);  
        builder.setMessage("不可为空");  
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener(){  
            @Override 
            public void onClick(DialogInterface dialog, int which) {  
                // TODO Auto-generated method stub  
                  
            }                     
        });  
        dialog = builder.create();  
        dialog.show();  
    }  */
}
