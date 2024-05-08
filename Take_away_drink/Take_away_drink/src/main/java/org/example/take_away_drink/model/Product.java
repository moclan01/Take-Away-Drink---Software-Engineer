package org.example.take_away_drink.model;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

import java.util.ArrayList;
import java.util.List;

public class Product {
    private String idproduct;
    private Type type;
    private String name;
    private Size size;
    private int price;
    private String describe;
    private String srcIMG;
    private List<Topping> toppings = new ArrayList<>();

    public Product(String idproduct, Type type, String name, int price, String describe, String srcIMG){
        this.idproduct = idproduct;
        this.type = type;
        this.name = name;
        this.price = price;
        this.describe = describe;
        this.srcIMG = srcIMG;

    }
    public Product(){

    }
    @ColumnName("idproduct")
    public String getIdproduct() {
        return idproduct;
    }

    public void setIdproduct(String idproduct) {
        this.idproduct = idproduct;
    }
    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }



    public String getNameType(){
        return type.getNametype();
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public String getSrcIMG() {
        return srcIMG;
    }

    public void setSrcIMG(String srcIMG) {
        this.srcIMG = srcIMG;
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    public void setToppings(List<Topping> toppings) {
        this.toppings = toppings;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Product{" +
                "describe='" + describe + '\'' +
                ", idproduct='" + idproduct + '\'' +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", size=" + size +
                ", price=" + price +
                ", srcIMG='" + srcIMG + '\'' +
                ", toppings=" + toppings +
                '}';
    }
}
