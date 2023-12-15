package model;

import java.math.BigDecimal;
import java.util.Date;

public class CurrencyValueModel {


    public static final String TABLE_NAME = "currency_value";
    public static final  String ID = "id";
    public static final String ID_CURRENCY_SOURCE = "id_currency_source";
    public static final String ID_CURRENCY_DESTINATION = "id_currency_destination";
    public static final String AMOUNT = "amount";
    public static String DATE_EFFET = "date_effet";


    private int id;
    private int id_currency_source;
    private  int id_currency_destination;
    private BigDecimal amount;
    private Date date_effet;



    public CurrencyValueModel(int id, int id_currency_source, int id_currency_destination, BigDecimal amount, Date date_effet) {
        this.id = id;
        this.id_currency_source = id_currency_source;
        this.id_currency_destination = id_currency_destination;
        this.amount = amount;
        this.date_effet = date_effet;
    }


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

    public java.sql.Date getDate_effet() {
        return (java.sql.Date) date_effet;
    }

    public void setDate_effet(Date date_effet) {
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
