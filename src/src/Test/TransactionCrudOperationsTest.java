package Test;

import model.TransactionModel;
import model.TransactionType;
import repository.TransactionCrudOperation;

import java.sql.SQLException;
import java.time.LocalDateTime;
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


        LocalDateTime transaction_date = LocalDateTime.now();
        TransactionModel transactionModel = new TransactionModel(1, "Cadeau Noel", 23000.0, transaction_date , TransactionType.DEBIT , 1);
        transactionCrudOperation.save(transactionModel);

        List<TransactionModel> transactionsToSave = new ArrayList<>();
        transactionsToSave.add(new TransactionModel(2, "Salaire", 23000.0 , transaction_date ,TransactionType.CREDIT , 2));

        List<TransactionModel> savedTransactions = transactionCrudOperation.saveAll(transactionsToSave);
        System.out.println("Transactions enregistrées :");
        for (TransactionModel tr : savedTransactions) {
            System.out.println(tr);
        }
    }
}
