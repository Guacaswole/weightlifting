package com.example.weightlifting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class WODExerciseListAdapter extends ArrayAdapter<Exercise>{
	
	private final Context context;
	private Exercise[] exercise_list;

	public WODExerciseListAdapter(Context context, Workout wod) {
		super(context, R.layout.wod_list_item, wod.getExercises());
		this.exercise_list = wod.getExercises();
		this.context = context;
	}
	
	@Override
	public View getView(final int position, final View convertView, ViewGroup parent) {
		
		Exercise exercise = exercise_list[position];
		LayoutInflater inflater = (LayoutInflater) context
		        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		final View row_view = inflater.inflate(R.layout.exercise_list_item, null);
		
        //Log.d(Constants.TAG, "Listview: " + exercise.getName());
		
		TextView tv_reps = (TextView) row_view.findViewById(R.id.reps);
		tv_reps.setText(String.valueOf(exercise.getSets()[0].getTargetReps()));
		TextView tv_name = (TextView) row_view.findViewById(R.id.name);
		tv_name.setText(exercise.getName());
		TextView tv_weight = (TextView) row_view.findViewById(R.id.weight);
		
		int target_weight = exercise.getSets()[0].getTargetWeight();
		String weight_str = "";
		if(target_weight == 0){
			weight_str = "Body Weight";
		}
		else {
			weight_str = String.valueOf(target_weight) + "kg";
		}
		tv_weight.setText(weight_str);
		 
		return row_view;
	}
}
