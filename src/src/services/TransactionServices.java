package services;

import model.AccountModel;
import model.BalanceModel;
import model.TransactionModel;
import repository.BalanceCrudOperations;
import repository.TransactionCrudOperation;
import repository.connectionDB;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;

public class TransactionServices {

    public static void FunctionTransaction(int id_account , TransactionModel transaction) throws SQLException {
        TransactionCrudOperation transactionCrudOperation = new TransactionCrudOperation();
        BalanceCrudOperations balanceCrudOperations = new BalanceCrudOperations();
        BalanceModel balanceModel = new BalanceModel();
        double amount = 0;
        switch (transaction.getType()){
            case CREDIT :
                transactionCrudOperation.save(transaction);
                balanceModel.setId_account(id_account);

                amount = Double.parseDouble(String.valueOf(balanceCrudOperations.findLastBalanceOf(id_account).getValue()));
                amount += Double.parseDouble(String.valueOf(transaction.getAmount()));

                balanceModel.setValue(BigDecimal.valueOf(amount));

                balanceCrudOperations.save(balanceModel);
            case DEBIT :
                transactionCrudOperation.save(transaction);
                balanceModel.setId_account(id_account);

                amount = Double.parseDouble(String.valueOf(balanceCrudOperations.findLastBalanceOf(id_account).getValue()));
                amount -= Double.parseDouble(String.valueOf(transaction.getAmount()));

                balanceModel.setValue(BigDecimal.valueOf(amount));

                balanceCrudOperations.save(balanceModel);

                break;
        }
    }
}
