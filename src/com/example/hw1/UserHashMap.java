package com.example.hw1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class UserHashMap {
	private HashMap<String, User> users;
	private Integer count;
	private Integer duplication;
	private List<User> userValues;
	private boolean sorted;
	private List<String> itemStrings;
	
	public UserHashMap(){
		this.users = new HashMap<String,User>();
		this.count=0;
		this.duplication=0;
		this.sorted=false;
		itemStrings=new ArrayList<String>();
	}
	
	public void addUser(User user){
		if(users.containsKey(user.getHashKey())){
			this.duplication++;
		}
		else{
			users.put(user.getHashKey(), user);
			this.count++;
		}
	}
	
	public boolean userExists(String userHashKey){
		if(users.containsKey(userHashKey)) return true;
		else return false;
	}

	/**
	 * @return the count
	 */
	public Integer getCount() {
		return count;
	}

	/**
	 * @return the duplication
	 */
	public Integer getDuplication() {
		return duplication;
	}
	
	public List<String> getItemStringsSortedByAge(){
		if(this.sorted==false){
			userValues = new ArrayList<User>(users.values());
			
			Collections.sort(userValues, new Comparator<User>() {
		        public int compare(User o1, User o2) {
		            return o1.getAge() - o2.getAge();
		        }
		    });
			this.sorted=true;
			
			for(User u: userValues){
				itemStrings.add(u.getHashKey());
			}
		}
		return itemStrings;
	}
}
