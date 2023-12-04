package Test;

import model.AccountModel;
import operation.AccountCrudOperations;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountCrudOperationsTest {
    public static void main(String[] args) {
        try {
            accountTest();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void accountTest() throws SQLException {
        AccountCrudOperations accountCrudOperations = new AccountCrudOperations();

        List<AccountModel> result = accountCrudOperations.findAll();
        System.out.println("Tous les Accounts :");
        for (AccountModel a : result) {
            System.out.println(a);
        }

        AccountModel accountModel = new AccountModel(1, "Savings", 2);
        accountCrudOperations.save(accountModel);

        List<AccountModel> accountsToSave = new ArrayList<>();
        accountsToSave.add(new AccountModel(2, "Checking", 1));

        List<AccountModel> savedAccounts = accountCrudOperations.saveAll(accountsToSave);
        System.out.println("Accounts enregistrés :");
        for (AccountModel ac : savedAccounts) {
            System.out.println(ac);
        }
    }
}
