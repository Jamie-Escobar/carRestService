package com.jamiesandison.demo.car.api;

import com.jamiesandison.demo.car.api.Controller.Controller;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class DemoCarApiApplicationTests {

	@Test
	void statusResponse_httpResponse_200() {

		Controller controller = new Controller();
		String response = String.valueOf(controller.getResponse());

		Assertions.assertEquals("200 OK", response);
	}

	@Test
	public void responseEntityTest() {

		Controller controller = new Controller();
		ResponseEntity<String> response = controller.response();

		Assertions.assertEquals("200", response);

	}

}
