package model;

public class CartDetailTopping {
    private String idproduct;
    private Topping topping;

    public CartDetailTopping(String idproduct, Topping topping) {
        this.idproduct = idproduct;
        this.topping = topping;
    }

    public String getIdproduct() {
        return idproduct;
    }

    public void setIdproduct(String idproduct) {
        this.idproduct = idproduct;
    }

    public Topping getTopping() {
        return topping;
    }

    public void setTopping(Topping topping) {
        this.topping = topping;
    }
}
