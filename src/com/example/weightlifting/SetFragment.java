package com.example.weightlifting;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SetFragment extends Fragment {
	
	private static final String EXTRA_SET_GSON = "SET_GSON";
	private static final String EXTRA_EXERCISE_NAME = "EXERCISE_NAME";
	
	private static EditText target_weight;
	private static EditText reps_completed;
	private static Set set;

    public static final SetFragment newInstance(Set set, String exercise_name){
    	SetFragment set_fragment = new SetFragment();
    	Bundle bundle = new Bundle();
    	GsonBuilder gson_builder = new GsonBuilder();
    	Gson gson = gson_builder.create();
		
		String set_gson = gson.toJson(set);
    	bundle.putString(EXTRA_SET_GSON, set_gson);
    	bundle.putString(EXTRA_EXERCISE_NAME, exercise_name);
    	set_fragment.setArguments(bundle);
    	
    	return set_fragment;
    }
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
		
		String set_gson = getArguments().getString(EXTRA_SET_GSON);
		String exercise_name = getArguments().getString(EXTRA_EXERCISE_NAME);
		
		GsonBuilder gson_builder = new GsonBuilder();
    	Gson gson = gson_builder.create();
		set = gson.fromJson(set_gson, Set.class);
		Typeface font = Typeface.createFromAsset(
				getActivity().getApplicationContext().getAssets(),"fonts/forcedSquare.ttf");
		
        View set_view = (View) inflater.inflate(R.layout.fragment_set, container, false);
        
        TextView exercise_title = (TextView) set_view.findViewById(R.id.exercise_name);
        TextView target_weight_label = (TextView) set_view.findViewById(R.id.target_weight_label);
        TextView target_reps_label   = (TextView) set_view.findViewById(R.id.target_reps_label);
        TextView reps_completed_label   = (TextView) set_view.findViewById(R.id.reps_completed_label);
        
        target_weight = (EditText) set_view.findViewById(R.id.target_weight);
        TextView target_reps   = (TextView) set_view.findViewById(R.id.target_reps);
        reps_completed = (EditText) set_view.findViewById(R.id.reps_completed); 

        
        exercise_title.setTypeface(font);
        target_weight.setTypeface(font);
        target_weight_label.setTypeface(font);
        target_reps.setTypeface(font);
        target_reps_label.setTypeface(font);
        reps_completed.setTypeface(font);
        reps_completed_label.setTypeface(font);
        
        exercise_title.setText(exercise_name);
        target_weight.setText(String.valueOf(set.getTargetWeight()));
        target_reps.setText(String.valueOf(set.getTargetReps()));
        reps_completed.setText(String.valueOf(set.getNoOfRepsCompleted()));
                
        return set_view;
    }
	
	@Override
	public void onStop() {
		set.setTargetWeight(Integer.parseInt(target_weight.getText().toString()));
		set.setNoOfRepsCompleted(Integer.parseInt(reps_completed.getText().toString()));
		super.onStop();
	}
}
