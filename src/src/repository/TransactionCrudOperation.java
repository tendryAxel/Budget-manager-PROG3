package repository;
import model.*;

import java.math.BigDecimal;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.List;


public class TransactionCrudOperation implements CrudOperations<TransactionModel>{

     public static BalanceModel getBalanceAtDateTime(AccountModel accountModel, Timestamp transaction_date) {
        try {
            String sql = String.format(
                    "SELECT * FROM \"%s\" WHERE %s = ? AND %s <= ? ORDER BY %s DESC LIMIT 1",
                    BalanceModel.TABLE_NAME,
                    BalanceModel.ID_ACCOUNT,
                    BalanceModel.DATETIME,
                    BalanceModel.DATETIME
            );
            PreparedStatement preparedStatement = connectionDB.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, accountModel.getId());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(transaction_date.toLocalDateTime()));
            ResultSet resultSet = preparedStatement.executeQuery();
            BalanceModel balanceModel = new BalanceModel();
            if (resultSet.next()) {
                balanceModel.setId_account(resultSet.getInt(BalanceModel.ID_ACCOUNT));
                balanceModel.setValue(resultSet.getBigDecimal(BalanceModel.VALUE));
                balanceModel.setDatetime(resultSet.getTimestamp(BalanceModel.DATETIME).toLocalDateTime());
                return balanceModel;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
         return null;
     }



