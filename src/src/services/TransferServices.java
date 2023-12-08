package services;


import model.TransactionModel;
import model.TransferModel;
import repository.CurrencyCrudOperations;
import repository.TransactionCrudOperation;
import repository.TransferCrudOperation;

import java.sql.SQLException;

public class TransferServices {
    public boolean makeTransfer(TransactionModel tr1, TransactionModel tr2){
        if (tr1.getId_account() == tr2.getId_account()){
            return false;
        }
        try {
            TransferCrudOperation transferCrudOperation = new TransferCrudOperation();
            TransactionCrudOperation transactionCrudOperation = new TransactionCrudOperation();
            CurrencyCrudOperations currencyCrudOperations = new CurrencyCrudOperations();

            int currency1 = currencyCrudOperations.getAccountCurrency(tr1.getId_account());
            int currency2 = currencyCrudOperations.getAccountCurrency(tr2.getId_account());
            TransferModel transferModel = new TransferModel();
            transferModel.setTransactionDebtor(transactionCrudOperation.save(tr1).getId());
            transferModel.setTransactionCredit(transactionCrudOperation.save(tr2).getId());
            transferCrudOperation.save(transferModel);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    };
}
