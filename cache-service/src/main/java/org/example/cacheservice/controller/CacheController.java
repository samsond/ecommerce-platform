package org.example.cacheservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/cache")
public class CacheController {
    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to Cache Service!";
    }
}

