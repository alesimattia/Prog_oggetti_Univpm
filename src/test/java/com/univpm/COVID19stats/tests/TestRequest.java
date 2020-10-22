package com.univpm.COVID19stats.tests;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.rules.ExpectedException;
import org.springframework.http.converter.HttpMessageNotReadableException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.univpm.COVID19stats.controller.MainController;
import com.univpm.COVID19stats.exceptions.RequestBodyException;

/** Classe con cui vengono effettuati i test 
 * usando il framework Junit 5
 * @author Alesi Mattia, Incipini Marco
 *
 */
class TestRequest {

	private MainController mainController;

	@BeforeEach
	void setUp() throws Exception {
		mainController = new MainController();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	
	/** Verifica che in assenza di un RequestBody della richiesta POST
	 *  venga lanciata l'eccezione che verrà poi gestita da un'apposita classe.
	 *  Non è rilevante la categoria/statistica che si inserisce nell'URL perchè
	 *  il metodo prevede valori di fallback
	 *  @see RequestBodyException.class
	 *  @exception NullPointerException
	 *  
	 *  L'operazione viene ripetuta per entrambi i metodi del controller
	 *  per testare entrambi i tipi di richiesta HTTP utilizzabili
	 *  @see MainController.class
	 */
	@Test
	void MissingReqBody() {
		assertThrows(NullPointerException.class, ()->mainController.work("whatever", null) );
		assertThrows(NullPointerException.class, ()->mainController.workWithStat("whatever", "any", null) );
	}
	
	
	/** Verifica che nel caso in cui si abbia scritto un JSON nel RequestBody
	 * in modo non corretto, venga lanciata l'eccezione che sarà gestita 
	 * dall'apposita classe custom.
	 * In particolare testa gli errori sia nell'attributo @see badJson1
	 * che nel valore @see badJson2 .
	 * 
	 * @see RequestBodyException.class
	 * @exception UnrecognizedPropertyException
	 * L'operazione viene ripetuta per entrambi i metodi del controller
	 * per testare entrambi i tipi di richiesta HTTP utilizzabili
	 * @see MainController.class
	 */
	@Test()
	void JsonParseError() {
		String badJson1 = "{\"A\": \"Barbados\", \"B\": \"barbados\", \"C\": \"BB\" }";
		assertThrows(UnrecognizedPropertyException.class, ()->mainController.work("whatever", badJson1) );
		assertThrows(UnrecognizedPropertyException.class, ()->mainController.workWithStat("whatever", "any", badJson1) );
		
		String badJson2 = "{\"Country\": \"Italy\",\"Slug\": \"italy\",\"ISO2\": \"IT\" }, "
				 + "{ \"percentuale\": FFAALLSSEE, \"valoremax\": AAA, \"valoremin\": 12, \"datamax\": null, \"datamin\": null }";
		assertThrows(JsonParseException.class, ()->mainController.work("whatever", badJson2) );
		assertThrows(JsonParseException.class, ()->mainController.workWithStat("whatever", "any", badJson2) );
	}
	
	
	/** Verifica che in presenza di una richiesta POST effettuata correttamente
	 * 	l'applicazione risponda senza lanciare alcuna eccezione.
	 *  L'operazione è ripetuta per entrambi i tipi di chiamate HTTP possibili.
	 *  
	 *  { @http://localhost:8080/{tipo} }
	 *  { @http://localhost:8080/{tipo}/{statistica} }
	 */
	@Test
	void correctExecution() {
		String goodJson1 = "{\"Country\": \"Barbados\", \"Slug\": \"barbados\", \"ISO2\": \"BB\" }";
		String goodJson2 = "{\"Country\": \"Italy\",\"Slug\": \"italy\",\"ISO2\": \"IT\" }, "
						 + "{ \"percentuale\": true, \"valoremax\": 1200, \"valoremin\": 12, \"datamax\": null, \"datamin\": null }";
		
		assertDoesNotThrow( ()->mainController.work("whatever", goodJson1) );
		assertDoesNotThrow( ()->mainController.workWithStat("whatever", "any", goodJson2) );
	}

}
