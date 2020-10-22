package com.univpm.COVID19stats.exceptions;

import org.springframework.http.converter.HttpMessageNotReadableException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.univpm.COVID19stats.controller.MainController;

/** Classe gestore-custom delle eccezioni.
 *  Si occupa di elaborare la risposta in base al tipo di eccezione
 *  che riceve dal controller
 *  
 *  Non utilizzato esplicitamente ma classe costruita in modo da fornire questa opportunità
 *  @see MainController
 *  @author Alesi Mattia, Incipini Marco
 */
public class RequestBodyException extends Throwable {
	
	public RequestBodyException(Exception e) {
		super();
	}
	
	/** Caso in cui si effettua una richiesta di tipo POST a una degli URL 
	 *  previsti ma non viene inviato alcun dato nel RequestBody
	 *  
	 * @param e Oggetto eccezione tipizzato dalla classe che l'ha lanciato
	 * @return String messaggio di errore
	 */
	public String missingRequestBody(HttpMessageNotReadableException e) {
		String msg = "Effettuata richiesta POST ma RequestBody vuoto! \nEccezione: \t" + e.getClass();
		System.out.println(msg);
	    return msg;
	}
	
	/** Caso in cui il JSON abbia attributi scritti in modo errato
	 * e quindi non "parsabili" nella classe Paese nè Filtro
	 * 
	 * {@code} "valoremaXXX": "1400" || "dummyField": 4
	 * @param e Oggetto eccezione tipizzato dalla classe che l'ha lanciato
	 * @return String messaggio di errore
	 */
	public String wrongJSONformat(UnrecognizedPropertyException e) {
		String msg = "RequestBody con campi JSON  mal formattati! \nEccezione: \t" + e.getClass();
		System.out.println(msg);
	    return msg;
	}
	
	/** Caso in cui il JSON abbia attributi con valori (a destra) non corrispondendi al dominio
	 * 
	 * { @code "percentuale": "FFALSSEE" || "valoremax": dieci }
	 * @param e Oggetto eccezione tipizzato dalla classe che l'ha lanciato
	 * @return String messaggio di errore
	 */
	public String wrongJSONvalues(JsonParseException e) {
		String msg = "RequestBody con valori del JSON non ammissibili! \nEccezione: \t" + e.getClass();
		System.out.println(msg);
	    return msg;
	}

}
