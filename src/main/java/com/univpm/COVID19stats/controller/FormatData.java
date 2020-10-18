package com.univpm.COVID19stats.controller;

import java.util.ArrayList;
import com.univpm.COVID19stats.model.Bundle;

public class FormatData {

	public void convert(ArrayList<Bundle> bundle, boolean isPercentuale ) {
		
		/** Memorizza il numero intero di casi giorno per giorno 
	    perchè il dato viene sovrascritto da entrambi i metodi */
		int[] numCasi = new int[bundle.size()];
		
		for(int i=0; i<bundle.size(); i++) 
			numCasi[i] = (int) bundle.get(i).getCases();
		
		if( isPercentuale )
			toPercentage(bundle, numCasi);
		else
			differenza(bundle, numCasi);
	}

	
	/** Variazione percentuale (contagi/morti/guariti) rispetto al giorno precedente */
	private void toPercentage(ArrayList<Bundle> bundle, int numCasi[] ) {
		// Si assume che le date siano ordinate e sequenzali
		
		/** Salta i primi giorni senza casi */
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
				double val = ( numCasi[i]*100/numCasi[i-1] );
				bundle.get(i).setCases(val);
			}
		}
	}
	
	
	/** Dataset con dati cumulativi -> ricava dati giornalieri 
	    tramite differenza con il giorno precedente */
	private void differenza(ArrayList<Bundle> bundle, int numCasi[]) {
		
		for(int i=1; i<bundle.size(); i++)
			bundle.get(i).setCases( numCasi[i] - numCasi[i-1] );
			int f=0;
	}

}
