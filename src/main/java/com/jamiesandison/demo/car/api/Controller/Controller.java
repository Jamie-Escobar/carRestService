package com.jamiesandison.demo.car.api.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("statusResponse")
    public HttpStatus getResponse() {

        return HttpStatus.OK;
    }

}
