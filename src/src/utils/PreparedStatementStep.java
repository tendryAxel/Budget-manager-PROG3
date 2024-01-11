package utils;

import model.AccountType;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDateTime;

public class PreparedStatementStep {
    PreparedStatement preparedStatement;
    private int index = 0;

    public PreparedStatementStep(PreparedStatement preparedStatement) {
        this.preparedStatement = preparedStatement;
    }

    public PreparedStatement getPreparedStatement() {
        return preparedStatement;
    }

    public PreparedStatementStep addValue(int value) throws SQLException {
        preparedStatement.setInt(index, value);
        index++;
        return this;
    };
    public PreparedStatementStep addValue(String value) throws SQLException {
        preparedStatement.setString(index, value);
        index++;
        return this;
    };
    public PreparedStatementStep addValue(LocalDateTime value) throws SQLException {
        preparedStatement.setTimestamp(index, Timestamp.valueOf(value));
        index++;
        return this;
    };
    public PreparedStatementStep addValue(AccountType value) throws SQLException {
        preparedStatement.setObject(index, value, Types.OTHER);
        index++;
        return this;
    };
}
