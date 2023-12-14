package services;

import model.AccountModel;
import model.CurrencyModel;
import model.CurrencyValueModel;
import model.TransactionModel;
import repository.AccountCrudOperations;
import repository.TransactionCrudOperation;
import repository.connectionDB;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountService {

    AccountCrudOperations accountCrudOperations;
    TransactionCrudOperation transactionCrudOperation;
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

    public Optional<BigDecimal> getActualBalanceBetween(int id_account, LocalDateTime startDate, LocalDateTime endDate) throws SQLException {
        List<BigDecimal> allTransaction = new ArrayList<>();

        for (TransactionModel t : transactionCrudOperation.findAllByIdAccount(id_account)){
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
}
