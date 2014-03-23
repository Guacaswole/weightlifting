package com.example.weightlifting;
import com.google.gson.annotations.SerializedName;

public class Workout {
	
	@SerializedName("exercises")
	private Exercise[] _exercises;

	public Workout() { }
	
	public void setExercises(Exercise[] exercises){ _exercises = exercises; }
	public Exercise[] getExercises() { return _exercises; }
}
