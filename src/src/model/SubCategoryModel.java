package model;

import java.sql.Timestamp;

public class SubCategoryModel {
    private   int id_subcategory;
    private String name;
    private TransactionType type;
    private int id_category;

    public static final String TABLE_NAME = "subcategory";
    public static final String ID = "id_subcategory";
    public static final  String NAME = "name";
    public static final  String TYPE = "type";
    public static final String ID_CATEGORY = "id_category";

    @Override
    public String toString() {
        return "SubCategoryModel{" +
                "id_subcategory=" + id_subcategory +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", id_category=" + id_category +
                '}';
    }

    public int getId_subcategory() {
        return id_subcategory;
    }

    public void setId_subcategory(int id_subcategory) {
        this.id_subcategory = id_subcategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public int getId_category() {
        return id_category;
    }

    public void setId_category(int id_category) {
        this.id_category = id_category;
    }

    public SubCategoryModel() {
    }

    public SubCategoryModel(int id_subcategory, String name, TransactionType type, int id_category) {
        this.id_subcategory = id_subcategory;
        this.name = name;
        this.type = type;
        this.id_category = id_category;
    }
}
