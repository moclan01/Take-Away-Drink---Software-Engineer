package org.example.take_away_drink.model;

public class Size {
    private String idsize;
    private double price;
    private String namesize;

    public Size(){

    }
    public Size(String idsize, String namesize, double price) {
        this.idsize = idsize;
        this.namesize = namesize;
        this.price = price;
    }

    public String getIdsize() {
        return idsize;
    }

    public void setIdsize(String idsize) {
        this.idsize = idsize;
    }

    public String getNamesize() {
        return namesize;
    }

    public void setNamesize(String namesize) {
        this.namesize = namesize;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
