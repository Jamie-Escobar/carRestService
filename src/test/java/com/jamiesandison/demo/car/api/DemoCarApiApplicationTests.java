package com.jamiesandison.demo.car.api;

import com.jamiesandison.demo.car.api.Controller.Controller;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@SpringBootTest
class DemoCarApiApplicationTests {

	/**
	 * need to fix this failing test below
	 */
	@Test
	public void privateStatusTest() {

		Controller controller = new Controller();

		ResponseEntity<String> stringResponseEntity = controller.privateStatusResponse();
		Assertions.assertEquals(HttpStatus.OK, stringResponseEntity);
	}

}
