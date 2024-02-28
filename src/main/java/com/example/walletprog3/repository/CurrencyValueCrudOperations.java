package com.example.walletprog3.repository;

import com.example.walletprog3.model.CurrencyValueModel;
import com.example.walletprog3.utils.PreparedStatementStep;
import org.springframework.stereotype.Repository;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CurrencyValueCrudOperations extends CrudOperationsImpl<CurrencyValueModel> {
    public CurrencyValueCrudOperations(com.example.walletprog3.repository.connectionDB connectionDB) {
        super(connectionDB);
    }

    @Override
    public CurrencyValueModel createT(ResultSet resultSet) throws SQLException {
        return new CurrencyValueModel(
                resultSet.getInt(CurrencyValueModel.ID),
                resultSet.getInt(CurrencyValueModel.ID_CURRENCY_SOURCE),
                resultSet.getInt(CurrencyValueModel.ID_CURRENCY_DESTINATION),
                resultSet.getBigDecimal(CurrencyValueModel.AMOUNT),
                resultSet.getDate(CurrencyValueModel.DATE_EFFET).toLocalDate()
        );
    }

    @Override
    public PreparedStatement createT(PreparedStatementStep pr, CurrencyValueModel model) throws SQLException, InvocationTargetException, IllegalAccessException {
        PreparedStatement preparedStatement = pr.getPreparedStatement();
        preparedStatement.setInt(1,model.getId());
        preparedStatement.setInt(2, model.getId_currency_source());
        preparedStatement.setInt(3, model.getId_currency_destination());
        preparedStatement.setBigDecimal(4 , model.getAmount());
        preparedStatement.setDate(5, Date.valueOf(model.getDate_effet()));
        return preparedStatement;
    }

    @Override
    public Class<CurrencyValueModel> getClassT() {
        return CurrencyValueModel.class;
    }

    @Override
    public CurrencyValueModel findById(Integer id) {
        return super.findById(id);
    }

    @Override
    public CurrencyValueModel delete(Integer id) {
        return super.delete(id);
    }

    public List<CurrencyValueModel> findByDateAndCurrency(LocalDate date, int id_source, int id_destination) throws SQLException {
        String sql = String.format(
                "SELECT * FROM \"%s\"" +
                        " WHERE %s BETWEEN ? AND date(?) + interval '1 day' " +
                        "AND %s = ? AND %s = ?",
                CurrencyValueModel.TABLE_NAME,
                CurrencyValueModel.DATE_EFFET,
                CurrencyValueModel.ID_CURRENCY_SOURCE,
                CurrencyValueModel.ID_CURRENCY_DESTINATION
        );
        List<CurrencyValueModel> allCurrencyValue = new ArrayList<>();
        PreparedStatement preparedStatement = connectionDB.getConnection().prepareStatement(sql);
        preparedStatement.setDate(1, Date.valueOf(date));
        preparedStatement.setDate(2, Date.valueOf(date));
        preparedStatement.setInt(3, id_source);
        preparedStatement.setInt(4, id_destination);
        System.out.println(preparedStatement);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            allCurrencyValue.add(new CurrencyValueModel(
                    resultSet.getInt(CurrencyValueModel.ID),
                    resultSet.getInt(CurrencyValueModel.ID_CURRENCY_SOURCE),
                    resultSet.getInt(CurrencyValueModel.ID_CURRENCY_DESTINATION),
                    resultSet.getBigDecimal(CurrencyValueModel.AMOUNT),
                    resultSet.getDate(CurrencyValueModel.DATE_EFFET).toLocalDate()
            ));
        }

        if (allCurrencyValue.size() == 0){
            throw new Error(String.format(
                    "They are not value of currency at %s between currency with index %s to %s",
                    date,
                    id_source,
                    id_destination
            ));
        }

        return allCurrencyValue;
    }

    public List<CurrencyValueModel> findByDateAndCurrency(LocalDateTime date, int id_source, int id_destination) throws SQLException{
        return findByDateAndCurrency(
                LocalDate.of(date.getYear(), date.getMonth(), date.getDayOfMonth()),
                id_source,
                id_destination
        );
    }
}
