package com.univpm.COVID19stats.controller;

import com.univpm.COVID19stats.model.Paese;

import com.univpm.COVID19stats.model.Response;
import com.univpm.COVID19stats.model.ResponseStat;
import com.univpm.COVID19stats.exceptions.RequestBodyException;
import com.univpm.COVID19stats.model.Bundle;
import com.univpm.COVID19stats.model.Filtro;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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

	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public String handler1(HttpMessageNotReadableException e) {
		RequestBodyException handler = new RequestBodyException(e);
		return handler.missingRequestBody(e);
	}
	@ExceptionHandler(UnrecognizedPropertyException.class)
	public String handler2(UnrecognizedPropertyException e) {
		RequestBodyException handler = new RequestBodyException(e);
		return handler.wrongJSONformat(e);
	}
	
	
	@RequestMapping(method=RequestMethod.POST, value="/{categoria}", produces="application/json" )
	public String work(@PathVariable String categoria, @RequestBody String body ) throws RequestBodyException, JsonProcessingException {

		body = body.replaceAll("[\\[\\]]","");
		body = body.replaceAll(" ","");
		Pattern patt = Pattern.compile("\\},");
		Scanner in = new Scanner(body);
		in.useDelimiter(patt);
		
		ObjectMapper obj = new ObjectMapper();
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.ITALIAN);
		obj.setDateFormat(dateFormat);
		
		//Informazioni contenute nel requestBody
		ArrayList<Paese> paesi = new ArrayList<Paese>();
		Filtro filtro = new Filtro();
		boolean hasFilter = false;
		
		/** Converte il requestBody negli oggetti Paese e Filtro */
		while(in.hasNext()) {
			String json=in.next()+"}";
			if(json.contains("percentuale")) {
				filtro = obj.readValue(json, Filtro.class);
				hasFilter = true;
			}
			else
				paesi.add(obj.readValue(json, Paese.class));
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
		
		return risp;
	}
	
	
	@RequestMapping(method=RequestMethod.POST, value="/{categoria}/{tipostat}", produces="application/json" )
	public String workWithStat(@PathVariable String categoria, @PathVariable String tipostat, @RequestBody String body ) throws JsonProcessingException, ParseException, RequestBodyException {

		body = body.replaceAll("[\\[\\]]","");
		body = body.replaceAll(" ","");
		Pattern patt = Pattern.compile("\\},");
		Scanner in = new Scanner(body);
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
				filtro = obj.readValue(json, Filtro.class);
				hasFilter = true;
			}
			else
				paesi.add(obj.readValue(json, Paese.class));
		}
		in.close();
    
		RequestGenerator requestGen = new RequestGenerator();
		FormatData formatter = new FormatData();
		Filter dataFilter = new Filter();
		StatsGenerator responseStat = new StatsGenerator();
		ArrayList<ResponseStat> risposta=new ArrayList<ResponseStat>();

		for(Paese p:paesi) {
			ArrayList<Bundle> dato=new ArrayList<Bundle>();
			dato.addAll( requestGen.getData (categoria, p.getSlug() ) );
			
			if(hasFilter) {
				formatter.convert(dato, filtro.isPercentuale() );
				dataFilter.filtra(dato, filtro);
			}
			else
				formatter.convert(dato, hasFilter );
			
			risposta.add(responseStat.getStat(dato, tipostat));
		}

		String risp ="";
		for(ResponseStat r:risposta)
			risp+=obj.writeValueAsString(r);
		
		return risp;
	}
}
