package repository;

import model.AccountModel;
import model.AccountType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class AccountCrudOperations implements CrudOperations <AccountModel>{
    @Override
    public List<AccountModel> findAll() throws SQLException {
        String sql = "SELECT * FROM \"account\" ";
        List<AccountModel> AllAccount = new ArrayList<>();
        ResultSet resultSet = connectionDB.getConnection().prepareStatement(sql).executeQuery();
        while (resultSet.next()){
            AllAccount.add(new AccountModel(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getTimestamp("updatedDate"),
                    resultSet.getInt("id_currency"),
                    AccountType.valueOf(resultSet.getString("type"))
            ));
        }
        return AllAccount;
    }

    @Override
    public List<AccountModel> saveAll(List<AccountModel> toSave) {
        String sql = "INSERT INTO \"account\" (name , updatedDate , id_currency , type) VALUES (?,?,?,?)";
        List<AccountModel> SaveAccount = new ArrayList<>();
        try(PreparedStatement preparedStatement = connectionDB.getConnection().prepareStatement(sql)){
            for (AccountModel accountModel : toSave){
                preparedStatement.setString(1, accountModel.getName());
                preparedStatement.setTimestamp(2 ,accountModel.getUpdateDate());
                preparedStatement.setInt(3,accountModel.getId_currency());
                preparedStatement.setObject(4 , accountModel.getType() , Types.OTHER);

                int rowAffected = preparedStatement.executeUpdate();
                if (rowAffected > 0){
                    SaveAccount.add(accountModel);
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return SaveAccount;
    }

    @Override
    public AccountModel save(AccountModel toSave)  {
        String sql = "INSERT INTO \"account\" (name , updatedDate , id_currency , type) VALUES (?,?,?,?) ";
        try (PreparedStatement preparedStatement = connectionDB.getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, toSave.getName());
            preparedStatement.setTimestamp(2,toSave.getUpdateDate());
            preparedStatement.setInt(3, toSave.getId_currency());
            preparedStatement.setObject(4 , toSave.getType() ,Types.OTHER);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}