package com.example.weightlifting;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
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
		
		AssetManager asset_manager = getAssets();
		
		InputStream workout_json_file;
		
		workout_json_file = null;
		try {
			workout_json_file = asset_manager.open("workout_json.txt");
		} catch (IOException e1) {
			Log.e(TAG, "WorkoutActivity:onCreate() File not found");
			e1.printStackTrace();
		}

		Reader file_reader = null;
		file_reader = new BufferedReader(new InputStreamReader(workout_json_file));
		
		if(file_reader != null){
			GsonBuilder gson_builder = new GsonBuilder();
			Gson gson = gson_builder.create();		
			Workout workout = gson.fromJson(file_reader, Workout.class);

			LinearLayout exercise_list_layout = (LinearLayout) findViewById(R.id.main_layout);
			for(Exercise exercise : workout.getExercises()){
				exercise_list_layout.addView(getExerciseRowView(exercise));
			}
		}
	}

	public View getExerciseRowView(final Exercise exercise) {
		
		View exercise_row_view = getLayoutInflater().inflate(R.layout.exercise_row_view, null);
		
		Button exercise_button = (Button) exercise_row_view.findViewById(R.id.go_to_exercise);
		exercise_button.setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View v){
				Intent intent = new Intent(v.getContext(), SetViewActivity.class);
				startActivity(intent);
				SetViewActivity.setSetList(exercise.getSets());
			}
		});

		TextView name = (TextView) exercise_row_view.findViewById(R.id.exercise_name);
		name.setText(exercise.getName());
		
		return exercise_row_view;
	}

}
//