package utils;

import java.sql.Timestamp;

public class category_amount_between_dates_Result {
    private int account_id ;
    private Timestamp start_date;
    private Timestamp end_date;

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public Timestamp getStart_date() {
        return start_date;
    }

    public void setStart_date(Timestamp start_date) {
        this.start_date = start_date;
    }

    public Timestamp getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Timestamp end_date) {
        this.end_date = end_date;
    }


    @Override
    public String toString() {
        return "CategorySumRes{" +
                "account_id=" + account_id +
                ", start_date=" + start_date +
                ", end_date=" + end_date +
                '}';
    }
}
