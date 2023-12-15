package repository;

import model.SubCategoryModel;
import model.TransactionType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class SubCategoryCrudOperations implements CrudOperations <SubCategoryModel>{
    @Override
    public List<SubCategoryModel> findAll() throws SQLException {
        String sql = String.format(
                "SELECT * FROM \"%s\"",
                SubCategoryModel.TABLE_NAME
        );
        List<SubCategoryModel> AllSubCategory = new ArrayList<>();
        ResultSet resultSet = connectionDB.getConnection().prepareStatement(sql).executeQuery();
        while (resultSet.next()){
            AllSubCategory.add(new SubCategoryModel(
                    resultSet.getInt(SubCategoryModel.ID),
                    resultSet.getString(SubCategoryModel.NAME),
                    TransactionType.valueOf(resultSet.getString(SubCategoryModel.TYPE)),
                    resultSet.getInt(SubCategoryModel.ID_CATEGORY)
            ));
        }
        return AllSubCategory;
    }

    @Override
    public List<SubCategoryModel> saveAll(List<SubCategoryModel> toSave) {
        String sql = String.format(
                "INSERT INTO \"%s\" (%s,%s,%s) VALUES (?,?,?)",
                SubCategoryModel.TABLE_NAME,
                SubCategoryModel.NAME,
                SubCategoryModel.TYPE,
                SubCategoryModel.ID_CATEGORY
        );
        System.out.println(sql);
        List<SubCategoryModel> saveSubCategory = new ArrayList<>();
        try(PreparedStatement preparedStatement = connectionDB.getConnection().prepareStatement(sql)){
            for (SubCategoryModel subCategoryModel : toSave){
                preparedStatement.setString(1, subCategoryModel.getName());
                preparedStatement.setObject(2 , subCategoryModel.getType() , Types.OTHER);
                preparedStatement.setInt(3 , subCategoryModel.getId_category());

                int rowAffected = preparedStatement.executeUpdate();
                if (rowAffected > 0){
                    saveSubCategory.add(subCategoryModel);
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return saveSubCategory;
    }

    @Override
    public SubCategoryModel save(SubCategoryModel toSave)  {
        String sql = String.format(
                "INSERT INTO \"%s\" (%s,%s,%s) VALUES (?,?,?)",
                SubCategoryModel.TABLE_NAME,
                SubCategoryModel.NAME,
                SubCategoryModel.TYPE,
                SubCategoryModel.ID_CATEGORY
        );
        try (PreparedStatement preparedStatement = connectionDB.getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, toSave.getName());
            preparedStatement.setObject(2 , toSave.getType() , Types.OTHER);
            preparedStatement.setInt(3 , toSave.getId_category());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}