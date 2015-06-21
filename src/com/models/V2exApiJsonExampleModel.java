package com.models;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class V2exApiJsonExampleModel extends BaseModel {

	// example http://www.v2ex.com/api/topics/hot.json
	/*
	 * id, title, url, content, content_rendered, replies: 90, member: { id,
	 * username, tagline, avatar_mini, avatar_normal, avatar_large }, node: {
	 * id, name, title, title_alternative, url: , topics, avatar_mini ,
	 * avatar_normal, avatar_large }, created, last_modified, last_touched
	 */

	private static final long serialVersionUID = 4833148966513158835L;

	private static ArrayList<V2exApiJsonExampleModel> list;
	public Member member;

	public class Member {
		public String id;
	}

	public static ArrayList<V2exApiJsonExampleModel> parseList(String data) {
		list = new ArrayList<V2exApiJsonExampleModel>();
		Gson gson = new Gson();
		list = gson.fromJson(data,
				new TypeToken<ArrayList<V2exApiJsonExampleModel>>() {
				}.getType());
		return list;
	}

	public String id, title;

}
