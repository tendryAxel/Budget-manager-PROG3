package repository;

import model.AccountModel;
import model.AccountType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountCrudOperations implements CrudOperations <AccountModel>{
    @Override
    public List<AccountModel> findAll() throws SQLException {
        String sql = String.format(
                "SELECT * FROM \"%s\"",
                AccountModel.TABLE_NAME
        );
        List<AccountModel> AllAccount = new ArrayList<>();
        ResultSet resultSet = connectionDB.getConnection().prepareStatement(sql).executeQuery();
        while (resultSet.next()){
            AllAccount.add(new AccountModel(
                    resultSet.getInt(AccountModel.ID),
                    resultSet.getString(AccountModel.NAME),
                    resultSet.getTimestamp(AccountModel.UPDATEDATE).toLocalDateTime(),
                    resultSet.getInt(AccountModel.ID_CURRENCY),
                    AccountType.valueOf(resultSet.getString(AccountModel.TYPE))
            ));
        }
        return AllAccount;
    }

    @Override
    public List<AccountModel> saveAll(List<AccountModel> toSave) {
        String sql = String.format(
                "INSERT INTO \"%s\" (%s,%s,%s,%s) VALUES (?,?,?,?)",
                AccountModel.TABLE_NAME,
                AccountModel.NAME,
                AccountModel.UPDATEDATE,
                AccountModel.ID_CURRENCY,
                AccountModel.TYPE
        );
        List<AccountModel> SaveAccount = new ArrayList<>();
        try(PreparedStatement preparedStatement = connectionDB.getConnection().prepareStatement(sql)){
            for (AccountModel accountModel : toSave){
                preparedStatement.setString(1, accountModel.getName());
                preparedStatement.setTimestamp(2 , Timestamp.valueOf(accountModel.getUpdateDate()));
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
        String sql = String.format(
                "INSERT INTO \"%s\" (%s,%s,%s,%s) VALUES (?,?,?,?)",
                AccountModel.TABLE_NAME,
                AccountModel.NAME,
                AccountModel.UPDATEDATE,
                AccountModel.ID_CURRENCY,
                AccountModel.TYPE
        );
        try (PreparedStatement preparedStatement = connectionDB.getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, toSave.getName());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(toSave.getUpdateDate()));
            preparedStatement.setInt(3, toSave.getId_currency());
            preparedStatement.setObject(4 , toSave.getType() ,Types.OTHER);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public AccountModel findById(int id) throws SQLException {
        String sql = String.format(
                "SELECT * FROM \"%s\" WHERE %s = ?",
                AccountModel.TABLE_NAME,
                AccountModel.ID
        );
        PreparedStatement preparedStatement = connectionDB.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return new AccountModel(
                resultSet.getInt(AccountModel.ID),
                resultSet.getString(AccountModel.NAME),
                resultSet.getTimestamp(AccountModel.UPDATEDATE).toLocalDateTime(),
                resultSet.getInt(AccountModel.ID_CURRENCY),
                AccountType.valueOf(resultSet.getString(AccountModel.TYPE))
        );
    }
}