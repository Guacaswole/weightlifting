package com.example.weightlifting;

import android.content.Context;
import android.content.Intent;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class Exercise {
	
	private String name;
	private Set[] sets;
	
	public String getName(){ return name; }
	public Set[] getSets() { return sets; }
	public int getSetsTotal() { return sets.length; }
	
	public Exercise(String exercise_str){
		name = parseExerciseString(exercise_str);
	}

	public Exercise(String name, int no_of_sets, int rep_target){
		this.name = name;
		
		sets = new Set[no_of_sets];
		for(int i = 0; i<no_of_sets; i++){
			sets[i] = new Set(i+1, rep_target);
		}
	}
	
	// Returns Exercise name, and creates Sets array
	private String parseExerciseString(String exercise_str) {
		// "Back Squat:4x3;"
		
		// TODO: Implement double reps -> 1 clean, 2 jerks
		// "Power Cl&Jerk:4x1x2;"
		
		int NAME = 0;
		int SETS = 1;
		
		String[] name_and_sets = exercise_str.split(":");
		String[] sets_and_reps = name_and_sets[SETS].split("x");
		
		SETS = 0;
		int REPS = 1;
		
		int no_of_sets = Integer.parseInt(sets_and_reps[SETS]);
		int target_reps = Integer.parseInt(sets_and_reps[REPS]);
		
		sets = new Set[no_of_sets];
		for(int i=0; i < no_of_sets; i++){
			sets[i] = new Set(i+1, target_reps);
		}
		
		return name_and_sets[NAME];
	}
	
	public LinearLayout getExerciseRowView(final Context context) {
		
		LinearLayout exercise_row_view = new LinearLayout(context);
		exercise_row_view.setOrientation(LinearLayout.HORIZONTAL);
		exercise_row_view.setClickable(true);
		
		exercise_row_view.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v){
				Intent intent = new Intent(context, SetViewActivity.class);
				context.startActivity(intent);
				SetViewActivity.setSetList(getSets());
			}
		});
		
		LinearLayout.LayoutParams layout_params = new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		
		exercise_row_view.setLayoutParams(layout_params);
		
		TextView name = new TextView(context);
		name.setText(getName());
		name.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
		name.setLayoutParams(new LayoutParams
				(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		exercise_row_view.addView(name);
		
		return exercise_row_view;
	}
}
