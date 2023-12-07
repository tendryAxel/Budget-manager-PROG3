package proccess;


import model.TransactionModel;
import model.TransferModel;
import repository.CurrencyCrudOperations;
import repository.TransactionCrudOperation;
import repository.TransferCrudOperation;

import java.sql.SQLException;
import java.util.TreeSet;

public class TransactionProccess {
    public boolean makeTransaction(TransactionModel tr1, TransactionModel tr2){
        if (tr1.getId_account() == tr2.getId_account()){
            return false;
        }
        try {
            int currency1 = CurrencyCrudOperations.getAccountCurrency(tr1.getId_account());
            int currency2 = CurrencyCrudOperations.getAccountCurrency(tr2.getId_account());
            TransferModel transferModel = new TransferModel();
            transferModel.setTransactionDebtor(TransactionCrudOperation.save(tr1));
            transferModel.setTransactionCredit(TransactionCrudOperation.save(tr2));
            TransferCrudOperation.save(transferModel);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    };
}
