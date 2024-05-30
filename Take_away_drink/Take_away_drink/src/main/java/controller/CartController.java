package controller;

import DB.DAOCart;
import DB.DAOCartDetail;
import DB.DAOCartDetailTopping;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Cart;
import model.CartDetail;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/MKh")
public class CartController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        try {
            DAOCart orderDAO = new DAOCart();
            DAOCartDetail daoCartDetail = new DAOCartDetail();
            DAOCartDetailTopping cartDetailTopping = new DAOCartDetailTopping();
            Cart order = null;
            order = orderDAO.getCartByUsername(username);

        if(order == null) {

            RequestDispatcher red = getServletContext().getRequestDispatcher("/cart.jsp");
            red.forward(request,response);
        }else {
            String maorder = order.getMaOrder();
            List<CartDetail> orderDetail = daoCartDetail.getListProductByUsername(maorder);
            String totalPrice = daoCartDetail.totalPrice(maorder);
            request.setAttribute("gioHang",orderDetail);
            request.setAttribute("order", order);
           request.setAttribute("totalPrice", totalPrice);
            System.out.println("Price: "+totalPrice);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/cart.jsp");
            rd.forward(request, response);
        }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
