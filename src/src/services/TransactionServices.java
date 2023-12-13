package services;

import model.AccountModel;
import model.BalanceModel;
import model.CurrencyModel;
import model.TransactionModel;
import repository.BalanceCrudOperations;
import repository.CurrencyCrudOperations;
import repository.TransactionCrudOperation;
import repository.connectionDB;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
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
        double amount = 0;
        switch (transaction.getType()){
            case CREDIT :
                this.transactionCrudOperation.save(transaction);
                balanceModel.setId_account(id_account);

                amount = Double.parseDouble(String.valueOf(this.balanceCrudOperations.findLastBalanceOf(id_account).getValue()));
                amount += Double.parseDouble(String.valueOf(transaction.getAmount()));

                balanceModel.setValue(BigDecimal.valueOf(amount));

                this.balanceCrudOperations.save(balanceModel);
            case DEBIT :
                this.transactionCrudOperation.save(transaction);
                balanceModel.setId_account(id_account);

                amount = Double.parseDouble(String.valueOf(this.balanceCrudOperations.findLastBalanceOf(id_account).getValue()));
                amount -= Double.parseDouble(String.valueOf(transaction.getAmount()));

                balanceModel.setValue(BigDecimal.valueOf(amount));

                this.balanceCrudOperations.save(balanceModel);

                break;
        }
        return balanceModel;
    }
}
