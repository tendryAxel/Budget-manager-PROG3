package Test;

import model.AccountModel;
import model.AccountType;
import repository.AccountCrudOperations;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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
            System.out.println("Account operation : ");
        AccountCrudOperations accountCrudOperations = new AccountCrudOperations();

        List<AccountModel> result = accountCrudOperations.findAll();
        System.out.println("Tous les Accounts :");
        for (AccountModel a : result) {
            System.out.println(a);
        }

        LocalDateTime updateDate = LocalDateTime.now();
        AccountModel accountModel = new AccountModel(1, "Savings", Timestamp.valueOf(updateDate), 1, AccountType.Banque);
        accountCrudOperations.save(accountModel);

        List<AccountModel> accountsToSave = new ArrayList<>();
        accountsToSave.add(new AccountModel(1, "Savings", Timestamp.valueOf(updateDate), 2, AccountType.Mobile_Money));

        List<AccountModel> savedAccounts = accountCrudOperations.saveAll(accountsToSave);
        System.out.println("Accounts enregistrés :");
        for (AccountModel ac : savedAccounts) {
            System.out.println(ac);
        }
        System.out.println("---------------");
        System.out.println("");
    }
}
