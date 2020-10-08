package com.univpm.COVID19stats.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Paese {
    @JsonProperty("Country")
    private String country;
    @JsonProperty("Slug")
    private String slug;
    @JsonProperty("ISO2")
    private String iso2;

    public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getSlug() {
		return slug;
	}
	public void setSlug(String slug) {
		this.slug = slug;
	}
	public String getIso2() {
		return iso2;
	}
	public void setIso2(String iso2) {
		this.iso2 = iso2;
	}

}
