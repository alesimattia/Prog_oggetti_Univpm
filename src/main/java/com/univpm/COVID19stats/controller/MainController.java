package com.univpm.COVID19stats.controller;

import com.univpm.COVID19stats.model.Paese;

import com.univpm.COVID19stats.model.Response;
import com.univpm.COVID19stats.model.ResponseStat;
import com.univpm.COVID19stats.model.Bundle;
import com.univpm.COVID19stats.model.Filtro;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
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
import java.util.Locale;
import java.util.regex.*;

/**
*Main controller è il controller principale dell'applicazione che implementa tre
*metodi in grado di gestire le richieste ricevute
*
*
*@author Mattia Alesi, Marco Incipini
*@version 1.0
*/
@RestController
public class MainController {

	/**
	*Metodo che gestisce una richiesta effettuata senza specificare statistica o
	*tipologia di dato
	*
	*@return Json contenente dati globali sul COVID-19
	*/
	@RequestMapping(method=RequestMethod.GET, value="/", produces="application/json" )
	public String home() {
		final String url = "https://api.covid19api.com/summary";

		RestTemplate restTemplate = new RestTemplate();
		String ris = restTemplate.getForObject(url, String.class);

		//tronca parte delle informazioni nel json
		return "{"+  ris.substring( 14, ris.indexOf("}") )
		+ "},"+ ris.substring( ris.length()-31, ris.length() );
	}


	/**
	*Metodo che gestisce una richiesta effettuata senza specificare una statistica
	*
	*@param categoria tipologia di dato su cui effettuare la ricerca
	*@param body json contenente i paesi d'interesse ed il filtro da applicare ai dati
	*@return Json contenente dati con maggiore o minore impatto
	*/
	@RequestMapping(method=RequestMethod.POST, value="/{categoria}", produces="application/json" )
	public String Dati(@PathVariable String categoria,	@RequestBody String body ) throws JsonProcessingException, ParseException {

		body=body.replaceAll("[\\[\\]]","");
		body=body.replaceAll(" ","");
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

		ArrayList<Response> risposta=new ArrayList<Response>();

		for(Paese p:paesi) {
			ArrayList<Bundle> dato=new ArrayList<Bundle>();
			Response response = new Response();
			dato.addAll( RequestGenerator.getData(categoria, p.getSlug()) );

			if(hasFilter) {
				FormatData.convert(dato, filtro.isPercentuale() );
				Filter.filtra(dato, filtro);
			}
			else
				FormatData.convert(dato, hasFilter );

			response.setMax( ResponseGenerator.getResponseMax(dato) );
			response.setMin( ResponseGenerator.getResponseMin(dato) );
			risposta.add(response);
		}

		String risp ="";
		for(Response r:risposta)
			risp+=obj.writeValueAsString(r);

		return risp;
	}

	/**
	*Metodo che gestisce la richiesta di una statistica sui dati
	*
	*@param categoria tipologia di dato su cui effettuare la ricerca
	*@param tipostat tipologia di statistica richiesta
	*@param body json contenente i paesi d'interesse ed il filtro da applicare ai dati
	*@return Json contenente dati con maggiore o minore impatto
	*/
	@RequestMapping(method=RequestMethod.POST, value="/{categoria}/{tipostat}", produces="application/json" )
	public String Dati(@PathVariable String categoria, @PathVariable String tipostat, @RequestBody String body ) throws JsonProcessingException, ParseException {

		body=body.replaceAll("[\\[\\]]","");
		body=body.replaceAll(" ","");
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

		ArrayList<ResponseStat> risposta=new ArrayList<ResponseStat>();

		for(Paese p:paesi) {
			ArrayList<Bundle> dato=new ArrayList<Bundle>();
			dato.addAll( RequestGenerator.getData(categoria, p.getSlug()) );

			if(hasFilter) {
				FormatData.convert(dato, filtro.isPercentuale() );
				Filter.filtra(dato, filtro);
			}
			else
				FormatData.convert(dato, hasFilter );

			risposta.add(StatsGenerator.getStat(dato, tipostat));
		}

		String risp ="";
		for(ResponseStat r:risposta)
			risp+=obj.writeValueAsString(r);
		return risp;
	}
}
