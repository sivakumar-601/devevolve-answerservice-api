package com.devevolve.answerservice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/answerService")
public class DemoController {

    @GetMapping("/welcome")
    public ResponseEntity<String> getWelcomeNote() {
        return ResponseEntity.ok("Welcome to DevEvolve..dev tools");
    }

}