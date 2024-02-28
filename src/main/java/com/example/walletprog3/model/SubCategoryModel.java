package com.example.walletprog3.model;

import com.example.walletprog3.utils.annotations.Column;
import com.example.walletprog3.utils.annotations.Table;

@Table(table_name = SubCategoryModel.TABLE_NAME, id = SubCategoryModel.ID)
public class SubCategoryModel extends DefaultModel {
    private   int id_subcategory;
    @Column(name = SubCategoryModel.NAME)
    private String name;
    @Column(name = SubCategoryModel.TYPE)
    private TransactionType type;
    @Column(name = SubCategoryModel.ID_CATEGORY)
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
