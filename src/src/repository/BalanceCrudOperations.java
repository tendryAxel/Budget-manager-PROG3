package repository;


import model.AccountModel;
import model.AccountType;
import model.BalanceModel;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.*;

public class BalanceCrudOperations extends CrudOperationsImpl<BalanceModel> {

    @Override
    public List<BalanceModel> findAll() throws SQLException {
        String sql = String.format(
                "SELECT * FROM \"%s\"",
                BalanceModel.TABLE_NAME
        );
        List<BalanceModel> AllBalances = new ArrayList<>();
        ResultSet resultSet = connectionDB.getConnection().prepareStatement(sql).executeQuery();
        while (resultSet.next()){
            AllBalances.add(new BalanceModel(
                    resultSet.getInt(BalanceModel.ID_ACCOUNT),
                    resultSet.getTimestamp(BalanceModel.DATETIME).toLocalDateTime(),
                    resultSet.getBigDecimal(BalanceModel.VALUE)
            ));
        }
        return AllBalances;
    }

    @Override
    public List<BalanceModel> saveAll(List<BalanceModel> toSave) throws SQLException {
        String sql = String.format(
                "INSERT INTO \"%s\" (%s,%s,%s) VALUES (?,?,?)",
                BalanceModel.TABLE_NAME,
                BalanceModel.ID_ACCOUNT,
                BalanceModel.DATETIME,
                BalanceModel.VALUE
        );
        List<BalanceModel> SaveBalance = new ArrayList<>();
        try(PreparedStatement preparedStatement = connectionDB.getConnection().prepareStatement(sql)){
            for (BalanceModel balanceModel : toSave){
                preparedStatement.setInt(1, balanceModel.getId_account());
                preparedStatement.setTimestamp(2, Timestamp.valueOf(balanceModel.getDatetime()));
                preparedStatement.setBigDecimal(3,balanceModel.getValue());
                int rowAffected = preparedStatement.executeUpdate();
                if (rowAffected > 0){
                    SaveBalance.add(balanceModel);
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return SaveBalance;
    }

    @Override
    public BalanceModel save(BalanceModel toSave) throws SQLException {
        String sql = String.format(
                "INSERT INTO \"%s\" (%s,%s) VALUES (?,?)",
                BalanceModel.TABLE_NAME,
                BalanceModel.ID_ACCOUNT,
                BalanceModel.DATETIME,
                BalanceModel.VALUE
        );
        try(PreparedStatement preparedStatement = connectionDB.getConnection().prepareStatement(sql)){
            preparedStatement.setInt(1, toSave.getId_account());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(toSave.getDatetime()));
            preparedStatement.setBigDecimal(3,toSave.getValue());
            preparedStatement.executeUpdate();

        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return toSave;
    }

    public BalanceModel findLastBalanceOf(int id_account) throws SQLException {
        String sql = String.format(
                "SELECT * FROM \"%s\" WHERE %s = ? ORDER BY %s DESC LIMIT 1 ",
                BalanceModel.TABLE_NAME,
                BalanceModel.ID_ACCOUNT,
                BalanceModel.DATETIME,
                BalanceModel.VALUE
        );
        PreparedStatement preparedStatement = connectionDB.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1, id_account);
        ResultSet resultSet = preparedStatement.executeQuery();
        return new BalanceModel(
                resultSet.getInt(BalanceModel.ID_ACCOUNT),
                resultSet.getTimestamp(BalanceModel.DATETIME).toLocalDateTime(),
                resultSet.getBigDecimal(BalanceModel.VALUE)
        );
    }
}