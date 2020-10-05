package com.univpm.COVID19stats.model;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CountryDetail extends Country{
	
	@JsonProperty("Cases")	
	private int cases;
	
	@JsonProperty("Status")	//confirmed, recovered, deaths
	private String status;	
	
	@JsonProperty("Date")
	private Date date;

	
	public int getCases() {
		return cases;
	}

	public void setCases(int cases) {
		this.cases = cases;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
