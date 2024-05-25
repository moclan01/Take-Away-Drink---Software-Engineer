package controller;

import DB.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import model.Cart;
import model.CartDetail;
import model.Topping;
import model.Product;
import model.Size;
import model.CartDetailTopping;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@WebServlet("/AddProductToCartController")
public class AddProductToCartController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        DAOProduct daoProduct = new DAOProduct();
        DAOSize daoSize = new DAOSize();
        DAOTopping daoTopping = new DAOTopping();
        DAOCartDetail daoCartDetail = new DAOCartDetail();
        DAOCartDetailTopping daoCartDetailTopping = new DAOCartDetailTopping();

        Account account = (Account) session.getAttribute("user");
        Cart cart = (Cart) session.getAttribute("cart");

        CartDetail cartDetail = new CartDetail();

        ArrayList<String> listIdCartDetail = null;
        try {
            listIdCartDetail = new DAOCartDetail().getAllIdCartDetail();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        ArrayList<Integer> listNumber = returnArrayNum(listIdCartDetail);
        int numberIdCartDetail = getNumRandom(listNumber);
        String idCartDetail = "cartdt" + numberIdCartDetail;
        cartDetail.setIdcartdetail(idCartDetail);
        cartDetail.setCart(cart);

        String[] listTopping = request.getParameterValues("seValue[]");
        List<Topping> toppings = new ArrayList<Topping>();

        String idproduct = request.getParameter("idproduct");
        String idsize = request.getParameter("idsize");

        String quantity = request.getParameter("quantity");
        int numberQuantity = Integer.parseInt(quantity);
        System.out.println(listTopping);
        if(listTopping != null){
            for (String toppingname: listTopping){
                Topping topping = null;
                try {
                    topping = daoTopping.getToppingByID(toppingname);

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                toppings.add(topping);

            }
        }
        try {
            Product product= daoProduct.getProductByID(idproduct);
            Size size = daoSize.getSizeByID(idsize);

            product.setSize(size);
            if (toppings.size() > 0){
                for (Topping topping : toppings) {
                    product.setPrice(product.getPrice() + topping.getPricetopping());
                }
            }else {
                product.setPrice(product.getPrice());
            }
            product.setPrice(product.getPrice() + size.getPrice());
            cartDetail.setItem(product);
            cartDetail.setPrice(product.getPrice());
            cartDetail.setSize(product.getSize());
            cartDetail.setQuantity(numberQuantity);

            System.out.println(cartDetail);
            daoCartDetail.insert(cartDetail);
            if (!toppings.isEmpty()){
                for (Topping topping : toppings) {
                    CartDetailTopping cartDetailTopping = new CartDetailTopping(cartDetail.getIdcartdetail(),cart,product,topping);
                    daoCartDetailTopping.insert(cartDetailTopping);
                    System.out.println(cartDetailTopping);
                }
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
}
