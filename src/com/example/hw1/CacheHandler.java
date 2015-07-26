package com.example.hw1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;

public class CacheHandler {
	private String cachefile;
	private Context context;
	
	public CacheHandler(Context context, String cachefile){
		this.cachefile=cachefile;
		this.context=context;
	}
	
	public long getCacheSize(){
		File f= new File(context.getCacheDir(), this.cachefile);
		Log.d("cachetest",context.getCacheDir()+" "+this.cachefile+" : "+f.length());
		return f.length();
	}
	
	private void test(){
		try {
			File f= new File(context.getCacheDir(), "cachefile");
			
			if(f.length()==0){
				BufferedWriter br=new BufferedWriter(new FileWriter( f.getPath()));
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void writeStringListToCache(List<String> list){
		try {
			FileOutputStream fos = context.openFileOutput(context.getCacheDir()+"/"+this.cachefile, Context.MODE_PRIVATE);
			String temp=new String();
			int i=0;
			int n=list.size();
			for(i=0;i<n;i++){
				temp+=list.get(i);
				temp+="\n";
			}
			fos.write(temp.getBytes());
			fos.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<String> loadFromFile(){
		List<String> list=new ArrayList<String>();
		try {
			FileInputStream fis=context.openFileInput(context.getCacheDir()+"/"+this.cachefile);
			BufferedReader br=new BufferedReader(new InputStreamReader(fis));
			
			String lineContent = null;
			
			while ((lineContent = br.readLine()) != null) {
				list.add(lineContent);
			}
			if (fis != null){
				br.close();
				fis.close();
			}
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
