package com.univpm.COVID19stats.controller;

import java.util.ArrayList;

import org.springframework.web.client.RestTemplate;
import com.univpm.COVID19stats.model.Bundle;


/**
*RequestGenerator è la classe che genera la richiesta all'API Covid-19 in base
*al paese e alla categoria indicata
*
*
*@author Mattia Alesi, Marco Incipini
*@version 1.0
*/
public class RequestGenerator {

	/**
	*Metodo che genera la richiesta all'API Covid-19 restituedo i dati ricevuti
	*
	*@param categoria String contenente la tipologia di dato da richiedere
	*@param paese String contenente il paese su cui effettuare la richiesta
	*@return ArrayList contenente dati ricevuti dall'API
	*/
	public static ArrayList<Bundle> getData(String categoria, String paese) {

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
