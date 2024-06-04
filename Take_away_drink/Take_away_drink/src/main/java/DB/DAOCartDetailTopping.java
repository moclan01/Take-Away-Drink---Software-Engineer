package DB;

import model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOCartDetailTopping extends  AbsDao<CartDetailTopping>{
    @Override
    public boolean insert(CartDetailTopping cartDetailTopping) throws SQLException {
        JDBIConnector.getJdbi().useHandle(handle -> {

            handle.createUpdate("Insert into cart_detail_toppings(idcartdetail,idcart, idproduct,idtopping) values(?,?,?,?)")
                    .bind(0,cartDetailTopping.getCartDetail().getIdcartdetail())
                    .bind(1,cartDetailTopping.getCart().getMaOrder())
                    .bind(2,cartDetailTopping.getProduct().getIdproduct())
                    .bind(3,cartDetailTopping.getTopping().getIdtopping())
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
    public List<CartDetailTopping> getListToppingByIdCartDetail(String idcartdetail){
        List<CartDetailTopping> toppingList = new ArrayList<>();
        try {

            Connection conn = JDBCConnector.getConnection();
            String sql = "SELECT * FROM cart_detail_toppings WHERE idcartdetail = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setObject(1,idcartdetail);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String idCartDetail = rs.getString("idcartdetail");
                String idCart = rs.getString("idcart");
                String idProduct = rs.getString("idproduct");
                String idtopping = rs.getString("idtopping");
              DAOCart daoCart  = new DAOCart();
              DAOProduct daoProduct = new DAOProduct();
              DAOTopping daoTopping = new DAOTopping();
                Cart cart = daoCart.getCartByID(idCart);
                Product product = daoProduct.getProductByID(idProduct);
                Topping topping = daoTopping.getToppingByID(idtopping);
                CartDetail cartDetail = new CartDetail(idCartDetail,cart,product,null,0,0);
                CartDetailTopping cartDetailTopping = new CartDetailTopping(cartDetail,cart,product,topping);
                toppingList.add(cartDetailTopping);
            }
            JDBCConnector.closeConnection(conn, stmt, rs);
        }catch (Exception e){
            e.printStackTrace();
        }
        return toppingList;
    }
    public String listToppingToString(String idcartdetail){
        List<CartDetailTopping> cartDetailToppings = getListToppingByIdCartDetail(idcartdetail);
        String str = "";
        for (int i = 0; i < cartDetailToppings.size(); i++) {
            if(i == cartDetailToppings.size()-1){
                str += cartDetailToppings.get(i).toString();
            }else{
                str+=cartDetailToppings.get(i).toString()+", ";

            }
        }
        return str;
    }
    public boolean deleteToppingByIdCartDetail(String idcartdetail){
        try {
            JDBIConnector.getJdbi().useHandle(handle -> {
                handle.createUpdate("delete from cart_detail_toppings where idcartdetail =?").bind(0,idcartdetail).execute();
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
    public static void main(String[] args) throws SQLException {
        Cart cart = new DAOCart().getCartByUsername("admin1");
        Product product = new DAOProduct().getProductByID("product1");
        Topping topping = new DAOTopping().getToppingByID("topping1");
        Size size = new DAOSize().getSizeByID("l");

       CartDetail cartDetail = new CartDetail("cartdt15",cart,product,size,2,2);

        System.out.println(new DAOCartDetailTopping().getListToppingByIdCartDetail("cartdt1001"));

    }

}
