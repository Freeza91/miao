package com.uis;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.meimiao.R;

public class LoadMoreListView extends ListView implements OnScrollListener {

	private View footer;
	private int totalItem;
	private int lastItem;
	private int firstVisibleItem;
	private boolean isLoading = false;
	private LayoutInflater layoutInflater;
	private Context context;
	public OnLoadMore onLoadmore;
	private LinearLayout layout;

	public LoadMoreListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initListView(context);
	}

	public LoadMoreListView(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initListView(context);
	}

	@SuppressLint("NewApi")
	public LoadMoreListView(Context context, AttributeSet attrs,
			int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		// TODO Auto-generated constructor stub
		initListView(context);
	}

	public LoadMoreListView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		initListView(context);
	}

	private void initListView(Context context) {
		this.context = context;
		layoutInflater = LayoutInflater.from(context);
		footer = layoutInflater.inflate(R.layout.load_more_listview, null,
				false);
		this.addFooterView(footer);
		layout = (LinearLayout) footer.findViewById(R.id.load_more_layout);
		layout.setVisibility(GONE);

		this.setOnScrollListener(this);
	}

	@Override
	public void onScroll(AbsListView arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		this.totalItem = arg3;
		this.lastItem = arg1 + arg2;
		this.firstVisibleItem = arg1;
	}

	@Override
	public void onScrollStateChanged(AbsListView arg0, int arg1) {
		// TODO Auto-generated method stub
		if (this.lastItem == totalItem && arg1 == SCROLL_STATE_IDLE) {
			if (this.firstVisibleItem == 0) {
				footer.setVisibility(GONE);
			} else if (!isLoading) {
				isLoading = true;
				layout.setVisibility(VISIBLE);
				Toast.makeText(context, "loading", Toast.LENGTH_LONG).show();
				onLoadmore.loadMore();
			}
		}
	}

	public void setLoadMoreListen(OnLoadMore onLoadMore) {
		this.onLoadmore = onLoadMore;
	}

	public void loadMoreComplete() {
		layout.setVisibility(GONE);
		isLoading = false;
	}

	public interface OnLoadMore {
		public void loadMore();
	}

}
