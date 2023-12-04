package Test;

import model.TransactionModel;
import operation.TransactionCrudOperation;

import java.sql.SQLException;
import java.util.List;

public class TransactionCrudOperationTest {
    public static void main(String[] args) {
        try {
            transactionTest();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }

    private static void transactionTest() throws SQLException {
        TransactionCrudOperation transactionCrudOperation = new TransactionCrudOperation();

        List<TransactionModel> result = transactionCrudOperation.findAll();
        System.out.println("Toutes les Transactions :");
        for (TransactionModel t : result) {
            System.out.println(t);
        }

        
    }
}
