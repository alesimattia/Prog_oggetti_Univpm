package com.univpm.COVID19stats.controller;

import java.util.ArrayList;

import com.univpm.COVID19stats.model.Bundle;

public class ResponseGenerator {

	public Bundle getResponseMax(ArrayList<Bundle> bundle) {
		
		double max = bundle.get(0).getCases();		//valore di riferimento
		Bundle dayMax = bundle.get(0);
		
		for(int i=1; i<bundle.size(); i++) {
			if(bundle.get(i).getCases() > max) {
				max = bundle.get(i).getCases();
				dayMax = bundle.get(i);
			}
		}
		return dayMax;
	}

	
	public Bundle getResponseMin(ArrayList<Bundle> bundle) {
		
		double min = bundle.get(0).getCases();		//valore di riferimento
		Bundle dayMin = bundle.get(0);
		
		for(int i=1; i<bundle.size(); i++) {
			if(bundle.get(i).getCases() < min) {
				min = bundle.get(i).getCases();
				dayMin = bundle.get(i);
			}
		}
		return dayMin;
	}
}