    public List<TransactionModel> findAll() {
        String sql = String.format(
                "SELECT * FROM \"%s\"",
                TransactionModel.TABLE_NAME
        );
        List<TransactionModel> allTransactions = new ArrayList<>();
        try (PreparedStatement preparedStatement = connectionDB.getConnection().prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                allTransactions.add(new TransactionModel(
                        resultSet.getInt(TransactionModel.ID),
                        resultSet.getString(TransactionModel.LABEL),
                        resultSet.getBigDecimal(TransactionModel.AMOUNT),
                        resultSet.getTimestamp(TransactionModel.TRANSACTION_DATE).toLocalDateTime(),
                        TransactionType.valueOf(resultSet.getString(TransactionModel.TYPE)),
                        resultSet.getInt(TransactionModel.TYPE),
                        resultSet.getInt(TransactionModel.ID_CURRENCY),
                        resultSet.getInt(TransactionModel.ID_SUBCATEGORY)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allTransactions;
    }



    @Override
    public List<TransactionModel> saveAll(List<TransactionModel> toSave) {
        String sql = String.format(
                "INSERT INTO \"%s\" (%s,%s,%s,%s,%s) VALUES(?,?,?,?,?)",
                TransactionModel.TABLE_NAME,
                TransactionModel.LABEL,
                TransactionModel.AMOUNT,
                TransactionModel.TRANSACTION_DATE,
                TransactionModel.TYPE,
                TransactionModel.ID_ACCOUNT
        );
        List<TransactionModel> SaveTransaction = new ArrayList<>();
        try(PreparedStatement preparedStatement = connectionDB.getConnection().prepareStatement(sql)){
            for (TransactionModel transactionModel : toSave){
                preparedStatement.setString(1, transactionModel.getLabel());
                preparedStatement.setBigDecimal(2,transactionModel.getAmount());
                preparedStatement.setTimestamp(3, Timestamp.valueOf(transactionModel.getTransaction_date()));
                preparedStatement.setObject(4 , transactionModel.getType() , Types.OTHER);
                preparedStatement.setInt(5,transactionModel.getId_account());

            }
        }
        catch (SQLException e){
            throw new RuntimeException();
        }
        return SaveTransaction;
    }

        @Override
        public TransactionModel save(TransactionModel toSave)  {
            String sql = String.format(
                    "INSERT INTO \"%s\" (%s,%s,%s,%s,%s) VALUES(?,?,?,?,?) RETURNING id",
                    TransactionModel.TABLE_NAME,
                    TransactionModel.LABEL,
                    TransactionModel.AMOUNT,
                    TransactionModel.TRANSACTION_DATE,
                    TransactionModel.TYPE,
                    TransactionModel.ID_ACCOUNT
            );
            try(PreparedStatement preparedStatement = connectionDB.getConnection().prepareStatement(sql)){
                preparedStatement.setString(1,toSave.getLabel());
                preparedStatement.setBigDecimal(2,toSave.getAmount());
                preparedStatement.setTimestamp(3, Timestamp.valueOf(toSave.getTransaction_date()));
                preparedStatement.setObject(4, toSave.getType() ,Types.OTHER);
                preparedStatement.setInt(5,toSave.getId_account());

                ResultSet resultSet = preparedStatement.executeQuery();
                resultSet.next();
                toSave.setId(resultSet.getInt(TransactionModel.ID));
            }
            catch (SQLException e){
                e.printStackTrace();
            }
            return toSave;
        }

        public List<BalanceModel> findAllByIdAccountAndDate(int id, LocalDateTime startDate, LocalDateTime endDate) throws SQLException {
            String sql = String.format(
                    "SELECT * FROM \"%s\" " +
                    "INNER JOIN \"%s\" " +
                    "ON \"%s\".%s = \"%s\".%s " +
                    "WHERE \"%s\".%s = ?" +
                    "AND \"%s\".%s BETWEEN ? AND ? " +
                    "ORDER BY \"%s\".%s DESC ",
                    BalanceModel.TABLE_NAME,
                    AccountModel.TABLE_NAME,
                    BalanceModel.TABLE_NAME,
                    BalanceModel.ID_ACCOUNT,
                    AccountModel.TABLE_NAME,
                    AccountModel.ID,
                    AccountModel.TABLE_NAME,
                    AccountModel.ID,
                    BalanceModel.TABLE_NAME,
                    BalanceModel.DATETIME,
                    BalanceModel.TABLE_NAME,
                    BalanceModel.DATETIME
            );
            PreparedStatement preparedStatement = connectionDB.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.setTimestamp(2, Timestamp.valueOf(startDate));
            preparedStatement.setTimestamp(3, Timestamp.valueOf(endDate));
            ResultSet resultSet = preparedStatement.executeQuery();
            List<BalanceModel> balances = new ArrayList<>();

            while (resultSet.next()){
                BalanceModel balanceModel = new BalanceModel();
                balanceModel.setId_account(resultSet.getInt(BalanceModel.ID_ACCOUNT));
                balanceModel.setDatetime(resultSet.getTimestamp(BalanceModel.DATETIME).toLocalDateTime());
                balanceModel.setValue(resultSet.getBigDecimal(BalanceModel.VALUE));
                balances.add(balanceModel);
            }

            return balances;
        }

    public BigDecimal getActualBalance(int id_account) throws SQLException {
         String table_column = "total_amount";
        String sql = String.format(
                "SELECT sum(\"%s\".%s * \"%s\".%s) as %s FROM \"%s\" " +
                "INNER JOIN \"%s\" " +
                "ON \"%s\".\"%s\" = \"%s\".\"%s\" " +
                "INNER JOIN \"%s\" " +
                "ON \"%s\".\"%s\" = \"%s\".\"%s\" " +
                "AND date(\"%s\".\"%s\") BETWEEN date(\"%s\".\"%s\") AND date(\"%s\".\"%s\")+1 " +
                "WHERE \"%s\".%s = ?",
                TransactionModel.TABLE_NAME,
                TransactionModel.AMOUNT,
                CurrencyValueModel.TABLE_NAME,
                CurrencyValueModel.AMOUNT,

                table_column,

                TransactionModel.TABLE_NAME,
                CurrencyModel.TABLE_NAME,

                TransactionModel.TABLE_NAME,
                TransactionModel.ID_CURRENCY,
                CurrencyModel.TABLE_NAME,
                CurrencyModel.ID,

                CurrencyModel.TABLE_NAME,

                CurrencyModel.TABLE_NAME,
                CurrencyModel.ID,
                CurrencyValueModel.TABLE_NAME,
                CurrencyValueModel.ID_CURRENCY_SOURCE,

                TransactionModel.TABLE_NAME,
                TransactionModel.TRANSACTION_DATE,
                CurrencyValueModel.TABLE_NAME,
                CurrencyValueModel.DATE_EFFET,
                CurrencyValueModel.TABLE_NAME,
                CurrencyValueModel.DATE_EFFET,
                TransactionModel.TABLE_NAME,
                TransactionModel.ID_ACCOUNT
        );
        PreparedStatement preparedStatement = connectionDB.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1, id_account);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet.getBigDecimal(table_column);
    }

    public TransactionType getTransactionType(int id_transaction) throws SQLException {
         String sql = String.format(
                 "SELECT %s as def, %s as sec FROM \"%s\" " +
                 "INNER JOIN \"%s\" ON %s = %s " +
                 "WHERE %s = ?",
                 "subcategory.type",
                 TransactionModel.TABLE_NAME+"."+TransactionModel.TYPE,
                 TransactionModel.TABLE_NAME,
                 "subcategory",
                 TransactionModel.TABLE_NAME+"."+TransactionModel.ID_SUBCATEGORY,
                 "subcategory.id_subcategory",
                 TransactionModel.TABLE_NAME+"."+TransactionModel.ID
         );
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connectionDB.getConnection().prepareStatement(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        preparedStatement.setInt(1, id_transaction);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        TransactionType result = TransactionType.valueOf(resultSet.getString("def"));
        if (result.equals("")){
            result = TransactionType.valueOf(resultSet.getString("sec"));
        }
        return result;
    }

    public List<TransactionModel> findAllByIdAccount(int id_account) {
        String sql = String.format(
                "SELECT * FROM \"%s\" WHERE %s = ?",
                TransactionModel.TABLE_NAME,
                TransactionModel.ID_ACCOUNT
        );
        List<TransactionModel> allTransactions = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connectionDB.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, id_account);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                allTransactions.add(new TransactionModel(
                        resultSet.getInt(TransactionModel.ID),
                        resultSet.getString(TransactionModel.LABEL),
                        resultSet.getBigDecimal(TransactionModel.AMOUNT),
                        resultSet.getTimestamp(TransactionModel.TRANSACTION_DATE).toLocalDateTime(),
                        TransactionType.valueOf(resultSet.getString(TransactionModel.TYPE)),
                        resultSet.getInt(TransactionModel.TYPE),
                        resultSet.getInt(TransactionModel.ID_CURRENCY),
                        resultSet.getInt(TransactionModel.ID_SUBCATEGORY)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allTransactions;
    }

    public List<TransactionModel> findAllByIdAccountAndSubCategory(int id_account, int id_subcategory) {
        String sql = String.format(
                "SELECT * FROM \"%s\" WHERE %s = ? AND %s = ?",
                TransactionModel.TABLE_NAME,
                TransactionModel.ID_ACCOUNT,
                TransactionModel.ID_SUBCATEGORY
        );
        List<TransactionModel> allTransactions = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connectionDB.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, id_account);
            preparedStatement.setInt(2, id_subcategory);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                allTransactions.add(new TransactionModel(
                        resultSet.getInt(TransactionModel.ID),
                        resultSet.getString(TransactionModel.LABEL),
                        resultSet.getBigDecimal(TransactionModel.AMOUNT),
                        resultSet.getTimestamp(TransactionModel.TRANSACTION_DATE).toLocalDateTime(),
                        TransactionType.valueOf(resultSet.getString(TransactionModel.TYPE)),
                        resultSet.getInt(TransactionModel.TYPE),
                        resultSet.getInt(TransactionModel.ID_CURRENCY),
                        resultSet.getInt(TransactionModel.ID_SUBCATEGORY)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allTransactions;
    }
}
