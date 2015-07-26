package com.example.hw1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.content.res.AssetManager;

public class PartOne {

	/**
	 * @param args
	 */
	private HashMap<String, ProtocolService> map;
	private List<String> topTen;
	private Context context;
	private String filename="packets.txt";
	private String cachefile="cachefile1";
	private CacheHandler c1;
	
	public PartOne(Context context){
		this.map = new HashMap<String, ProtocolService>();
		this.topTen= new ArrayList<String>();
		this.context=context;
		this.c1=new CacheHandler(this.context, this.cachefile);
		this.readServiceProtocolPairsIntoFile(this.filename);
	}

	public void readServiceProtocolPairsIntoFile(String filename) {
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
				String key = null;
				
				while ((lineContent = br.readLine()) != null) {
					String[] resultingTokens = lineContent.split(",");
					
					// 2nd field id protocol name and 3rd field is service name
					// used toLowerCase() to convert everything into lower case.
					// Avoid confusion between HTTP and http if present
					ProtocolService pair = new ProtocolService(
							resultingTokens[1].trim().toLowerCase(),
							resultingTokens[2].trim().toLowerCase()
							);

					// Key for HashMap
					key = pair.getKey();

					// Store count in HashMap
					if (this.map.containsKey(key)) {
						this.map.get(key).IncrementCounter();
					} else {
						pair.IncrementCounter();
						this.map.put(key, pair);
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
	
	public List<String> getTopTenPSPairs(){
		//c1.getCacheSize();
		if(this.topTen.size()==0){
			/*if(c1.getCacheSize()>0){
				this.topTen=c1.loadFromFile();
			}*/
			List<ProtocolService> list = new ArrayList<ProtocolService>(map.values());
	
			Collections.sort(list, new Comparator<ProtocolService>() {
				@Override
				public int compare(ProtocolService o1, ProtocolService o2) {
					return o2.getCounter() - o1.getCounter();
				}
			});

			for (int i = 0; i < 10; i++) {
				this.topTen.add( list.get(i).getKey() + " : "+ list.get(i).getCounter() );
			}
			//c1.writeStringListToCache(this.topTen);
		}
		return this.topTen;
	}

}
