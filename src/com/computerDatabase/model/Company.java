package com.computerDatabase.model;

public class Company {
	
	private int id;
	private String name;
	
	public Company(){
		
	}
	
	public Company(String name) {
		super();
		this.name = name;
	}
	
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
