package repository;
import model.CurrencyValueModel;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CurrencyValueCrudOperations implements CrudOperations<CurrencyValueModel>{
    @Override
    public List<CurrencyValueModel> findAll() throws SQLException {
        String sql = String.format(
                "SELECT * FROM \"%s\"",
                CurrencyValueModel.TABLE_NAME
        );
        List<CurrencyValueModel> AllCurrencyValue = new ArrayList<>();
        ResultSet resultSet = connectionDB.getConnection().prepareStatement(sql).executeQuery();
        while (resultSet.next()){
            AllCurrencyValue.add(new CurrencyValueModel(
                   resultSet.getInt(CurrencyValueModel.ID),
                    resultSet.getInt(CurrencyValueModel.ID_CURRENCY_SOURCE),
                    resultSet.getInt(CurrencyValueModel.ID_CURRENCY_DESTINATION),
                    resultSet.getBigDecimal(CurrencyValueModel.AMOUNT),
                    resultSet.getDate(CurrencyValueModel.DATE_EFFET)
            ));
        }
        return AllCurrencyValue;
    }

    @Override
    public List<CurrencyValueModel> saveAll(List<CurrencyValueModel> toSave) throws SQLException {
        String sql = String.format(
                "INSERT INTO \"%s\" (%s,%s,%s,%s) VALUES (?,?,?,?,?)",
                CurrencyValueModel.TABLE_NAME,
                CurrencyValueModel.ID,
                CurrencyValueModel.ID_CURRENCY_SOURCE,
                CurrencyValueModel.ID_CURRENCY_DESTINATION,
                CurrencyValueModel.AMOUNT,
                CurrencyValueModel.DATE_EFFET
        );
        System.out.println(sql);
        List<CurrencyValueModel> SaveCurrencyValue = new ArrayList<>();
        try (PreparedStatement preparedStatement = connectionDB.getConnection().prepareStatement(sql)){
            for (CurrencyValueModel currencyValueModel : toSave){
                preparedStatement.setInt(1,currencyValueModel.getId());
                preparedStatement.setInt(2, currencyValueModel.getId_currency_source());
                preparedStatement.setInt(3, currencyValueModel.getId_currency_destination());
                preparedStatement.setBigDecimal(4, currencyValueModel.getAmount());
                preparedStatement.setDate(5, currencyValueModel.getDate_effet());
                int rowAffected = preparedStatement.executeUpdate();
                if (rowAffected > 0){
                    SaveCurrencyValue.add(currencyValueModel);
                }
            }
        }
        return SaveCurrencyValue;
    }

    @Override
    public CurrencyValueModel save(CurrencyValueModel toSave) throws SQLException {
        String sql = String.format(
                "INSERT INTO \"%s\" (%s,%s,%s,%s) VALUES (?,?,?,?,?)",
                CurrencyValueModel.TABLE_NAME,
                CurrencyValueModel.ID,
                CurrencyValueModel.ID_CURRENCY_SOURCE,
                CurrencyValueModel.ID_CURRENCY_DESTINATION,
                CurrencyValueModel.AMOUNT,
                CurrencyValueModel.DATE_EFFET
        );

        try (PreparedStatement preparedStatement = connectionDB.getConnection().prepareStatement(sql)){
            preparedStatement.setInt(1,toSave.getId());
            preparedStatement.setInt(2, toSave.getId_currency_source());
            preparedStatement.setInt(3, toSave.getId_currency_destination());
            preparedStatement.setBigDecimal(4 , toSave.getAmount());
            preparedStatement.setDate(5, toSave.getDate_effet());
        }

        return toSave;
    }

    public List<CurrencyValueModel> findByDate(LocalDate date) throws SQLException {
        String sql = String.format(
                "SELECT * FROM \"%s\" WHERE %s BETWEEN ? AND date(?) + interval '1 day'",
                CurrencyValueModel.TABLE_NAME,
                CurrencyValueModel.DATE_EFFET
        );
        List<CurrencyValueModel> AllCurrencyValue = new ArrayList<>();
        PreparedStatement preparedStatement = connectionDB.getConnection().prepareStatement(sql);
        preparedStatement.setDate(1, Date.valueOf(date));
        preparedStatement.setDate(2, Date.valueOf(date));
        System.out.println(preparedStatement);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            AllCurrencyValue.add(new CurrencyValueModel(
                    resultSet.getInt(CurrencyValueModel.ID),
                    resultSet.getInt(CurrencyValueModel.ID_CURRENCY_SOURCE),
                    resultSet.getInt(CurrencyValueModel.ID_CURRENCY_DESTINATION),
                    resultSet.getBigDecimal(CurrencyValueModel.AMOUNT),
                    resultSet.getDate(CurrencyValueModel.DATE_EFFET)
            ));
        }
        return AllCurrencyValue;
    }

    public List<CurrencyValueModel> findByDate(LocalDateTime date) throws SQLException{
        return findByDate(LocalDate.of(date.getYear(), date.getMonth(), date.getDayOfMonth()));
    }
}
