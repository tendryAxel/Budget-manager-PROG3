package Test;

import model.CurrencyValueModel;
import repository.CurrencyValueCrudOperations;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CurrencyValueCrudOperationsTest {
    public static void main(String[] args) throws SQLException {
        currencyValueTest();
    }
    public static void currencyValueTest() throws SQLException {
        System.out.println("Currency value operation : ");
        CurrencyValueCrudOperations  currencyValueCrudOperations = new CurrencyValueCrudOperations();
        List<CurrencyValueModel> result = currencyValueCrudOperations.findAll();
        System.out.println("Tous les CurrencyValues :");
        for (CurrencyValueModel cv : result){
            System.out.println(cv);
        }
        Date date = new Date();
        CurrencyValueModel currencyValueModel = new CurrencyValueModel(4 , 2 ,3 , BigDecimal.valueOf(2300.00) , date);
        currencyValueCrudOperations.save(currencyValueModel);

        List<CurrencyValueModel>  CurrencyValueToSave = new ArrayList<>();
        CurrencyValueToSave.add(new CurrencyValueModel(5 , 2 , 3 ,BigDecimal.valueOf(1100.00) , date));


        List<CurrencyValueModel> savedAccounts  = currencyValueCrudOperations.saveAll(CurrencyValueToSave);
        System.out.println("Currency enregistrés :");
        for (CurrencyValueModel cvv : savedAccounts) {
            System.out.println(cvv);
        }
        System.out.println("---------------");
        System.out.println("");
    }
    }

