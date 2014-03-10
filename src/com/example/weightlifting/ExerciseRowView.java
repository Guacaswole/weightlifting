package com.example.weightlifting;

import android.content.Context;
import android.text.InputFilter;
import android.text.InputType;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ExerciseRowView extends LinearLayout {
	
	public ExerciseRowView(Context context, Exercise exercise) {
		super(context);
		
		// Label for the name
		TextView exercise_name = new TextView(context);
		exercise_name.setText(exercise.getName());

		// Create LayoutParams 
		exercise_name.setLayoutParams(new LayoutParams
				(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 2f));	
		addView(exercise_name);
		
		// Edit Texts for the sets
		int max_chars = 2;
		EditText[] sets = new EditText[exercise.getSetsTotal()];
		
		for(EditText set : sets){
			set = new EditText(context);
			set.setInputType(InputType.TYPE_CLASS_NUMBER);
			set.setFilters(new InputFilter[]{new InputFilter.LengthFilter(max_chars)});
			set.setLayoutParams(new LayoutParams
					(0, LayoutParams.WRAP_CONTENT, 1f));

			addView(set);
		}
	}
}
