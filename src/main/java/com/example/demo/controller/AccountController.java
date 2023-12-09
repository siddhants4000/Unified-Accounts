package com.example.demo.controller;

import com.example.demo.entity.Account;
import com.example.demo.entity.User;
import com.example.demo.model.WrapperResponse;
import com.example.demo.request.AccountRequest;
import com.example.demo.response.AccountResponse;
import com.example.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/add")
    public WrapperResponse<AccountResponse> addAccount(@RequestBody AccountRequest accountRequest) {
        return accountService.addAccount(accountRequest);
    }

    @GetMapping("/list")
    public List<Account> allAccounts() {
        return accountService.allAccounts();
    }

    @GetMapping("/users")
    public List<Account> allAccountsByUserId(@RequestParam UUID userId) {
        return accountService.allAccountsByUserId(userId);
    }

}
