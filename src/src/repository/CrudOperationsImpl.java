package repository;

import model.DefaultModel;
import utils.PreparedStatementStep;
import utils.annotations.Column;
import utils.annotations.GetColumn;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CrudOperationsImpl<T extends DefaultModel> implements CrudOperations<T, Integer> {

    public T createT(ResultSet resultSet) throws SQLException {
        return null;
    }
    public Class<T> getClassT(){
        return null;
    }
    public PreparedStatement createT(PreparedStatementStep pr, T model) throws SQLException, InvocationTargetException, IllegalAccessException {
        for (Method m : getClassT().getDeclaredMethods()){
            if (m.isAnnotationPresent(GetColumn.class)){
                GetColumn annotation = m.getAnnotation(GetColumn.class);
                pr.addValues(m.invoke(model), annotation.type());
            }
        }
        return pr.getPreparedStatement();
    };
    public String createInsertQuery(List<String> columns){
        return createInsertQuery(columns.size());
    }
    public String createInsertQuery(){
        return createInsertQuery(getListColumn().size());
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
    public List<Field> getListColumn(){
        List<Field> result = new ArrayList<>();
        for (Field f : getClassT().getFields()){
            if (f.isAnnotationPresent(Column.class)){
                result.add(f);
            }
        }
        return result;
    };


    @Override
    public List<T> findAll(){
        String sql = String.format(
                "SELECT * FROM \"%s\"",
                T.TABLE_NAME
        );
        List<T> AllData = new ArrayList<>();
        ResultSet resultSet = null;
        try {
            resultSet = connectionDB.getConnection().prepareStatement(sql).executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        while (true){
            try {
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                AllData.add(createT(resultSet));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return AllData;
    }

    @Override
    public List<T> saveAll(List<T> toSave){
        for (T element : toSave){
            save(element);
        }
        return toSave;
    }

    @Override
    public T save(T toSave){
        String sql = createInsertQuery(0);
        try {
            PreparedStatementStep pr = new PreparedStatementStep(connectionDB.getConnection().prepareStatement(sql));

            createT(pr, toSave).executeUpdate();

            return toSave;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public T findById(Integer id){
        String sql = String.format(
                "SELECT * FROM \"%s\" WHERE \"%s\".\"%s\" = ? ",
                T.TABLE_NAME,
                T.TABLE_NAME,
                T.ID
        );
        PreparedStatementStep pr = null;
        try {
            pr = new PreparedStatementStep(connectionDB.getConnection().prepareStatement(sql));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            pr.addValue(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ResultSet resultSet = null;
        try {
            resultSet = pr.getPreparedStatement().executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        while (true){
            try {
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                return createT(resultSet);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    @Override
    public T delete(Integer id) {
        try {
            T toDelete = findById(id);
            String sql = String.format(
                    "DELETE FROM \"%s\" WHERE \"%s\".\"%s\" = ? ",
                    T.TABLE_NAME,
                    T.TABLE_NAME,
                    T.ID
            );
            PreparedStatementStep pr = new PreparedStatementStep(connectionDB.getConnection().prepareStatement(sql));
            pr.addValue(id);
            int result = pr.getPreparedStatement().executeUpdate();
            if (result == 1) {
                return toDelete;
            }
        } catch (SQLException e){
            throw new RuntimeException();
        }
        return null;
    }
}
