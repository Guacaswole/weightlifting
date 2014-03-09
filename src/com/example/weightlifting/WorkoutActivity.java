package com.example.weightlifting;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

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
		for(Exercise exercise : workout.getExerciseList()){
			//createLayoutForExercise(exercise);
		}
	}
	
	private LinearLayout createLayoutForExercise(Exercise exercise){
		LinearLayout layout = new LinearLayout(this);
		
		LinearLayout.LayoutParams layout_params = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT
				);
		
		// Label for the name
		TextView exercise_name = new TextView(this);
		exercise_name.setText(exercise.getName());

		// Create Params 
		LinearLayout.LayoutParams exercise_name_params = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT
				);
		LinearLayout.LayoutParams set_params = exercise_name_params;
		
		exercise_name_params.weight = 1.0f;
		exercise_name.setLayoutParams(exercise_name_params);
		
		layout.setLayoutParams(layout_params);
		layout.addView(exercise_name);
		
		// Edit Texts for the sets
		EditText[] sets = new EditText[exercise.getNumberOfSets()];
		for(EditText set : sets){
			set = new EditText(this);
			set.setLayoutParams(set_params);
			layout.addView(set);
		}	
		
		return layout;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.workout, menu);
		return true;
	}

}
