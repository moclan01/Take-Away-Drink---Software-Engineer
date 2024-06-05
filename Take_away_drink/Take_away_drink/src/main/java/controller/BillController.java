package controller;

import DB.DAOBill;
import DB.DAOBillDetail;
import DB.DAOBillDetailTopping;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Bill;
import model.BillDetail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/BillController")
public class BillController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        DAOBill daoBill = new DAOBill();
        DAOBillDetail daoBillDetail = new DAOBillDetail();
        DAOBillDetailTopping daoBillDetailTopping = new DAOBillDetailTopping();

        List<Bill> bills = daoBill.getListBillByUserName(username);
        List<BillDetail> billDetails = new ArrayList<>();
        for (Bill bill : bills) {
            List<BillDetail> billDetails1 = daoBillDetail.getListBillDetailByIdBill(bill.getIdbill());
            billDetails.addAll(billDetails1);
        }


        request.setAttribute("bills", bills);
        request.setAttribute("billDetails", billDetails);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/billPage.jsp");
        rd.forward(request, response);


    }
}
