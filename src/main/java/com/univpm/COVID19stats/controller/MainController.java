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

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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

	
	/** Gestori delle eccezioni: Catturano le eccezioni previste
	 * e le inviano alla classe custom {@link RequestBodyException}
	 * per la gestione.
	 * 
	 * @see RequestBodyException
	 * @param e Oggetto errore tipizzato dalla classe che ha lanciato l'eccezione
	 * @return String da inviare nel ResponseObject
	 */
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public String handler1(HttpMessageNotReadableException e) {
		RequestBodyException handler = new RequestBodyException(e);
		return handler.missingRequestBody(e);
	}
	/** Gestori delle eccezioni: Catturano le eccezioni previste
	 * e le inviano alla classe custom {@link RequestBodyException}
	 * per la gestione.
	 * 
	 * @see RequestBodyException
	 * @param e Oggetto errore tipizzato dalla classe che ha lanciato l'eccezione
	 * @return String da inviare nel ResponseObject
	 */
	@ExceptionHandler(UnrecognizedPropertyException.class)
	public String handler2(UnrecognizedPropertyException e) {
		RequestBodyException handler = new RequestBodyException(e);
		return handler.wrongJSONformat(e);
	}
	/** Gestori delle eccezioni: Catturano le eccezioni previste
	 * e le inviano alla classe custom {@link RequestBodyException}
	 * per la gestione.
	 * 
	 * @see RequestBodyException
	 * @param e Oggetto errore tipizzato dalla classe che ha lanciato l'eccezione
	 * @return String da inviare nel ResponseObject
	 */
	@ExceptionHandler(JsonParseException.class)
	public String handler3(JsonParseException e) {
		RequestBodyException handler = new RequestBodyException(e);
		return handler.wrongJSONvalues(e);
	}
 
	
	/**
	 *Metodo che gestisce una richiesta effettuata senza specificare una statistica
	 *
	 *@param categoria tipologia di dato su cui effettuare la ricerca
	 *@param body json contenente i paesi d'interesse ed il filtro da applicare ai dati
	 *@throws JsonProcessingException
	 *@return Json contenente dati con maggiore o minore impatto
	*/
	@RequestMapping(method=RequestMethod.POST, value="/{categoria}", produces="application/json" )
	public String work(@PathVariable String categoria, @RequestBody String body ) throws JsonProcessingException {

		body = body.replaceAll("[\\[\\]]","");
		body = body.replaceAll(" ","");
		Pattern patt = Pattern.compile("\\},");
		Scanner in = new Scanner(body);
		in.useDelimiter(patt);

		ObjectMapper obj = new ObjectMapper();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ITALIAN);
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

		ArrayList<Response> risposta=new ArrayList<Response>();

		for(Paese p:paesi) {
			
			ArrayList<Bundle> dato=new ArrayList<Bundle>();
			dato.addAll( RequestGenerator.getData(categoria, p.getSlug()) );
			if(dato.size() == 0)
				return "Nessun dato per il paese: " + p.getCountry();
			
			if(hasFilter) {
				FormatData.convert(dato, filtro.isPercentuale() );
				Filter.filtra(dato, filtro);
			}
			else
				FormatData.convert(dato, hasFilter );
			
			if(dato.size() == 0)
				return "Non ci sono dati che soddisfano il filtro per il paese:" + p.getCountry();
			
			Response response = new Response();
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
	*@throws JsonProcessingException
	*@return Json contenente dati con maggiore o minore impatto
	*/
	@RequestMapping(method=RequestMethod.POST, value="/{categoria}/{tipostat}", produces="application/json" )
	public String workWithStat(@PathVariable String categoria, @PathVariable String tipostat, @RequestBody String body ) throws JsonProcessingException {

		body = body.replaceAll("[\\[\\]]","");
		body = body.replaceAll(" ","");
		Pattern patt = Pattern.compile("\\},");
		Scanner in = new Scanner(body);
		in.useDelimiter(patt);

		ObjectMapper obj = new ObjectMapper();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ITALIAN);
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

		ArrayList<ResponseStat> risposta=new ArrayList<ResponseStat>();

		for(Paese p:paesi) {
			
			ArrayList<Bundle> dato=new ArrayList<Bundle>();
			dato.addAll( RequestGenerator.getData(categoria, p.getSlug()) );
			if(dato.size() == 0)
				return "Nessun dato per il paese selezionato";
			
			if(hasFilter) {
				FormatData.convert(dato, filtro.isPercentuale() );
				Filter.filtra(dato, filtro);
			}
			else
				FormatData.convert(dato, hasFilter );
			
			if(dato.size() == 0)
				return "Non ci sono dati che soddisfano il filtro";
			
			risposta.add(StatsGenerator.getStat(dato, tipostat));
		}

		String risp ="";
		for(ResponseStat r:risposta)
			risp+=obj.writeValueAsString(r);
    
		return risp;
	}
}
