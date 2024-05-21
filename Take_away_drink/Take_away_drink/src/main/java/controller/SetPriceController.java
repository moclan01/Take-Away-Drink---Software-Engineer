package controller;

import DB.DAOProduct;
import DB.DAOSize;
import DB.DAOTopping;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Product;
import model.Size;
import model.Topping;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/SetPriceController")
public class SetPriceController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DAOProduct daoProduct = new DAOProduct();
        DAOSize daoSize = new DAOSize();
        DAOTopping daoTopping = new DAOTopping();
        String idproduct = request.getParameter("idproduct");
        String idsize = request.getParameter("idsize");

        try {
            Product product= daoProduct.getProductByID(idproduct);
           Size size = daoSize.getSizeByID(idsize);

            product.setPrice(product.getPrice() + size.getPrice());
           String price = product.getPrice()+"";
            PrintWriter out = response.getWriter();
           out.print(price);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

}
