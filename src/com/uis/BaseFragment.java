package com.uis;

import java.util.ArrayList;
import java.util.HashMap;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adapters.LoadMoreAdapter;
import com.meimiao.R;

@SuppressLint("NewApi")
public class BaseFragment extends Fragment implements
		LoadMoreListView.OnLoadMore, OnRefreshListener {

	protected SwipeRefreshLayout refresh;
	protected LoadMoreListView loadmore;
	protected ArrayList<HashMap<String, String>> list;
	protected HashMap<String, String> map;
	protected LoadMoreAdapter adapter;
	protected final int REFRESH = 0, LOADMORE = 1;
	protected int UpdateStatus = REFRESH;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.base_fragment, container, false);
		initView(view);

		return view;
	}
	
	private void initView(View view) {
		refresh = (SwipeRefreshLayout) view
				.findViewById(R.id.base_fragment_refresh);
		loadmore = (LoadMoreListView) view
				.findViewById(R.id.base_fragment_load_more_listview);

		refresh.post(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				refresh.setRefreshing(true);
			}
		});
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
