package repository;

import model.CurrencyModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CurrencyCrudOperations implements CrudOperations<CurrencyModel>{
    @Override
    public List<CurrencyModel> findAll() throws SQLException {
        String sql = "SELECT * FROM currency";
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
        String sql = "INSERT INTO currency (id , name) VALUES (?, ?)";
        List<CurrencyModel> SaveCurrency = new ArrayList<>();
        try(PreparedStatement preparedStatement = connectionDB.getConnection().prepareStatement(sql)){
            for (CurrencyModel currencyModel : toSave){
                preparedStatement.setInt(1, currencyModel.getId());
                preparedStatement.setString(2, currencyModel.getName());
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    SaveCurrency.add(currencyModel);
                }
            }
        }
        catch (SQLException e){
            throw new RuntimeException();
        }
        return SaveCurrency;
    }

    @Override
    public CurrencyModel save(CurrencyModel toSave)  {
        String sql = "INSERT INTO currency (id , name) VALUES (?, ?)";
        try(PreparedStatement preparedStatement = connectionDB.getConnection().prepareStatement(sql)){
            preparedStatement.setInt(1, toSave.getId());
            preparedStatement.setString(2,toSave.getName());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            throw new RuntimeException();
        }
        return toSave;
    }
}
