package repository;


import model.AccountModel;
import model.AccountType;
import model.BalanceModel;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.*;

public class BalanceCrudOperations implements CrudOperations <BalanceModel>{

    @Override
    public List<BalanceModel> findAll() throws SQLException {
        String sql = "SELECT * FROM \"balance\" ";
        List<BalanceModel> AllBalances = new ArrayList<>();
        ResultSet resultSet = connectionDB.getConnection().prepareStatement(sql).executeQuery();
        while (resultSet.next()){
            AllBalances.add(new BalanceModel(
                    resultSet.getInt("id_account"),
                    resultSet.getTimestamp("datetime").toLocalDateTime(),
                    resultSet.getBigDecimal("value")
            ));
        }
        return AllBalances;
    }

    @Override
    public List<BalanceModel> saveAll(List<BalanceModel> toSave) throws SQLException {
        String sql = "INSERT INTO \"balance\" (id_account , value) VALUES (?,?)";
        List<BalanceModel> SaveBalance = new ArrayList<>();
        try(PreparedStatement preparedStatement = connectionDB.getConnection().prepareStatement(sql)){
            for (BalanceModel balanceModel : toSave){
                preparedStatement.setInt(1, balanceModel.getId_account());
                preparedStatement.setDouble(2, Double.parseDouble(String.valueOf(balanceModel.getValue())));

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
        String sql = "INSERT INTO \"balance\" (id_account , value) VALUES (?,?)";
        try(PreparedStatement preparedStatement = connectionDB.getConnection().prepareStatement(sql)){
            preparedStatement.setInt(1, toSave.getId_account());
            preparedStatement.setDouble(2, Double.parseDouble(String.valueOf(toSave.getValue())));

            preparedStatement.executeUpdate();

        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return toSave;
    }

    public BalanceModel findLastBalanceOf(int id_account) throws SQLException {
        String sql = "SELECT * FROM \"balance\" WHERE id_account = ? ORDER BY datetime DESC LIMIT 1 ";
        PreparedStatement preparedStatement = connectionDB.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1, id_account);
        ResultSet resultSet = preparedStatement.executeQuery();
        return new BalanceModel(
                resultSet.getInt("id_account"),
                resultSet.getTimestamp("datetime").toLocalDateTime(),
                resultSet.getBigDecimal("value")
        );
    }
}