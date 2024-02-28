package java.com.example.walletprog3.model;

import java.com.example.walletprog3.utils.ColumnType;
import java.com.example.walletprog3.utils.annotations.Column;
import java.com.example.walletprog3.utils.annotations.GetColumn;
import java.com.example.walletprog3.utils.annotations.Table;

import java.time.LocalDateTime;

@Table(table_name = AccountModel.TABLE_NAME, id = AccountModel.ID)
public class AccountModel extends DefaultModel {
    private   int id_account;
    @Column(name = AccountModel.NAME)
    private String name;
    @Column(name = AccountModel.UPDATEDATE)
    private LocalDateTime updateDate;
    @Column(name = AccountModel.ID_CURRENCY)
    private    int id_currency;
    @Column(name = AccountModel.TYPE)
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

    @GetColumn(type = ColumnType.INT)
    public int getId() {
        return id_account;
    }

    public void setId(int id) {
        this.id_account = id;
    }

    @GetColumn(type = ColumnType.STRING)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @GetColumn(type = ColumnType.LOCAL_DATETIME)
    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    @GetColumn(type = ColumnType.INT)
    public int getId_currency() {
        return id_currency;
    }

    public void setId_currency(int id_currency) {
        this.id_currency = id_currency;
    }

    @GetColumn(type = ColumnType.ACCOUNT_TYPE)
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