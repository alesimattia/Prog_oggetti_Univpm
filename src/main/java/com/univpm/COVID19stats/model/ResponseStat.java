package com.univpm.COVID19stats.model;

/**
*Questa classe modella la risposta contenente la statistica richiesta per un dato paese
*
*
*@author Mattia Alesi, Marco Incipini
*@version 1.0
*/
public class ResponseStat {

    /**
	*Indica il paese inerente al dato statistico
	*/
    private String paese;
    /**
	*Indica la tipologia di dato richiesto
	*/
    private String tipodidato;
    /**
	*Indica la tipologia di statistica richiesta
	*/
    private String tipodistatistica;
    /**
	*Indica il valore della statistica richiesta
	*/
    private double valore;

    /**
	*Metodo costruttore in cui non vengono passati parametri che setta tutti gli
	*attributi dell'oggetto a null o 0
	*/
    public ResponseStat() {
		this.paese = null;
		this.tipodidato = null;
		this.tipodistatistica = null;
		this.valore = 0;
	}

    /**
	*Metodo che ritorna il valore di paese
	*
	*@return String che indica il paese inerente al dato statistico
	*/
	public String getPaese() {
		return paese;
	}
    /**
	*Modifica il valore paese nell'oggetto
	*
	*@param paese String contenente il valore da impostare
	*/
	public void setPaese(String paese) {
		this.paese = paese;
	}
    /**
	*Metodo che ritorna il valore di tipodidato
	*
	*@return String che indica la tipologia di dato richiesto
	*/
	public String getTipodidato() {
		return tipodidato;
	}
    /**
	*Modifica il valore tipodidato nell'oggetto
	*
	*@param tipodidato String contenente il valore da impostare
	*/
	public void setTipodidato(String tipodidato) {
		this.tipodidato = tipodidato;
	}
    /**
	*Metodo che ritorna il valore di tipodistatistica
	*
	*@return String che indica la tipologia di statistica richiesta
	*/
	public String getTipodistatistica() {
		return tipodistatistica;
	}
    /**
	*Modifica il valore tipodistatistica nell'oggetto
	*
	*@param tipodistatistica Sring contenente il valore da impostare
	*/
	public void setTipodistatistica(String tipodistatistica) {
		this.tipodistatistica = tipodistatistica;
	}
    /**
	*Metodo che ritorna il valore dell'attributo valore
	*
	*@return double che indica il valore della statistica richiesta
	*/
	public double getValore() {
		return valore;
	}
    /**
	*Modifica il valore dell'attributo valore nell'oggetto
	*
	*@param valore double contenente il valore da impostare
	*/
	public void setValore(double valore) {
		this.valore = valore;
	}

}
