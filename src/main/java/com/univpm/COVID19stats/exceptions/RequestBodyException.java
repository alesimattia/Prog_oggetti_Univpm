package com.univpm.COVID19stats.exceptions;

import org.springframework.http.converter.HttpMessageNotReadableException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;

public class RequestBodyException extends Throwable {
	
	public RequestBodyException(Exception e) {
		super();
	}
	
	public String missingRequestBody(HttpMessageNotReadableException e) {
		String msg = "Effettuata richiesta POST ma RequestBody vuoto! \nEccezione: \t" + e.getClass();
		System.out.println(msg);
	    return msg;
	}
	
	public String wrongJSONformat(UnrecognizedPropertyException e) {
		String msg = "RequestBody con JSON  mal formattato! \nEccezione: \t" + e.getClass();
		System.out.println(msg);
	    return msg;
	}

}
