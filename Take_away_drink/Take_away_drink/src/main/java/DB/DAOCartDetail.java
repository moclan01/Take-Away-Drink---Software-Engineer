package DB;

import model.*;

import java.sql.SQLException;
import java.util.List;

public class DAOCartDetail extends AbsDao<CartDetail>{
    @Override
    public boolean insert(CartDetail cartDetail) throws SQLException {
        JDBIConnector.getJdbi().useHandle(handle -> {

            handle.createUpdate("Insert into cart_details(idcart, idproduct,idsize,quantity) values(?,?,?,?)")
                    .bind(0,cartDetail.getCart().getMaOrder())
                    .bind(1,cartDetail.getItem().getIdproduct())
                    .bind(2,cartDetail.getSize().getIdsize())
                    .bind(3,cartDetail.getQuantity())
                    .execute();
        });
        return true;
    }

    @Override
    public boolean delete(CartDetail cartDetail) {
        return false;
    }

    @Override
    public List<CartDetail> selectAll() throws SQLException {
        return List.of();
    }

    @Override
    public boolean update(CartDetail cartDetail) {
        return false;
    }

    public static void main(String[] args) throws SQLException {
        Cart cart = new DAOCart().getCartByUsername("admin1");
        Account account = new DaoUser().getUserByUsername("admin1");
        Size size = new DAOSize().getSizeByID("l");
        Product product = new DAOProduct().getProductByID("product1");

        CartDetail cartDetail = new CartDetail(cart,product ,product.getPrice(), 2, size);
        System.out.println(new DAOCartDetail().insert(cartDetail));

    }

}
