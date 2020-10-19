package com.univpm.COVID19stats.tests;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.univpm.COVID19stats.controller.MainController;
import com.univpm.COVID19stats.exceptions.RequestBodyException;

class TestRequest {

	private MainController mainController;


	@BeforeEach
	void setUp() throws Exception {
		mainController = new MainController();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	/*Il test lancia UnrecognizedProperty che però viene catturato e gestito dal nostro RequestBodyException
	 * Vedere come adattare il test
	 */
	@Test
	void MissingReqBody() {
		assertAll(							/** Anche non passando correttamente il primo parametro, i metodi 'work' prevedono dei valori di fallback" */
			(Executable) assertThrows(RequestBodyException.class, ()->mainController.work("whatever", null) ),
			(Executable) assertThrows(RequestBodyException.class, ()->mainController.workWithStat("whatever", "any", null) )
		);
	}
	
	@Test
	void JsonParseError() {
		String badJson = "{\"A\": \"Barbados\", \"B\": \"barbados\", \"C\": \"BB\" }";
		assertThrows(RequestBodyException.class, ()->mainController.work("whatever", badJson) );
	}
	
	@Test
	void correctExecution() {
		String goodJson1 = "{\"Country\": \"Barbados\", \"Slug\": \"barbados\", \"ISO2\": \"BB\" }";
		String goodJson2 = "richiesta con filtro";
		
		assertDoesNotThrow( ()->mainController.work("whatever", goodJson1) );
		assertDoesNotThrow( ()->mainController.workWithStat("whatever", "any", goodJson2) );
	}

}
