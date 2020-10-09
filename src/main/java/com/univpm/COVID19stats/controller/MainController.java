package com.univpm.COVID19stats.controller;

import com.univpm.COVID19stats.model.Paese;
import com.univpm.COVID19stats.model.Bundle;
import com.univpm.COVID19stats.model.Filtro;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	public String UriGenerator( @RequestParam(name="categoria",defaultValue="contagi") String selectedCategoria,	
								@RequestBody String body ) 
	throws JsonProcessingException {
		return null;
	}
}
