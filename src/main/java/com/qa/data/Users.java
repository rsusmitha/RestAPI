package com.qa.data;


//pojo - plain old java object

public class Users {
	String name;
	String job;
	
	public Users() {
		
	}
	public Users(String name, String job) {
		this.name= name;
		this.job = job;
	}
	//getters and setters code
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	
}
