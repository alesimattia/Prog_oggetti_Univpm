package com.univpm.COVID19stats.model;

/**
*Questa classe modella una risposta generica,in cui non viene richiesta nessuna
*statistica, contenente i giorni con maggiore o minore impatto rispetto alla
*tipologia di dato richiesto.
*
*
*@author Mattia Alesi, Marco Incipini
*@version 1.0
*/
public class Response {

	/**
	*Indica il dato inerente al giorno con maggiore impatto
	*/
	private Bundle max;
	/**
	*Indica il dato inerente al giorno con maggiore impatto
	*/
    private Bundle min;

	/**
	*Metodo costruttore in cui non vengono passati parametri che setta tutti gli
	*attributi dell'oggetto a null
	*/
    public Response() {
		this.max = null;
		this.min = null;
	}

	/**
	*Metodo che ritorna l'oggetto Bundle max
	*
	*@return Bundle giorno con maggiore impatto
	*/
	public Bundle getMax() {
		return max;
	}
	/**
	*Modifica l'oggetto max
	*
	*@param max Bundle contenente il valore da impostare
	*/
	public void setMax(Bundle max) {
		this.max = max;
	}
	/**
	*Metodo che ritorna l'oggetto Bundle min
	*
	*@return Bundle giorno con minore impatto
	*/
	public Bundle getMin() {
		return min;
	}
	/**
	*Modifica l'oggetto min
	*
	*@param min Bundle contenente il valore da impostare
	*/
	public void setMin(Bundle min) {
		this.min = min;
	}

}
