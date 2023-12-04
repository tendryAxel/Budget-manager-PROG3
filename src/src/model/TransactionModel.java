package model;

import java.sql.Date;

public class TransactionModel {
   private int id;
   private int value;
  private   String description;
   private int id_account;
    private  Date transaction_date;

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


    @Override
    public String toString() {
        return "TransactionModel{" +
                "id=" + id +
                ", value=" + value +
                ", description='" + description + '\'' +
                ", id_account=" + id_account +
                ", transaction_date=" + transaction_date +
                '}';
    }
}
