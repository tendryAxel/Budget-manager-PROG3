package services;

import model.CurrencyModel;
import repository.CurrencyCrudOperations;

import java.sql.SQLException;
import java.util.List;

public class CurrencyService {

    CurrencyCrudOperations currencyCrudOperations;
    public CurrencyService(CurrencyCrudOperations currencyCrudOperations) {
        this.currencyCrudOperations = currencyCrudOperations;
    }

    public List<CurrencyModel> findAll() throws SQLException {
        return currencyCrudOperations.findAll();
    }

    public CurrencyModel save(CurrencyModel toSave) throws SQLException {
        return currencyCrudOperations.save(toSave);
    }

    public List<CurrencyModel> saveAll(List<CurrencyModel> toSave) throws SQLException {
        return currencyCrudOperations.saveAll(toSave);
    }
}
