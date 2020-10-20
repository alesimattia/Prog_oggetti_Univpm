package com.univpm.COVID19stats.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
*Questa classe modella un paese per cui calcolare le statistiche
*
*@author Mattia Alesi, Marco Incipini
*@version 1.0
*/
public class Paese {
    /**
	*Indica in nome del paese
	*/
    @JsonProperty("Country")
    private String country;
    /**
	*Indica il nome del paese privo di spazi e caratteri maiuscoli
	*/
    @JsonProperty("Slug")
    private String slug;
    /**
	*Indica il codice univoco del paese
	*/
    @JsonProperty("ISO2")
    private String iso2;

    /**
	*Metodo che ritorna il valore di country
	*
	*@return String contenente il nome del paese
	*/
    public String getCountry() {
		return country;
	}
    /**
	*Modifica il valore country nell'oggetto
	*
	*@param country String contenente il valore da impostare
	*/
	public void setCountry(String country) {
		this.country = country;
	}
    /**
	*Metodo che ritorna il valore di slug
	*
	*@return String contenente lo slug del paese
	*/
	public String getSlug() {
		return slug;
	}
    /**
	*Modifica il valore slug nell'oggetto
	*
	*@param slug String contenente il valore da impostare
	*/
	public void setSlug(String slug) {
		this.slug = slug;
	}
    /**
	*Metodo che ritorna il valore di iso2
	*
	*@return String contenente il codice del paese
	*/
	public String getIso2() {
		return iso2;
	}
    /**
	*Modifica il valore iso2 nell'oggetto
	*
	*@param iso2 String contenente il valore da impostare
	*/
	public void setIso2(String iso2) {
		this.iso2 = iso2;
	}

}
