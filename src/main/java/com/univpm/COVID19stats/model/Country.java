package com.univpm.COVID19stats.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Country {
	
	@JsonProperty("Country")
	private String country;
	
	@JsonProperty("CountryCode")
	private String countryCode;
	
	//@JsonProperty("Province")
	//private String province;	//sempre vuoto nel db?
	
	@JsonProperty("Lat")
	private Double lat;
	
	@JsonProperty("Lon")
	private Double lon;

	
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLon() {
		return lon;
	}

	public void setLon(Double lon) {
		this.lon = lon;
	}
	
	
}
