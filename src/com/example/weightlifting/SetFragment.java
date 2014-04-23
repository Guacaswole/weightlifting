package com.example.weightlifting;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class SetFragment extends Fragment {
	
	final static String EXTRA_SET_GSON = "SET_GSON";

    public static final SetFragment newInstance(Set set){
    	SetFragment set_fragment = new SetFragment();
    	Bundle bundle = new Bundle(1);
    	GsonBuilder gson_builder = new GsonBuilder();
    	Gson gson = gson_builder.create();
		
		String set_gson = gson.toJson(set);
    	bundle.putString(EXTRA_SET_GSON, set_gson);
    	set_fragment.setArguments(bundle);
    	
    	return set_fragment;
    }
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
		
		String set_gson = getArguments().getString(EXTRA_SET_GSON);
		GsonBuilder gson_builder = new GsonBuilder();
    	Gson gson = gson_builder.create();
		Set set = gson.fromJson(set_gson, Set.class);
		
        View set_view = (View) inflater.inflate(R.layout.fragment_set, container, false);
        
        EditText target_weight = (EditText) set_view.findViewById(R.id.target_weight);
        TextView target_reps   = (TextView) set_view.findViewById(R.id.target_reps);
        EditText no_of_reps_completed = (EditText) set_view.findViewById(R.id.reps_completed); 
        
        target_weight.setText(String.valueOf(set.getTargetWeight()));
        target_reps.setText(String.valueOf(set.getTargetReps()));
        no_of_reps_completed.setText(String.valueOf(set.getNoOfRepsCompleted()));
        
        
        return set_view;
    }

}
