package com.univpm.COVID19stats.controller;

import java.util.ArrayList;
import java.util.Vector;

import com.univpm.COVID19stats.model.Bundle;
import com.univpm.COVID19stats.model.Filtro;

public class FormatData {

	public void convert(ArrayList<Bundle> bundle,Filtro filter) {
		if(filter.isPercentuale()) {
			toPercentage(bundle);
		}else {
			differenza(bundle);
		}
	}

	private void toPercentage(ArrayList<Bundle> bundle) {

	}

	/** Dataset con dati cumulativi -> ricava dati giornalieri 
	 * tramite differenza con il giorno precedente */
	private void differenza(ArrayList<Bundle> bundle) {

	}

}
