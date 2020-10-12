package com.univpm.COVID19stats.controller;

import com.univpm.COVID19stats.model.Filtro;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import com.univpm.COVID19stats.model.Bundle;

public class Filter {

	public void filtra(ArrayList<Bundle> bundle, Filtro filtro) {
		if(filtro.getValoremax()>0 || filtro.getValoremin()>0) {
			filtraValore(bundle,filtro.getValoremin(),filtro.getValoremax() );
		}
		if(filtro.getDatamax()!=null || filtro.getDatamin()!=null) {
			filtraData(bundle,filtro.getDatamax(),filtro.getDatamin() );
		}

	}

	private void filtraValore(ArrayList<Bundle> bundle, double valMin,double valMax) {
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
	//meglio passare Filtro.dataMin piuttosto che tutto l'oggetto filtro? (modifica diagramma classi)
	private void filtraData(ArrayList<Bundle> bundle, Date datamax, Date datamin) {
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
