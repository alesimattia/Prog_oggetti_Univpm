package com.univpm.COVID19stats.exceptions;

public class InvalidJSONformat extends Throwable {

	public InvalidJSONformat() {
		super();
		System.out.println("Errore durante il parsing del RequestBody (JSON)");
	}
}
