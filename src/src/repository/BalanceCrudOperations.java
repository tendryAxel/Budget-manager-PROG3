package repository;

import model.BalanceModel;
import utils.PreparedStatementStep;

import java.lang.reflect.InvocationTargetException;

import java.sql.*;

public class BalanceCrudOperations extends CrudOperationsImpl<BalanceModel> {
    @Override
    public BalanceModel createT(ResultSet resultSet) throws SQLException {
     return new BalanceModel(
             resultSet.getInt(BalanceModel.ID_ACCOUNT),
             resultSet.getTimestamp(BalanceModel.DATETIME).toLocalDateTime(),
             resultSet.getBigDecimal(BalanceModel.VALUE)
     );
    }

    @Override
    public PreparedStatement createT(PreparedStatementStep pr, BalanceModel model) throws SQLException, InvocationTargetException, IllegalAccessException {
        PreparedStatement preparedStatement = pr.getPreparedStatement();
        preparedStatement.setInt(1, model.getId_account());
        preparedStatement.setTimestamp(2, Timestamp.valueOf(model.getDatetime()));
        preparedStatement.setBigDecimal(3,model.getValue());
        return preparedStatement;
    }

    public BalanceModel findLastBalanceOf(int id_account) throws SQLException {
        String sql = String.format(
                "SELECT * FROM \"%s\" WHERE %s = ? ORDER BY %s DESC LIMIT 1 ",
                BalanceModel.TABLE_NAME,
                BalanceModel.ID_ACCOUNT,
                BalanceModel.DATETIME,
                BalanceModel.VALUE
        );
        PreparedStatement preparedStatement = connectionDB.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1, id_account);
        ResultSet resultSet = preparedStatement.executeQuery();
        return new BalanceModel(
                resultSet.getInt(BalanceModel.ID_ACCOUNT),
                resultSet.getTimestamp(BalanceModel.DATETIME).toLocalDateTime(),
                resultSet.getBigDecimal(BalanceModel.VALUE)
        );
    }

    @Override
    public BalanceModel findById(Integer id) {
        return super.findById(id);
    }

    @Override
    public BalanceModel delete(Integer id){
        return super.delete(id);
    }
}