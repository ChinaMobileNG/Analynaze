package com.adapter.analyzecase;

import com.rock.analyzecase.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private Context context;
	//private static final int[] icons={
	//		R.drawable.baidu,R.drawable.sixiu,R.drawable.server,R.drawable.bokeyuan,R.drawable.csdn,R.drawable.zhinan};
	private static final String[] names={"Main","学习","机构","案例","我的"};
	public MainAdapter(Context mContext) {
		// TODO Auto-generated constructor stub
		context=mContext;
		inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return names.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view=inflater.inflate(R.layout.main_bottombutton_item, null);
		Button bottomButton=(Button)view.findViewById(R.id.bottom_button);
		bottomButton.setText(names[position]);
		return view;
	}
}
