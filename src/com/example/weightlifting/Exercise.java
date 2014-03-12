package com.example.weightlifting;

import android.content.Context;
import android.text.InputFilter;
import android.text.InputType;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class Exercise {
	
	private String name;
	private int weight;
	private Set[] sets;
	
	public String getName(){ return name; }
	public int getWeight(){ return weight; }
	public Set[] getSets() { return sets; }
	public int getSetsTotal() { return sets.length; }
	
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
	
	public LinearLayout getExerciseRowView(Context context) {
		
		LinearLayout exercise_row_view = new LinearLayout(context);
		//exercise_row_view.setId(i);
		exercise_row_view.setOrientation(LinearLayout.HORIZONTAL);
		
		LinearLayout.LayoutParams layout_params = new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		
		exercise_row_view.setLayoutParams(layout_params);
		
		TextView name = new TextView(context);
		name.setText(getName());
		name.setLayoutParams(new LayoutParams
				(0, LayoutParams.WRAP_CONTENT, 2f));
		exercise_row_view.addView(name);
		
		EditText[] sets_view = new EditText[getSetsTotal()];
		for(EditText set : sets_view){
			set = new EditText(context);
			set.setInputType(InputType.TYPE_CLASS_NUMBER);
			set.setFilters(new InputFilter[]{new InputFilter.LengthFilter(2)});
			set.setLayoutParams(new LayoutParams
					(0, LayoutParams.WRAP_CONTENT, 1f));
			
			exercise_row_view.addView(set);
		}
		
		return exercise_row_view;
	}
}
