package com.example.weightlifting;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class ExerciseAdapter extends ArrayAdapter<Exercise> {
	
	Context context; 
    int layoutResourceId;    
    Exercise exercises[] = null;
	
	public ExerciseAdapter(Context context, int layoutResourceId, Exercise[] exercises) {
        super(context, layoutResourceId, exercises);
        
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.exercises = exercises;
        
        //notifyDataSetChanged();
    }
	
	@Override
    public View getView(int position, View convertView, ViewGroup parent) {
		
		Exercise exercise = exercises[position];
		convertView = new ExerciseRowView(context, exercise);
		
		return convertView;
	}
}
