package DB;

import model.*;

import java.sql.SQLException;
import java.sql.Timestamp;
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

}
