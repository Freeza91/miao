package com.uis;

import helpers.StatusCode;

import java.util.ArrayList;
import java.util.HashMap;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.adapters.LoadMoreAdapter;
import com.adapters.SettingsFragmentViewPgaer;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.meimiao.R;
import com.models.V2exApiJsonExampleModel;
import com.nets.MainActivityHttp;

@SuppressLint("NewApi")
public class SettingsFragment extends BaseFragment implements
		Response.Listener<ArrayList<V2exApiJsonExampleModel>>,
		Response.ErrorListener, StatusCode {

	private RequestQueue mQueue;
	private Context context;
	private ViewPager settings_viewpager;
	private LinearLayout settings_picture, settings_topic, settings_followers,
			settings_fans;
	private TextView settings_picture_num, settings_topic_num,
			settings_followers_num, settings_fans_num;
	private View settings_divider;
	
	private ArrayList<Fragment> viewpagerList;
	private SettingsFragmentViewPgaer settings_adapter;
	private int currentIndex;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateView(inflater, container, savedInstanceState);

		View view = inflater.inflate(R.layout.settings, null, false);
		initView(view);
		initData();
		addListener();
		return view;
	}

	public SettingsFragment(Context context) {
		this.context = context;
	}

	private void initView(View view) {
		settings_viewpager = (ViewPager) view
				.findViewById(R.id.settings_viewpager);

		settings_picture = (LinearLayout) view.findViewById(R.id.settings_picture);
		settings_topic = (LinearLayout) view.findViewById(R.id.settings_topic);
		settings_followers = (LinearLayout) view.findViewById(R.id.settings_followers);
		settings_fans = (LinearLayout) view.findViewById(R.id.settings_fans);
		
		settings_picture_num = (TextView) view.findViewById(R.id.settings_picture_num);
		settings_topic_num = (TextView) view
				.findViewById(R.id.settings_topic_num);
		settings_followers_num = (TextView) view
				.findViewById(R.id.settings_followers_num);
		settings_fans_num = (TextView) view
				.findViewById(R.id.settings_fans_num);
		
		settings_divider = (View) view.findViewById(R.id.settings_divider);

		viewpagerList = new ArrayList<Fragment>();
		viewpagerList.add(new SettingsFragmentPicture());
		viewpagerList.add(new SettingsFragmentTopic());
		viewpagerList.add(new SettingsFragmentFollowers());
		viewpagerList.add(new SettingsFragmentFans());
		settings_adapter = new SettingsFragmentViewPgaer(getActivity()
				.getSupportFragmentManager(), viewpagerList);
		settings_viewpager.setAdapter(settings_adapter);
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

	private void addListener(){
		settings_viewpager
				.setOnPageChangeListener(new MyOnPageChangeListener());
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

	private class MyOnPageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub
			// LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams)
			// .getLayoutParams();
		}

		@Override
		public void onPageSelected(int arg0) {
			// TODO Auto-generated method stub
			currentIndex = arg0;
		}

	}
}
