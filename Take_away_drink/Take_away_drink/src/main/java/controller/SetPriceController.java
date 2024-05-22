package controller;

import DB.DAOCartDetail;
import DB.DAOProduct;
import DB.DAOSize;
import DB.DAOTopping;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/SetPriceController")
public class SetPriceController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        DAOProduct daoProduct = new DAOProduct();
        DAOSize daoSize = new DAOSize();
        DAOTopping daoTopping = new DAOTopping();
        DAOCartDetail daoCartDetail = new DAOCartDetail();

        Account account = (Account) session.getAttribute("user");
        Cart cart = (Cart) session.getAttribute("cart");

        CartDetail cartDetail = new CartDetail();
        cartDetail.setCart(cart);
        String[] listTopping = request.getParameterValues("seValue[]");
        List<Topping> toppings = new ArrayList<Topping>();

        String idproduct = request.getParameter("idproduct");
        String idsize = request.getParameter("idsize");

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
            if (toppings.size()>0){
                for (Topping topping : toppings) {
                    product.setPrice(product.getPrice() + topping.getPricetopping());

                }
            }
            product.setPrice(product.getPrice() + size.getPrice());
           cartDetail.setItem(product);
           cartDetail.setPrice(product.getPrice());
           cartDetail.setSize(product.getSize());
           cartDetail.setQuantity(2);
            String price = product.getPrice()+"";

            PrintWriter out = response.getWriter();
           out.print(price);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

}
