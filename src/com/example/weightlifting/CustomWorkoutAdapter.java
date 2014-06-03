package com.example.weightlifting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomWorkoutAdapter extends ArrayAdapter<Exercise> {
	
	private Exercise[] exercise_list;
	private Context context;

	public CustomWorkoutAdapter(Context context, Workout workout) {
		super(context, R.layout.custom_workout_list_item, workout.getExercises());
		exercise_list = workout.getExercises();
		this.context = context;
	}
	
	@Override
	public View getView(final int position, final View convertView, ViewGroup parent) {
		
		Exercise exercise = exercise_list[position];
		LayoutInflater inflater = (LayoutInflater) context
		        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		final View row_view = inflater.inflate(R.layout.custom_workout_list_item, null);
		
		TextView tv_name = (TextView) row_view.findViewById(R.id.exercise_name);
		tv_name.setText(exercise.getName());
		
		
		return row_view;
	}

}
