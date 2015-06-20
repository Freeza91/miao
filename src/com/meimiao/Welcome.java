package com.meimiao;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.adapter.ViewPagerAdapter;

public class Welcome extends Activity {

	private ImageButton image = null;
	private ViewPager viewpager = null;
	private LinearLayout layout = null;
	private ImageButton qq = null, sina = null;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        
        getUI();
        addListener();
    }
	
	private void getUI(){
		viewpager = (ViewPager) findViewById(R.id.viewpager);
		qq = (ImageButton) findViewById(R.id.qq);
		sina = (ImageButton) findViewById(R.id.sina);
		layout = (LinearLayout) findViewById(R.id.layout);
		
		if(alreadyOpen()){
			viewpager.setVisibility(View.GONE);
		}else {
			layout.setVisibility(View.GONE);
			addViews();
		}
	}
	
	private void addViews(){
		LayoutInflater layoutInflater = getLayoutInflater();
		View view1 = layoutInflater.inflate(R.layout.view_pager_01, null);
		View view2 = layoutInflater.inflate(R.layout.view_pager_02, null);
		View view3 = layoutInflater.inflate(R.layout.view_pager_03, null);
		View view4 = layoutInflater.inflate(R.layout.view_pager_04, null);
		
		ArrayList<View> list = new ArrayList<View>();
		list.add(view1);
		list.add(view2);
		list.add(view3);
		list.add(view4);
		
		ViewPagerAdapter adapter = new ViewPagerAdapter(list);
		viewpager.setAdapter(adapter);
	}
	
	private void addListener(){
		qq.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(), MainActivity.class);
				startActivity(i);
			}
		});
		
		sina.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(), MainActivity.class);
				startActivity(i);
			}
		});
		
	}
	
	private boolean alreadyOpen(){
		SharedPreferences sp = getSharedPreferences("init", Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sp.edit();
		if(sp.getBoolean("opened?", false) == false){
			editor.putBoolean("opened?", true);
			editor.commit();
			return false;
		}else{
			return true;
		}
	}
}
