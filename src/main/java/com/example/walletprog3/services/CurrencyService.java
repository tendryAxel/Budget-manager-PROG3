package com.example.walletprog3.services;

import com.example.walletprog3.model.CurrencyModel;
import com.example.walletprog3.repository.CurrencyCrudOperations;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
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
