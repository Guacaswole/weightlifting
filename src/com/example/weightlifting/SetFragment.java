package com.example.weightlifting;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
		Typeface font = Typeface.createFromAsset(
				getActivity().getApplicationContext().getAssets(),"fonts/AldotheApache.ttf");
		
        View set_view = (View) inflater.inflate(R.layout.fragment_set, container, false);
        
        EditText target_weight = (EditText) set_view.findViewById(R.id.target_weight);
        TextView target_weight_label = (TextView) set_view.findViewById(R.id.target_weight_label);
        TextView target_reps   = (TextView) set_view.findViewById(R.id.target_reps);
        TextView target_reps_label   = (TextView) set_view.findViewById(R.id.target_reps_label);
        EditText reps_completed = (EditText) set_view.findViewById(R.id.reps_completed); 
        TextView reps_completed_label   = (TextView) set_view.findViewById(R.id.reps_completed_label);
        
        target_weight.setTypeface(font);
        target_weight_label.setTypeface(font);
        target_reps.setTypeface(font);
        target_reps_label.setTypeface(font);
        reps_completed.setTypeface(font);
        reps_completed_label.setTypeface(font);
        
        target_weight.setText(String.valueOf(set.getTargetWeight()));
        target_reps.setText(String.valueOf(set.getTargetReps()));
        reps_completed.setText(String.valueOf(set.getNoOfRepsCompleted()));
                
        return set_view;
    }

}
