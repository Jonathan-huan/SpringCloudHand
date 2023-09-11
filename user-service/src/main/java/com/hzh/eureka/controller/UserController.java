package com.hzh.eureka.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
public class UserController {
    private final static Random random = new Random();
    @GetMapping("/{id}")
    public String user(@PathVariable Long id) {
        System.out.println("user-service: " + id);
        return "user-service: " + id;
    }

    @GetMapping("/circuit-breaker/{id}")
    public String circuitBreaker(@PathVariable Long id) {
        if(random.nextBoolean()) throw new RuntimeException("circuit-breaker");
        return "user-service: " + id;
    }

}
