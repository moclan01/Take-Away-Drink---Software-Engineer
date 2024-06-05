package DB;

import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOBillDetail extends AbsDao<BillDetail> {
    @Override
    public boolean delete(BillDetail billDetail) {
        return false;
    }

    @Override
    public boolean insert(BillDetail billDetail) throws SQLException {
        try {
            JDBIConnector.getJdbi().useHandle(handle -> {
                handle.createUpdate("insert into bill_details(idbill,idbilldetail,idproduct,idsize,quantity,price)" +
                                " values(?,?,?,?,?,?)")
                        .bind(0,billDetail.getBill().getIdbill())
                        .bind(1,billDetail.getIdbilldetail())
                        .bind(2,billDetail.getProduct().getIdproduct())
                        .bind(3,billDetail.getSize().getIdsize())
                        .bind(4,billDetail.getQuantity())
                        .bind(5,billDetail.getPrice())
                        .execute();
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public List<BillDetail> selectAll() throws SQLException {
        return List.of();
    }

    @Override
    public boolean update(BillDetail billDetail) {
        return false;
    }
    public ArrayList<String> getAllIdBillDetail() throws SQLException{
        ArrayList<String> idbillDetails =(ArrayList<String>) JDBIConnector.getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT idbilldetail from bill_details").mapTo(String.class).list();
        });

        return idbillDetails;
    }
    public String newIdBill(ArrayList<String> arrstr){
        String rs ="";
        ArrayList<Integer> arrNum = new DAOCartDetail().returnArrayNum(arrstr, 6);
        int number = new DAOCartDetail().getNumRandom(arrNum);
        rs = "billdt"+number;
        return rs;
    }
    public List<BillDetail> getListBillDetailByIdBill(String idBill){
        List<BillDetail> billDetails = new ArrayList<>();
        try {
            Connection conn = JDBCConnector.getConnection();
            String sql = "SELECT * FROM bill_details WHERE idbill = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setObject(1,idBill);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String idbill= rs.getString("idbill");
                String idbilldetail = rs.getString("idbilldetail");
                String idProduct = rs.getString("idproduct");
                String idSize = rs.getString("idsize");
                int quantity = rs.getInt("quantity");
                int price = rs.getInt("price");
                Bill bill = new DAOBill().getBillByID(idbill);
                Product product = new DAOProduct().getProductByID(idProduct);
                Size size = new DAOSize().getSizeByID(idSize);
               BillDetail billDetail = new BillDetail(idbilldetail,bill,product,size,quantity,price);

                billDetails.add(billDetail);
            }
            JDBCConnector.closeConnection(conn, stmt, rs);
        }catch (Exception e){
            e.printStackTrace();
        }
        return billDetails;
    }

}
