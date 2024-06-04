package model;

import java.sql.Date;
import java.sql.Timestamp;

public class Bill {
    private String idbill;
    private Account account;
    private Timestamp date;
    private int totalprice;
    private String name;
    private int phone;
    private String address;


    public Bill() {}
    public Bill(String idbill, Account account, Timestamp date, int totalprice, String name, int phone,String address) {
        this.idbill = idbill;
        this.account = account;
        this.date = date;
        this.totalprice = totalprice;
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getIdbill() {
        return idbill;
    }

    public void setIdbill(String idbill) {
        this.idbill = idbill;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(int totalprice) {
        this.totalprice = totalprice;
    }
}
