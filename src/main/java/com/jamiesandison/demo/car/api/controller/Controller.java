package com.jamiesandison.demo.car.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/private")
public class Controller {

    @GetMapping("/status")
    public ResponseEntity<String> privateStatusResponse() {

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }



}
