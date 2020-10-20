package com.univpm.COVID19stats.controller;

import com.univpm.COVID19stats.model.Filtro;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import com.univpm.COVID19stats.model.Bundle;

/**
*Filter è la componente che permette di rimuovere i dati della collection che
*non soddisfano il filtro indicato
*
*
*@author Mattia Alesi, Marco Incipini
*@version 1.0
*/
public class Filter {

	/**
	*Metodo pubblico che controlla il filtro e lo applica sui valori o sulle date
	*
	*@param bundle ArrayList contenente i dati
	*@param filtro Filtro contenente i valori massimi e minimi
	*/
	public static void filtra(ArrayList<Bundle> bundle, Filtro filtro) {
		if(filtro.getValoremax()>0 || filtro.getValoremin()>0) {
			filtraValore(bundle,filtro.getValoremin(),filtro.getValoremax() );
		}
		if(filtro.getDatamax()!=null || filtro.getDatamin()!=null) {
			filtraData(bundle,filtro.getDatamax(),filtro.getDatamin() );
		}

	}

	/**
	*Metodo privato che implementa il filtro sui valori
	*
	*@param bundle ArrayList contenente i dati
	*@param valMax double contenente il valore massimo da filtrare
	*@param valMin double contenente il valore minimo da filtrare
	*/
	private static void filtraValore(ArrayList<Bundle> bundle, double valMin,double valMax) {
		Iterator<Bundle> i=bundle.iterator();
		if(valMin==0) {
			while(i.hasNext()) {
				Bundle b=i.next();
				if((b.getCases()>valMax)) {
					i.remove();
				}
			}
		}else if(valMax==0) {
			while(i.hasNext()) {
				Bundle b=i.next();
				if((b.getCases()<valMin)) {
					i.remove();
				}
			}
		}else {
			while(i.hasNext()) {
				Bundle b=i.next();
				if(b.getCases()<valMin || b.getCases()>valMax) {
					i.remove();
				}
			}
		}


	}

	/**
	*Metodo privato che implementa il filtro dulle date
	*
	*@param bundle ArrayList contenente i dati
	*@param datamax double contenente la data massima da filtrare
	*@param datamin double contenente la data minima da filtrare
	*/
	private static void filtraData(ArrayList<Bundle> bundle, Date datamax, Date datamin) {
		Iterator<Bundle> i=bundle.iterator();
		if(datamin==null) {
			while(i.hasNext()) {
				Bundle b=i.next();
				if((b.getData().after(datamax))) {
					i.remove();
				}
			}
		}else if(datamax==null) {
			while(i.hasNext()) {
				Bundle b=i.next();
				if((b.getData().before(datamin))) {
					i.remove();
				}
			}
		}else {
			while(i.hasNext()) {
				Bundle b=i.next();
				if(b.getData().after(datamax) || b.getData().before(datamin)) {
					i.remove();
				}
			}
		}
	}
}
