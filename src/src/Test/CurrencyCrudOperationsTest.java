package Test;

import model.CurrencyModel;
import repository.CurrencyCrudOperations;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CurrencyCrudOperationsTest {
    public static void main(String[] args) {
        try {
            currencyTest();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void currencyTest() throws SQLException {
        CurrencyCrudOperations currencyCrudOperations = new CurrencyCrudOperations();

        List<CurrencyModel> result = currencyCrudOperations.findAll();
        System.out.println("Tous les Currency :");
        for (CurrencyModel c : result) {
            System.out.println(c);
        }

        CurrencyModel currencyModel = new CurrencyModel(12, "zah" , "USD");
        currencyCrudOperations.save(currencyModel);

        List<CurrencyModel> currenciesToSave = new ArrayList<>();
        currenciesToSave.add(new CurrencyModel(18, "baba" , "EUR"));

        List<CurrencyModel> savedCurrencies = currencyCrudOperations.saveAll(currenciesToSave);
        System.out.println("Currencies enregistrés :");
        for (CurrencyModel cu : savedCurrencies) {
            System.out.println(cu);
        }
    }
}
