package com.example.weightlifting;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class SetViewAdapter extends FragmentPagerAdapter{
	
	private List<Fragment> set_fragments;

	public SetViewAdapter(FragmentManager fm, Set[] sets) {
		super(fm);
		
		set_fragments = new ArrayList<Fragment>();
		
		for (Set set : sets) {
			set_fragments.add(SetFragment.newInstance(set));
		}
	}
	
	@Override
	public Fragment getItem(int position){
		return set_fragments.get(position);
	}
	
	@Override
	public int getCount(){
		return set_fragments.size();
	}

}
