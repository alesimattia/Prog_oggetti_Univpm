package com.univpm.COVID19stats.tests;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.univpm.COVID19stats.controller.MainController;
import com.univpm.COVID19stats.exceptions.RequestBodyMissingException;

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
		assertAll(									/** Anche non passando correttamente il primo parametro, i metodi 'work' prevedono dei valori di fallback" */
			(Executable) assertThrows(RequestBodyMissingException.class, ()->mainController.work("whatever", null) ),
			(Executable) assertThrows(RequestBodyMissingException.class, ()->mainController.workWithStat("whatever", "any", null) )
		);
	}

}
