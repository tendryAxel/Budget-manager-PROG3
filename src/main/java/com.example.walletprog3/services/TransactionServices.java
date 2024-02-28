package java.com.example.walletprog3.services;

import model.*;

import java.com.example.walletprog3.model.BalanceModel;
import java.com.example.walletprog3.model.TransactionModel;
import java.com.example.walletprog3.repository.BalanceCrudOperations;
import java.com.example.walletprog3.repository.TransactionCrudOperation;

import java.math.BigDecimal;
import java.sql.*;
import java.util.List;

public class TransactionServices {

    TransactionCrudOperation transactionCrudOperation;
    BalanceCrudOperations balanceCrudOperations;
    public TransactionServices() {
        this.transactionCrudOperation = new TransactionCrudOperation();
        this.balanceCrudOperations = new BalanceCrudOperations();
    }

    public List<TransactionModel> findAll() throws SQLException {
        return transactionCrudOperation.findAll();
    }

    public TransactionModel save(TransactionModel toSave) throws SQLException {
        return transactionCrudOperation.save(toSave);
    }

    public List<TransactionModel> saveAll(List<TransactionModel> toSave) throws SQLException {
        return transactionCrudOperation.saveAll(toSave);
    }

    public BalanceModel FunctionTransaction(int id_account , TransactionModel transaction) throws SQLException {
        BalanceModel balanceModel = new BalanceModel();
        double amount = Double.parseDouble(String.valueOf(this.balanceCrudOperations.findLastBalanceOf(id_account).getValue()));
        TransactionModel transactionResult = this.transactionCrudOperation.save(transaction);
        balanceModel.setId_account(id_account);
        switch (transactionCrudOperation.getTransactionType(transactionResult.getId())){
            case CREDIT :
                amount += Double.parseDouble(String.valueOf(transaction.getAmount()));

                balanceModel.setValue(BigDecimal.valueOf(amount));

                this.balanceCrudOperations.save(balanceModel);
                break;
            case DEBIT :
                amount -= Double.parseDouble(String.valueOf(transaction.getAmount()));

                balanceModel.setValue(BigDecimal.valueOf(amount));

                this.balanceCrudOperations.save(balanceModel);

                break;
        }
        return balanceModel;
    }

    public BigDecimal getActualBalance(int id_account) throws SQLException {
        return transactionCrudOperation.getActualBalance(id_account);
    }
}
