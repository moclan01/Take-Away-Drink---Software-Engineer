package controller;

import DB.DAOProduct;
import DB.DAOType;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Account;
import model.Product;
import model.Type;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/admin")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)
public class AdminController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String UPLOAD_DRRECTORY
            = "C:\\Users\\ADMIN\\OneDrive - st.hcmuaf.edu.vn\\Desktop\\Take-Away-Drink---Software-Engineer\\Take_away_drink\\Take_away_drink\\src\\main\\webapp\\images";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        HttpSession session = request.getSession(true);


        String hanhDong = request.getParameter("hanhDong");
        if (hanhDong == null) {
            hanhDong = "ADMIN";
        }

        if (hanhDong.equals("ADD")) {
            try {
                ADD(request, response);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            if (hanhDong.equals("DELETE")) {
                DELETE(request, response);
            } else {
                if (hanhDong.equals("ADMIN")) {
                    try {
                        ADMIN(request, response);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

    public void ADD(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String name = request.getParameter("name");
        String price = request.getParameter("price");
        int priceInt = Integer.parseInt(price);

        String typeStr = request.getParameter("type");
        String describe = request.getParameter("describe");


        File uploadDir = new File(UPLOAD_DRRECTORY);
//			nơi lưu trữ ảnh nếu chưa tồn tại thì tạo nó
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        Part part = request.getPart("photo");
//			lấy ra file name p6.png
        String fileName = part.getSubmittedFileName();

        String realPath = request.getServletContext().getRealPath("/images/product");

        // part.write(UPLOAD_DRRECTORY + File.separator + fileName);
        part.write(realPath + File.separator + fileName);

       DAOProduct sanPhamDAO = new DAOProduct();

        DAOType daoType = new DAOType();
      Type type =  daoType.getTypeByID(typeStr);
        System.out.println(typeStr);


        Product sanPham = new Product("",type,name, priceInt, describe, fileName);
        sanPhamDAO.insert(sanPham);
        response.sendRedirect("admin?hanhDong=ADMIN");
    }

//	xóa sản phầm

    public void DELETE(HttpServletRequest request, HttpServletResponse response) throws IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String idproduct = request.getParameter("pid");

        DAOProduct productDAO = new DAOProduct();

        productDAO.deleteByIdProduct(idproduct);
        response.sendRedirect("admin?hanhDong=ADMIN");

    }

//	TRANG ADMIN

    public void ADMIN(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
//		render sản phẩm
        HttpSession session = request.getSession();
        DAOProduct sanPhamDAO = new DAOProduct();
        List<Product> listAmin = sanPhamDAO.selectAll();

        session.setAttribute("listP", listAmin);
        Account acc = (Account) session.getAttribute("user");
        String url ="";
        if(acc.getRole().equalsIgnoreCase("admin")){
            url = "/admin.jsp";
        }else {
            url = "/403-error.jsp";
        }

        request.getRequestDispatcher(url).forward(request, response);

    }

}
