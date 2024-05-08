package org.example.take_away_drink.model;

import java.io.Serializable;

public class CartDetail implements Serializable {
    private Cart cart;
    private Product item;
    private Topping topping;
    private Size size;
    private int quantity;
    private int price;

    public CartDetail(){

    }

    public CartDetail(Cart cart, Product item, int price, int quantity, Size size, Topping topping) {
        this.cart = cart;
        this.item = item;
        this.price = price;
        this.quantity = quantity;
        this.size = size;
        this.topping = topping;
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

    public Topping getTopping() {
        return topping;
    }

    public void setTopping(Topping topping) {
        this.topping = topping;
    }

    @Override
    public String toString() {
        return "CartDetail{" +
                "item=" + item +
                ", topping=" + topping +
                ", size=" + size +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
