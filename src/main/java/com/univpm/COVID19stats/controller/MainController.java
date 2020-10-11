package com.univpm.COVID19stats.controller;

import com.univpm.COVID19stats.model.Paese;

import com.univpm.COVID19stats.model.Response;

import com.univpm.COVID19stats.model.Bundle;
import com.univpm.COVID19stats.model.Filtro;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.*;

@RestController
public class MainController {

	/*Homepage con dati riassuntivi*/
	@RequestMapping(method=RequestMethod.GET, value="/", produces="application/json" )
	public String home() {
		final String url = "https://api.covid19api.com/summary";

		RestTemplate restTemplate = new RestTemplate();
		String ris = restTemplate.getForObject(url, String.class);

		//tronca parte delle informazioni nel json
		return "{"+  ris.substring( 14, ris.indexOf("}") )
		+ "},"+ ris.substring( ris.length()-31, ris.length() );
	}


	@RequestMapping(method=RequestMethod.POST, value="/{categoria}", produces="application/json" )
	public String Dati(@RequestParam(name="categoria",defaultValue="contagi") String categoria,	@RequestBody String body ) throws JsonProcessingException {

		Scanner in = new Scanner(body);
		Pattern patt=Pattern.compile("\\},");
		in.useDelimiter(patt);
		
		ObjectMapper obj = new ObjectMapper();
		
		//Informazioni contenute nel requestBody
		ArrayList<Paese> paesi= new ArrayList<Paese>();
		Filtro filtro=new Filtro();
		
		/** Converte il requestBody negli oggetti Paese e Filtro */
		while(in.hasNext()) {
			String json=in.next()+"}";
			if(json.contains("percentuale")) {
				try {
					filtro = obj.readValue(json, Filtro.class);
				}
				catch(JsonProcessingException e){
					e.printStackTrace();
				}
			}
			else {
				try {
					paesi.add(obj.readValue(json, Paese.class));
				}
				catch(JsonProcessingException e){
					e.printStackTrace();
				}
			}
		}
		in.close();

		Response risposta=new Response();
		RequestGenerator rg = new RequestGenerator();
		FormatData formatter = new FormatData();

		for(Paese p:paesi) {
			ArrayList<Bundle> dato=new ArrayList<Bundle>();
			dato.addAll(rg.getData (categoria, p.getSlug()));
			formatter.convert(dato, filtro);
			//Formatdata
			//Filter
			//Responsegenerator
		}
		//return risposta
		return "risposta";
	}

}
