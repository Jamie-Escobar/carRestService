package com.jamiesandison.demo.car.api.ControllerTests;

import com.jamiesandison.demo.car.api.Controller.Controller;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;


@SpringBootTest
public class ControllerTests {

    @Autowired
    private Controller controller;
    @Test
    void privateStatusResponse() {

        String response = String.valueOf(controller.privateStatusResponse());

        Assertions.assertEquals("OK", response);

    }
}
