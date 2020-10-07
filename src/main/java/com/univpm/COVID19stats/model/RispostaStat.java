package com.univpm.COVID19stats.model;

public class RispostaStat {
    private String paese;
    private String tipodidato;
    private String tipodistatistica;
    private double valore;
	
    public String getPaese() {
		return paese;
	}
	public void setPaese(String paese) {
		this.paese = paese;
	}
	public String getTipodidato() {
		return tipodidato;
	}
	public void setTipodidato(String tipodidato) {
		this.tipodidato = tipodidato;
	}
	public String getTipodistatistica() {
		return tipodistatistica;
	}
	public void setTipodistatistica(String tipodistatistica) {
		this.tipodistatistica = tipodistatistica;
	}
	public double getValore() {
		return valore;
	}
	public void setValore(double valore) {
		this.valore = valore;
	}
    
}
