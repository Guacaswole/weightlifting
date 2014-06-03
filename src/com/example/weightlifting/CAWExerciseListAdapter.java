package com.example.weightlifting;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

// Create-A-Workout ExerciseListAdapter
public class CAWExerciseListAdapter extends ArrayAdapter<String>{

	private final Context context;
	private String[] exercise_list;
	private static List<Exercise> workout;

	public CAWExerciseListAdapter(Context context, String[] exercise_list) {
		super(context, R.layout.caw_list_item, exercise_list);
		this.exercise_list = exercise_list;
		this.context = context;
		workout = new ArrayList<Exercise>();
	}
	
	@Override
	public View getView(final int position, final View convertView, ViewGroup parent) {
		
		LayoutInflater inflater = (LayoutInflater) context
		        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		final View row_view = inflater.inflate(R.layout.caw_list_item, null);
		
		TextView tv_exercise_name = (TextView) row_view.findViewById(R.id.exercise_name);
		final String exercise_name = exercise_list[position];
		tv_exercise_name.setText(exercise_list[position]);
		
		row_view.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				int pos = checkIfExerciseIsInWorkout(exercise_name);
				if(pos >= 0) {
					workout.remove(pos);
					row_view.setBackgroundColor(context.getResources().getColor(R.color.not_selected));
					Toast.makeText(context, exercise_name + " has been removed from your workout: " + workout.size(),
							   Toast.LENGTH_SHORT).show();
				}
				else {
					workout.add(new Exercise(exercise_name));
					row_view.setBackgroundColor(context.getResources().getColor(R.color.light_green));
					Toast.makeText(context, exercise_name + " has been added to your workout: " + workout.size(),
							   Toast.LENGTH_SHORT).show();
				}
			}

			private int checkIfExerciseIsInWorkout(String exercise_name) {
				int length = workout.size();
				if(length > 0){
					for(int i=0; i<length; i++){
						if(workout.get(i).getName() == exercise_name){
							return i;
						}
					}
					return -1;
				}
				else {
					return -1;
				}				
			}
		});
		
		return row_view;
	}
	
	public Workout getWorkout(){
		return new Workout(workout.toArray(new Exercise[workout.size()]));
	}
}
