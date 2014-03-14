package com.example.weightlifting;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

public class SetViewActivity extends FragmentActivity {
	
	private static Set[] sets;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_set_view);
		
		SetViewAdapter set_view_adapter = new SetViewAdapter(getSupportFragmentManager(), sets);
		ViewPager set_view_pager = (ViewPager) findViewById(R.id.set_view_pager);
	    set_view_pager.setAdapter(set_view_adapter);		
	}
	
	public static final void setSetList(Set[] set_list){
		sets = set_list;
	}
}
