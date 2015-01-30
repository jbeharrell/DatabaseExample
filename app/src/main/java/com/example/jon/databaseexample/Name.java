package com.example.jon.databaseexample;

/**
 * Author: Jon Beharrell
 * Date: 1/29/2015
 * Description: This class serves as a model to hold name data
 */

public class Name {

	private int id;
	private String firstName;
	private String lastName;

	public int getId(){
		return id;
	}

	public void setId(int id){
		this.id = id;
	}

	public String getFirstName(){
		return firstName;
	}

	public void setFirstName(String firstName){
		this.firstName = firstName;
	}

	public String getLastName(){
		return lastName;
	}

	public void setLastName(String lastName){
		this.lastName = lastName;
	}

	// toString is used by the arrayAdapter
	@Override
	public String toString(){
		return firstName +" "+lastName;
	}
}
