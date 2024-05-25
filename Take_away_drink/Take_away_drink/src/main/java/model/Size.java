package model;

public class Size {
    private String idsize;
    private int price;
    private String namesize;

    public Size(){

    }
    public Size(String idsize, String namesize, int price) {
        this.idsize = idsize;
        this.namesize = namesize;
        this.price = price;
    }

    public String getIdsize() {
        return idsize;
    }

    public void setIdsize(String idsize) {
        this.idsize = idsize;
    }

    public String getNamesize() {
        return namesize;
    }

    public void setNamesize(String namesize) {
        this.namesize = namesize;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "idsize=" + idsize + ", namesize=" + namesize + ", price=" + price;
    }
}
