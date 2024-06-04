package DB;

import model.Account;
import model.Bill;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
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
    public static void main(String[] args) throws SQLException {

    }
}
