package com.univpm.COVID19stats.controller;

import java.util.ArrayList;

import com.univpm.COVID19stats.model.Bundle;
import com.univpm.COVID19stats.model.ResponseStat;

public class StatsGenerator {

	public ResponseStat getStat(ArrayList<Bundle> bundle, String statType, String tipoDato) {
		ResponseStat r=new ResponseStat();
		r.setPaese(bundle.get(0).getCountry());
		r.setTipodidato(tipoDato);
		r.setTipodistatistica(statType);
		switch(statType) {
		case "somma":{
			somma(r,bundle);

		}break;
		case "media":{
			media(r,bundle);

		}break;
		case "max":{
			max(r,bundle);
		}break;
		case "min":{
			min(r,bundle);

		}break;
		case "contagiorni":{
			contaGiorni(r,bundle);

		}break;
		default:{
			contaGiorni(r,bundle);
		}
		}
		return r;


	}

	private void somma(ResponseStat r, ArrayList<Bundle> bundle) {
		double somma=0;
		for(Bundle b:bundle) {
			somma+=b.getCases();
		}
		r.setValore(somma);
	}

	private void media(ResponseStat r, ArrayList<Bundle> bundle) {
		double somma=0;
		for(Bundle b:bundle) {
			somma+=b.getCases();
		}
		r.setValore((somma/bundle.size()));

	}

	private void max(ResponseStat r, ArrayList<Bundle> bundle) {

	}

	private void min(ResponseStat r, ArrayList<Bundle> bundle) {

	}

	private void contaGiorni(ResponseStat r, ArrayList<Bundle> bundle) {
		r.setValore(bundle.size());

	}
}
