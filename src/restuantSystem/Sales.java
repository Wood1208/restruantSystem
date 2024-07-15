package restuantSystem;

import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

public class Sales {

    //销售统计类，记录每日销售情况
    private LocalDate date;
    private double account;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getAccount() {
        return account;
    }

    public void setAccount(double account) {
        this.account = account;
    }

    public Sales(LocalDate date, double account) {
        this.date = date;
        this.account = account;
    }

}
