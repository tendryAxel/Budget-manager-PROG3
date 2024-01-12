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
    public PreparedStatement createT(PreparedStatement pr){
        return pr;
    };
    public String createInsertQuery(List<String> columns){
        String result = String.format(
                "INSERT INTO \"%s\" (",
                T.TABLE_NAME
        );
        for (String element : columns){
            result += "%s,";
        }
        result = result.substring(0, result.length()-1);
        result += ") VALUES (";
        for (String element : columns){
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
            PreparedStatement pr = connectionDB.getConnection().prepareStatement(sql);

            createT(pr).executeUpdate();

            return toSave;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
