package com.uis;

import helpers.StatusCode;

import java.util.ArrayList;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.models.V2exApiJsonExampleModel;

public class SettingsTopic extends BaseFragment implements
		Response.Listener<ArrayList<V2exApiJsonExampleModel>>,
		Response.ErrorListener, StatusCode {


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

	@Override
	public void onErrorResponse(VolleyError error) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onResponse(ArrayList<V2exApiJsonExampleModel> response) {
		// TODO Auto-generated method stub

	}

}
