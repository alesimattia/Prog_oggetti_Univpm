package com.univpm.COVID19stats.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Country {
	
	@JsonProperty("Country")
	private String Country;
	@JsonProperty("CountryCode")
	private String CountryCode;
	//private String Province;
	@JsonProperty("Lat")
	private Double Lat;
	@JsonProperty("Lon")
	private Double Lon;
	@JsonProperty("Confirmed")
	private int Confirmed;  	//"confirmed" = morti+infetti
	@JsonProperty("Deaths")
	private int Deaths;	
	@JsonProperty("Recovered")
	private int Recovered;		//"curati"
	@JsonProperty("Active")
	private int Active;			//"infetti"
	@JsonProperty("Date")
	private Date Date;
	
	
	public String getCountry() {
		return Country;
	}
	public void setCountry(String country) {
		Country = country;
	}
	public String getCountryCode() {
		return CountryCode;
	}
	public void setCountryCode(String countryCode) {
		CountryCode = countryCode;
	}
	public Double getLat() {
		return Lat;
	}
	public void setLat(Double lat) {
		Lat = lat;
	}
	public Double getLon() {
		return Lon;
	}
	public void setLon(Double lon) {
		Lon = lon;
	}
	public int getConfirmed() {
		return Confirmed;
	}
	public void setConfirmed(int confirmed) {
		Confirmed = confirmed;
	}
	public int getDeaths() {
		return Deaths;
	}
	public void setDeaths(int deaths) {
		Deaths = deaths;
	}
	public int getRecovered() {
		return Recovered;
	}
	public void setRecovered(int recovered) {
		Recovered = recovered;
	}
	public int getActive() {
		return Active;
	}
	public void setActive(int active) {
		Active = active;
	}
	public Date getDate() {
		return Date;
	}
	public void setDate(Date date) {
		Date = date;
	}
	
	
}
