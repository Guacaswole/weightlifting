package com.example.weightlifting;
import com.google.gson.annotations.SerializedName;

public class Exercise {
	
	@SerializedName("name")
	private String name;
	
	@SerializedName("sets")
	private Set[] sets;
	
	public Exercise() { } 
	
	public Exercise(String name) { this.name = name; }
	
	public String getName(){ return name; }
	public void   setName(String name){ this.name = name; }
	
	public Set[] getSets() { return sets; }
	public void  setSets(Set[] sets) { this.sets = sets; }
}

