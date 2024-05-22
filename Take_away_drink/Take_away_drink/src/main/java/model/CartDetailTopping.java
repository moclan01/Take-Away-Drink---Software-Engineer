package model;

public class CartDetailTopping {
    private Cart cart;
    private Product product;
    private Topping topping;

    public CartDetailTopping(Cart cart, Product product, Topping topping) {
        this.cart = cart;
        this.product = product;
        this.topping = topping;
    }
    public CartDetailTopping(){

    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Topping getTopping() {
        return topping;
    }

    public void setTopping(Topping topping) {
        this.topping = topping;
    }
}
