package com.example.weightlifting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CreateAWorkoutActivity extends Activity {
	
	private List<String> exercise_list;
	private ListView lv_exercise_list;
	private CAWExerciseListAdapter adapter;
	private Context context;
	
	private void loadExerciseList() throws IOException{
        //Log.d(Constants.TAG, "Loading WODs...");
        
        Resources resources = getApplicationContext().getResources();
        InputStream input = resources.openRawResource(R.raw.exercise_list);
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        exercise_list = new ArrayList<String>();
        
        try {
        	String line = "";
        	while ((line = reader.readLine()) != null){
        		exercise_list.add(line);
        		//Log.d(Constants.TAG, "Added Exercise [" + line +"]");
        	}
        	
        } finally {
        	reader.close();
        }
        
        //Log.d(Constants.TAG, "Exercise List loading completed.");
    }

	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_a_workout);
        
        try {
        	loadExerciseList();
		} catch (IOException e) {
			//Log.d(Constants.TAG, "Failed to load exercise list");
			e.printStackTrace();
		}
        
        lv_exercise_list = (ListView) findViewById(R.id.exercise_list_view);
		adapter = new CAWExerciseListAdapter(this, exercise_list.toArray(new String[exercise_list.size()]));
		lv_exercise_list.setAdapter(adapter);   
		
		GsonBuilder gson_builder = new GsonBuilder();
    	final Gson gson = gson_builder.create();
    	
    	context = getApplicationContext();
		Button btn_select_sets_reps = (Button) findViewById(R.id.next_button);
		btn_select_sets_reps.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Workout workout = adapter.getWorkout();
				if(workout.getExercises().length > 0) {
					Bundle bundle = new Bundle();
					String workout_json = gson.toJson(workout, Workout.class);
					bundle.putString(Constants.WOD, workout_json);
					Intent intent = new Intent(context, CustomWorkoutActivity.class);
					intent.putExtras(bundle);
					startActivity(intent);
				}
				else {
					Toast.makeText(context, "Please choose at least one exercise",
							   Toast.LENGTH_SHORT).show();
				}
			}
		});	
	}

}
