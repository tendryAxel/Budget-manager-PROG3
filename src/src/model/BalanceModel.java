package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BalanceModel extends DefaultModel{
    private int id_account;
    private LocalDateTime datetime;
    private BigDecimal value;

    public static final String TABLE_NAME = "balance";
    public static final  String ID_ACCOUNT = "id_account";
    public static final  String DATETIME = "datetime";
    public static final  String VALUE = "value";

    @Override
    public String toString() {
        return "BalanceModel{" +
                "id_account=" + id_account +
                ", datetime=" + datetime +
                ", value=" + value +
                '}';
    }

    public int getId_account() {
        return id_account;
    }

    public void setId_account(int id_account) {
        this.id_account = id_account;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public BalanceModel() {
    }

    public BalanceModel(int id_account, LocalDateTime datetime, BigDecimal value) {
        this.id_account = id_account;
        this.datetime = datetime;
        this.value = value;
    }
}
