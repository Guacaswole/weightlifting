package com.example.weightlifting;

public class Set {
	
	private String set_number;
	private int completed_reps;
	private int target_reps;
	private int target_weight;
	private int actual_weight;
	private int no_of_reps_completed;
	
	public String getSetNumber() { return set_number; }
	
	public Set(int set_number, int target_reps){
		this.set_number = "Set " + set_number;
		this.target_reps = target_reps;
	}
	
	public Set(int set_number, int target_weight, int target_reps) {
		this.set_number = "Set " + set_number;
		this.target_weight = target_weight;
		this.target_reps = target_reps;
	}

}
