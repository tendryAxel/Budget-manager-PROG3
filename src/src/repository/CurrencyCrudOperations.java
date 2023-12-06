package repository;

import model.CurrencyModel;
import utils.utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CurrencyCrudOperations implements CrudOperations<CurrencyModel>{
    @Override
    public List<CurrencyModel> findAll() throws SQLException {
        String sql = "SELECT * FROM \"currency\"";
        List<CurrencyModel> AllCurrency = new ArrayList<>();

        ResultSet resultSet = connectionDB.getConnection().prepareStatement(sql).executeQuery();
        while (resultSet.next()){
            AllCurrency.add(new CurrencyModel(
                    resultSet.getInt("id"),
                    resultSet.getString("name")
            ));
        }
        return AllCurrency;
    }

    public List<CurrencyModel> saveAll(List<CurrencyModel> toSave)  {
        String sql = utils.createPsqlInsertRequest(
                "INSERT INTO \"currency\" (name) VALUES ",
                1,
                toSave.size()
        );
        List<CurrencyModel> SaveCurrency = new ArrayList<>();
        try(PreparedStatement preparedStatement = connectionDB.getConnection().prepareStatement(sql)){
            for (int i = 1; i < toSave.size()+1; i++) {
                preparedStatement.setString(i, toSave.get(i).getName());
                SaveCurrency.add(toSave.get(i));
            }
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            throw new RuntimeException();
        }
        return SaveCurrency;
    }

    @Override
    public CurrencyModel save(CurrencyModel toSave)  {
        String sql = "INSERT INTO \"currency\" (name) VALUES (?)";
        try(PreparedStatement preparedStatement = connectionDB.getConnection().prepareStatement(sql)){
            preparedStatement.setString(1,toSave.getName());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            throw new RuntimeException();
        }
        return toSave;
    }
}
