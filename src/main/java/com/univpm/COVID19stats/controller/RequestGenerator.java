package com.univpm.COVID19stats.controller;

import com.univpm.COVID19stats.model.Paese;

import java.util.ArrayList;

import org.springframework.web.client.RestTemplate;
import com.univpm.COVID19stats.model.Bundle;


public class RequestGenerator {

	public ArrayList<Bundle> getData(String categoria, String paese) {
		
		String url;
       switch(categoria) {
       	case "decessi":
                url = "https://api.covid19api.com/total/dayone/country/"+ paese +"/status/deaths";
            break;
            case "guariti":
                url = "https://api.covid19api.com/total/dayone/country/"+ paese +"/status/recovered";
            break;
            default:
                url = "https://api.covid19api.com/total/dayone/country/"+ paese +"/status/confirmed";
            break;
       }
        RestTemplate restTemplate = new RestTemplate();
        ArrayList<Bundle> objects = new ArrayList<Bundle>();
        for(Bundle b: restTemplate.getForObject(url, Bundle[].class)) {
        	objects.add(b);
        }
        return objects;
	}

}
