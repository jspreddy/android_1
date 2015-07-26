/**
 * 
 */
package com.example.hw1;

public class User {
	private String firstName, middleInitial, lastName;
	private int age;
	private String city, state;
	
	public User(String line){
		parseUser(line);
	}
	
	private void parseUser(String line){
		String[] resultingTokens = line.split(",");
		for (int i = 0; i < resultingTokens.length; i++) {
			
			this.firstName=resultingTokens[0].trim();
			this.middleInitial=resultingTokens[1].trim();
			this.lastName=resultingTokens[2].trim();
			this.age=Integer.parseInt(resultingTokens[3].trim());
			this.city=resultingTokens[4].trim();
			this.state=resultingTokens[5].trim();
			
		}
	}
	
	public String getHashKey(){
		return this.firstName+" "+this.middleInitial+" "+this.lastName+" "+this.age+" "+this.city+" "+this.state;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @return the middleInitial
	 */
	public String getMiddleInitial() {
		return middleInitial;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	
	
}
