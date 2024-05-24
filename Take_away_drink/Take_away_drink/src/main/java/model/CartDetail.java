package model;

import java.io.Serializable;
import java.util.ArrayList;

public class CartDetail implements Serializable {
    private  String idcartdetail;
    private Cart cart;
    private Product item;
    private Size size;
    private int quantity;
    private int price;

    private ArrayList<Topping> toppings = new ArrayList<>();

    public CartDetail(){

    }
    public CartDetail(String idcartdetail, Cart cart, Product item, Size size, int quantity, int price){
        this.idcartdetail = idcartdetail;
        this.cart = cart;
        this.item = item;
        this.size = size;
        this.quantity = quantity;
        this.price = price;

    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Product getItem() {
        return item;
    }

    public void setItem(Product item) {
        this.item = item;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public ArrayList<Topping> getToppings() {
        return toppings;
    }

    public void setToppings(ArrayList<Topping> toppings) {
        this.toppings = toppings;
    }

    public String getIdcartdetail() {
        return idcartdetail;
    }

    public void setIdcartdetail(String idcartdetail) {
        this.idcartdetail = idcartdetail;
    }

    @Override
    public String toString() {
        return "CartDetail{" + "idcartdetail=" + idcartdetail + ", cart=" + cart + ", item=" + item + ", size=" + size + ", quantity=" + quantity + ", price=" + price + ", toppings=" + toppings + '}';
    }
}
