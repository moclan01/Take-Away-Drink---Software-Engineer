package DB;

import model.*;

import java.sql.SQLException;
import java.util.List;

public class DAOCartDetailTopping extends  AbsDao<CartDetailTopping>{
    @Override
    public boolean insert(CartDetailTopping cartDetailTopping) throws SQLException {
        JDBIConnector.getJdbi().useHandle(handle -> {

            handle.createUpdate("Insert into cart_detail_toppings(idcart, idproduct,idtopping) values(?,?,?)")
                    .bind(0,cartDetailTopping.getCart().getMaOrder())
                    .bind(1,cartDetailTopping.getProduct().getIdproduct())
                    .bind(2,cartDetailTopping.getTopping().getIdtopping())
                    .execute();
        });
        return true;
    }

    @Override
    public boolean delete(CartDetailTopping cartDetailTopping) {
        return false;
    }

    @Override
    public List<CartDetailTopping> selectAll() throws SQLException {
        return List.of();
    }

    @Override
    public boolean update(CartDetailTopping cartDetailTopping) {
        return false;
    }

    public static void main(String[] args) throws SQLException {
        Cart cart = new DAOCart().getCartByUsername("admin1");
        Product product = new DAOProduct().getProductByID("product1");
        Topping topping = new DAOTopping().getToppingByID("topping1");

        CartDetailTopping cartDetailTopping = new CartDetailTopping(cart,product,topping);
        System.out.println(new DAOCartDetailTopping().insert(cartDetailTopping));
    }

}
