package com.jamiesandison.demo.car.api.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@SpringBootTest
public class ControllerTests {

    @Autowired
    private Controller controller;
    @Test
    void privateStatusResponse() {

        ResponseEntity<String> privateResponse = controller.privateStatusResponse();

        Assertions.assertEquals("OK", privateResponse.getBody());
        Assertions.assertEquals(HttpStatus.OK, privateResponse.getStatusCode());
    }
}
