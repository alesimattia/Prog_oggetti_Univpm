package com.univpm.COVID19stats.controller;

import java.util.ArrayList;

import com.univpm.COVID19stats.model.Bundle;
import com.univpm.COVID19stats.model.Response;

public class ResponseGenerator {

	public Response getResponseMax(ArrayList<Bundle> bundle) {
		
		double max = bundle.get(0).getCases();		//valore di riferimento
		Bundle dayMax = null;
		
		for(int i=0; i<bundle.size(); i++) {
			if(bundle.get(i).getCases()> max)
				max = bundle.get(i).getCases();
				dayMax = bundle.get(i);
		}
		
		Response risposta = new Response();
		risposta.setMax(dayMax);
		
		return risposta;
	}

	
	public Response getResponseMin(ArrayList<Bundle> bundle) {
		
		double min = bundle.get(0).getCases();
		Bundle dayMin = null;
		
		for(int i=0; i<bundle.size(); i++) {
			if(bundle.get(i).getCases() < min)
				min = bundle.get(i).getCases();
				dayMin = bundle.get(i);
		}
		
		Response risposta = new Response();
		risposta.setMin(dayMin);
		
		return risposta;
	}
}
