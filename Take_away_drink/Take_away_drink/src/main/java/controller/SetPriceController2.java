package controller;

import DB.DAOProduct;
import DB.DAOTopping;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Product;
import model.Topping;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/SetPriceController2")

public class SetPriceController2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DAOTopping daoTopping = new DAOTopping();
        DAOProduct daoProduct = new DAOProduct();
     //   String idproduct = request.getParameter("idproduct");

        String[] listTopping = request.getParameterValues("selectValues");
        System.out.println(listTopping);
        List<Topping> toppings = new ArrayList<Topping>();

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
//        try {
//            Product product= daoProduct.getProductByID(idproduct);
//            if (toppings.size()>0){
//                for (Topping topping : toppings) {
//                    product.setPrice(product.getPrice() + topping.getPricetopping());
//
//                }
//            }
//            String price = product.getPrice()+"";
//
//            PrintWriter out = response.getWriter();
//            out.print(price);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DAOTopping daoTopping = new DAOTopping();
        DAOProduct daoProduct = new DAOProduct();
       // String idproduct = req.getParameter("idproduct");

        String[] selectValues = req.getParameterValues("seValue");
        System.out.println(selectValues);
//        for (String value : selectValues) {
//            System.out.println("Giá trị được chọn từ JavaScript: " + value);
//        }
        List<Topping> toppings = new ArrayList<Topping>();
    }
}
