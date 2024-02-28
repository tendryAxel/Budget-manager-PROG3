package com.example.walletprog3.services;


import com.example.walletprog3.model.*;
import com.example.walletprog3.repository.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransferServices {
    CurrencyCrudOperations currencyCrudOperations;
    TransactionCrudOperation transactionCrudOperation;
    TransferCrudOperation transferCrudOperation;
    TransactionServices transactionServices;

    public TransferServices(CurrencyCrudOperations currencyCrudOperations, TransactionCrudOperation transactionCrudOperation, TransferCrudOperation transferCrudOperation, TransactionServices transactionServices) {
        this.currencyCrudOperations = currencyCrudOperations;
        this.transactionCrudOperation = transactionCrudOperation;
        this.transferCrudOperation = transferCrudOperation;
        this.transactionServices = transactionServices;
    }

    public List<TransferModel> findAll() throws SQLException {
        return transferCrudOperation.findAll();
    }

    public boolean makeTransfer(TransactionModel tr1, TransactionModel tr2){
        if (tr1.getId_account() == tr2.getId_account()){
            return false;
        }
        try {
            this.transactionServices.FunctionTransaction(tr1.getId_account(), tr1);
            this.transactionServices.FunctionTransaction(tr2.getId_account(), tr2);

            int currency1 = this.currencyCrudOperations.getAccountCurrency(tr1.getId_account());
            int currency2 = this.currencyCrudOperations.getAccountCurrency(tr2.getId_account());
            TransferModel transferModel = new TransferModel();
            transferModel.setTransactionDebtor(this.transactionCrudOperation.save(tr1).getId());
            transferModel.setTransactionCredit(this.transactionCrudOperation.save(tr2).getId());
            this.transferCrudOperation.save(transferModel);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    };

    public boolean makeTransfer(int idSender, int idReceive, String label, BigDecimal amount, LocalDateTime datetime) throws SQLException {
        if (idSender == idReceive){
            return false;
        }

        TransactionModel sendTransaction = new TransactionModel();
        sendTransaction.setAmount(amount);
        sendTransaction.setLabel(label);
        sendTransaction.setType(TransactionType.DEBIT);
        sendTransaction.setTransaction_date(datetime);
        sendTransaction.setId_account(idSender);

        TransactionModel receiveTransaction = new TransactionModel();
        receiveTransaction.setAmount(amount);
        receiveTransaction.setLabel(label);
        receiveTransaction.setType(TransactionType.CREDIT);
        receiveTransaction.setTransaction_date(datetime);
        sendTransaction.setId_account(idReceive);

        transactionServices.FunctionTransaction(idSender, sendTransaction);
        transactionServices.FunctionTransaction(idReceive, receiveTransaction);

        return true;
    };
}
