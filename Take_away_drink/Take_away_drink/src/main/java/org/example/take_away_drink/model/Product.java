package org.example.take_away_drink.model;

public class Product {
    private String maSanPham;
    private String tenSanPham;
    private long giaBan;
    private String dungTich;
    private Type loaiSapPham;
    private String moTa;
    private String srcIMG;

    public Product() {
        // TODO Auto-generated constructor stub
    }

    public Product(String maSanPham, String tenSanPham, long giaBan, String dungTich, Type loaiSapPham, String moTa, String srcIMG) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.giaBan = giaBan;
        this.dungTich = dungTich;
        this.loaiSapPham = loaiSapPham;
        this.moTa = moTa;
        this.srcIMG = srcIMG;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public long getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(long giaBan) {
        this.giaBan = giaBan;
    }

    public String getDungTich() {
        return dungTich;
    }

    public void setDungTich(String dungTich) {
        this.dungTich = dungTich;
    }

    public Type getLoaiSapPham() {
        return loaiSapPham;
    }

    public void setLoaiSapPham(Type loaiSapPham) {
        this.loaiSapPham = loaiSapPham;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getSrcIMG() {
        return srcIMG;
    }

    public void setSrcIMG(String srcIMG) {
        this.srcIMG = srcIMG;
    }

    @Override
    public String toString() {
        return "Product{" +
                "maSanPham='" + maSanPham + '\'' +
                ", tenSanPham='" + tenSanPham + '\'' +
                ", giaBan=" + giaBan +
                ", dungTich='" + dungTich + '\'' +
                ", loaiSapPham=" + loaiSapPham +
                ", moTa='" + moTa + '\'' +
                ", srcIMG='" + srcIMG + '\'' +
                '}';
    }
}
