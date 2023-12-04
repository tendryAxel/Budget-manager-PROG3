package Test;

import model.TransactionModel;
import operation.TransactionCrudOperation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionCrudOperationsTest {
    public static void main(String[] args) {
        try {
            transactionTest();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void transactionTest() throws SQLException {
        TransactionCrudOperation transactionCrudOperation = new TransactionCrudOperation();

        List<TransactionModel> result = transactionCrudOperation.findAll();
        System.out.println("Toutes les Transactions :");
        for (TransactionModel t : result) {
            System.out.println(t);
        }

        TransactionModel transactionModel = new TransactionModel(1, 500, "Purchase", 2, java.sql.Date.valueOf("2023-12-04"));
        transactionCrudOperation.save(transactionModel);

        List<TransactionModel> transactionsToSave = new ArrayList<>();
        transactionsToSave.add(new TransactionModel(2, 1000, "Salary", 3, java.sql.Date.valueOf("2023-12-05")));

        List<TransactionModel> savedTransactions = transactionCrudOperation.saveAll(transactionsToSave);
        System.out.println("Transactions enregistrées :");
        for (TransactionModel tr : savedTransactions) {
            System.out.println(tr);
        }
    }
}
