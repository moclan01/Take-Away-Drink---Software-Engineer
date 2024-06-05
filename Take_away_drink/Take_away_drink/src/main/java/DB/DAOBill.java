package DB;

import model.*;
import org.jdbi.v3.core.Handle;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DAOBill extends AbsDao<Bill>{

    @Override
    public boolean insert(Bill bill) throws SQLException {
        try {
            JDBIConnector.getJdbi().useHandle(handle -> {
                handle.createUpdate("insert into bills(idbill,username,date,totalprice,name,phone,address) values(?,?,?,?,?,?,?)")
                        .bind(0,bill.getIdbill())
                        .bind(1,bill.getAccount().getUsername())
                        .bind(2,bill.getDate())
                        .bind(3,bill.getTotalprice())
                        .bind(4,bill.getName())
                        .bind(5,bill.getPhone())
                        .bind(6,bill.getAddress())
                        .execute();
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public boolean delete(Bill bill) {
        return false;
    }

    @Override
    public List<Bill> selectAll() throws SQLException {
        return List.of();
    }

    @Override
    public boolean update(Bill bill) {
        return false;
    }
    public ArrayList<String> getAllIdBill() throws SQLException{
        ArrayList<String> idbills =(ArrayList<String>) JDBIConnector.getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT idbill from bills").mapTo(String.class).list();
        });

        return idbills;
    }
    public String newIdBill(ArrayList<String> arrstr){
        String rs ="";
        ArrayList<Integer> arrNum = new DAOCartDetail().returnArrayNum(arrstr, 4);
        int number = new DAOCartDetail().getNumRandom(arrNum);
        rs = "bill"+number;
        return rs;
    }
    public Bill getBillByID(String idbill) throws SQLException {
        Bill bill =null;
        try (Handle handle = JDBIConnector.getJdbi().open()) {
            String sql = "SELECT * FROM bills WHERE idbill = ?";
            bill = handle.createQuery(sql)
                    .bind(0, idbill)
                    .mapToBean(Bill.class)
                    .findFirst()
                    .orElse(null);
            return bill;
        }
    }
    public List<Bill> getListBillByUserName(String username){
        List<Bill> billList = new ArrayList<>();
        try {
            Connection conn = JDBCConnector.getConnection();
            String sql = "SELECT * FROM bills WHERE username = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setObject(1,username);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String idbill = rs.getString("idbill");
                String userName = rs.getString("username");
                Timestamp date = rs.getTimestamp("date");
                int price = rs.getInt("totalprice");
                Bill bill = new Bill();
                bill.setIdbill(idbill);
                bill.setTotalprice(price);
                bill.setDate(date);

                billList.add(bill);
            }
            JDBCConnector.closeConnection(conn, stmt, rs);
        }catch (Exception e){
            e.printStackTrace();
        }
        return billList;
    }
    public static void main(String[] args) throws SQLException {
        List<Bill> bills = new  DAOBill().getListBillByUserName("admin1");
        List<BillDetail> billDetails = new ArrayList<>();
        for (Bill bill : bills) {
            List<BillDetail> billDetails1 = new DAOBillDetail().getListBillDetailByIdBill(bill.getIdbill());
            billDetails.addAll(billDetails1);
        }
        System.out.println(billDetails);
    }
}
