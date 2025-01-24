package org.example.movieservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/movie")
public class MovieController {

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to Movie Service!";
    }
}
