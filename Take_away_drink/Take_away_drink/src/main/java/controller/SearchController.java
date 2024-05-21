package controller;

import DB.DaoUser;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Account;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/SearchController")
public class SearchController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String str = req.getParameter("genre");

        DaoUser daouser = new DaoUser();
        try {
            Account user = daouser.getUserByUsername(str);
            PrintWriter out = resp.getWriter();

            out.println("<table id=\"tablefind\" style=\"width: 100%\">\r\n"
                    +"<thead>\r\n<tr><th>Name</th>\r\n<th>Nickname</th> </tr></thead>\r\n" +
                    "<tbody>\r\n <tr>\r\n <td>\r\n" +user.getUsername() +"</td>\r\n <td>\r\n"+user.getPassword()+ "</td> </tr></tbody>"
                    +                    "</table>");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
