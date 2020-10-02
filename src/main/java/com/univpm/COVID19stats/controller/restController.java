package com.univpm.COVID19stats.controller;

import java.net.http.HttpResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.univpm.COVID19stats.model.Country;

@RestController
public class restController {
	
	@RequestMapping(method=RequestMethod.GET, value="/lista", produces="application/json" )
	public String lista() throws JsonMappingException, JsonProcessingException {
		final String uri = "https://api.covid19api.com/live/country/south-africa/status/confirmed/date/2020-03-21T13:13:30Z";
		
		//Recupera i dati da API esterna e li converte in oggetto
		RestTemplate restTemplate = new RestTemplate();
	    Country[] objects = restTemplate.getForObject(uri, Country[].class);
	    
	    //Rilegge l'oggetto e lo trasforma in json da inviare come risposta
	    ObjectMapper mapper = new ObjectMapper();
	    return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(objects);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/", produces="application/json" )
	public String home() throws JsonMappingException, JsonProcessingException {
		/*final String uri ="https://api.covid19api.com/summary";
		ObjectMapper mapper = new ObjectMapper();
	    JSONPObject resume = mapper.readValue(uri, String.class);
		return resume.getJSONObject(0);*/
		return "Pagina iniziale";
	}
}
