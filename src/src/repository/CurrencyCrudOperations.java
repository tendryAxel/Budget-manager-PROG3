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
        String sql = "SELECT * FROM \"currency\"";
        List<CurrencyModel> AllCurrency = new ArrayList<>();

        ResultSet resultSet = connectionDB.getConnection().prepareStatement(sql).executeQuery();
        while (resultSet.next()){
            AllCurrency.add(new CurrencyModel(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("code")
            ));
        }
        return AllCurrency;
    }

    public List<CurrencyModel> saveAll(List<CurrencyModel> toSave)  {
        String sql = "INSERT INTO \"currency\" (name , code) VALUES (?,?)";
        List<CurrencyModel> SaveCurrency = new ArrayList<>();
        try(PreparedStatement preparedStatement = connectionDB.getConnection().prepareStatement(sql)){
            for (CurrencyModel currencyModel : toSave){
                preparedStatement.setString(1, currencyModel.getName());
                preparedStatement.setString(2 , currencyModel.getCode());
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
        String sql = "INSERT INTO \"currency\" (name , code) VALUES (?,?)";
        try(PreparedStatement preparedStatement = connectionDB.getConnection().prepareStatement(sql)){
            preparedStatement.setString(1,toSave.getName());
            preparedStatement.setString(2 ,toSave.getCode());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            throw new RuntimeException();
        }
        return toSave;
    }


    public int getAccountCurrency(int id_account) throws SQLException {
        String sql = "SELECT id_currency FROM \"account\" WHERE ?";
        PreparedStatement preparedStatement = connectionDB.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1, id_account);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt("id_currency");
    }
}
