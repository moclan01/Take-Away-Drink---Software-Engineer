package org.example.take_away_drink.model;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

import java.io.Serializable;
import java.util.ArrayList;

public class Cart implements Serializable {
    private String idcart;
    private Account account;
    private ArrayList<CartDetail> items = new ArrayList<>();

    public Cart(String maOrder, Account account) {
        super();
        this.idcart = maOrder;
        this.account = account;
    }
    public  Cart(){

    }

    @ColumnName("idcart")
    public String getMaOrder() {
        return idcart;
    }
    public void setMaOrder(String maOrder) {
        this.idcart = maOrder;
    }

    public Account getAccount() {
        return account;
    }
    public String getUsername() {
        return account.getUsername();
    }
    public void setAccount(Account account) {
        this.account = account;
    }


    @Override
    public String toString() {
        return  "idcart: " + idcart + " account: " + account;
    }
}
