package controller;

import DB.DAOCartDetail;
import DB.DAOCartDetailTopping;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/DeleteCartDetailInCart")

public class DeleteCartDetailInCart extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idcartdetail = req.getParameter("idcartdetail");

        DAOCartDetail daoCartDetail = new DAOCartDetail();
        DAOCartDetailTopping daoCartDetailTopping = new DAOCartDetailTopping();

        daoCartDetailTopping.deleteToppingByIdCartDetail(idcartdetail);
        daoCartDetail.deleteByIdCartDetail(idcartdetail);
    }
}
