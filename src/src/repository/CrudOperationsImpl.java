package repository;

import model.DefaultModel;
import utils.PreparedStatementStep;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CrudOperationsImpl<T extends DefaultModel> implements CrudOperations<T> {

    public T createT(ResultSet resultSet){
        return null;
    };
    public PreparedStatement createT(PreparedStatementStep pr){
        /**
         * To add another column value :
         *      pr.addValue()
         * For every column
         */
        return pr.getPreparedStatement();
    };
    public String createInsertQuery(List<String> columns){
        return createInsertQuery(columns.size());
    }
    public String createInsertQuery(int columnsCount){
        String result = String.format(
                "INSERT INTO \"%s\" (",
                T.TABLE_NAME
        );
        for (int i = 0; i<columnsCount; i++){
            result += "%s,";
        }
        result = result.substring(0, result.length()-1);
        result += ") VALUES (";
        for (int i = 0; i<columnsCount; i++){
            result += "?,";
        }
        result = result.substring(0, result.length()-1);
        result += ")";
        return result;
    }

    @Override
    public List<T> findAll() throws SQLException {
        String sql = String.format(
                "SELECT * FROM \"%s\"",
                T.TABLE_NAME
        );
        List<T> AllData = new ArrayList<>();
        ResultSet resultSet = connectionDB.getConnection().prepareStatement(sql).executeQuery();
        while (resultSet.next()){
            AllData.add(createT(resultSet));
        }
        return AllData;
    }

    @Override
    public List<T> saveAll(List<T> toSave) throws SQLException {
        for (T element : toSave){
            save(element);
        }
        return toSave;
    }

    @Override
    public T save(T toSave) throws SQLException {
        String sql = createInsertQuery(new ArrayList<>());
        try {
            PreparedStatementStep pr = new PreparedStatementStep(connectionDB.getConnection().prepareStatement(sql));

            createT(pr).executeUpdate();

            return toSave;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
