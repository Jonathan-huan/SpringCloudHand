package com.hzh.eureka.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope   // 通过 Spring Cloud 原生注解 @RefreshScope 实现配置自动更新
public class ConfigController {
    @Value("${server.port}")
    private String serverPort;
    @Value("${config.info}")
    private String configInfo;
    @Value("${config.version}")
    private String configVersion;

    @GetMapping("/config")
    public String getConfig() {
        return "serverPort: " + serverPort + "\nconfigInfo: " + configInfo + "\nconfigVersion: " + configVersion;
    }
}
