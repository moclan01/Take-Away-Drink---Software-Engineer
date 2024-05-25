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
        return false;
    }

    @Override
    public List<CartDetail> selectAll() throws SQLException {
        List<CartDetail> cartDetails = (List<CartDetail>) JDBIConnector.getJdbi().withHandle(handle -> {
            return handle.createQuery("select * from cart_details").mapToBean(CartDetail.class).list();
        });
        return cartDetails;
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

    public List<CartDetail> getListProductByUsername(String idcart){
        List<CartDetail> productList = new ArrayList<>();
        try {
//            productList = JDBIConnector.getJdbi().withHandle(handle ->
//                handle.createQuery("SELECT cart_details.* FROM cart_details inner join cart on cart_details.idcart = cart.idcart WHERE cart.iduser = ?")
//                        .bind(0,username)
//                        .mapToBean(CartDetail.class)
//                        .list()
//            ) ;
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
        Cart cart = new DAOCart().getCartByUsername("admin1");
        Account account = new DaoUser().getUserByUsername("admin1");
        Size size = new DAOSize().getSizeByID("l");
        Product product = new DAOProduct().getProductByID("product1");

        ArrayList<String> listIdCartDetail = new DAOCartDetail().getAllIdCartDetail();

        ArrayList<Integer> listNumber = new DAOCartDetail().returnArrayNum(listIdCartDetail);
        int numberIdCartDetail = new DAOCartDetail().getNumRandom(listNumber);
        String idCartDetail = "cartdt" + numberIdCartDetail;
        CartDetail cartDetail = new CartDetail("cartdt01",cart,product,size,2,2);

        List<CartDetail> productList = new DAOCartDetail().getListProductByUsername("cart1");
        for (CartDetail product1 : productList){

            System.out.println(product1.getItem());
        }
        System.out.println(productList.size());
//        System.out.println(new DAOCartDetail().updateQuantity(2, cartDetail));

    }

}
