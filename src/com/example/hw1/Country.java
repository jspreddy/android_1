package com.example.hw1;

public class Country {

	private String continent, country_name, ISO3, country_number, country_full_name;
	
	/**
	 * 
	 * @param lineContent
	 */
	public Country(String lineContent){
		String[] resultingTokens = lineContent.split(",");
		this.continent = resultingTokens[0].trim();
		this.country_name = resultingTokens[1].trim();
		this.ISO3 = resultingTokens[2].trim();
		this.country_number = resultingTokens[3].trim();
		this.country_full_name = resultingTokens[4].trim();
	}
	
	/**
	 * 
	 * @return
	 */
	public String getContinent(){
		return this.continent;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCountryName(){
		return this.country_name;
	}
	
	/**
	 * @return the iSO3
	 */
	public String getISO3() {
		return ISO3;
	}

	/**
	 * @return the country_number
	 */
	public String getCountry_number() {
		return country_number;
	}

	/**
	 * @return the country_full_name
	 */
	public String getCountry_full_name() {
		return country_full_name;
	}
}
