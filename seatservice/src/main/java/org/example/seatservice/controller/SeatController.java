package org.example.seatservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/seat")
public class SeatController {
    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to Seat Service!";
    }
}

