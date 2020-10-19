package com.univpm.COVID19stats.exceptions;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;


public class RequestBodyException extends Throwable {
	
	public RequestBodyException(Exception e) {
		super();
		System.out.println("Formato JSON errato");
	}
	
	/* Inserire l'exception handler qui serviva a utilizzare l'oggetto 
	 * come "ascoltatore" nel controller -> se avanza tempo provare ancora
	 */
	
	//@ExceptionHandler(HttpMessageNotReadableException.class)
	public String missingRequestBody(HttpMessageNotReadableException e) {
		String msg = "Effettuata richiesta POST ma RequestBody vuoto";
		System.out.println(msg);
	    return msg;
	}
	
	//@ExceptionHandler(UnrecognizedPropertyException.class)
	public String wrongJSONformat(UnrecognizedPropertyException e) {
		String msg = "RequestBody con JSON  mal formattato";
		System.out.println(msg);
	    return msg;
	}
	
	public String errorJSONparse() {
		return null;
	}
}
