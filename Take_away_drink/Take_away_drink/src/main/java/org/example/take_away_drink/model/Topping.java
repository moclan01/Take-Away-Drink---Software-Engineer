package org.example.take_away_drink.model;

import java.io.Serializable;

public class Topping implements Serializable {
    private String idtopping;
    private String nametopping;
    private int pricetopping;

    public Topping(String idtopping, String nametopping, int pricetopping) {
        this.idtopping = idtopping;
        this.nametopping = nametopping;
        this.pricetopping = pricetopping;
    }
    public Topping(){

    }

    public String getIdtopping() {
        return idtopping;
    }

    public void setIdtopping(String idtopping) {
        this.idtopping = idtopping;
    }

    public String getNametopping() {
        return nametopping;
    }

    public void setNametopping(String nametopping) {
        this.nametopping = nametopping;
    }

    public int getPricetopping() {
        return pricetopping;
    }

    public void setPricetopping(int pricetopping) {
        this.pricetopping = pricetopping;
    }

    @Override
    public String toString() {
        return "Topping{" +
                "idtopping='" + idtopping + '\'' +
                ", nametopping='" + nametopping + '\'' +
                ", pricetopping=" + pricetopping +
                '}';
    }
}
