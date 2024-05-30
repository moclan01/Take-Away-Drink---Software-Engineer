package model;

public class CartDetailTopping {
    private CartDetail cartDetail;
    private Cart cart;
    private Product product;
    private Topping topping;


    public CartDetailTopping(){

    }

    public CartDetailTopping(CartDetail idcartdetail, Cart cart, Product product, Topping topping) {
        this.cartDetail= idcartdetail;
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

    public CartDetail getCartDetail() {
        return cartDetail;
    }

    public void setCartDetail(CartDetail cartDetail) {
        this.cartDetail = cartDetail;
    }

    @Override
    public String toString() {
        return topping.getNametopping();
    }
}
