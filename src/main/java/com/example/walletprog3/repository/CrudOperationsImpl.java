package com.example.walletprog3.repository;

import com.example.walletprog3.model.DefaultModel;
import com.example.walletprog3.utils.PreparedStatementStep;
import com.example.walletprog3.utils.annotations.Column;
import com.example.walletprog3.utils.annotations.GetColumn;
import com.example.walletprog3.utils.annotations.Table;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CrudOperationsImpl<T extends DefaultModel> implements CrudOperations<T, Integer> {
    private connectionDB connectionDB;

    public CrudOperationsImpl(com.example.walletprog3.repository.connectionDB connectionDB) {
        this.connectionDB = connectionDB;
    }

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
    public String createInsertQuery(){
        return createInsertQuery(getListColumn());
    }
    protected String getTableName(){
        Table a = getClassT().getAnnotation(Table.class);
        return a.table_name();
    };
    protected String getId(){
        Table a = getClassT().getAnnotation(Table.class);
        return a.id();
    };
    public String createInsertQuery(List<String> columns){
        String result = String.format(
                "INSERT INTO \"%s\" (",
                getTableName()
        );
        for (String column : columns){
            result += column + ",";
        }
        result = result.substring(0, result.length()-1);
        result += ") VALUES (";
        for (String column : columns){
            result += "?,";
        }
        result = result.substring(0, result.length()-1);
        result += ")";
        return result;
    }
    public List<String> getListColumn(){
        List<String> result = new ArrayList<>();
        for (Field f : getClassT().getDeclaredFields()){
            if (f.isAnnotationPresent(Column.class)){
                String cName = f.getAnnotation(Column.class).name();
                result.add(cName);
            }
        }
        return result;
    };


    @Override
    public List<T> findAll(){
        String sql = String.format(
                "SELECT * FROM \"%s\"",
                getTableName()
        );
        List<T> AllData = new ArrayList<>();
        ResultSet resultSet = null;
        try {
            resultSet = this.connectionDB.connection.prepareStatement(sql).executeQuery();
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
        String sql = createInsertQuery();
        try {
            PreparedStatementStep pr = new PreparedStatementStep(this.connectionDB.connection.prepareStatement(sql));

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
                getTableName(),
                getTableName(),
                getId()
        );
        PreparedStatementStep pr = null;
        try {
            pr = new PreparedStatementStep(this.connectionDB.connection.prepareStatement(sql));
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
                    getTableName(),
                    getTableName(),
                    getId()
            );
            PreparedStatementStep pr = new PreparedStatementStep(this.connectionDB.connection.prepareStatement(sql));
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
