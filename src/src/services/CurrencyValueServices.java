package services;

import model.CurrencyValueModel;
import repository.CurrencyValueCrudOperations;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class CurrencyValueServices {

    CurrencyValueCrudOperations currencyValueCrudOperations;

    public CurrencyValueServices() {
        this.currencyValueCrudOperations = new CurrencyValueCrudOperations();
    }

    public List<CurrencyValueModel> getAllCurrencyValues() throws SQLException {
        return currencyValueCrudOperations.findAll();
    }

    public List<CurrencyValueModel> saveAllCurrencyValues(List<CurrencyValueModel> toSave) throws SQLException {
        return currencyValueCrudOperations.saveAll(toSave);
    }

    public CurrencyValueModel saveCurrencyValue(CurrencyValueModel toSave) throws SQLException {
        return currencyValueCrudOperations.save(toSave);
    }

    public List<CurrencyValueModel> getCurrencyValuesByDateAndCurrency(LocalDate date, int id_source, int id_destination) throws SQLException {
        return currencyValueCrudOperations.findByDateAndCurrency(date, id_source, id_destination);
    }

}
