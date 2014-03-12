package com.example.weightlifting;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.ListView;

public class WorkoutActivity extends Activity {
	
	private ListView exercise_listview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_workout);
		
		String workout_str="Back Squat:4x3;" +
				"Power Cl&Jerk:4x1x2;" +
				"Power Snatch:3x3;" +
				"Clean Pulls:4x5";
		
		Workout workout = new Workout(workout_str);
		
		LinearLayout exercise_list_layout = (LinearLayout) findViewById(R.id.main_layout);
		for(Exercise exercise : workout.getExerciseList()){
			exercise_list_layout.addView(exercise.getExerciseRowView(this));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.workout, menu);
		return true;
	}

}
//