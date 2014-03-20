package com.example.weightlifting;

public class Set {
	
	private String _name;
	private int _target_weight;
	private int _target_reps;
	private int _no_of_reps_completed;
	private String _notes;
	
	public String getSetName() { return _name; }
	
	public Set(int set_number, int target_reps){
		this._name = "Set " + set_number;
		this._target_reps = target_reps;
		this._target_weight = 0;
		this._notes = "";
	}
	
	public Set(int set_number, int target_weight, int target_reps) {
		this._name = "Set " + set_number;
		this._target_weight = target_weight;
		this._target_reps = target_reps;
		this._notes = "";
	}

}
