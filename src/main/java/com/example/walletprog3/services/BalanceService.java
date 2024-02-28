package com.example.walletprog3.services;

import com.example.walletprog3.model.BalanceModel;
import com.example.walletprog3.repository.BalanceCrudOperations;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class BalanceService {

    BalanceCrudOperations balanceCrudOperations;
    public BalanceService(BalanceCrudOperations balanceCrudOperations) {
        this.balanceCrudOperations = balanceCrudOperations;
    }

    public List<BalanceModel> findAll() throws SQLException {
        return balanceCrudOperations.findAll();
    }

    public BalanceModel save(BalanceModel toSave) throws SQLException {
        return balanceCrudOperations.save(toSave);
    }

    public List<BalanceModel> saveAll(List<BalanceModel> toSave) throws SQLException {
        return balanceCrudOperations.saveAll(toSave);
    }
}
