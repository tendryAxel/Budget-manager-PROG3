package com.example.walletprog3.controller;

import com.example.walletprog3.model.AccountModel;
import com.example.walletprog3.services.AccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    AccountService accountService;

    public AccountController(AccountService accountService) throws SQLException {
        this.accountService = accountService;
    }

    @GetMapping
    public List<AccountModel> findAll() throws SQLException {
        return this.accountService.findAll();
    }
}
