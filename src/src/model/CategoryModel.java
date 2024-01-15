package model;

import utils.annotations.Table;

@Table(table_name = CategoryModel.TABLE_NAME, id = CategoryModel.ID)
public class CategoryModel extends DefaultModel {
    private int id_category;
    private String name;


    public CategoryModel(int id_category, String name) {
        this.id_category = id_category;
        this.name = name;
    }

    public int getId_category() {
        return id_category;
    }

    public void setId_category(int id_category) {
        this.id_category = id_category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CategoryModel{" +
                "id_category=" + id_category +
                ", name='" + name + '\'' +
                '}';
    }
}
