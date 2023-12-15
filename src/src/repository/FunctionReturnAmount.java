package repository;

import utils.sum_amount_between_dates_Result;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class FunctionReturnAmount {
    public  static sum_amount_between_dates_Result getSumAmount(int account_id , Timestamp start_date , Timestamp end_date){
        sum_amount_between_dates_Result sumAmount = new sum_amount_between_dates_Result();
        String sql = "SELECT * FROM get_sum_amount(? ,? ,?)";
        try(PreparedStatement preparedStatement = connectionDB.getConnection().prepareStatement(sql)){
            preparedStatement.setObject(1, end_date);
            preparedStatement.setObject(2 , start_date);
            preparedStatement.setObject(3 , account_id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return sumAmount;
    }
}
