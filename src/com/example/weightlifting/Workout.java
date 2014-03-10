package com.example.weightlifting;

import android.util.Log;

public class Workout {
	
	private Exercise[] exercises;
	
	public Exercise[] getExerciseList(){ return exercises; }
	
	public Workout(String workout_str){
		/*String workout_str="Back Squat:4x3;" +
				"Power Cl&Jerk:4x1x2;" +
				"Power Snatch:3x3;" +
				"Clean Pulls:4x5";*/
		
		parseWorkoutString(workout_str);
	}

	private void parseWorkoutString(String workout_string) {
		
		String[] exercise_strings = workout_string.split(";");
		int no_of_exercises = exercise_strings.length;
		exercises = new Exercise[no_of_exercises];
		
		for(int i=0; i < no_of_exercises; i++){
			exercises[i] = new Exercise(exercise_strings[i]);
		}
	}
}
