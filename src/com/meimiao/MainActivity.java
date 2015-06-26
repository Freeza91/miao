package com.meimiao;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.uis.GroupFragment;
import com.uis.HomeFragment;
import com.uis.NotifFragment;
import com.uis.SettingsFragment;

public class MainActivity extends FragmentActivity {

	private Fragment homeFragment, groupFragment, notiFragment,
			settingsFragment;
	private ArrayList<Fragment> fragments = new ArrayList<Fragment>();
	private FragmentManager fm = null;
	private FragmentTransaction ft = null;
	private LinearLayout layout_home = null, layout_group = null,
			layout_notify = null, layout_settings = null;
	private int[] dark_images = { R.drawable.home_dark, R.drawable.group_dark,
			R.drawable.notify_dark, R.drawable.settings_dark };
	private int[] light_images = { R.drawable.home_light,
			R.drawable.group_light, R.drawable.notify_light,
			R.drawable.settings_light };
	private ArrayList<LinearLayout> layouts;
	private ArrayList<ImageView> images;
	// 定义要用的颜色值
	private int white = 0xFFFFFFFF;
	private int gray = 0xFF7597B3;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initFragment();
		addLayoutListener();
	}

	@SuppressLint("NewApi")
	private void initFragment() {
		homeFragment = new HomeFragment();
		fragments.add(homeFragment);
		fm = getSupportFragmentManager();
		ft = fm.beginTransaction();
		ft.add(R.id.fragement, homeFragment, "home");
		ft.commit();
	}
	
	private void addLayoutListener(){

		layout_home = (LinearLayout) findViewById(R.id.home);
		layout_group = (LinearLayout) findViewById(R.id.group);
		layout_notify = (LinearLayout) findViewById(R.id.notify);
		layout_settings = (LinearLayout) findViewById(R.id.settings);

		images = new ArrayList<ImageView>();
		images.add((ImageView) findViewById(R.id.home_image));
		images.add((ImageView) findViewById(R.id.group_image));
		images.add((ImageView) findViewById(R.id.notify_image));
		images.add((ImageView) findViewById(R.id.settings_image));

		layouts = new ArrayList<LinearLayout>();
		layouts.add(layout_home);
		layouts.add(layout_group);
		layouts.add(layout_notify);
		layouts.add(layout_settings);

		layout_home.setOnClickListener(new LayoutOnClick("home"));
		layout_group.setOnClickListener(new LayoutOnClick("group"));
		layout_notify.setOnClickListener(new LayoutOnClick("notify"));
		layout_settings.setOnClickListener(new LayoutOnClick("settings"));
	}

	class LayoutOnClick implements OnClickListener {

		private String tag = null;

		public LayoutOnClick(String tag) {
			// TODO Auto-generated constructor stub
			this.tag = tag;
		}

		@SuppressLint("NewApi") @Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			ft = fm.beginTransaction();

			switch (tag) {
			case "home":
				if (homeFragment == null) {
					homeFragment = new HomeFragment();
					ft.add(R.id.fragement, homeFragment, tag);
					fragments.add(homeFragment);
				} else if (fm.findFragmentByTag(tag) != null) {
					ft.show(homeFragment);
				}
				SetNav(0);
				break;
			case "group":
				if (groupFragment == null) {
					Log.d("hello", tag);
					groupFragment = new GroupFragment(MainActivity.this);
					ft.add(R.id.fragement, groupFragment, tag);
					fragments.add(groupFragment);
				} else if (fm.findFragmentByTag(tag) != null) {
					ft.show(groupFragment);
				}
				SetNav(1);
				break;
			case "notify":
				if (notiFragment == null) {
					notiFragment = new NotifFragment(MainActivity.this);
					ft.add(R.id.fragement, notiFragment, tag);
					fragments.add(notiFragment);
				} else if (fm.findFragmentByTag(tag) != null) {
					ft.show(notiFragment);
				}
				SetNav(2);
				break;
			case "settings":
				if (settingsFragment == null) {
					settingsFragment = new SettingsFragment(MainActivity.this);
					ft.add(R.id.fragement, settingsFragment, tag);
					fragments.add(settingsFragment);
				} else if (fm.findFragmentByTag(tag) != null) {
					ft.show(settingsFragment);
				}
				SetNav(3);
				break;
			default:
				break;
			}
			hideOthers(tag);
			ft.commit();
		}

		@SuppressLint("NewApi")
		private void hideOthers(String tag) {

			for (int i = 0; i < fragments.size(); i++) {
				Fragment fragment = (Fragment) fragments.get(i);
				if (fragment != null && fragment.getTag() != tag) {
					ft.hide(fragment);
				}
			}
		}
		@SuppressLint("NewApi")
		private void SetNav(int num) {
			LinearLayout layout = null;
			ImageView image = null;
			for (int i = 0; i < dark_images.length; i++) {
				if (num == i) {
					image = images.get(i);
					image.setImageDrawable(getResources().getDrawable(
							light_images[i]));
					layout = layouts.get(i);
					layout.setBackgroundColor(gray);
				} else {
					image = images.get(i);
					image.setImageDrawable(getResources().getDrawable(
							dark_images[i]));
					layout = layouts.get(i);
					layout.setBackgroundColor(white);
				}
			}
		}
	}

}
