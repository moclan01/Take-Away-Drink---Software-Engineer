package DB;

import model.*;
import org.jdbi.v3.core.Jdbi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DAOCartDetail extends AbsDao<CartDetail>{
    Jdbi jdbi;
    DAOProduct daoProduct;
    DAOSize daoSize;
    DAOCart daoCart;
    public DAOCartDetail() {
        this.jdbi = JDBCConnector.getInstance();
        daoProduct = new DAOProduct();
        daoSize = new DAOSize();
        daoCart = new DAOCart();
    }

    @Override
    public boolean insert(CartDetail cartDetail) throws SQLException {
        JDBIConnector.getJdbi().useHandle(handle -> {

            handle.createUpdate("Insert into cart_details(idcartdetail,idcart, idproduct,idsize,quantity,price) values(?,?,?,?,?,?)")
                    .bind(0,cartDetail.getIdcartdetail())
                    .bind(1,cartDetail.getCart().getMaOrder())
                    .bind(2,cartDetail.getItem().getIdproduct())
                    .bind(3,cartDetail.getSize().getIdsize())
                    .bind(4,cartDetail.getQuantity())
                    .bind(5,cartDetail.getPrice())
                    .execute();
        });
        return true;
    }


    @Override
    public boolean delete(CartDetail cartDetail) {
        try {
            JDBIConnector.getJdbi().useHandle(handle -> {
                handle.createUpdate("delete from cart_details where idcartdetail =?").bind(0,cartDetail.getIdcartdetail()).execute();
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public boolean deleteByIdCartDetail(String idcartdetail){
        try {
            JDBIConnector.getJdbi().useHandle(handle -> {
                handle.createUpdate("delete from cart_details where idcartdetail =?").bind(0,idcartdetail).execute();
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
    @Override
    public List<CartDetail> selectAll() throws SQLException {
        List<CartDetail> cartDetails = (List<CartDetail>) JDBIConnector.getJdbi().withHandle(handle -> {
            return handle.createQuery("select * from cart_details").mapToBean(CartDetail.class).list();
        });
        return cartDetails;
    }
    public CartDetail getCartDetail(String idcartdetail) throws SQLException {
        for (CartDetail cartDetail: selectAll()){
            if(cartDetail.getIdcartdetail().equalsIgnoreCase(idcartdetail)){
                return cartDetail;
            }
        }
        return null;
    }

    @Override
    public boolean update(CartDetail cartDetail) {
        return false;
    }

    public ArrayList<String> getAllIdCartDetail() throws SQLException{
        ArrayList<String> idcartdetails =(ArrayList<String>) JDBIConnector.getJdbi().withHandle(handle -> {
            return handle.createQuery("select idcartdetail from cart_details").mapTo(String.class).list();
        });

        return idcartdetails;
    }
    public boolean updateQuantity(int number, CartDetail cartDetail) throws SQLException {
        JDBIConnector.getJdbi().useHandle(handle -> {

            handle.createUpdate("update cart_details set quantity = quantity + ?  where idcartdetail = ?")
                    .bind(0,number)
                    .bind(1,cartDetail.getIdcartdetail())

                    .execute();
        });
        return true;
    }
    public String totalPrice(String idcart) throws SQLException {
        String rs ="";
        int total = 0;
        for (CartDetail cartDetail: getListProductByUsername(idcart)){
            total += cartDetail.getPrice();
        }

        rs = String.valueOf(total);
        return rs;
    }
    public ArrayList<Integer> returnArrayNum(ArrayList<String> arrStr) {
        ArrayList<Integer> arrNum = new ArrayList<>();
        for (String str : arrStr) {
            int num = removeString(str);
            arrNum.add(num);
        }
        return arrNum;

    }
    public int removeString(String str) {
        int rs;
        String newStr = str.substring(6);
        rs = Integer.parseInt(newStr);
        return rs;
    }

    public int getNumRandom(ArrayList<Integer> arrNum) {
        Random random = new Random();

        int rs;
        while (true) {
            // Random số
            int randomNumber = random.nextInt(100) + 1;

            // Kiểm tra xem số có nằm trong mảng không
            boolean isInArray = false;
            for (int number : arrNum) {
                if (number == randomNumber) {
                    isInArray = true;
                    break;
                }
            }

            // Nếu số không nằm trong mảng, in ra và thoát khỏi vòng lặp
            if (!isInArray) {
                rs = randomNumber;
                break;
            }
        }
        return rs;
    }

    public boolean setQuantityCartDetail(int quantity,int price, String idcartdetail){
        try (Connection connection = JDBCConnector.getConnection()) {
            String sql = "UPDATE cart_details SET quantity = ?, price =? WHERE idcartdetail = ?";
            System.out.println(sql);
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                preparedStatement.setInt(1, quantity);
                preparedStatement.setLong(2, price);
                preparedStatement.setString(3, idcartdetail);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    public List<CartDetail> getListProductByUsername(String idcart){
        List<CartDetail> productList = new ArrayList<>();
        try {
            Connection conn = JDBCConnector.getConnection();
            String sql = "SELECT * FROM cart_details WHERE idcart = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setObject(1,idcart);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String idCartDetail = rs.getString("idcartdetail");
                String idCart = rs.getString("idcart");
                String idProduct = rs.getString("idproduct");
                String idSize = rs.getString("idsize");
                int quantity = rs.getInt("quantity");
                int price = rs.getInt("price");
                Cart cart = daoCart.getCartByID(idCart);
                Product product = daoProduct.getProductByID(idProduct);
                Size size = daoSize.getSizeByID(idSize);
                CartDetail cartDetail = new CartDetail(idCartDetail,cart,product,size,quantity,price);
                productList.add(cartDetail);
            }
            JDBCConnector.closeConnection(conn, stmt, rs);
        }catch (Exception e){
            e.printStackTrace();
        }
        return productList;
    }
    public static void main(String[] args) throws SQLException {
//
        System.out.println(new DAOCartDetail().totalPrice("cart1001"));

    }

}
