package controller;

import DB.DAOProduct;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Product;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/SetPriceWithQuantity")

public class SetPriceWithQuantity  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String quantity = req.getParameter("quantity");
        String idproduct = req.getParameter("idproduct");

        System.out.println(idproduct);
        DAOProduct daoProduct = new DAOProduct();
        try {
            Product product = daoProduct.getProductByID(idproduct);
            int price = product.getPrice() * Integer.parseInt(quantity);
            PrintWriter out = resp.getWriter();
            out.print(price);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
