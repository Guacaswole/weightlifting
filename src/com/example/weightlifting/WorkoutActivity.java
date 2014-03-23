package com.example.weightlifting;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class WorkoutActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_workout);
		
		File file = new File("C:\\Users\\Mark\\Desktop\\workout_json.txt");

		Reader reader = null;
		try {
			reader = new FileReader(file);
		} catch (FileNotFoundException e) {
			Log.e("DBG", "WorkoutActivity:onCreate() FIle not found");
			e.printStackTrace();
		}
		
		GsonBuilder gb = new GsonBuilder();
		Gson g = gb.create();

		Workout workout = g.fromJson(reader, Workout.class);
		
		LinearLayout exercise_list_layout = (LinearLayout) findViewById(R.id.main_layout);
		for(Exercise exercise : workout.getExercises()){
			exercise_list_layout.addView(getExerciseRowView(exercise));
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