import Test.AccountCrudOperationsTest;
import Test.BalanceCrudOperationsTest;
import Test.CurrencyCrudOperationsTest;
import Test.TransactionCrudOperationsTest;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        AccountCrudOperationsTest.accountTest();
        CurrencyCrudOperationsTest.currencyTest();
        TransactionCrudOperationsTest.transactionTest();
        CurrencyCrudOperationsTest.currencyTest();
        BalanceCrudOperationsTest.balanceTest();
        TransactionCrudOperationsTest.transactionTest();
    }
}