package model;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Product implements Serializable {
    private String idproduct;
    private Type type;
    private String name;
    private Size size;
    private int price;
    private String describe;
    private String srcIMG;
    private String idtype;
    private List<Topping> toppings = new ArrayList<>();

    public Product(String idproduct,Type type,String name, int price, String describe, String srcIMG){
        this.idproduct = idproduct;
        this.type = type;
        this.name = name;
        this.price = price;
        this.describe = describe;
        this.srcIMG = srcIMG;

    }
    public Product(){

    }

    public String getDescribe() {
        return describe;
    }

    public String getIdproduct() {
        return idproduct;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public Size getSize() {
        return size;
    }

    public String getSrcIMG() {
        return srcIMG;
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    public Type getType() {
        return type;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public void setIdproduct(String idproduct) {
        this.idproduct = idproduct;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    public void setSize(Size size) {
        this.size = size;
    }

    public void setSrcIMG(String srcIMG) {
        this.srcIMG = srcIMG;
    }

    public void setToppings(List<Topping> toppings) {
        this.toppings = toppings;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getNameType(){
        return type.getNametype();
    }
    public String getidtype(){
        return type.getidtype();
    }

    public String getIdtype() {
        return idtype;
    }

    public void setIdtype(String idtype) {
        this.idtype = idtype;
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
