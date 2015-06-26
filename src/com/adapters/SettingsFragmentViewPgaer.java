package com.adapters;

import java.util.ArrayList;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class SettingsFragmentViewPgaer extends FragmentPagerAdapter {

	private ArrayList<Fragment> list;

	public SettingsFragmentViewPgaer(FragmentManager fm,
			ArrayList<Fragment> viewpagerList) {
		super(fm);
		this.list = viewpagerList;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

}