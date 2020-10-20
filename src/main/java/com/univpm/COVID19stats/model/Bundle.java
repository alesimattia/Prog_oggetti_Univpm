package com.univpm.COVID19stats.model;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
*Questa classe mappa i dati che vengono forniti dall'API
*
*@author Mattia Alesi, Marco Incipini
*@version 1.0
*/
public class Bundle {

	/**
	*Indica il nome della nazione inerente al singolo dato
	*/
    @JsonProperty("Country")
    private String country;
	/**
	*Indica il codice della nazione inerente al singolo dato
	*/
    @JsonProperty("CountryCode")
    private String countryCode;
	/**
	*Indica la latitudine della nazione inerente al singolo dato
	*/
    @JsonProperty("Lat")
    private float lat;
	/**
	*Indica la longitudine della nazione inerente al singolo dato
	*/
    @JsonProperty("Lon")
    private float lon;
	/**
	*Indica il valore inerente del singolo dato nel formato richiesto (in variazione percentuale o come numero di casi intero)
	*/
    @JsonProperty("Cases")
    private double cases;
	/**
	*Indica la tipologia del singolo dato
	*/
    @JsonProperty("Status")
    private String status;
	/**
	*Indica la data inerente al singolo dato
	*/
    @JsonProperty("Date")
    private Date data;

	/**
	*Metodo che ritorna il valore di country
	*
	*@return String contenente il nome del paese
	*/
    public String getCountry() {
		return country;
	}
	/**
	*Modifica il valore countrty nell'oggetto
	*
	*@param countriy String contenente il valore da impostare
	*/
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	*Metodo che ritorna il valore di countryCode
	*
	*@return String contenente il codice del paese
	*/
	public String getCountryCode() {
		return countryCode;
	}
	/**
	*Modifica il valore countrtyCode nell'oggetto
	*
	*@param countryCode String contenente il valore da impostare
	*/
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	/**
	*Metodo che ritorna il valore di lat
	*
	*@return float contenente il valore della latitudine
	*/
	public float getLat() {
		return lat;
	}
	/**
	*Modifica il valore lat nell'oggetto
	*
	*@param lat float contenente il valore da impostare
	*/
	public void setLat(float lat) {
		this.lat = lat;
	}
	/**
	*Metodo che ritorna il valore di lon
	*
	*@return float contenente il valore della longitudine
	*/
	public float getLon() {
		return lon;
	}
	/**
	*Modifica il valore lon nell'oggetto
	*
	*@param lon float contenente il valore da impostare
	*/
	public void setLon(float lon) {
		this.lon = lon;
	}
	/**
	*Metodo che ritorna il valore di cases
	*
	*@return double contenente il valore dei casi
	*/
	public double getCases() {
		return cases;
	}
	/**
	*Modifica il valore cases nell'oggetto
	*
	*@param cases double contenente il valore da impostare
	*/
	public void setCases(double cases) {
		this.cases = cases;
	}
	/**
	*Metodo che ritorna il valore di status
	*
	*@return String contenente la tipologia del dato
	*/
	public String getStatus() {
		return status;
	}
	/**
	*Modifica il valore status nell'oggetto
	*
	*@param status String contenente il valore da impostare
	*/
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	*Metodo che ritorna il valore di data
	*
	*@return Data contenente la data inerente al dato
	*/
	public Date getData() {
		return data;
	}
	/**
	*Modifica il valore data nell'oggetto
	*
	*@param data Date contenente il valore da impostare
	*/
	public void setData(Date data) {
		this.data = data;
	}

}
