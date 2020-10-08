package com.univpm.COVID19stats.model;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Filtro {
    @JsonProperty("percentuale")
    private boolean percentuale;
    @JsonProperty("valoremax")
    private double valoremax;
    @JsonProperty("valoremin")
    private double valoremin;
    @JsonProperty("datamax")
    private Date datamax;
    @JsonProperty("datamin")
    private Date datamin;

    public boolean isPercentuale() {
		return percentuale;
	}
	public void setPercentuale(boolean percentuale) {
		this.percentuale = percentuale;
	}
	public double getValoremax() {
		return valoremax;
	}
	public void setValoremax(double valoremax) {
		this.valoremax = valoremax;
	}
	public double getValoremin() {
		return valoremin;
	}
	public void setValoremin(double valoremin) {
		this.valoremin = valoremin;
	}
	public Date getDatamax() {
		return datamax;
	}
	public void setDatamax(Date datamax) {
		this.datamax = datamax;
	}
	public Date getDatamin() {
		return datamin;
	}
	public void setDatamin(Date datamin) {
		this.datamin = datamin;
	}

}
