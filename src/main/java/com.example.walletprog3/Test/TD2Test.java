package java.com.example.walletprog3.Test;

import java.com.example.walletprog3.services.AccountService;

import java.sql.SQLException;
import java.time.LocalDateTime;

import static java.com.example.walletprog3.repository.FunctionReturnAmount.getSumAmount;
import static java.com.example.walletprog3.repository.FunctionReturnAmountByCategory.getCategorySum;

public class TD2Test {
    public static void main(String[] args) {
        try {
            test();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void test() throws SQLException {
        System.out.println("TD2 test : ");
        AccountService accountService = new AccountService();

        LocalDateTime localDateTime1 = LocalDateTime.of(2000, 1,1,1,1,1,1);
        LocalDateTime localDatetime2 = LocalDateTime.of(3000, 1, 1, 1, 1, 1, 2);

        System.out.println(localDateTime1);
        System.out.println(localDatetime2);
        System.out.println("Exercise 2-3: use sql function in java");

        System.out.println(getSumAmount(
                1,
                localDateTime1,
                localDatetime2
        ));

        System.out.println("");

        System.out.println(getCategorySum(
                1,
                localDateTime1,
                localDatetime2
        ));

        System.out.println("");
        System.out.println("Exercise 4: use java function like the last sql function");

        System.out.println(accountService.getBalanceBetween(
                1,
                localDateTime1,
                localDatetime2
        ));
        System.out.println(accountService.getTransactionByCategory(
                1,
                localDateTime1,
                localDatetime2
        ));

        System.out.println("");
        System.out.println("---------------");
        System.out.println("");
    }
}
