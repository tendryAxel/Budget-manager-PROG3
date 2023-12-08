package repository;
import model.AccountModel;
import model.TransactionModel;
import model.TransactionType;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

import java.util.List;


public class TransactionCrudOperation implements CrudOperations<TransactionModel>{
   
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
            throw new RuntimeException();
        }
        return toSave;
    }
}
