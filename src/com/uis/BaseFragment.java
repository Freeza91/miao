package com.uis;

import java.util.ArrayList;
import java.util.HashMap;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adapter.LoadMoreAdapter;
import com.meimiao.R;

@SuppressLint("NewApi")
public class BaseFragment extends Fragment implements
		LoadMoreListView.OnLoadMore, OnRefreshListener {

	protected SwipeRefreshLayout refresh;
	protected LoadMoreListView loadmore;
	protected ArrayList<HashMap<String, String>> list;
	protected LoadMoreAdapter adapter;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.base_fragment, container, false);
		initView(view);

		Log.d("hello", "hello basefragemt");
		return view;
	}
	
	private void initView(View view) {
		refresh = (SwipeRefreshLayout) view
				.findViewById(R.id.base_fragment_refresh);
		loadmore = (LoadMoreListView) view
				.findViewById(R.id.base_fragment_load_more_listview);

		refresh.setOnRefreshListener(this);
		loadmore.setLoadMoreListen(this);
	}

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
	}

	@Override
	public void loadMore() {
		// TODO Auto-generated method stub
	}

}
