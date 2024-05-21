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

import javax.servlet.Servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@WebServlet("/AddPopupController")

public class AddPopupController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse  response) throws ServletException, IOException {
        String searchInput = request.getParameter("searchInput");
        try {
            Product product = new DAOProduct().getProductByID(searchInput);
            PrintWriter out = response.getWriter();
            DAOSize daoSize = new DAOSize();
            DAOTopping daoTopping = new DAOTopping();
            List<Size> sizes = daoSize.selectAll();
            List<Topping> toppings = daoTopping.selectAll();
            out.println("<div class=\"model-deltail\">\r\n"
                    + "<div class=\"top-body\"> \r\n"
                    + "<div class=\"left-body\"> \r\n"
                    + "<img id=\"card-img-popup\" class=\"card-img-top\" src=\"images/product/"+product.getSrcIMG()+"\" alt=\"\">"
                    +"</div>"
                    +  "<div class=\"right-body\"> \r\n"
                    + "<div class=\"name-product\">"+product.getName()+"\r\n"
                    +"</div>"
                    + "<div class=\"price-product\">"+product.getPrice()+"\r\n"
                    +"</div>"
                    + "<div class=\"describe-product\">"+product.getDescribe()+"\r\n"
                    +"</div>"
                    +"</div>"
                    +"</div>"
                    +"<div class=\"bottom-body\">\r\n"
                    +"<div class=\"size-body\">\r\n"
                    +"<div class=\"size-body\">\r\n"
                    +"<div class=\"title\">Chọn size </div>\r\n"
                    +"<div class=\"size-detail\">\r\n"
);
            for (Size size : sizes) {
                out.println(" <input type=\"radio\" id=\""+size.getIdsize()+"\" name=\"size\" value=\""+size.getIdsize()+"\" " +
                        "onchange=\"updateValue('" + product.getIdproduct()+ "')\" >"
                        +"<label class=\"size\" for=\""+size.getIdsize()+"\">"+size.getNamesize()+"</label><br>\n"

                );
            }

            out.println(

                    "</div>"
                            +"</div>"
                            +"</div>"
                            +"<div class=\"title\">Chọn topping </div>\r\n"
                            +"<div class=\"topping-body\">\r\n");

          //  out.println(  "<form id = \"myForm\">");
//            for (Topping topping : toppings) {
//                out.println(
//                       " <input type=\"checkbox\" id=\""+topping.getIdtopping()+"\" name=\"topping\" value=\""+topping.getIdtopping()+"\" >"
//                                +"<label class=\"topping\" for=\""+topping.getIdtopping()+"\">"+topping.getNametopping()+"</label>" +
//                               "<label class=\"topping\" for=\""+topping.getIdtopping()+"\">+"+topping.getPricetopping()+" đ</label>"+
//                               "<br>\n"
//
//
//                );
//            }

            out.print(
//                    "</form>"+
//                            "<button onclick=\"updateValue2()\"></button>\n"+
                            "</div>"
                            +"</div>"
                            +"</div>");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
