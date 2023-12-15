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
        this.subCategoryCrudOperations = new SubCategoryCrudOperations();
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

    public BigDecimal sumOfTransaction(List<TransactionModel> toAdd, LocalDateTime startDate, LocalDateTime endDate){
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

        return allTransaction.stream().reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
    }

    public BigDecimal getActualBalanceBetween(int id_account, LocalDateTime startDate, LocalDateTime endDate) throws SQLException {
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
