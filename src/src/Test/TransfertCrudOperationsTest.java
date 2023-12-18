package Test;

import model.TransferModel;
import repository.TransferCrudOperation;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransfertCrudOperationsTest {
    public static void main(String[] args) {
        try {
            transferTest();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void transferTest() throws SQLException {
        System.out.println("Transfer operation : ");
        TransferCrudOperation transferCrudOperation = new TransferCrudOperation();

        List<TransferModel> allTransfers = transferCrudOperation.findAll();
        System.out.println("Tous les Transferts");
        for (TransferModel tr : allTransfers) {
            System.out.println(tr);
        }


        LocalDateTime dateTime = LocalDateTime.now();
        TransferModel transferModel = new TransferModel(1, 2, 3, dateTime);
        transferCrudOperation.save(transferModel);

        List<TransferModel> transferToSave = new ArrayList<>();
        transferToSave.add(new TransferModel(2, 3, 4, dateTime));

        List<TransferModel> savedTransfers = transferCrudOperation.saveAll(transferToSave);
        System.out.println("Transferts enregistrés:");
        for (TransferModel tm : savedTransfers) {
            System.out.println(tm);
        }
        System.out.println("---------------");
        System.out.println("");
    }
}
