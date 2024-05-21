package DB;

import model.Account;
import model.Cart;
import org.jdbi.v3.core.Handle;

import java.sql.SQLException;
import java.util.List;

public class DAOCart extends  AbsDao<Cart>{

    @Override
    public boolean insert(Cart cart) throws SQLException {
        JDBIConnector.getJdbi().useHandle(handle -> {

            handle.createUpdate("Insert  into cart(idcart, iduser) values(?,?)")
                    .bind(0,cart.getMaOrder()).bind(1,cart.getUsername()).execute();
        });
        return true;
    }

    @Override
    public boolean delete(Cart cart) {
        return false;
    }

    @Override
    public List<Cart> selectAll() throws SQLException {
        List<Cart> carts = (List<Cart>) JDBIConnector.getJdbi().withHandle(handle -> {
            return handle.createQuery("select * from cart").mapToBean(Cart.class).list();
        });
        return carts;
    }

    @Override
    public boolean update(Cart cart) {
        return false;
    }

    public Cart getCartByUsername(String iduser) throws SQLException {
        Cart cart =null;
        try (Handle handle = JDBIConnector.getJdbi().open()) {
            String sql = "SELECT * FROM cart WHERE iduser = ?";
             cart = handle.createQuery(sql)
                    .bind(0, iduser)
                    .mapToBean(Cart.class)
                    .findFirst()
                    .orElse(null);

            if (cart != null) {
                Account customer = new  DaoUser().getUserByUsername(iduser);
                cart.setAccount(customer);
            }

            return cart;
        }
    }
    public static void main(String[] args) throws SQLException {
        Account user = new DaoUser().getUserByUsername("easylove");
        System.out.println(user);
        Cart cart = new Cart("",user);
        System.out.print(new DAOCart().getCartByUsername("easylove"));


    }
}
