package controller;

import DB.DAOCart;
import DB.DaoUser;

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
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/SignUpController")

public class SignUpController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean checkUsername = false;
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String specialCharacters = "!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~";
        HttpSession session = request.getSession();
        for (int i = 0; i < username.length(); i++) {
            if (specialCharacters.contains(String.valueOf(username.charAt(i))) || Character.isWhitespace(username.charAt(i))) {
                checkUsername = true;

            }
        }
        if(checkUsername){
            request.setAttribute("baoLoi","Username không được chứa kí tự đặc biệt và khoảng trắng");
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/sign_up.jsp");
            rd.forward(request,response);
        }else {
            DaoUser daoUser = new DaoUser();
            DAOCart daoCart = new DAOCart();
            try {
                ArrayList<String> usernameList = daoUser.getAllUsernames();
                if(usernameList.contains(username)){
                    request.setAttribute("baoLoi","Username đã tồn tại");
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/sign_up.jsp");
                    rd.forward(request,response);

                }else{
                    Pattern pattern = Pattern.compile("^(?=.*[a-zA-Z])(?=.*[0-9]).{8,16}$");
                    Matcher matcher = pattern.matcher(password);
                    if (matcher.matches()) {
                        Account user = new Account(username,email,password);
                        daoUser.insert(user);
                        Cart cart = new Cart("",user);
                        daoCart.insert(cart);
                        session.setAttribute("user",user);
                        session.setAttribute("cart",cart);
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
                        rd.forward(request,response);
                    } else {
                        request.setAttribute("baoLoi","Password không hợp lệ. Password phải có độ dài lớn hơn 8 và nhỏ hơn 16, có kí tự đặc biệt và số ");
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/sign_up.jsp");
                        rd.forward(request,response);
                    }

                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }



    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


}
