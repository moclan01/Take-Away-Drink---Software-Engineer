package model;

public class BillDetail {
    private String idbilldetail;
    private Bill bill;
    private Product product;
    private Size size;
    private int quantity;
    private int price;

    public BillDetail(){}
    public BillDetail(String idbilldetail, Bill bill, Product product, Size size, int quantity, int price) {
        this.idbilldetail = idbilldetail;
        this.bill = bill;
        this.product = product;
        this.size = size;
        this.quantity = quantity;
        this.price = price;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public String getIdbilldetail() {
        return idbilldetail;
    }

    public void setIdbilldetail(String idbilldetail) {
        this.idbilldetail = idbilldetail;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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
}
