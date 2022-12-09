package com.jamiesandison.demo.car.api;

import com.jamiesandison.demo.car.api.Controller.Controller;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoCarApiApplicationTests {

	@Test
	void httpResponse_200() {

		Controller controller = new Controller();
		String response = String.valueOf(controller.getResponse());

		Assertions.assertEquals("200 OK", response);
	}

}
