package com.example.weightlifting;

import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class SetViewActivity extends FragmentActivity {
	
	private static Set[] sets;
	private Fragment[] set_fragments;
	private ActionBar.Tab[] set_tabs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_set_view);
		
		ActionBar action_bar = getActionBar();
        action_bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        
        int no_of_sets = sets.length;
		set_fragments = new Fragment[no_of_sets];	
		set_tabs = new ActionBar.Tab[no_of_sets];
		
		for(int i=0; i<sets.length; i++){
			set_fragments[i] = SetFragment.newInstance(sets[i]);
			set_tabs[i] = action_bar.newTab();
			set_tabs[i].setText(sets[i].getName());
			set_tabs[i].setTabListener(new SetTabListener(set_fragments[i]));
			action_bar.addTab(set_tabs[i]);
		}		
	}
	
	public static final void setSetList(Set[] set_list){
		sets = set_list;
	}
}
