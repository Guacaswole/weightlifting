package com.example.weightlifting;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class SetFragment extends Fragment {
	
	final static String EXTRA_EXERCISE_NAME = "EXERCISE_NAME";
	
    public static final SetFragment newInstance(Exercise exercise){
    	SetFragment set_fragment = new SetFragment();
    	Bundle bundle = new Bundle(1);
    	
    	bundle.putString(EXTRA_EXERCISE_NAME, exercise.getName());
    	
    	return set_fragment;
    }
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
		
		// TEMP!!!
		String extra_exercise_name = getArguments().getString(EXTRA_EXERCISE_NAME);
		// TEMP!!!! RECONSTRUCT FROM BUNDLE!!!
		
        View set_view = (View) inflater.inflate(R.layout.fragment_set, container, false);
        
        TextView exercise_name = (TextView) set_view.findViewById(R.id.exercise_name);
        TextView target_weight = (TextView) set_view.findViewById(R.id.target_weight);
        TextView target_reps   = (TextView) set_view.findViewById(R.id.target_reps);
        
        EditText actual_weight        = (EditText) set_view.findViewById(R.id.actual_weight);
        EditText no_of_reps_completed = (EditText) set_view.findViewById(R.id.no_of_reps_completed); 
        
        exercise_name.setText(extra_exercise_name);
        
        return set_view;
    }

}
