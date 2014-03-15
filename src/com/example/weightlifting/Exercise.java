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
}
