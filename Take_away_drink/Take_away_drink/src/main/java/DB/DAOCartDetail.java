package DB;

import model.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DAOCartDetail extends AbsDao<CartDetail>{
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
    private ArrayList<Integer> returnArrayNum(ArrayList<String> arrStr) {
        ArrayList<Integer> arrNum = new ArrayList<>();
        for (String str : arrStr) {
            int num = removeString(str);
            arrNum.add(num);
        }
        return arrNum;

    }
    private int removeString(String str) {
        int rs;
        String newStr = str.substring(6);
        rs = Integer.parseInt(newStr);
        return rs;
    }

    private int getNumRandom(ArrayList<Integer> arrNum) {
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


        System.out.println(new DAOCartDetail().updateQuantity(2, cartDetail));

    }

}
