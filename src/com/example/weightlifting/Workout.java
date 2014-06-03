package com.example.weightlifting;
import com.google.gson.annotations.SerializedName;

public class Workout {
	
	@SerializedName("exercises")
	private Exercise[] exercises;
	
	@SerializedName("name")
	private String name;
	
	@SerializedName("info")
	private String info;

	@SerializedName("rounds")
	private int rounds;
	
	public Workout() { }
	
	public Workout(Exercise[] exercises) {
		this.exercises = exercises;
		name = "";
		info = "";
		rounds = 0;
	}
	
	public void setName(String name){ this.name = name; }
	public String getName() { return name; }
	
	public void setExercises(Exercise[] exercises){ this.exercises = exercises; }
	public Exercise[] getExercises() { return exercises; }
	
	public void setInfo(String info){ this.info = info; }
	public String getInfo() { return info; }
	
	public void setRounds(int rounds){ this.rounds = rounds; }
	public int getRounds() { return rounds; }
}
