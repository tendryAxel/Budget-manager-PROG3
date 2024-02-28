package com.example.walletprog3.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/ping")
public class pingController {
    @GetMapping
    public String getPing(){
        return "pong";
    }
}
