package DB;

import model.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOBillDetailTopping extends AbsDao<BillDetailTopping> {
    @Override
    public boolean delete(BillDetailTopping billDetailTopping) {
        return false;
    }

    @Override
    public boolean insert(BillDetailTopping billDetailTopping) throws SQLException {
        try {
            JDBIConnector.getJdbi().useHandle(handle -> {
                handle.createUpdate("insert into bill_detail_topping(idbilldetail,idproduct,idtopping)" +
                                " values(?,?,?)")
                        .bind(0,billDetailTopping.getBillDetail().getIdbilldetail())
                        .bind(1,billDetailTopping.getProduct().getIdproduct())
                        .bind(2,billDetailTopping.getTopping().getIdtopping())

                        .execute();
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public List<BillDetailTopping> selectAll() throws SQLException {
        return List.of();
    }

    @Override
    public boolean update(BillDetailTopping billDetailTopping) {
        return false;
    }
    public static void main(String[] args) throws SQLException {
        List<CartDetail> cartDetails = new DAOCartDetail().getListProductByUsername("cart1001");
        Bill bill = new Bill("bill101",new Account("admin1","",""),null, 0, "String name",0,"");
        new DAOBill().insert(bill);
        for (CartDetail cartDetail : cartDetails) {
            BillDetail billDetail = new BillDetail(new DAOBillDetail().newIdBill(new DAOBillDetail().getAllIdBillDetail()), bill, cartDetail.getItem(), cartDetail.getSize(), cartDetail.getQuantity(),cartDetail.getPrice());
            new DAOBillDetail().insert(billDetail);
            List<CartDetailTopping> cartDetailToppings = new DAOCartDetailTopping().getListToppingByIdCartDetail(cartDetail.getIdcartdetail());
            for (CartDetailTopping cartDetailTopping : cartDetailToppings) {
                BillDetailTopping billDetailTopping = new BillDetailTopping(billDetail,cartDetailTopping.getProduct(),cartDetailTopping.getTopping());
                new DAOBillDetailTopping().insert(billDetailTopping);
            }

        }
    }
}
