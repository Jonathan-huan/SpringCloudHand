package com.hzh.eureka.controller;

import com.hzh.eureka.client.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class ResilienceController {
    @Resource
    private UserClient userClient;

    @GetMapping("/circuit-breaker/{id}")
    public String circuitBreaker(@PathVariable Long id) {
        return userClient.circuitBreaker(id);
    }

}
