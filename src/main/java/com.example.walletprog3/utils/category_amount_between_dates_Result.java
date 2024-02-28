package java.com.example.walletprog3.utils;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class category_amount_between_dates_Result {
    private int account_id ;
    private LocalDateTime start_date;
    private LocalDateTime end_date;

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public LocalDateTime getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDateTime start_date) {
        this.start_date = start_date;
    }

    public LocalDateTime getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDateTime end_date) {
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
