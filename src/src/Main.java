import Test.AccountCrudOperationsTest;
import Test.BalanceCrudOperationsTest;
import Test.CurrencyCrudOperationsTest;
import Test.TD2Test;
import Test.TransactionCrudOperationsTest;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        AccountCrudOperationsTest.accountTest();
        CurrencyCrudOperationsTest.currencyTest();
        TransactionCrudOperationsTest.transactionTest();
        BalanceCrudOperationsTest.balanceTest();
        TransactionCrudOperationsTest.transactionTest();
        TD2Test.test();
    }
}