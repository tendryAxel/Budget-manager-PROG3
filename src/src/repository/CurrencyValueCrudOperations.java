package repository;
import model.CurrencyValueModel;

import java.math.BigDecimal;
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
                    resultSet.getDate(CurrencyValueModel.DATE_EFFET).toLocalDate()
            ));
        }
        return AllCurrencyValue;
    }

    @Override
    public List<CurrencyValueModel> saveAll(List<CurrencyValueModel> toSave) throws SQLException {
        String sql = String.format(
                "INSERT INTO \"%s\" (%s,%s,%s,%s) VALUES (?,?,?,?)",
                CurrencyValueModel.TABLE_NAME,
                CurrencyValueModel.ID_CURRENCY_SOURCE,
                CurrencyValueModel.ID_CURRENCY_DESTINATION,
                CurrencyValueModel.AMOUNT,
                CurrencyValueModel.DATE_EFFET
        );
        System.out.println(sql);
        List<CurrencyValueModel> SaveCurrencyValue = new ArrayList<>();
        try (PreparedStatement preparedStatement = connectionDB.getConnection().prepareStatement(sql)){
            for (CurrencyValueModel currencyValueModel : toSave){
                preparedStatement.setInt(1, currencyValueModel.getId_currency_source());
                preparedStatement.setInt(2, currencyValueModel.getId_currency_destination());
                preparedStatement.setBigDecimal(3, currencyValueModel.getAmount());
                preparedStatement.setDate(4, Date.valueOf(currencyValueModel.getDate_effet()));
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
                "INSERT INTO \"%s\" (%s,%s,%s,%s) VALUES (?,?,?,?,?) RETURNING id",
                CurrencyValueModel.TABLE_NAME,
                CurrencyValueModel.ID,
                CurrencyValueModel.ID_CURRENCY_SOURCE,
                CurrencyValueModel.ID_CURRENCY_DESTINATION,
                CurrencyValueModel.AMOUNT,
                CurrencyValueModel.DATE_EFFET
        );

        try {
            PreparedStatement preparedStatement = connectionDB.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1,toSave.getId());
            preparedStatement.setInt(2, toSave.getId_currency_source());
            preparedStatement.setInt(3, toSave.getId_currency_destination());
            preparedStatement.setBigDecimal(4 , toSave.getAmount());
            preparedStatement.setDate(5, Date.valueOf(toSave.getDate_effet()));

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            toSave.setId(resultSet.getInt("id"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return toSave;
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
