package model;

public class BillDetailTopping {
    private BillDetail billDetail;
    private Product product;
    private Topping topping;

    public BillDetailTopping(){}
    public BillDetailTopping(BillDetail billDetail, Product product, Topping topping){
        this.billDetail =  billDetail;
        this.product = product;
        this.topping = topping;
    }

    public BillDetail getBillDetail() {
        return billDetail;
    }

    public void setBillDetail(BillDetail billDetail) {
        this.billDetail = billDetail;
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
