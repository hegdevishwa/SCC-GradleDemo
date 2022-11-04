package com.contract.demo.springcontractdemo;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    @GetMapping(value = "/greet", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GreetingModel> get() {
        return ResponseEntity.ok().body(new GreetingModel("Hello world!"));
    }

    public static class GreetingModel {

        private final String greeting;

        public GreetingModel(final String greeting) {
            this.greeting = greeting;
        }

        public String getGreeting() {
            return greeting;
        }
    }
}
