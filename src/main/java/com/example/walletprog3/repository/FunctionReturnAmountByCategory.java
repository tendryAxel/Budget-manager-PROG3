package com.example.walletprog3.repository;

import com.example.walletprog3.utils.category_amount_between_dates_Result;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public  class FunctionReturnAmountByCategory {
    public static Map<String, BigDecimal> getCategorySum(int account_id , LocalDateTime start_date , LocalDateTime end_date){
        category_amount_between_dates_Result categorySumRes = new category_amount_between_dates_Result();
        String sql = "SELECT * FROM get_sum_amount_by_category(? ,? ,?)";
        Map<String, BigDecimal> result = new HashMap<>();
        try(PreparedStatement preparedStatement = connectionDB.getConnection().prepareStatement(sql)){
            preparedStatement.setTimestamp(3, Timestamp.valueOf(end_date));
            preparedStatement.setTimestamp(2 , Timestamp.valueOf(start_date));
            preparedStatement.setInt(1 , account_id);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            result.put("restaurant", resultSet.getBigDecimal("restaurant"));
            result.put("salary", resultSet.getBigDecimal("salary"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
