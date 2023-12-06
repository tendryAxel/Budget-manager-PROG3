package repository;

import model.AccountModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

    public class AccountCrudOperations implements CrudOperations <AccountModel>{
        @Override
        public List<AccountModel> findAll() throws SQLException {
            String sql = "SELECT * FROM account ";
            List<AccountModel> AllAccount = new ArrayList<>();
            ResultSet resultSet = connectionDB.getConnection().prepareStatement(sql).executeQuery();
            while (resultSet.next()){
                AllAccount.add(new AccountModel(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("id_currency")
                ));
            }
            return AllAccount;
        }

        @Override
        public List<AccountModel> saveAll(List<AccountModel> toSave) {
            String sql = "INSERT INTO account (name , id_currency) VALUES (?,?)";
            List<AccountModel> SaveAccount = new ArrayList<>();
            try(PreparedStatement preparedStatement = connectionDB.getConnection().prepareStatement(sql)){
                for (AccountModel accountModel : toSave){
                    preparedStatement.setString(1, accountModel.getName());
                    preparedStatement.setInt(2, accountModel.getId_currency());

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
            String sql = "INSERT INTO account (name, id_currency) VALUES (?,?) ";
            try (PreparedStatement preparedStatement = connectionDB.getConnection().prepareStatement(sql)) {
                preparedStatement.setString(1, toSave.getName());
                preparedStatement.setInt(2, toSave.getId_currency());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException();
            }
            return null;
        }

    }