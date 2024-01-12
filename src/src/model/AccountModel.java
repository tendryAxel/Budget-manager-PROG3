package model;

import utils.ColumnType;
import utils.annotations.Column;
import utils.annotations.GetColumn;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static utils.ColumnType.*;

public class AccountModel extends DefaultModel {
    @Column
    private   int id_account;
    @Column
    private String name;
    @Column
    private LocalDateTime updateDate;
    @Column
    private    int id_currency;
    @Column
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

    @GetColumn(type = INT)
    public int getId() {
        return id_account;
    }

    public void setId(int id) {
        this.id_account = id;
    }

    @GetColumn(type = STRING)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @GetColumn(type = LOCAL_DATETIME)
    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    @GetColumn(type = INT)
    public int getId_currency() {
        return id_currency;
    }

    public void setId_currency(int id_currency) {
        this.id_currency = id_currency;
    }

    @GetColumn(type = ACCOUNT_TYPE)
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
