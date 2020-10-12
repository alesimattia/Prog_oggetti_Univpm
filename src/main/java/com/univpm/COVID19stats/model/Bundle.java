package com.univpm.COVID19stats.model;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Bundle {
	
    @JsonProperty("Country")
    private String country;
    @JsonProperty("CountryCode")
    private String countryCode;
    @JsonProperty("Lat")
    private float lat;
    @JsonProperty("Lon")
    private float lon;
    @JsonProperty("Cases")
    private double cases;
    @JsonProperty("Status")
    private String status;
    @JsonProperty("Date")
    private Date data;

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
	public float getLat() {
		return lat;
	}
	public void setLat(float lat) {
		this.lat = lat;
	}
	public float getLon() {
		return lon;
	}
	public void setLon(float lon) {
		this.lon = lon;
	}
	public double getCases() {
		return cases;
	}
	public void setCases(double cases) {
		this.cases = cases;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}

}
