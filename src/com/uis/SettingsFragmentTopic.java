package com.uis;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.meimiao.R;


public class SettingsFragmentTopic extends BaseFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateView(inflater, container, savedInstanceState);

		View view = inflater.inflate(R.layout.settings_fragment_topic,
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
