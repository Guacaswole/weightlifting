package com.example.weightlifting;
import com.google.gson.annotations.SerializedName;

public class Exercise {
	
	@SerializedName("name")
	private String _name;
	
	@SerializedName("sets")
	private Set[] _sets;
	
	public Exercise() { } 
	
	public String getName(){ return _name; }
	public void   setName(String name){ _name = name; }
	
	public Set[] getSets() { return _sets; }
	public void  setSets(Set[] sets) { _sets = sets; }
}

