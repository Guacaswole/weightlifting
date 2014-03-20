package com.example.weightlifting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
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
		
		LinearLayout exercise_list_layout = (LinearLayout) findViewById(R.id.main_layout);
		for(Exercise exercise : workout.getExerciseList()){
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