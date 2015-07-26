/**
 * 
 */
package com.example.hw1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import android.content.Context;
import android.content.res.AssetManager;

public class PartTwo {

	/**
	 * @param args
	 */
	private UserHashMap oldUsers;
	private UserHashMap newRecords;
	private Context context;
	private String OldFile="userList1.txt";
	private String NewFile="userList1New.txt";
	//private String cacheFile="cachefile2";
	
	public PartTwo(Context context){
		this.context=context;
		this.oldUsers=new UserHashMap();
		this.newRecords=new UserHashMap();
		
		readFileIntoOldUsers(this.OldFile);
		checkAndGetDifferencesFromNewFileIntoNewRecords(this.NewFile);
	}

	private void readFileIntoOldUsers(String filename) {
		// Lets make sure the file path is not empty or null
		if (filename == null || filename.isEmpty()) {
			System.out.println("Invalid File Path");
		}
		//String filePath = "/HW1/assets/" + filename;
		AssetManager am= context.getAssets();
		
		InputStream inputStream = null;
		Reader reader =null;
		BufferedReader br=null;
		// We need a try catch block so we can handle any potential IO errors
		try {
			try {
				inputStream = am.open(filename);
				reader = new InputStreamReader(inputStream);
				br = new BufferedReader(reader);
				String lineContent = null;
				
				while ((lineContent = br.readLine()) != null) {
					this.oldUsers.addUser(new User(lineContent));
				}
			}
			// Make sure we close the buffered reader.
			finally {
				if (inputStream != null){
					br.close();
					reader.close();
					inputStream.close();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void checkAndGetDifferencesFromNewFileIntoNewRecords(String filename) {
		if (filename == null || filename.isEmpty()) {
			System.out.println("Invalid File Path");
		}
		//String filePath = "/HW1/assets/" + filename;
		AssetManager am= context.getAssets();
		
		InputStream inputStream = null;
		Reader reader =null;
		BufferedReader br=null;
		// We need a try catch block so we can handle any potential IO errors
		try {
			try {
				inputStream = am.open(filename);
				reader = new InputStreamReader(inputStream);
				br = new BufferedReader(reader);
				String lineContent = null;
				
				while ((lineContent = br.readLine()) != null) {
					User tmp=new User(lineContent);
					//if tmp user doesn't exist in oldUsers list, them add to newRecords
					if( !this.oldUsers.userExists(tmp.getHashKey()) ){
						this.newRecords.addUser(tmp);
					}
				}
			}
			// Make sure we close the buffered reader.
			finally {
				if (inputStream != null){
					br.close();
					reader.close();
					inputStream.close();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int getNewUserCount(){
		return this.newRecords.getCount();
	}

	public List<String> getNewUsers(){
		return this.newRecords.getItemStringsSortedByAge();
	}
}
