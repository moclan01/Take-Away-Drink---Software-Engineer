package org.example.take_away_drink.model;

import java.io.Serializable;

public class CartDetail implements Serializable {
    private Product item;
    private int soLuong;
    private long giaBan;
    public CartDetail(Product item, int soLuong,long giaBan) {
        super();
        this.item = item;
        this.soLuong = soLuong;
        this.giaBan = giaBan;
    }
    public Product getItem() {
        return item;
    }
    public void setItem(Product item) {
        this.item = item;
    }
    public int getSoLuong() {
        return soLuong;
    }
    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
    public long getGiaBan() {
        return giaBan;
    }
    public void setGiaBan(long l) {
        this.giaBan = l;
    }
}
