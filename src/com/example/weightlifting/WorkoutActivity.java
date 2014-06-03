package com.example.weightlifting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

public class WorkoutActivity extends Activity {
	
	private static final String TAG = "DEBUG";
	
    private String loadWorkout() throws IOException{
        //Log.d(Constants.TAG, "Loading WODs...");
        
        Resources resources = getApplicationContext().getResources();
        InputStream input = resources.openRawResource(R.raw.workout_json);
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        String workout_str = "";
        try {
        	String line = "";
        	while ((line = reader.readLine()) != null){
        		workout_str = line;
        		//Log.d(Constants.TAG, "Added WOD [" + line +"]");
        	}
        } finally {
        	reader.close();
        }
        return workout_str;
        //Log.d(Constants.TAG, "WODs loading completed.");
    }

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_workout);
		
		/* DatabaseHandler data_source = new DatabaseHandler(this);
		User me = data_source.getUser(1);
		String workout_str = data_source.getNextWorkoutForUser(me); */
		
		GsonBuilder gson_builder = new GsonBuilder();
		Gson gson = gson_builder.create();		
		Workout workout = new Workout();
		try {
			workout = gson.fromJson(loadWorkout(), Workout.class);
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		LinearLayout exercise_list_layout = (LinearLayout) findViewById(R.id.main_layout);
		if(workout.getExercises().length > 0){
			for(Exercise exercise : workout.getExercises()){
				exercise_list_layout.addView(getExerciseRowView(exercise));
			}
		}
		
	}

	public View getExerciseRowView(final Exercise exercise) {
		
		final View exercise_row_view = getLayoutInflater().inflate(R.layout.exercise_row_view, null);
		
		exercise_row_view.setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View v){
				Intent intent = new Intent(v.getContext(), SetViewActivity.class);
				startActivity(intent);
				SetViewActivity.setExercise(exercise);
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