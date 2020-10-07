package com.univpm.COVID19stats.model;

import java.util.Date;

public class Filtro {
    private boolean percentuale;
    private double valoremax;
    private double valoremin;
    private Date datamax;
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
