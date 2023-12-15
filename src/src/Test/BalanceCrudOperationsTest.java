package Test;

import model.BalanceModel;
import repository.BalanceCrudOperations;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BalanceCrudOperationsTest {
    public static void main(String[] args) {
        try {
            balanceTest();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void balanceTest() throws SQLException {
        BalanceCrudOperations balanceCrudOperations = new BalanceCrudOperations();
        List<BalanceModel> allBalances = balanceCrudOperations.findAll();
        System.out.println("Tous les soldes :");
        for (BalanceModel b : allBalances) {
            System.out.println(b);
        }

        LocalDateTime dateTime = LocalDateTime.now();
        BalanceModel balanceModelToSave = new BalanceModel(1, dateTime, BigDecimal.valueOf(12000));

        List<BalanceModel> balancesToSave = new ArrayList<>();
        balancesToSave.add(balanceModelToSave);

        List<BalanceModel> savedBalances = balanceCrudOperations.saveAll(balancesToSave);
        System.out.println("Soldes enregistrés :");
        for (BalanceModel bl : savedBalances) {
            System.out.println(bl);
        }
    }
}
