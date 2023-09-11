package com.hzh.eureka.client;

import com.hzh.eureka.config.LoadBalancerConfig;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "user-service")
@LoadBalancerClient(value = "user-service", configuration = LoadBalancerConfig.class)
public interface UserClient {
    @GetMapping("/user/{id}")
    String user(@PathVariable Long id);
}
