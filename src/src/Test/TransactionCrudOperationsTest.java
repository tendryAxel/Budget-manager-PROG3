package Test;

import model.TransactionModel;
import repository.TransactionCrudOperation;

import java.sql.SQLException;
import java.sql.Timestamp;
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
        TransactionCrudOperation transactionCrudOperations = new TransactionCrudOperation();

        List<TransactionModel> result = transactionCrudOperations.findAll();
        System.out.println("Toutes les Transactions :");
        for (TransactionModel t : result) {
            System.out.println(t);
        }

        TransactionModel transactionModel = new TransactionModel(1, 500, "Purchase", 2, Timestamp.valueOf("2023-12-04").toLocalDateTime());
        transactionCrudOperations.save(transactionModel);

        List<TransactionModel> transactionsToSave = new ArrayList<>();
        transactionsToSave.add(new TransactionModel(2, 1000, "Salary", 3, Timestamp.valueOf("2023-12-05").toLocalDateTime()));

        List<TransactionModel> savedTransactions = transactionCrudOperations.saveAll(transactionsToSave);
        System.out.println("Transactions enregistrées :");
        for (TransactionModel tr : savedTransactions) {
            System.out.println(tr);
        }
    }
}
