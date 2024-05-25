package model;

public class CartDetailTopping {
    private String idcartdetail;
    private Cart cart;
    private Product product;
    private Topping topping;


    public CartDetailTopping(){

    }

    public CartDetailTopping(String idcartdetail, Cart cart, Product product, Topping topping) {
        this.idcartdetail = idcartdetail;
        this.cart = cart;
        this.product = product;
        this.topping = topping;

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

    public String getIdcartdetail() {
        return idcartdetail;
    }

    public void setIdcartdetail(String idcartdetail) {
        this.idcartdetail = idcartdetail;
    }

    @Override
    public String toString() {
        return "CartDetailTopping{" +
                "cart=" + cart +
                ", idcartdetail='" + idcartdetail + '\'' +
                ", product=" + product +
                ", topping=" + topping +
                '}';
    }
}
