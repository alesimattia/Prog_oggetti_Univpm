package com.univpm.COVID19stats.model;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
*Questa classe modella un filtro applicabile ai dati in cui è possibile specificare
*se i dati devono essere considerati in variazione percentuale o in valore intero
*
*@author Mattia Alesi, Marco Incipini
*@version 1.0
*/
public class Filtro {

	/**
	*Indica se i dati devono essere in variazione percentuale o in valore intero
	*/
    @JsonProperty("percentuale")
    private boolean percentuale;
	/**
	*Indica il valore massimo di un dato per essere preso in considerazione nella risposta
	*/
    @JsonProperty("valoremax")
    private double valoremax;
	/**
	*Indica il valore minimo di un dato per essere preso in considerazione nella risposta
	*/
    @JsonProperty("valoremin")
    private double valoremin;
	/**
	*Indica la data massima inerente ad un dato per essere preso in considerazione nella risposta
	*/
    @JsonProperty("datamax")
    private Date datamax;
	/**
	*Indica la data minima inerente ad un dato per essere preso in considerazione nella risposta
	*/
    @JsonProperty("datamin")
    private Date datamin;

	/**
	*Metodo che ritorna il valore di percentuale
	*
	*@return boolean che indica la tipologia dei valori
	*/
    public boolean isPercentuale() {
		return percentuale;
	}
	/**
	*Modifica il valore percentuale nell'oggetto
	*
	*@param percentuale boolean contenente il valore da impostare
	*/
	public void setPercentuale(boolean percentuale) {
		this.percentuale = percentuale;
	}
	/**
	*Metodo che ritorna il valore di valoremax
	*
	*@return double che indica il valore massimo dei dati considerati
	*/
	public double getValoremax() {
		return valoremax;
	}
	/**
	*Modifica il valore valoremax nell'oggetto
	*
	*@param valoremax double contenente il valore da impostare
	*/
	public void setValoremax(double valoremax) {
		this.valoremax = valoremax;
	}
	/**
	*Metodo che ritorna il valore di valoremin
	*
	*@return double che indica il valore minimo dei dati considerati
	*/
	public double getValoremin() {
		return valoremin;
	}
	/**
	*Modifica il valore valoremin nell'oggetto
	*
	*@param valoremin double contenente il valore da impostare
	*/
	public void setValoremin(double valoremin) {
		this.valoremin = valoremin;
	}
	/**
	*Metodo che ritorna il valore di datamax
	*
	*@return double che indica la data massima dei dati considerati
	*/
	public Date getDatamax() {
		return datamax;
	}
	/**
	*Modifica il valore datamax nell'oggetto
	*
	*@param datamax Date contenente il valore da impostare
	*/
	public void setDatamax(Date datamax) {
		this.datamax = datamax;
	}
	/**
	*Metodo che ritorna il valore di datamin
	*
	*@return double che indica la data minima dei dati considerati
	*/
	public Date getDatamin() {
		return datamin;
	}
	/**
	*Modifica il valore datamin nell'oggetto
	*
	*@param datamin Date contenente il valore da impostare
	*/
	public void setDatamin(Date datamin) {
		this.datamin = datamin;
	}

}
