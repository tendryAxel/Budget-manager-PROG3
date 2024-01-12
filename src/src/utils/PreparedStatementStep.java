package utils;

import model.AccountType;

import java.lang.reflect.Type;
import java.sql.*;
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
    public PreparedStatementStep addValues(Object value, ColumnType type) throws SQLException {
        switch (type){
            case INT -> {
                return addValue((int) value);
            }
            case STRING -> {
                return addValue((String) value);
            }
            case LOCAL_DATETIME -> {
                return addValue((LocalDateTime) value);
            }
            case ACCOUNT_TYPE -> {
                return addValue((AccountType) value);
            }
        }
        return null;
    };

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
