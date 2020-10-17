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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
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
	public String Dati(@RequestParam(name="categoria",defaultValue="contagi") String categoria,	@RequestBody String body ) throws JsonProcessingException, ParseException {

		Scanner in = new Scanner(body);
		Pattern patt=Pattern.compile("\\},");
		in.useDelimiter(patt);
		
		ObjectMapper obj = new ObjectMapper();
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.ITALIAN);
		obj.setDateFormat(dateFormat);
		
		//Informazioni contenute nel requestBody
		ArrayList<Paese> paesi= new ArrayList<Paese>();
		Filtro filtro = new Filtro();
		boolean hasFilter = false;
		
		/** Converte il requestBody negli oggetti Paese e Filtro */
		while(in.hasNext()) {
			String json=in.next()+"}";
			if(json.contains("percentuale")) {
				try {
					filtro = obj.readValue(json, Filtro.class);
					hasFilter = true;
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
    
		RequestGenerator requestGen = new RequestGenerator();
		FormatData formatter = new FormatData();
		Filter dataFilter = new Filter();
		ResponseGenerator responseGen = new ResponseGenerator();
		ArrayList<Response> risposta=new ArrayList<Response>();

		for(Paese p:paesi) {
			ArrayList<Bundle> dato=new ArrayList<Bundle>();
			Response response = new Response();
			dato.addAll( requestGen.getData (categoria, p.getSlug() ) );
			
			if(hasFilter) {
				formatter.convert(dato, filtro.isPercentuale() );
				dataFilter.filtra(dato, filtro);
			}
			else
				formatter.convert(dato, hasFilter );
			
			response.setMax( responseGen.getResponseMax(dato) );
			response.setMin( responseGen.getResponseMin(dato) );
			risposta.add(response);
		}

		String risp ="";
		for(Response r:risposta)
			risp+=obj.writeValueAsString(r);
			
			//Provato a sostituire Date con stringa direttamente nel json
			/*int start = risp.lastIndexOf("\"Data\":\"")+1;
			int end = risp.indexOf("lat:");
			
			char[] dateChar = null;
			risp.getChars(start, end, dateChar, 0);
			DateFormat format = new SimpleDateFormat("dd-MM-yyyy", Locale.ITALIAN);
			Date date = format.parse(dateChar.toString());
			String dateString = date.toGMTString();
			
			risp.replace(dateChar.toString(), dateString);*/
		
		return risp;
	}
}
