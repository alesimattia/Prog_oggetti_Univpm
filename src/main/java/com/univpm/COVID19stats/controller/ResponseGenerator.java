package com.univpm.COVID19stats.controller;

import java.util.ArrayList;

import com.univpm.COVID19stats.model.Bundle;

/**
*ResponseGenerator è la componente che consente di trovare i dati con maggiore
*o minore impatto tra quelli passati
*
*
*@author Mattia Alesi, Marco Incipini
*@version 1.0
*/
public class ResponseGenerator {

	/**
	*Metodo pubblico che restituisce il record (giorno) contenente il maggiore impatto
	*
	*@param bundle ArrayList contenente i dati
	*@return Bundle contenente il record con maggiore impatto
	*/
	public static Bundle getResponseMax(ArrayList<Bundle> bundle) {

		double max = bundle.get(0).getCases();
		Bundle dayMax = bundle.get(0);

		for(int i=1; i<bundle.size(); i++) {
			if(bundle.get(i).getCases() > max) {
				max = bundle.get(i).getCases();
				dayMax = bundle.get(i);
			}
		}
		return dayMax;
	}

	/**
	*Metodo pubblico che restituisce il record (giorno) contenente il minore impatto
	*
	*@param bundle ArrayList contenente i dati
	*@return Bundle contenente il record con minore impatto
	*/
	public static Bundle getResponseMin(ArrayList<Bundle> bundle) {
		
		double min = bundle.get(0).getCases();
		Bundle dayMin = bundle.get(0);

		for(int i=1; i<bundle.size(); i++) {
			if(bundle.get(i).getCases() < min) {
				min = bundle.get(i).getCases();
				dayMin = bundle.get(i);
				int f=0;
			}
		}
		return dayMin;
	}
}
