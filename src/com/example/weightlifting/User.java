package com.example.weightlifting;

public class User {
	
	private int _id;
	private String _name;
	private String _username;
	private String _password;
	
	public User() { }
	
	public User(String name, String username, String password){
		_name = name;
		_username = username;
		_password = password;
	}
	
	public int getId() { return _id; }
	public void setId(int id) { _id = id; }
	
	public String getName() { return _name; }
	public void setName(String name) { _name = name; }
	
	public String getUsername() { return _username; }
	public void setUsername(String username) { _username = username; }
	
	public String getPassowrd() { return _password; }
	public void setPassword(String password) { _password = password; }

}
