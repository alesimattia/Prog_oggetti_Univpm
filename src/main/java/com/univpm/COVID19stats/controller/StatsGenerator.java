package com.univpm.COVID19stats.controller;

import java.util.ArrayList;

import com.univpm.COVID19stats.model.Bundle;
import com.univpm.COVID19stats.model.Response;
import com.univpm.COVID19stats.model.ResponseStat;

public class StatsGenerator {

	public ResponseStat[] getStat(Bundle[] bundle, String statType) {
		
	}

	private ResponseStat[] somma(Bundle[] bundle, String statType) {
		
	}

	private ResponseStat[] media(Bundle[] bundle, String statType) {

	}

	private void max(ArrayList<Bundle> bundle) {
		
		double max = bundle.get(0).getCases();		//valore di riferimento
		Bundle dayMax = null;
		
		for(Bundle current : bundle)
			if(current.getCases() > max) {
				max = current.getCases();
				dayMax = current;
			}
			
		ResponseStat risposta = new ResponseStat();
		risposta.setPaese( dayMax.getCountry() );
		risposta.setTipodidato( dayMax.getStatus() );
		risposta.setTipodistatistica("Max");
		risposta.setValore(max);
	}

	private void min(ArrayList<Bundle> bundle) {
		
		double min = bundle.get(0).getCases();
		Bundle dayMin = null;
		
		for(Bundle current : bundle)
			if(current.getCases() < min) {
				min = current.getCases();
				dayMin = current;
			}
			
		ResponseStat risposta = new ResponseStat();
		risposta.setPaese( dayMin.getCountry() );
		risposta.setTipodidato( dayMin.getStatus() );
		risposta.setTipodistatistica("Min");
		risposta.setValore(min);
	}

	private ResponseStat[] contaGiorni(Bundle[] bundle, String statType) {

	}
}
