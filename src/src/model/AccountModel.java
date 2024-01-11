package model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class AccountModel extends DefaultModel {
    private   int id_account;
    private String name;
    private LocalDateTime updateDate;
    private    int id_currency;
    private AccountType type;

    public static final String TABLE_NAME = "account";
    public static final String ID = "id_account";
    public static final  String NAME = "name";
    public static final  String UPDATEDATE = "updatedDate";
    public static final  String ID_CURRENCY = "id_currency";
    public static final  String TYPE = "type";

    public AccountModel(int id_account, String name, LocalDateTime updateDate, int id_currency, AccountType type) {
        this.id_account = id_account;
        this.name = name;
        this.updateDate = updateDate;
        this.id_currency = id_currency;
        this.type = type;
    }

    public int getId() {
        return id_account;
    }

    public void setId(int id) {
        this.id_account = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
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
                "id=" + id_account +
                ", name='" + name + '\'' +
                ", updateDate=" + updateDate +
                ", id_currency=" + id_currency +
                ", type=" + type +
                '}';
    }
}
