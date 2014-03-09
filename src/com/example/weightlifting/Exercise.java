package com.example.weightlifting;

import android.content.Context;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Exercise {
	
	String name;
	int weight;
	Set[] sets;
	
	public String getName(){ return name; }
	public int getWeight(){ return weight; }
	public Set[] getSets() { return sets; }
	public int getNumberOfSets() { return sets.length; }
	
	public Exercise(String exercise_str){
		name = parseExerciseString(exercise_str);
	}

	public Exercise(String name, int no_of_sets, int rep_target){
		this.name = name;
		
		sets = new Set[no_of_sets];
		for(Set set : sets){
			set = new Set(rep_target);
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
			sets[i] = new Set(target_reps);
		}
		
		return name_and_sets[NAME];
	}
	
	public LinearLayout createLayoutForExercise(Context context){
		LinearLayout layout = new LinearLayout(context);
		
		LinearLayout.LayoutParams layout_params = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT
				);
		
		// Label for the name
		TextView exercise_name = new TextView(context);
		exercise_name.setText(getName());

		// Create Params 
		LinearLayout.LayoutParams exercise_name_params = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT
				);
		LinearLayout.LayoutParams set_params = exercise_name_params;
		
		exercise_name_params.weight = 1.0f;
		exercise_name.setLayoutParams(exercise_name_params);
		
		layout.setLayoutParams(layout_params); // NOT SURE ABOUT THIS LINE
		layout.addView(exercise_name);
		
		// Edit Texts for the sets
		EditText[] sets = new EditText[getNumberOfSets()];
		for(EditText set : sets){
			set = new EditText(context);
			set.setLayoutParams(set_params);
			layout.addView(set);
		}	
		
		return layout;
	}
}
