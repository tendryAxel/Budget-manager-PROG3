package repository;

import utils.category_amount_between_dates_Result;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public  class FunctionReturnAmountByCategory {
    public static category_amount_between_dates_Result getCategorySum(int account_id , Timestamp start_date , Timestamp end_date){
        category_amount_between_dates_Result categorySumRes = new category_amount_between_dates_Result();
        String sql = "SELECT * FROM get_sum_amount_by_category(? ,? ,?)";
        try(PreparedStatement preparedStatement = connectionDB.getConnection().prepareStatement(sql)){
            preparedStatement.setObject(1, end_date);
            preparedStatement.setObject(2 , start_date);
            preparedStatement.setObject(3 , account_id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categorySumRes;
    }
}
