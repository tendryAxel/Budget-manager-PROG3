package model;

import java.sql.Date;

public class TransactionModel {
    int id;
    int value;
    String description;
    int id_account;
    Date transaction_date;

    public TransactionModel(int id, int value, String description, int id_account, Date transaction_date) {
        this.id = id;
        this.value = value;
        this.description = description;
        this.id_account = id_account;
        this.transaction_date = transaction_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId_account() {
        return id_account;
    }

    public void setId_account(int id_account) {
        this.id_account = id_account;
    }

    public Date getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(Date transaction_date) {
        this.transaction_date = transaction_date;
    }

    public TransactionModel() {
    }
}
