package com.univpm.COVID19stats.controller;

import java.util.ArrayList;

import com.univpm.COVID19stats.model.Bundle;
import com.univpm.COVID19stats.model.Filtro;

public class FormatData {

	public void convert(ArrayList<Bundle> bundle,Filtro filter) {
		
		if(filter.isPercentuale())
			toPercentage(bundle);
		else
			differenza(bundle);
	}


	/** Variazione percentuale (contagi/morti/guariti) rispetto al giorno precedente */
	private void toPercentage(ArrayList<Bundle> bundle) {
		double rif= bundle.get(0).getCases(); 	//Riferimento giorno uno
		
		for(int i=1; i<=bundle.size(); i++) {
			double val=( bundle.get(i).getCases()*100/rif )-100;
			rif = val;
			bundle.get(i).setCases(val);
		}
	}

	
	/** Dataset con dati cumulativi -> ricava dati giornalieri 
	    tramite differenza con il giorno precedente */
	private void differenza(ArrayList<Bundle> bundle) {
		
		for(int i=1; i<=bundle.size(); i++) {
			bundle.get(i).setCases(bundle.get(i-1).getCases());
		}
	}

}
