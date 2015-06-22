package com.uis;

import helpers.StatusCode;

import java.util.ArrayList;
import java.util.HashMap;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.adapter.LoadMoreAdapter;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.meimiao.PostCameraPictures;
import com.meimiao.PostShortMessage;
import com.meimiao.R;
import com.models.V2exApiJsonExampleModel;
import com.nets.MainActivityHttp;

@SuppressLint("NewApi")
public class HomeFragment extends Fragment implements
		LoadMoreListView.OnLoadMore,
		OnRefreshListener,
		Response.Listener<ArrayList<V2exApiJsonExampleModel>>,
		Response.ErrorListener, StatusCode {

	private RequestQueue mQueue;

	private SwipeRefreshLayout refresh;
	private LoadMoreListView loadmore;
	private ArrayList<HashMap<String, String>> list;
	private LoadMoreAdapter adapter;
	private ImageButton carame, choice, dynamic;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.home, container, false);
		initView(view);
		initData();
		addListener();

		return view;
	}

	private void initView(View view) {
		refresh = (SwipeRefreshLayout) view.findViewById(R.id.refresh);
		loadmore = (LoadMoreListView) view
				.findViewById(R.id.load_more_listview);
		carame = (ImageButton) view.findViewById(R.id.camera);
		choice = (ImageButton) view.findViewById(R.id.choice);
		dynamic = (ImageButton) view.findViewById(R.id.dynamic);
		
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

	private void addListener() {
		refresh.setOnRefreshListener(this);
		loadmore.setLoadMoreListen(this);

		carame.setOnLongClickListener(new OnLongClickListener() {

			@Override
			public boolean onLongClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(),
						PostShortMessage.class);
				startActivityForResult(intent, POST_MESSAGE_SUCCESS);
				return false;
			}
		});

		carame.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(),
						PostCameraPictures.class);
				startActivityForResult(intent, POST_CAMERA_SUCCESS);
			}
		});

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

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		switch (resultCode) {
		case POST_MESSAGE_SUCCESS:
			Toast.makeText(getActivity(), "发布消息成功", Toast.LENGTH_SHORT).show();
			break;
		case QUIT_POST_MESSAGE:
			Toast.makeText(getActivity(), "放弃发布消息", Toast.LENGTH_SHORT).show();
			break;
		case POST_CAMERA_SUCCESS:
			Toast.makeText(getActivity(), "发布图片成功", Toast.LENGTH_SHORT).show();
			break;
		case QUIT_POST_CAMERA:
			Toast.makeText(getActivity(), "放弃发布图片", Toast.LENGTH_SHORT).show();
			break;
		default:
			break;
		}
	}

}
