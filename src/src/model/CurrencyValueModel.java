package model;

import utils.annotations.Column;
import utils.annotations.Table;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Table(table_name = CurrencyValueModel.TABLE_NAME, id = CurrencyValueModel.ID)
public class CurrencyValueModel extends DefaultModel {


    public static final String TABLE_NAME = "currency-value";
    public static final  String ID = "id";
    public static final String ID_CURRENCY_SOURCE = "id_currency_source";
    public static final String ID_CURRENCY_DESTINATION = "id_currency_destination";
    public static final String AMOUNT = "amount";
    public static final String DATE_EFFET = "date_effet";


    private int id;
    @Column(name = CurrencyValueModel.ID_CURRENCY_DESTINATION)
    private int id_currency_source;
    @Column(name = CurrencyValueModel.ID_CURRENCY_DESTINATION)
    private  int id_currency_destination;
    @Column(name = CurrencyValueModel.AMOUNT)
    private BigDecimal amount;
    @Column(name = CurrencyValueModel.DATE_EFFET)
    private LocalDate date_effet;



    public CurrencyValueModel(int id, int id_currency_source, int id_currency_destination, BigDecimal amount, LocalDate date_effet) {
        this.id = id;
        this.id_currency_source = id_currency_source;
        this.id_currency_destination = id_currency_destination;
        this.amount = amount;
        this.date_effet = date_effet;
    }

    public CurrencyValueModel() {}


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_currency_source() {
        return id_currency_source;
    }

    public void setId_currency_source(int id_currency_source) {
        this.id_currency_source = id_currency_source;
    }

    public int getId_currency_destination() {
        return id_currency_destination;
    }

    public void setId_currency_destination(int id_currency_destination) {
        this.id_currency_destination = id_currency_destination;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getDate_effet() {
        return date_effet;
    }

    public void setDate_effet(LocalDate date_effet) {
        this.date_effet = date_effet;
    }

    @Override
    public String toString() {
        return "CurrencyValueModel{" +
                "id=" + id +
                ", id_currency_source=" + id_currency_source +
                ", id_currency_destination=" + id_currency_destination +
                ", amount=" + amount +
                ", date_effet=" + date_effet +
                '}';
    }
}
