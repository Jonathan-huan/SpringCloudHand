package com.hzh.eureka.client;

import com.hzh.eureka.client.fallback.UserClientFallback;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "user-service")
public interface UserClient {
    @GetMapping("/user/circuit-breaker/{id}")
    @CircuitBreaker(name = "myCircuitBreaker",fallbackMethod = "fallback")
    String circuitBreaker(@PathVariable Long id);

    default String fallback(Long id,Throwable throwable) {
        return "request fails, id=" + id + " UserClientFallback";
    }
}
