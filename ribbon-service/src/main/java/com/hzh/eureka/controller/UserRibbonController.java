package com.hzh.eureka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/ribbon")
public class UserRibbonController {
    @Autowired
    private RestTemplate restTemplate;
    @GetMapping("/{id}")
    public String ribbon(@PathVariable("id") Long id) {
        return restTemplate.getForObject("http://user-service/user/{1}", String.class,id);
    }
}
