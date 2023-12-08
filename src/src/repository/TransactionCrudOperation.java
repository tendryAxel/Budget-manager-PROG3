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
            String sql = "SELECT * FROM \"balance\" WHERE id_account = ? AND datetime <= ? ORDER BY datetime DESC LIMIT 1";
            PreparedStatement preparedStatement = connectionDB.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, accountModel.getId());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(transaction_date.toLocalDateTime()));
            ResultSet resultSet = preparedStatement.executeQuery();
            BalanceModel balanceModel = new BalanceModel();
            if (resultSet.next()) {
                balanceModel.setId_account(resultSet.getInt("id_account"));
                balanceModel.setValue(resultSet.getBigDecimal("value"));
                balanceModel.setDatetime(resultSet.getTimestamp("datetime").toLocalDateTime());
                return balanceModel;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
         return null;
     }



    public List<TransactionModel> findAll() {
        String sql = "SELECT * FROM \"transaction\"";
        List<TransactionModel> allTransactions = new ArrayList<>();
        try (PreparedStatement preparedStatement = connectionDB.getConnection().prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                allTransactions.add(new TransactionModel(
                        resultSet.getInt("id"),
                        resultSet.getString("label"),
                        resultSet.getBigDecimal("amount"),
                        resultSet.getTimestamp("transaction_date").toLocalDateTime(),
                        TransactionType.valueOf(resultSet.getString("type")),
                        resultSet.getInt("id_account")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allTransactions;
    }



    @Override
    public List<TransactionModel> saveAll(List<TransactionModel> toSave) {
        String sql = "INSERT INTO \"transaction\" (label , amount , transaction_date ,type ,id_account) VALUES(?,?,?,?,?)";
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
            String sql = "INSERT INTO \"transaction\" (label , amount , transaction_date ,type ,id_account) VALUES(?,?,?,?,?) ";
            try(PreparedStatement preparedStatement = connectionDB.getConnection().prepareStatement(sql)){
                preparedStatement.setString(1,toSave.getLabel());
                preparedStatement.setBigDecimal(2,toSave.getAmount());
                preparedStatement.setTimestamp(3, Timestamp.valueOf(toSave.getTransaction_date()));
                preparedStatement.setObject(4, toSave.getType() ,Types.OTHER);
                preparedStatement.setInt(5,toSave.getId_account());

                preparedStatement.executeUpdate();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
            return toSave;
        }

        public List<TransactionModel> findAllByIdAccountAndDate(int id, LocalDateTime startDate, LocalDateTime endDate) throws SQLException {
            String sql = "SELECT * FROM \"transaction\" " +
                    "INNER JOIN \"account\" " +
                    "ON \"transaction\".id_account = \"account\".id " +
                    "WHERE \"account\".id = ?" +
                    "AND \"transaction\".transaction_date BETWEEN ? AND ? " +
                    "ORDER BY \"transaction\".transaction_date DESC ";
            PreparedStatement preparedStatement = connectionDB.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.setTimestamp(2, Timestamp.valueOf(startDate));
            preparedStatement.setTimestamp(3, Timestamp.valueOf(endDate));
            ResultSet resultSet = preparedStatement.executeQuery();
            List<TransactionModel> transactions = new ArrayList<>();

            while (resultSet.next()){
                transactions.add(new TransactionModel(
                        resultSet.getInt("\"transaction\".id"),
                        resultSet.getString("label"),
                        resultSet.getBigDecimal("amount"),
                        resultSet.getTimestamp("transaction_date").toLocalDateTime(),
                        TransactionType.valueOf(resultSet.getString("type")),
                        resultSet.getInt("id_account")
                ));
            }

            return transactions;
        }
}
