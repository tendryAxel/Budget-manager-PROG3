package repository;

import utils.sum_amount_between_dates_Result;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class FunctionReturnAmount {
    public  static Map<String, BigDecimal> getSumAmount(int account_id , LocalDateTime start_date , LocalDateTime end_date){
        sum_amount_between_dates_Result sumAmount = new sum_amount_between_dates_Result();
        String sql = "SELECT * FROM get_sum_amount(? ,? ,?)";
        Map<String, BigDecimal> result = new HashMap<>();
        try(PreparedStatement preparedStatement = connectionDB.getConnection().prepareStatement(sql)){
            preparedStatement.setTimestamp(3, Timestamp.valueOf(end_date));
            preparedStatement.setTimestamp(2 , Timestamp.valueOf(start_date));
            preparedStatement.setInt(1 , account_id);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            result.put("total_credit", resultSet.getBigDecimal("total_credit"));
            result.put("total_debit", resultSet.getBigDecimal("total_debit"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
