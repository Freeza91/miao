package com.adapters;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.meimiao.R;

public class LoadMoreAdapter extends BaseAdapter {

	private ViewHolder cardView = null;
	private LayoutInflater layoutInflater = null;
	private Context context;
	private ArrayList<HashMap<String, String>> list;

	public LoadMoreAdapter(Context context,
			ArrayList<HashMap<String, String>> list) {
		this.context = context;
		this.list = list;
		layoutInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		if (arg1 == null) {
			arg1 = layoutInflater.inflate(R.layout.home_cardview, null);
			cardView = new ViewHolder();
			cardView.tv = (TextView) arg1.findViewById(R.id.home_card_view);
			arg1.setTag(cardView);
		} else {
			cardView = (ViewHolder) arg1.getTag();
		}
		cardView.tv.setText(list.get(arg0).get("key").toString());
		return arg1;
	}

	private class ViewHolder {
		TextView tv;
	}

}
