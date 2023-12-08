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
                    resultSet.getBigDecimal("balance"),
                    resultSet.getTimestamp("updateDate "),
                    resultSet.getInt("id_currency"),
                    AccountType.valueOf(resultSet.getString("type"))
            ));
        }
        return AllAccount;
    }

    @Override
    public List<AccountModel> saveAll(List<AccountModel> toSave) {
        String sql = "INSERT INTO \"account\" (name ,balance , updadeDate , id_currency , type) VALUES (?,?,?,?)";
        List<AccountModel> SaveAccount = new ArrayList<>();
        try(PreparedStatement preparedStatement = connectionDB.getConnection().prepareStatement(sql)){
            for (AccountModel accountModel : toSave){
                preparedStatement.setString(1, accountModel.getName());
                preparedStatement.setBigDecimal(2,accountModel.getBalance());
                preparedStatement.setTimestamp(3,accountModel.getUpdateDate());
                preparedStatement.setInt(4,accountModel.getId_currency());
                preparedStatement.setObject(5 , accountModel.getType() , Types.OTHER);

                int rowAffected = preparedStatement.executeUpdate();
                if (rowAffected > 0){
                    SaveAccount.add(accountModel);
                }
            }
        }
        catch (SQLException e){
            throw new RuntimeException();
        }
        return SaveAccount;
    }

    @Override
    public AccountModel save(AccountModel toSave)  {
        String sql = "INSERT INTO \"account\" (name ,balance , updadeDate , id_currency , type) VALUES (?,?,?,?) ";
        try (PreparedStatement preparedStatement = connectionDB.getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, toSave.getName());
            preparedStatement.setBigDecimal(2,toSave.getBalance());
            preparedStatement.setTimestamp(3,toSave.getUpdateDate());
            preparedStatement.setInt(4, toSave.getId_currency());
            preparedStatement.setObject(5 , toSave.getType() ,Types.OTHER);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return null;
    }

}