package controller;

import DB.DAOCartDetail;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.CartDetail;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/SetPriceInCart")

public class SetPriceInCart  extends HttpServlet {
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
            PrintWriter out = response.getWriter();
            out.println(newPrice);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
