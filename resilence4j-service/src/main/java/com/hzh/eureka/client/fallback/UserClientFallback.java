package com.hzh.eureka.client.fallback;

import com.hzh.eureka.client.UserClient;
import org.springframework.stereotype.Component;

@Component
public class UserClientFallback implements UserClient {
    @Override
    public String circuitBreaker(Long id) {
        return "request fails, id=" + id + " UserClientFallback";
    }
}
