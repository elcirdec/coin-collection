package com.coincollection.backend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Basic controller to verify that the API is up and running.
 */
@RestController
public class HelloController {

    @GetMapping("/api/hello")
    public String sayHello() {
        // Simple smoke test message
        return "Backend is responding: Hello World!";
    }
}