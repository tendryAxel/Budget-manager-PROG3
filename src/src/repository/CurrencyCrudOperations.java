package repository;
import model.CurrencyModel;
import utils.PreparedStatementStep;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
;

public class CurrencyCrudOperations extends CrudOperationsImpl<CurrencyModel> {

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
