package com.univpm.COVID19stats.controller;

import java.util.Date;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.univpm.COVID19stats.model.CountryExtDetail;


public class restController_temp {	// VEDI SE EVENTUALMENTE DICHIARARE RESTTEMPLATE+MAPPER GLOBALE
	
	/*Informazioni giornaliere per un dato paese => corrisponde a "Day One All status" */
	@RequestMapping(method=RequestMethod.GET, value="/{paese}", produces="application/json" )
	public String details( @RequestParam(name="paese" , defaultValue="italy") String paese) throws JsonProcessingException {
		
		String url = "https://api.covid19api.com/dayone/country/"+paese;
		
		//Recupera i dati da API esterna e li converte in oggetto
		RestTemplate restTemplate = new RestTemplate();
	    CountryExtDetail[] objects = restTemplate.getForObject(url, CountryExtDetail[].class);
	    
	    ObjectMapper mapper = new ObjectMapper();
	    //Rilegge l'oggetto e lo formatta in json da inviare come risposta
	    return mapper.writerWithDefaultPrettyPrinter()
	    			 .writeValueAsString(objects);
	}
	
	
	/** Periodo con {max} {contagi} per un determinato {paese}*/
	@RequestMapping(method=RequestMethod.GET, value="/{valore}/{tipo}/{paese}", produces="application/json" )
	public String period( @RequestParam(name="valore" , defaultValue="max") String valore, 
						  @RequestParam(name="tipo" , defaultValue="contagi") String tipo,
						  @RequestParam(name="paese" , defaultValue="italy") String paese  ) 
	       throws JsonProcessingException {
		
		String url = "https://api.covid19api.com/dayone/country/" + paese;
		
		RestTemplate restTemplate = new RestTemplate();
	    CountryExtDetail[] objects = restTemplate.getForObject(url, CountryExtDetail[].class);
	    
	    Date selectedDate;	//memorizza la data con maggiore impatto per ciò che si è scelto 
	    	 
	    switch (valore) {
	    
		case "min" :
			/** Gestisce l'eccezione di url con parametro "tipo" non corretto */
			if(! (tipo.equals("contagi") || tipo.equals("guariti") || tipo.equals("decessi") )  )	/** Necessaria risposta boolean => no compareTo() */
				selectedDate = evaluate(valore, "contagi", objects);
			else 
				selectedDate = evaluate(valore, tipo, objects);
		break;
		
		default:	/** "max" - Gestisce l'eccezione di url con parametro non corretto */
			if(! (tipo.equals("contagi") || tipo.equals("guariti") || tipo.equals("decessi") )  )
				selectedDate = evaluate("max", "contagi", objects);
			else 
				selectedDate = evaluate("max", tipo, objects);
		break;
	    }
	    
	    
	    CountryExtDetail response = getRecordAt(selectedDate, objects);
	    
	    ObjectMapper mapper = new ObjectMapper();
	    //Rilegge l'oggetto e lo formatta in json da inviare come risposta
	    return mapper.writerWithDefaultPrettyPrinter()
	    			 .writeValueAsString(response);
	}
	
	
	private Date evaluate(String order, String type, CountryExtDetail[] objects) {
		Date date = null;
		switch (type) {
		
		case "contagi":
			if( order.compareTo("max") == 1 ) {
				int max = 0;
				for(CountryExtDetail object : objects) {
			    	if( object.getDeaths() > max )
			    		max = object.getDeaths();
			    		date = object.getDate();  //memorizza la data come "chiave" per poi ritrovare l'elemento
				}
				return date;
			}
			else {
				int min = Integer.MAX_VALUE;
				for(CountryExtDetail object : objects) {
			    	if( object.getDeaths() < min )
			    		min = object.getDeaths();
			    		date = object.getDate();  //memorizza la data come "chiave" per poi ritrovare l'elemento
				}
				return date;
			}
		}
		return date;
	}
	
	private CountryExtDetail getRecordAt(Date selDate, CountryExtDetail[] objects) {
		for(CountryExtDetail object : objects)
			if(object.getDate() == selDate)
				return object;
		return null;
	}
	
}
