package com.univpm.COVID19stats.exceptions;

public class RequestBodyMissingException extends Throwable {
	
	public RequestBodyMissingException() {
		super();
		System.out.println("Effettuata richiesta di tipo POST ma non c'è niente nel RequestBody");
	}
}
