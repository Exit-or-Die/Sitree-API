package com.eod.sitree.health;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/health")
public class HealthCheckController {

    @GetMapping
    public String healthCheck(){
        return "healthy";
    }
}
