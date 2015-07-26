/**
 * 
 */
package com.example.hw1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import android.content.Context;
import android.content.res.AssetManager;

public class PartThree {

	private Context context;
	private HashMap<String, List<String>> map;
	private String filename = "countries-info.txt";
	private List<String> continentCountryList;
	
	public PartThree(Context context){
		this.context=context;
		this.map = new HashMap<String, List<String>>();
		this.continentCountryList = new ArrayList<String>();
		
		readFileAtPath(this.filename);
		
	}
	
	public void readFileAtPath(String filename) {
		// Lets make sure the file path is not empty or null
		if (filename == null || filename.isEmpty()) {
			System.out.println("Invalid File Path");
			return;
		}
		
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

				// Loop will iterate over each line within the file.
				// It will stop when no new lines are found.
				while ((lineContent = br.readLine()) != null) {
					Country c = new Country(lineContent);
					String key = c.getContinent();
					
					// Add continents(key) into HashMap and contries to corresponding Lists
					if (this.map.containsKey(key)) {
						this.map.get(key).add(c.getCountryName());
					} else {
						List<String> list = new ArrayList<String>();
						list.add(c.getCountryName());
						this.map.put(key, list);
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
	
	public List<String> getContinentCountrySets(){
		if(this.continentCountryList.isEmpty()){
			Iterator<String> itr = this.map.keySet().iterator();
			
			while (itr.hasNext()) {
				String continentName = (String) itr.next();
				List<String> l = new ArrayList<String>();
				l = (List) this.map.get(continentName);
				
				Collections.sort(l, new Comparator<String>() {
					public int compare(String s1, String s2) {
						if (s1.compareTo(s2) > 0) {
							return 1;
						} else if (s1.compareTo(s2) < 0) {
							return -1;
						} else {
							return 0;
						}
					}
				});
				String temp=new String();
				temp = continentName;
				temp+=":\n-------------------------------\n";
				for (String countryName : l){
					temp+=countryName + ", ";
				}
				temp+="\n";
				
				this.continentCountryList.add(temp);
			}
		}
		return this.continentCountryList;
	}
}
