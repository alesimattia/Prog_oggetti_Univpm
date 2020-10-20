package com.univpm.COVID19stats.controller;

import java.util.ArrayList;

import com.univpm.COVID19stats.model.Bundle;
import com.univpm.COVID19stats.model.ResponseStat;

/**
*StatsGenerator è la componente che consente di calcolare statistiche sui dati
*che vengono passati
*
*
*@author Mattia Alesi, Marco Incipini
*@version 1.0
*/
public class StatsGenerator {

	/**
	*Metodo pubblico che restituisce il dato statistico calcolato
	*
	*@param bundle ArrayList contenente i dati
	*@return ResponseStat contenente il dato statistico
	*/
	public static ResponseStat getStat(ArrayList<Bundle> bundle, String statType) {
		ResponseStat r = new ResponseStat();
		r.setPaese(bundle.get(0).getCountry());
		r.setTipodidato(bundle.get(0).getStatus());
		r.setTipodistatistica(statType);

		switch(statType) {
        case "somma":{ somma(r,bundle); }
        break;
        case "media":{ media(r,bundle); }
        break;
        case "max":{ max(r,bundle); }
        break;
        case "min":{ min(r,bundle); }
        break;
        default:{ contaGiorni(r,bundle); }
		}
		return r;
	}

	/**
	*Metodo che calcola la somma dei dati passati
	*
	*@param bundle ArrayList contenente i dati
	*@param r ResponseStat contenente il dato statistico da valorizzare
	*/
	private static void somma(ResponseStat r, ArrayList<Bundle> bundle) {
      double somma=0;
      for(Bundle b:bundle)
        somma+=b.getCases();
      r.setValore(somma);
	}

	/**
	*Metodo che calcola la media dei dati passati
	*
	*@param bundle ArrayList contenente i dati
	*@param r ResponseStat contenente il dato statistico da valorizzare
	*/
	private static void media(ResponseStat r, ArrayList<Bundle> bundle) {
		double somma=0;
		for(Bundle b:bundle)
			somma+=b.getCases();
		r.setValore((somma/bundle.size()));
	}

	/**
	*Metodo che trova il massimo tra i dati passati
	*
	*@param bundle ArrayList contenente i dati
	*@param r ResponseStat contenente il dato statistico da valorizzare
	*/
	private static void max(ResponseStat r, ArrayList<Bundle> bundle) {
      double max = bundle.get(0).getCases();		//valore di riferimento

      for(Bundle current : bundle)
        if(current.getCases() > max) {
          max = current.getCases();
        }
      r.setValore(max);
	}

	/**
	*Metodo che trova il minimo tra i dati passati
	*
	*@param bundle ArrayList contenente i dati
	*@param r ResponseStat contenente il dato statistico da valorizzare
	*/
	private static void min(ResponseStat r, ArrayList<Bundle> bundle) {
      double min = bundle.get(0).getCases();

      for(Bundle current : bundle)
        if(current.getCases() < min) {
          min = current.getCases();
        }
      r.setValore(min);
	}

	/**
	*Metodo che conta i dati passati
	*
	*@param bundle ArrayList contenente i dati
	*@param r ResponseStat contenente il dato statistico da valorizzare
	*/
	private static void contaGiorni(ResponseStat r, ArrayList<Bundle> bundle) {
	    r.setValore(bundle.size());
	}

}
