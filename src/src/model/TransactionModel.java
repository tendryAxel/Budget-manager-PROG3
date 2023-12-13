package model;


import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionModel {
    private int id;
    private String label ;

    private BigDecimal amount;

    private LocalDateTime transaction_date;
    private TransactionType type ;
    private int id_account;

    public static final String TABLE_NAME = "transaction";
    public static final String ID = "id";
    public static final  String LABEL = "label";
    public static final  String AMOUNT = "amount";
    public static final  String TRANSACTION_DATE = "transaction_date";
    public static final  String TYPE = "type";
    public static final  String ID_ACCOUNT = "id_account";

    public TransactionModel(int id, String label, BigDecimal amount, LocalDateTime transaction_date, TransactionType type, int id_account) {
        this.id = id;
        this.label = label;
        this.amount = amount;
        this.transaction_date = transaction_date;
        this.type = type;
        this.id_account = id_account;
    }

    public TransactionModel() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
                "id=" + id +
                ", label='" + label + '\'' +
                ", amount=" + amount +
                ", transaction_date=" + transaction_date +
                ", type=" + type +
                ", id_account=" + id_account +
                '}';
    }
}