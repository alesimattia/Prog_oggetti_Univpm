package com.univpm.COVID19stats.controller;

import com.univpm.COVID19stats.model.Filtro;

import java.util.ArrayList;

import com.univpm.COVID19stats.model.Bundle;

public class Filter {

	public void filtra(ArrayList<Bundle> bundle, Filtro filtro) {
		if(filtro.getValoremax()>0 || filtro.getValoremin()>0) {
			filtraValore(bundle,filtro.getValoremin(),filtro.getValoremax() );
		}
		if(filtro.getValoremax()>0 || filtro.getValoremin()>0) {
			filtraValore(bundle,filtro.getValoremin(),filtro.getValoremax() );
		}

	}

	private void filtraValore(ArrayList<Bundle> Bundle, double valMin,double valMax) {

	}
	//meglio passare Filtro.dataMin piuttosto che tutto l'oggetto filtro? (modifica diagramma classi)
	private void filtraData(ArrayList<Bundle> bundle, Filtro filtro) {

	}
}
