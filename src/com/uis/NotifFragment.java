package com.uis;

import helpers.StatusCode;

import java.util.ArrayList;
import java.util.HashMap;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adapter.LoadMoreAdapter;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.models.V2exApiJsonExampleModel;
import com.nets.MainActivityHttp;

@SuppressLint("NewApi")
public class NotifFragment extends BaseFragment implements
		Response.Listener<ArrayList<V2exApiJsonExampleModel>>,
		Response.ErrorListener, StatusCode {

	private RequestQueue mQueue;
	private Context context;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = super.onCreateView(inflater, container, savedInstanceState);
		initData();
		return view;
	}

	public NotifFragment(Context context) {
		this.context = context;
	}

	private void initData() {
		mQueue = Volley.newRequestQueue(context);
		mQueue.add(new MainActivityHttp(this, this));

		list = new ArrayList<HashMap<String, String>>();
		for (int i = 0; i < 20; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("key", "this is " + i);
			list.add(map);
		}

		adapter = new LoadMoreAdapter(context, list);
		loadmore.setAdapter(adapter);
	}

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		super.onRefresh();
		list = new ArrayList<HashMap<String, String>>();
		for (int i = 0; i < 10; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("key", "this is " + i);
			list.add(map);
		}
		adapter = new LoadMoreAdapter(this.context, list);
		loadmore.setAdapter(adapter);
		mQueue.add(new MainActivityHttp(this, this));

		refresh.setRefreshing(false);

	}

	@Override
	public void loadMore() {
		// TODO Auto-generated method stub
		super.loadMore();
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

	@Override
	public void onResponse(ArrayList<V2exApiJsonExampleModel> response) {
		// TODO Auto-generated method stub
		ArrayList<V2exApiJsonExampleModel> models = response;
		for (int i = 0; i < models.size(); i++) {
			V2exApiJsonExampleModel model = models.get(i);
			Log.d("id", model.id);
			Log.d("inner id", model.member.id);
		}
	}

}
