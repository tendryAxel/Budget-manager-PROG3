package com.example.walletprog3.repository;

import com.example.walletprog3.model.CurrencyModel;
import com.example.walletprog3.utils.PreparedStatementStep;
import org.springframework.stereotype.Repository;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;

@Repository
public class CurrencyCrudOperations extends CrudOperationsImpl<CurrencyModel> {

    public CurrencyCrudOperations(com.example.walletprog3.repository.connectionDB connectionDB) {
        super(connectionDB);
    }

    @Override
    public CurrencyModel createT(ResultSet resultSet) throws SQLException {
        return  new CurrencyModel(
                resultSet.getInt(CurrencyModel.ID),
                resultSet.getString(CurrencyModel.NAME),
                resultSet.getString(CurrencyModel.CODE)
        );
    }

    @Override
    public PreparedStatement createT(PreparedStatementStep pr, CurrencyModel model) throws SQLException, InvocationTargetException, IllegalAccessException {
        PreparedStatement preparedStatement = pr.getPreparedStatement();
        preparedStatement.setString(1, model.getName());
        preparedStatement.setString(2 , model.getCode());
        return preparedStatement;
    }

    @Override
    public Class<CurrencyModel> getClassT() {
        return CurrencyModel.class;
    }

    public int getAccountCurrency(int id_account) throws SQLException {
        String sql = String.format(
                "SELECT %s FROM \"%s\" WHERE ?",
                CurrencyModel.ID,
                CurrencyModel.TABLE_NAME
        );
        PreparedStatement preparedStatement = connectionDB.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1, id_account);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt("id_currency");
    }


    @Override
    public CurrencyModel findById(Integer id) {
        return super.findById(id);
    }

    @Override
    public CurrencyModel delete(Integer id){
        return super.delete(id);
    }
}