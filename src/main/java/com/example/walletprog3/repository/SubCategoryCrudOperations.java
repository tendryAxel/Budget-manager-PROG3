package com.example.walletprog3.repository;

import com.example.walletprog3.model.SubCategoryModel;
import com.example.walletprog3.model.TransactionType;
import com.example.walletprog3.utils.PreparedStatementStep;
import org.springframework.stereotype.Repository;

import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

@Repository
public class SubCategoryCrudOperations extends CrudOperationsImpl<SubCategoryModel> {
    public SubCategoryCrudOperations(com.example.walletprog3.repository.connectionDB connectionDB) {
        super(connectionDB);
    }

    @Override
    public SubCategoryModel createT(ResultSet resultSet) throws SQLException {
        return new SubCategoryModel(
                resultSet.getInt(SubCategoryModel.ID),
                resultSet.getString(SubCategoryModel.NAME),
                TransactionType.valueOf(resultSet.getString(SubCategoryModel.TYPE)),
                resultSet.getInt(SubCategoryModel.ID_CATEGORY)
        );
    }

    @Override
    public PreparedStatement createT(PreparedStatementStep pr, SubCategoryModel model) throws SQLException, InvocationTargetException, IllegalAccessException {
        PreparedStatement preparedStatement = pr.getPreparedStatement();
        preparedStatement.setString(1, model.getName());
        preparedStatement.setObject(2 , model.getType() , Types.OTHER);
        preparedStatement.setInt(3 , model.getId_category());
        return super.createT(pr, model);
    }

    @Override
    public Class<SubCategoryModel> getClassT() {
        return SubCategoryModel.class;
    }

    @Override
    public SubCategoryModel findById(Integer id) {
        return super.findById(id);
    }

    @Override
    public SubCategoryModel delete(Integer id) {
        return super.delete(id);
    }
}