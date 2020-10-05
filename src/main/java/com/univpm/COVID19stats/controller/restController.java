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

@RestController
public class restController {	// VEDI SE EVENTUALMENTE DICHIARARE RESTTEMPLATE+MAPPER GLOBALE
	
	
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
	
	
	/*Periodo con {max} {contagi} per un determinato {paese}*/
	@RequestMapping(method=RequestMethod.GET, value="/{valore}/{tipo}/{paese}", produces="application/json" )
	public String period( @RequestParam(name="valore" , defaultValue="max") String valore, 
						  @RequestParam(name="tipo" , defaultValue="contagi") String tipo,
						  @RequestParam(name="paese" , defaultValue="italy") String paese  ) 
	       throws JsonProcessingException {
		
		String url = "https://api.covid19api.com/dayone/country/" + paese;
		
		RestTemplate restTemplate = new RestTemplate();
	    CountryExtDetail[] objects = restTemplate.getForObject(url, CountryExtDetail[].class);
	    
	    Date selected;	//memorizza la data con maggiore impatto per ciò che si è scelto
	    
	    	 //max o min
	    switch (valore) {
			case "max" :
			    for(CountryExtDetail object : objects) {
			    	if( object.getDeaths()() >> object.next().getDeaths() )	//UNA COSA DEL GENERE
			    		selected = object.getDate();
			    }
			break;
	    }
	    
	    ObjectMapper mapper = new ObjectMapper();
	    //Rilegge l'oggetto e lo formatta in json da inviare come risposta
	    return mapper.writerWithDefaultPrettyPrinter()
	    			 .writeValueAsString(objects);
	}
	
}
