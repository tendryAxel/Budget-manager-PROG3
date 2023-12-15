package Test;

import services.AccountService;

import java.sql.SQLException;
import java.sql.Timestamp;

import static repository.FunctionReturnAmount.getSumAmount;
import static repository.FunctionReturnAmountByCategory.getCategorySum;

public class TD2Test {
    public static void main(String[] args) {
        try {
            test();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void test() throws SQLException {
        AccountService accountService = new AccountService();

        Timestamp timestamp1 = new Timestamp(2000, 1, 1, 1, 1, 1, 1);
        Timestamp timestamp2 = new Timestamp(2055, 1, 1, 1, 1, 1, 2);

        System.out.println("Exercise 2-3: use sql function in java");

        System.out.println(getSumAmount(
                1,
                timestamp1,
                timestamp2
        ));

        System.out.println("");

        System.out.println(getCategorySum(
                1,
                timestamp1,
                timestamp2
        ));

        System.out.println("");
        System.out.println("Exercise 4: use java function like the last sql function");

        System.out.println(accountService.getActualBalanceBetween(
                1,
                timestamp1.toLocalDateTime(),
                timestamp2.toLocalDateTime()
        ));
        System.out.println(accountService.getTransactionByCategory(
                1,
                timestamp1.toLocalDateTime(),
                timestamp2.toLocalDateTime()
        ));

        System.out.println("");
    }
}
