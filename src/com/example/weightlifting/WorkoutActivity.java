package com.example.weightlifting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class WorkoutActivity extends Activity {
	
	private static final String TAG = "DEBUG";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_workout);
		
		DatabaseHandler data_source = new DatabaseHandler(this);
		User me = data_source.getUser(1);
		String workout_str = data_source.getNextWorkoutForUser(me);
		
		GsonBuilder gson_builder = new GsonBuilder();
		Gson gson = gson_builder.create();		
		Workout workout = gson.fromJson(workout_str, Workout.class);

		LinearLayout exercise_list_layout = (LinearLayout) findViewById(R.id.main_layout);
		for(Exercise exercise : workout.getExercises()){
			exercise_list_layout.addView(getExerciseRowView(exercise));
		}
		
	}

	public View getExerciseRowView(final Exercise exercise) {
		
		final View exercise_row_view = getLayoutInflater().inflate(R.layout.exercise_row_view, null);
		
		exercise_row_view.setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View v){
				Intent intent = new Intent(v.getContext(), SetViewActivity.class);
				startActivity(intent);
				SetViewActivity.setSetList(exercise.getSets());
			}
		});
		
		final View padding = (View) exercise_row_view.findViewById(R.id.row_padding);
		final TextView name = (TextView) exercise_row_view.findViewById(R.id.exercise_name);
		final TextView go = (TextView) exercise_row_view.findViewById(R.id.go_to_exercise);
		
		exercise_row_view.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
		        if(event.getAction() == MotionEvent.ACTION_DOWN) {
		        	padding.setBackgroundColor(getResources().getColor(R.color.textcolor));
		        	setViewBackgroundColourOnClick(name);
		        	setViewBackgroundColourOnClick(go);
		        } 
		        else if (event.getAction() == MotionEvent.ACTION_UP) {
		        	padding.setBackgroundColor(getResources().getColor(R.color.transparent));
		        	setViewBackgroundColourOnRelease(name);
		        	setViewBackgroundColourOnRelease(go);
		            v.performClick();
		        }
		        return true;
		    }
		});

		name.setText(exercise.getName());
		
		return exercise_row_view;
	}
	
	public void setViewBackgroundColourOnClick(TextView v){
		v.setBackgroundColor(getResources().getColor(R.color.textcolor));
        v.setTextColor(getResources().getColor(R.color.transparent));
	}
	
	public void setViewBackgroundColourOnRelease(TextView v){
		v.setBackgroundColor(getResources().getColor(R.color.transparent));
    	v.setTextColor(getResources().getColor(R.color.textcolor));
	}

}
//