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
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.meimiao.R;
import com.models.V2exApiJsonExampleModel;
import com.nets.MainActivityHttp;

@SuppressLint("NewApi")
public class HomeFragment extends Fragment implements
		LoadMoreListView.OnLoadMore,
		OnRefreshListener,
		Response.Listener<ArrayList<V2exApiJsonExampleModel>>,
		Response.ErrorListener {

	private RequestQueue mQueue;

	private SwipeRefreshLayout refresh;
	private LoadMoreListView loadmore;
	private ArrayList<HashMap<String, String>> list;
	private LoadMoreAdapter adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.home, container, false);
		initView(view);
		initData();

		return view;
	}

	private void initView(View view) {
		refresh = (SwipeRefreshLayout) view.findViewById(R.id.refresh);
		loadmore = (LoadMoreListView) view
				.findViewById(R.id.load_more_listview);
		
		refresh.setOnRefreshListener(this);
		loadmore.setLoadMoreListen(this);
	}

	private void initData() {
		mQueue = Volley.newRequestQueue(getActivity());
		mQueue.add(new MainActivityHttp(this, this));

		list = new ArrayList<HashMap<String, String>>();
		for (int i = 0; i < 10; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("key", "this is " + i);
			list.add(map);
		}
		adapter = new LoadMoreAdapter(getActivity(), list);
		loadmore.setAdapter(adapter);
	}

	@Override
	public void loadMore() {
		// TODO Auto-generated method stub
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("key", "this is add by loadmore");
		list.add(map);
		Log.d("coming", "comming loadmore");
		adapter.notifyDataSetChanged();
		loadmore.loadMoreComplete();

		mQueue.add(new MainActivityHttp(this, this));
	}

	@Override
	public void onErrorResponse(VolleyError error) {
		// TODO Auto-generated method stub

	}

	public void onResponse(ArrayList<V2exApiJsonExampleModel> response) {
		// TODO Auto-generated method stub
		ArrayList<V2exApiJsonExampleModel> models = response;
		for (int i = 0; i < models.size(); i++) {
			V2exApiJsonExampleModel model = models.get(i);
			Log.d("id", model.id);
			Log.d("inner id", model.member.id);
		}
	}

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		list = new ArrayList<HashMap<String, String>>();
		for (int i = 0; i < 10; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("key", "this is " + i);
			list.add(map);
		}
		adapter = new LoadMoreAdapter(getActivity(), list);
		loadmore.setAdapter(adapter);
		mQueue.add(new MainActivityHttp(this, this));

		refresh.setRefreshing(false);

	}

}
