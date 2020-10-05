package com.univpm.COVID19stats.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CountryExtDetail extends Country{

	@JsonProperty("Confirmed")
	private int confirmed;  	//"confirmed" = totale
	
	@JsonProperty("Deaths")
	private int deaths;	
	
	@JsonProperty("Recovered")
	private int recovered;		//"curati"
	
	@JsonProperty("Active")
	private int active;			//"infetti"

	@JsonProperty("Date")
	private Date date;
	
	
	public int getConfirmed() {
		return confirmed;
	}

	public void setConfirmed(int confirmed) {
		this.confirmed = confirmed;
	}

	public int getDeaths() {
		return deaths;
	}

	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}

	public int getRecovered() {
		return recovered;
	}

	public void setRecovered(int recovered) {
		this.recovered = recovered;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}
	
	public Date getDate() {
		/*final String OLD_FORMAT = "yyyy-MM-dd";
		final String NEW_FORMAT = "dd/MM/yyyy";

		SimpleDateFormat formatter = new SimpleDateFormat(OLD_FORMAT);
		Date d = formatter.parse( (String) date );
		formatter.applyPattern(NEW_FORMAT);
		return formatter.format(d);*/
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}
