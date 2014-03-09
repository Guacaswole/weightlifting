package com.example.weightlifting;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class WorkoutActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_workout);
		
		String workout_str="Back Squat:4x3;" +
				"Power Cl&Jerk:4x1x2;" +
				"Power Snatch:3x3;" +
				"Clean Pulls:4x5";
		
		Workout workout = new Workout(workout_str);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.workout, menu);
		return true;
	}

}
