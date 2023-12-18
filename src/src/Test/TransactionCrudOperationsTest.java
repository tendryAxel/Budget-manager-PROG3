package Test;

import model.TransactionModel;
import model.TransactionType;
import repository.TransactionCrudOperation;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
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
        System.out.println("Transaction operation : ");
        System.out.println("Toutes les Transactions :");
        TransactionCrudOperation transactionCrudOperation = new TransactionCrudOperation();

        List<TransactionModel> result = transactionCrudOperation.findAll();
        for (TransactionModel t : result) {
            System.out.println(t);
        }


        LocalDateTime transaction_date = LocalDateTime.now();
        TransactionModel transactionModel = new TransactionModel(1, "Cadeau Noel", new BigDecimal("23000.0"), transaction_date , TransactionType.DEBIT , 1, 1, 1);
        transactionCrudOperation.save(transactionModel);

        List<TransactionModel> transactionsToSave = new ArrayList<>();
        transactionsToSave.add(new TransactionModel(2, "Salaire", new BigDecimal("23000.0"), transaction_date ,TransactionType.CREDIT , 2, 1, 1));

        List<TransactionModel> savedTransactions = transactionCrudOperation.saveAll(transactionsToSave);
        System.out.println("Transactions enregistrées :");
        for (TransactionModel tr : savedTransactions) {
            System.out.println(tr);
        }
        System.out.println("---------------");
        System.out.println("");
    }
}
