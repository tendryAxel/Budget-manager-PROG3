package com.example.walletprog3.model;

import com.example.walletprog3.utils.annotations.Column;
import com.example.walletprog3.utils.annotations.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table(table_name = TransactionModel.TABLE_NAME, id = TransactionModel.ID)
public class TransactionModel extends DefaultModel {
    public int getId_transaction() {
        return id_transaction;
    }

    public void setId_transaction(int id_transaction) {
        this.id_transaction = id_transaction;
    }

    public int getId_currency() {
        return id_currency;
    }

    public void setId_currency(int id_currency) {
        this.id_currency = id_currency;
    }

    public int getId_subcategory() {
        return id_subcategory;
    }

    public void setId_subcategory(int id_subcategory) {
        this.id_subcategory = id_subcategory;
    }

    private int id_transaction;
    @Column(name = TransactionModel.LABEL)
    private String label ;
    @Column(name = TransactionModel.AMOUNT)
    private BigDecimal amount;
    @Column(name = TransactionModel.TRANSACTION_DATE)
    private LocalDateTime transaction_date;
    @Column(name = TransactionModel.TYPE)
    private TransactionType type ;
    @Column(name = TransactionModel.ID_ACCOUNT)
    private int id_account;
    @Column(name = TransactionModel.ID_CURRENCY)
    private int id_currency;
    @Column(name = TransactionModel.ID_SUBCATEGORY)
    private int id_subcategory;

    public static final String TABLE_NAME = "transaction";
    public static final String ID = "id_transaction";
    public static final  String LABEL = "label";
    public static final  String AMOUNT = "amount";
    public static final  String TRANSACTION_DATE = "transaction_date";
    public static final  String TYPE = "type";
    public static final  String ID_ACCOUNT = "id_account";
    public static final  String ID_CURRENCY = "id_currency";
    public static final  String ID_SUBCATEGORY = "id_subcategory";

    public TransactionModel(
            int id_transaction,
            String label,
            BigDecimal amount,
            LocalDateTime transaction_date,
            TransactionType type,
            int id_account,
            int id_currency,
            int id_subcategory
    ) {
        this.id_transaction = id_transaction;
        this.label = label;
        this.amount = amount;
        this.transaction_date = transaction_date;
        this.type = type;
        this.id_account = id_account;
        this.id_currency = id_currency;
        this.id_subcategory = id_subcategory;
    }

    public TransactionModel() {

    }

    public int getId() {
        return id_transaction;
    }

    public void setId(int id) {
        this.id_transaction = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(LocalDateTime transaction_date) {
        this.transaction_date = transaction_date;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public int getId_account() {
        return id_account;
    }

    public void setId_account(int id_account) {
        this.id_account = id_account;
    }

    @Override
    public String toString() {
        return "TransactionModel{" +
                "id_transaction=" + id_transaction +
                ", label='" + label + '\'' +
                ", amount=" + amount +
                ", transaction_date=" + transaction_date +
                ", type=" + type +
                ", id_account=" + id_account +
                ", id_currency=" + id_currency +
                ", id_subcategory=" + id_subcategory +
                '}';
    }
}