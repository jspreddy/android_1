package com.example.hw1;

public class ProtocolService {

	String Protocol, Service;
	Integer Count;

	// Constructor
	public ProtocolService(String p, String s) {
		this.Protocol = p;
		this.Service = s;
		this.Count = 0;
	}

	// Get key for Hashmap
	public String getKey() {
		return (this.Protocol + " " + this.Service);
	}

	// Increment the couter value in hash map
	public void IncrementCounter() {
		this.Count++;
	}

	// Get the counter value for printing 
	public Integer getCounter() {
		return this.Count;
	}
}
