package repository;
import model.CategoryModel;
import utils.PreparedStatementStep;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class CategoryCrudOperations extends  CrudOperationsImpl <CategoryModel>{

    @Override
    public CategoryModel createT(ResultSet resultSet) throws SQLException {
        int id_category = resultSet.getInt("id_category");
        String name = resultSet.getString("name");
        return new CategoryModel(id_category, name);
    }

    @Override
    public PreparedStatement createT(PreparedStatementStep pr,CategoryModel model) throws SQLException, InvocationTargetException, IllegalAccessException {
        PreparedStatement preparedStatement = pr.getPreparedStatement();
        preparedStatement.setString(1 , model.getName());
        return preparedStatement;
    }

    @Override
    public Class<CategoryModel> getClassT() {
        return CategoryModel.class;
    }

    @Override
    public CategoryModel findById(Integer id) {
        return super.findById(id);
    }

    @Override
    public CategoryModel delete(Integer id){
        return super.delete(id);
    }
}
