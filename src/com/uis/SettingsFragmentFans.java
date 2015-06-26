package com.uis;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.meimiao.R;

@SuppressLint("NewApi")
public class SettingsFragmentFans extends BaseFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateView(inflater, container, savedInstanceState);

		View view = inflater.inflate(R.layout.settings_fragment_fans,
				container, false);
		initView();

		return view;
	}

	private void initView() {

	}

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		super.onRefresh();
	}

	@Override
	public void loadMore() {
		// TODO Auto-generated method stub
		super.loadMore();
	}

}
