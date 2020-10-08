package com.univpm.COVID19stats.controller;

import com.univpm.COVID19stats.model.Paese;
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
	@RequestMapping(method=RequestMethod.POST, value="/{categoria}", produces="application/json" )
	public String UriGenerator(@RequestParam(name="categoria",defaultValue="contagi") String categoria,	@RequestBody String body ) throws JsonProcessingException {

		Filtro bodyFilter= new Filtro();
		Paese bodypaesi= new Paese();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = mapper.readTree(body);
		bodypaese = jsonNode.get("slug");
		while(){
		}

		jsonNode.getClass();

		return "chchc";
	}
}
