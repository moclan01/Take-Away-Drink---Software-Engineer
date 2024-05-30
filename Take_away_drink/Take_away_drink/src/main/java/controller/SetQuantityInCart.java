package controller;

import DB.DAOCart;
import DB.DAOCartDetail;
import DB.DAOProduct;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Cart;
import model.CartDetail;
import model.Product;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/SetQuantityInCart")

public class SetQuantityInCart extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String quantity = request.getParameter("quantity");
        int quantityNumber = Integer.parseInt(quantity);


        String idcartdetail = request.getParameter("idcartdetail");
        String username = request.getParameter("username");
        DAOCartDetail daoCartDetail = new DAOCartDetail();

        try {
            CartDetail cartDetail = daoCartDetail.getCartDetail(idcartdetail);
            int quantityCart = cartDetail.getQuantity();
            int price = cartDetail.getPrice()/quantityCart;
            int newPrice = price * quantityNumber;
            daoCartDetail.setQuantityCartDetail(quantityNumber,newPrice,idcartdetail);
            PrintWriter out = response.getWriter();
            out.println(quantity);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }






    }
}
