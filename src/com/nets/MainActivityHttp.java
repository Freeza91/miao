package com.nets;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import apis.V2exExampleApi;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.models.V2exApiJsonExampleModel;

// http://www.pocketdigi.com/20140511/1315.html

public class MainActivityHttp extends JsonRequest<ArrayList<V2exApiJsonExampleModel>> {

	public MainActivityHttp(
			Response.Listener<ArrayList<V2exApiJsonExampleModel>> listener,
			Response.ErrorListener errorListener) {
		super(Method.GET, V2exExampleApi.hot, null, listener, errorListener);
	}

	@Override
	protected Response<ArrayList<V2exApiJsonExampleModel>> parseNetworkResponse(
			NetworkResponse response) {
		// 配合Gson,转换成我们定义的V2exApiJsonExampleModel
		try {
			String json = new String(response.data,
					HttpHeaderParser.parseCharset(response.headers));
			Gson gson = new Gson();
			return Response.success(V2exApiJsonExampleModel.parseList(json),
					HttpHeaderParser.parseCacheHeaders(response));
		} catch (UnsupportedEncodingException e) {
			return Response.error(new ParseError(e));
		} catch (JsonSyntaxException e) {
			return Response.error(new ParseError(e));
		}
	}

}
