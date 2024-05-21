package controller;

import DB.DAOCart;
import DB.JDBIConnector;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import model.Cart;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();
        JDBIConnector dao = new JDBIConnector();
        try {
            Account user = dao.getUser(username,password);
            if(user!=null){
                DAOCart daoCart = new DAOCart();
                Cart cart = daoCart.getCartByUsername(user.getUsername());

                if(cart == null){
                    cart = new Cart("",user);
                    daoCart.insert(cart);
                }
                session.setAttribute("user",user);
                session.setAttribute("cart",cart);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
                rd.forward(req,resp);

            }else {
                req.setAttribute("baoLoi","Sai tên hoặc password");
               RequestDispatcher rd= getServletContext().getRequestDispatcher("/login.jsp");
               rd.forward(req,resp);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
