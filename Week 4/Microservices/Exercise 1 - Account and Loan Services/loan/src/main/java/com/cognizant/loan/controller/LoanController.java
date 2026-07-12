package com.cognizant.loan.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoanController {

    @GetMapping("/loans/{number}")
    public LoanDetails getLoanDetails(@PathVariable String number) {
        return new LoanDetails(number, "car", 400000.0, 3258.0, 18);
    }

    public static record LoanDetails(String number, String type, double loan, double emi, int tenure) {}
}
