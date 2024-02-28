package com.example.walletprog3.repository;

import com.example.walletprog3.model.AccountModel;
import com.example.walletprog3.model.AccountType;
import com.example.walletprog3.utils.PreparedStatementStep;
import org.springframework.stereotype.Repository;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;


@Repository
public class AccountCrudOperations extends CrudOperationsImpl <AccountModel>{

    public AccountCrudOperations(com.example.walletprog3.repository.connectionDB connectionDB) {
        super(connectionDB);
    }

    @Override
    public AccountModel createT(ResultSet resultSet) throws SQLException {
        return new AccountModel(
                resultSet.getInt(AccountModel.ID),
                resultSet.getString(AccountModel.NAME),
                resultSet.getTimestamp(AccountModel.UPDATEDATE).toLocalDateTime(),
                resultSet.getInt(AccountModel.ID_CURRENCY),
                AccountType.valueOf(resultSet.getString(AccountModel.TYPE))
        );
    }

    @Override
    public PreparedStatement createT(PreparedStatementStep pr, AccountModel model) throws SQLException, InvocationTargetException, IllegalAccessException {
        PreparedStatement preparedStatement = pr.getPreparedStatement();
        preparedStatement.setString(1, model.getName());
        preparedStatement.setTimestamp(2 , Timestamp.valueOf(model.getUpdateDate()));
        preparedStatement.setInt(3,model.getId_currency());
        preparedStatement.setObject(4 , model.getType() , Types.OTHER);
        return preparedStatement;
    }

    @Override
    public Class<AccountModel> getClassT() {
        return AccountModel.class;
    }

    @Override
    public AccountModel findById(Integer id) {
        return super.findById(id);
    }

    @Override
    public AccountModel delete(Integer id){
        return super.delete(id);
    }
}