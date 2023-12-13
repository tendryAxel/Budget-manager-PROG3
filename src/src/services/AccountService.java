package services;

import model.AccountModel;
import repository.AccountCrudOperations;

import java.sql.SQLException;
import java.util.List;

public class AccountService {

    AccountCrudOperations accountCrudOperations;
    public AccountService(AccountCrudOperations accountCrudOperations) {
        this.accountCrudOperations = accountCrudOperations;
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
}
