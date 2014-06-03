package com.example.weightlifting;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CustomWorkoutActivity extends Activity {
	
	private Workout custom_workout;
	private CustomWorkoutAdapter adapter;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_a_workout);
        
		Bundle bundle = getIntent().getExtras();
		custom_workout = new Workout();
		
		if(bundle != null){
			GsonBuilder gson_builder = new GsonBuilder();
	    	Gson gson = gson_builder.create();
	    	custom_workout = gson.fromJson(bundle.getString(Constants.WOD), Workout.class);
		}
		
	    ListView lv_custom_workout = (ListView) findViewById(R.id.exercise_list_view);
		adapter = new CustomWorkoutAdapter(this, custom_workout);
		lv_custom_workout.setAdapter(adapter);       
	}
}
