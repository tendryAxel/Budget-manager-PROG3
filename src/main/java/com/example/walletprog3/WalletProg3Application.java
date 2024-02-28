package com.example.walletprog3;

import com.example.walletprog3.controller.AccountController;
import com.example.walletprog3.repository.AccountCrudOperations;
import com.example.walletprog3.services.AccountService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.sql.SQLException;

@SpringBootApplication(scanBasePackages = "com.example.walletprog3")
public class WalletProg3Application {
	public static void main(String[] args) {
		/* AccountService accountService = new AccountService();
		accountService.setAccountCrudOperations(new AccountCrudOperations());
		AccountController accountController = new AccountController(accountService);
		System.out.println(accountController.findAll());
		SpringApplication.run(WalletProg3Application.class, args);4
		 */
		SpringApplication.run(WalletProg3Application.class, args);
	}
}
