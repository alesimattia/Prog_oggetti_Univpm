package com.univpm.COVID19stats.controller;

import org.springframework.web.bind.annotation.RequestParam;

public class RestController {
	
	public String UriGenerator(@RequestParam(name="categoria",defaultValue="contagi") String categoria, 
																					  Paese paese) {
		
	}
}
