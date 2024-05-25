package controller;

import DB.DAOCart;
import DB.DAOCartDetail;
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
            Cart order = null;
            order = orderDAO.getCartByUsername(username);

        if(order == null) {

            RequestDispatcher red = getServletContext().getRequestDispatcher("/cart.jsp");
            red.forward(request,response);
        }else {
            String maorder = order.getMaOrder();
            List<CartDetail> orderDetail = daoCartDetail.getListProductByUsername(maorder);
            request.setAttribute("gioHang",orderDetail);
            request.setAttribute("order", order);
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
