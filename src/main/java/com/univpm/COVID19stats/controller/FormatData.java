package com.univpm.COVID19stats.controller;

import java.util.ArrayList;
import com.univpm.COVID19stats.model.Bundle;

/**
*FormatData è la componente che permette di modificare i dati da cumulativi in valori
*giornalieri (valore assoluto o percentuale). FormatData assume che i valori siano
*in ordine crescente di data
*
*
*@author Mattia Alesi, Marco Incipini
*@version 1.0
*/
public class FormatData {

	/**
	*Metodo pubblico che controlla in quale formato vanno convertiti i dati
	*
	*@param bundle ArrayList contenente i dati
	*@param isPercentuale boolean che indica se i valori devono essere in variazione percentuale
	*/
	public static void convert(ArrayList<Bundle> bundle, boolean isPercentuale ) {

		int[] numCasi = new int[bundle.size()];
		for(int i=0; i<bundle.size(); i++) 
			numCasi[i] = (int) bundle.get(i).getCases();

		if( isPercentuale )
			toPercentage(bundle, numCasi);
		else
			differenza(bundle, numCasi);
	}


	/**
	*Metodo che effettua la conversione da dati cumulativi in dati percentuali
	*
	*@param bundle ArrayList contenente i dati
	*@param numCasi Array contenente i valori cumulativi
	*/
	private static void toPercentage(ArrayList<Bundle> bundle, int numCasi[] ) {

		int count = 0;
		int rif = 0;
		while(rif == 0) {
			rif = (int) bundle.get(count).getCases();
			count++;
		}

		for(int i=count+1; i<bundle.size(); i++) {
			if( numCasi[i-1] == numCasi[i] ) 
				bundle.get(i).setCases(0);
			else {
				double val = ( (numCasi[i]*100)/numCasi[i-1] );
				bundle.get(i).setCases(val);
			}
		}
	}
	

	/**
	*Metodo che effettua la conversione da dati cumulativi in dati assoluti
	*
	*@param bundle ArrayList contenente i dati
	*@param numCasi Array contenente i valori cumulativi
	*/
	private static void differenza(ArrayList<Bundle> bundle, int numCasi[]) {

		for(int i=1; i<bundle.size(); i++) {
			bundle.get(i).setCases( numCasi[i] - numCasi[i-1] );
		}
	}

}
