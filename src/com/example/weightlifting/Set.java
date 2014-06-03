package com.example.weightlifting;
import com.google.gson.annotations.SerializedName;

public class Set {
	
	@SerializedName("name")
	private String _name;
	
	@SerializedName("target_weight")
	private int _target_weight;
	
	@SerializedName("target_reps")
	private int _target_reps;
	
	@SerializedName("no_of_reps_completed")
	private int _no_of_reps_completed;
	
	@SerializedName("notes")
	private String _notes;
	
	public Set() { }
	
	// {{ getSetMethods
	
	public String getName() { return _name; }
	public void setName(String name) { _name = name; }
	
	public int getTargetWeight() { return _target_weight; }
	public void setTargetWeight(int tw) { _target_weight = tw; }
	
	public int getTargetReps() { return _target_reps; }
	public void setTargetReps(int tr) { _target_reps = tr; }
	
	public int getNoOfRepsCompleted() { return _no_of_reps_completed; }
	public void setNoOfRepsCompleted(int num) { _no_of_reps_completed = num; }
	
	public String getNotes() { return _notes; }
	public void setNotes(String notes) { _notes = notes; }
	
	// }}
}

