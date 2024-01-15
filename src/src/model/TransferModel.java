package model;

import utils.annotations.Column;
import utils.annotations.Table;

import java.time.LocalDateTime;

@Table(table_name = TransferModel.TABLE_NAME, id = TransferModel.ID)
public class TransferModel extends DefaultModel {
    int id;
    @Column(name = TransferModel.ID_DEBIT)
    int transactionDebtor;
    @Column(name = TransferModel.ID_CREDIT)
    int transactionCredit;
    @Column(name = TransferModel.TRANSFER_DATE)
    LocalDateTime datetime;

    public static final String TABLE_NAME = "transfert";
    public static final String ID = "id";
    public static final  String ID_DEBIT = "id_debtor";
    public static final  String ID_CREDIT = "id_credit";
    public static final  String TRANSFER_DATE = "transfer_date";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTransactionDebtor() {
        return transactionDebtor;
    }

    public void setTransactionDebtor(int transactionDebtor) {
        this.transactionDebtor = transactionDebtor;
    }

    public int getTransactionCredit() {
        return transactionCredit;
    }

    public void setTransactionCredit(int transactionCredit) {
        this.transactionCredit = transactionCredit;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public TransferModel() {
    }

    public TransferModel(int id, int transactionDebtor, int transactionCredit, LocalDateTime datetime) {
        this.id = id;
        this.transactionDebtor = transactionDebtor;
        this.transactionCredit = transactionCredit;
        this.datetime = datetime;
    }
}
