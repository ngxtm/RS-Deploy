package com.rs.retailstore.controller;

import com.rs.retailstore.model.Greeting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/v1")
public class CustomerGreetingController {
      private static final String greetingTemplate = "Hello, %s %s";
      private final AtomicLong counter = new AtomicLong();
      @GetMapping("/greeting")
      public Greeting greeting(@RequestParam(value = "gender", defaultValue = "1") boolean gender
      , @RequestParam(value = "name", defaultValue = "World") String name) {
            return Greeting.builder()
                    .id(counter.incrementAndGet())
                    .content(String.format(greetingTemplate, gender ? "Mr." : "Ms.", name))
                    .build();
      }
}
