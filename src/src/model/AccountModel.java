package model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class AccountModel {
    private   int id;
    private String name;
    private Timestamp updateDate;
    private    int id_currency;
    private AccountType type;

    public static final String TABLE_NAME = "account";
    public static final String ID = "id";
    public static final  String NAME = "name";
    public static final  String UPDATEDATE = "updatedDate";
    public static final  String ID_CURRENCY = "id_currency";
    public static final  String TYPE = "type";

    public AccountModel(int id, String name, Timestamp updateDate, int id_currency, AccountType type) {
        this.id = id;
        this.name = name;
        this.updateDate = updateDate;
        this.id_currency = id_currency;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }

    public int getId_currency() {
        return id_currency;
    }

    public void setId_currency(int id_currency) {
        this.id_currency = id_currency;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "AccountModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", updateDate=" + updateDate +
                ", id_currency=" + id_currency +
                ", type=" + type +
                '}';
    }
}
