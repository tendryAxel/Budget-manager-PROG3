package com.example.walletprog3.services;

import com.example.walletprog3.model.*;
import com.example.walletprog3.repository.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class AccountService {
    public void setAccountCrudOperations(AccountCrudOperations accountCrudOperations) {
        this.accountCrudOperations = accountCrudOperations;
    }

    AccountCrudOperations accountCrudOperations;
    TransactionCrudOperation transactionCrudOperation;
    SubCategoryCrudOperations subCategoryCrudOperations;
    CurrencyValueCrudOperations currencyValueCrudOperations;

    public AccountService(AccountCrudOperations accountCrudOperations, TransactionCrudOperation transactionCrudOperation, SubCategoryCrudOperations subCategoryCrudOperations, CurrencyValueCrudOperations currencyValueCrudOperations) {
        this.accountCrudOperations = accountCrudOperations;
        this.transactionCrudOperation = transactionCrudOperation;
        this.subCategoryCrudOperations = subCategoryCrudOperations;
        this.currencyValueCrudOperations = currencyValueCrudOperations;
    }

    public List<AccountModel> findAll() throws SQLException {
        return this.accountCrudOperations.findAll();
    }

    public AccountModel save(AccountModel toSave){
        return accountCrudOperations.save(toSave);
    }

    public List<AccountModel> saveAll(List<AccountModel> toSave){
        return accountCrudOperations.saveAll(toSave);
    }

    public BigDecimal sumOfTransaction(List<TransactionModel> toAdd, LocalDateTime startDate, LocalDateTime endDate) throws SQLException {
        List<BigDecimal> allTransaction = new ArrayList<>();

        for (TransactionModel t : toAdd){
            LocalDateTime dateTime = t.getTransaction_date();
            if (dateTime.isAfter(startDate) || dateTime.isBefore(endDate)){
                switch (t.getType()){
                    case CREDIT -> {
                        allTransaction.add(
                                t.getAmount().multiply(currencyValueCrudOperations.findByDateAndCurrency(
                                    dateTime,
                                    t.getId_currency(),
                                    accountCrudOperations.findById(t.getId_account()).getId_currency()
                                ).get(0).getAmount())
                        );
                        break;
                    }
                    case DEBIT -> {
                        allTransaction.add(
                                t.getAmount().multiply(currencyValueCrudOperations.findByDateAndCurrency(
                                        dateTime,
                                        accountCrudOperations.findById(t.getId_account()).getId_currency(),
                                        t.getId_currency()
                                ).get(0).getAmount())
                        );
                        break;
                    }
                }
            }
        }

        return allTransaction.stream().reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
    }

    public BigDecimal getBalanceBetween(int id_account, LocalDateTime startDate, LocalDateTime endDate) throws SQLException {
        return sumOfTransaction(transactionCrudOperation.findAllByIdAccount(id_account), startDate, endDate);
    }

    public Map<SubCategoryModel, BigDecimal> getTransactionByCategory(int id_account, LocalDateTime startDate, LocalDateTime endDate) throws SQLException {
        List<SubCategoryModel> subCategoryModelList = subCategoryCrudOperations.findAll();
        Map<SubCategoryModel, BigDecimal> allTransactionByCategory = new HashMap<>();
        for (SubCategoryModel subCategory : subCategoryModelList){
            List<TransactionModel> transactionList = transactionCrudOperation.findAllByIdAccountAndSubCategory(
                    id_account,
                    subCategory.getId_subcategory()
            );

            BigDecimal totalValue = sumOfTransaction(transactionList, startDate, endDate);

            allTransactionByCategory.put(subCategory, totalValue);
        }

        return allTransactionByCategory;
    }
}
