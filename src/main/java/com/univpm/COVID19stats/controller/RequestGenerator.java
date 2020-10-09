package com.univpm.COVID19stats.controller;

import com.univpm.COVID19stats.model.Paese;


import org.springframework.web.client.RestTemplate;
import com.univpm.COVID19stats.model.Bundle;


public class RequestGenerator {

	public Bundle[] getData(String categoria, Paese paese) {
		
		String url = "https://api.covid19api.com/total/dayone/country/"+ paese +"/status/"+categoria;

		RestTemplate restTemplate = new RestTemplate();
		Bundle[] objects = restTemplate.getForObject(url, Bundle[].class);
		
		return objects;
	}

}
