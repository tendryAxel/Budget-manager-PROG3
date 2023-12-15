package services;

import model.*;
import repository.AccountCrudOperations;
import repository.SubCategoryCrudOperations;
import repository.TransactionCrudOperation;
import repository.connectionDB;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;

public class AccountService {

    AccountCrudOperations accountCrudOperations;
    TransactionCrudOperation transactionCrudOperation;
    SubCategoryCrudOperations subCategoryCrudOperations;
    public AccountService() {
        this.accountCrudOperations = new AccountCrudOperations();
        this.transactionCrudOperation = new TransactionCrudOperation();
    }

    public List<AccountModel> findAll() throws SQLException {
        return accountCrudOperations.findAll();
    }

    public AccountModel save(AccountModel toSave){
        return accountCrudOperations.save(toSave);
    }

    public List<AccountModel> saveAll(List<AccountModel> toSave){
        return accountCrudOperations.saveAll(toSave);
    }

    public Optional<BigDecimal> sumOfTransaction(List<TransactionModel> toAdd, LocalDateTime startDate, LocalDateTime endDate){
        List<BigDecimal> allTransaction = new ArrayList<>();

        for (TransactionModel t : toAdd){
            LocalDateTime dateTime = t.getTransaction_date();
            if (dateTime.isAfter(startDate) || dateTime.isBefore(endDate)){
                switch (t.getType()){
                    case CREDIT -> {
                        allTransaction.add(t.getAmount());
                        break;
                    }
                    case DEBIT -> {
                        allTransaction.add(t.getAmount().multiply(BigDecimal.valueOf(-1)));
                        break;
                    }
                }
            }
        }

        return allTransaction.stream().reduce(BigDecimal::add);
    }

    public Optional<BigDecimal> getActualBalanceBetween(int id_account, LocalDateTime startDate, LocalDateTime endDate) throws SQLException {
        return sumOfTransaction(transactionCrudOperation.findAllByIdAccount(id_account), startDate, endDate);
    }

    public Optional<BigDecimal> getTransactionByCategory(int id_account, LocalDateTime startDate, LocalDateTime endDate, int id_subcategory) throws SQLException {
        List<BigDecimal> allTransaction = new ArrayList<>();

        for (TransactionModel t : transactionCrudOperation.findAllByIdAccountAndSubCategory(id_account, id_subcategory)){
            LocalDateTime dateTime = t.getTransaction_date();
            if (dateTime.isAfter(startDate) || dateTime.isBefore(endDate)){
                switch (t.getType()){
                    case CREDIT -> {
                        allTransaction.add(t.getAmount());
                        break;
                    }
                    case DEBIT -> {
                        allTransaction.add(t.getAmount().multiply(BigDecimal.valueOf(-1)));
                        break;
                    }
                }
            }
        }

        return allTransaction.stream().reduce(BigDecimal::add);
    }

    public Map<SubCategoryModel, Optional<BigDecimal>> getTransactionByCategory(int id_account, LocalDateTime startDate, LocalDateTime endDate) throws SQLException {
        List<SubCategoryModel> subCategoryModelList = subCategoryCrudOperations.findAll();
        Map<SubCategoryModel, Optional<BigDecimal>> allTransactionByCategory = new HashMap<>();
        for (SubCategoryModel subCategory : subCategoryModelList){
            List<TransactionModel> transactionList = transactionCrudOperation.findAllByIdAccountAndSubCategory(
                    id_account,
                    subCategory.getId_subcategory()
            );

            Optional<BigDecimal> totalValue = sumOfTransaction(transactionList, startDate, endDate);

            allTransactionByCategory.put(subCategory, totalValue);
        }

        return allTransactionByCategory;
    }
}
