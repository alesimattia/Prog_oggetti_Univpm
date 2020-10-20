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

	
	@Test
	void MissingReqBody() {
		assertThrows(NullPointerException.class, ()->mainController.work("whatever", null) );
		assertThrows(NullPointerException.class, ()->mainController.workWithStat("whatever", "any", null) );
		
	}
	
	@Test()
	void JsonParseError() {
		String badJson = "{\"A\": \"Barbados\", \"B\": \"barbados\", \"C\": \"BB\" }";
		assertThrows(UnrecognizedPropertyException.class, ()->mainController.work("whatever", badJson) );
	}
	
	@Test
	void correctExecution() {
		String goodJson1 = "{\"Country\": \"Barbados\", \"Slug\": \"barbados\", \"ISO2\": \"BB\" }";
		String goodJson2 = "{\"Country\": \"Italy\",\"Slug\": \"italy\",\"ISO2\": \"IT\" }, "
						 + "{ \"percentuale\": true, \"valoremax\": 1200, \"valoremin\": 12, \"datamax\": null, \"datamin\": null }";
		
		assertDoesNotThrow( ()->mainController.work("whatever", goodJson1) );
		assertDoesNotThrow( ()->mainController.workWithStat("whatever", "any", goodJson2) );
	}

}
