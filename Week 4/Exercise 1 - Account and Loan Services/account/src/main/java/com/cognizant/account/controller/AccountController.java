package com.cognizant.account.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @GetMapping("/accounts/{number}")
    public AccountDetails getAccountDetails(@PathVariable String number) {
        return new AccountDetails(number, "savings", 234343.0);
    }

    public static record AccountDetails(String number, String type, double balance) {}
}
