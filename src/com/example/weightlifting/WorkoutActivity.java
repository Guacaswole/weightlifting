package com.example.weightlifting;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

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
			exercise_list_layout.addView(getExerciseRowView(exercise));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.workout, menu);
		return true;
	}
	
	public LinearLayout getExerciseRowView(final Exercise exercise) {
		
		LinearLayout exercise_row_view = new LinearLayout(this);
		exercise_row_view.setOrientation(LinearLayout.HORIZONTAL);
		exercise_row_view.setClickable(true);
		
		exercise_row_view.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v){
				Intent intent = new Intent(v.getContext(), SetViewActivity.class);
				startActivity(intent);
				SetViewActivity.setSetList(exercise.getSets());
			}
		});
		
		LinearLayout.LayoutParams layout_params = new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		
		exercise_row_view.setLayoutParams(layout_params);
		
		TextView name = new TextView(this);
		name.setText(exercise.getName());
		name.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
		name.setLayoutParams(new LayoutParams
				(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		exercise_row_view.addView(name);
		
		return exercise_row_view;
	}

}
//