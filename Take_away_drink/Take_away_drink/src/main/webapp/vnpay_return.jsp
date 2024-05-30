<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.nio.charset.StandardCharsets" %>
<%@ page import="controller.Config" %>
<%@ page import="model.Account" %><%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 5/30/2024
  Time: 1:29 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.io.IOException" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <title>KẾT QUẢ THANH TOÁN</title>
    <!-- Bootstrap core CSS -->
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css"
            rel="stylesheet">
    <script
            src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js"
            integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT"
            crossorigin="anonymous"></script>

    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>

<style>
    #back {
        display: flex;
        align-items: center;
        justify-content: center;
        font-size: 20px;
        border-radius: 5px;
        width: 120px;
        height: 40px;
        margin-left: 50px;
        border: none;
        background-color: white;
    }

    #back:hover {
        background: #f0f0f0;
        transition: 0.2s;
    }

    a {
        text-decoration: none;
        color: black;
    }

    a:hover {
        text-decoration: none;
        color: black;
    }
</style>

<body>
<%
    //Begin process return from VNPAY
    Map fields = new HashMap();
    for (Enumeration params = request.getParameterNames(); params.hasMoreElements();) {
        String fieldName = URLEncoder.encode((String) params.nextElement(), StandardCharsets.US_ASCII.toString());
        String fieldValue = URLEncoder.encode(request.getParameter(fieldName), StandardCharsets.US_ASCII.toString());
        if ((fieldValue != null) && (fieldValue.length() > 0)) {
            fields.put(fieldName, fieldValue);
        }
    }

    String vnp_SecureHash = request.getParameter("vnp_SecureHash");
    if (fields.containsKey("vnp_SecureHashType")) {
        fields.remove("vnp_SecureHashType");
    }
    if (fields.containsKey("vnp_SecureHash")) {
        fields.remove("vnp_SecureHash");
    }
    String signValue = Config.hashAllFields(fields);
%>
<!--Begin display -->
<div class="container">
    <div class="header clearfix">
        <h3 class="text-muted">KẾT QUẢ THANH TOÁN</h3>
    </div>
    <div class="table-responsive">
        <div class="form-group">
            <label>Mã giao dịch thanh toán:</label> <label><%=request.getParameter("vnp_TxnRef")%></label>
        </div>
        <div class="form-group">
            <label>Số tiền:</label> <label><%=request.getParameter("vnp_Amount")%></label>
        </div>
        <div class="form-group">
            <label>Mô tả giao dịch:</label> <label><%=request.getParameter("vnp_OrderInfo")%></label>
        </div>
        <div class="form-group">
            <label>Mã lỗi thanh toán:</label> <label><%=request.getParameter("vnp_ResponseCode")%></label>
        </div>
        <div class="form-group">
            <label>Mã giao dịch tại CTT VNPAY-QR:</label> <label><%=request.getParameter("vnp_TransactionNo")%></label>
        </div>
        <div class="form-group">
            <label>Mã ngân hàng thanh toán:</label> <label><%=request.getParameter("vnp_BankCode")%></label>
        </div>
        <div class="form-group">
            <label>Thời gian thanh toán:</label> <label><%=request.getParameter("vnp_PayDate")%></label>
        </div>

        <div class="form-group">
            <label>Tình trạng giao dịch:</label>
            <label>

                <%
                    if (signValue.equals(vnp_SecureHash)) {
                %>

                <%
                    Account acc = (Account) session.getAttribute("account");
                    if (acc != null) {
                %>
                <%
                } else {
                %>
                <%
                    }
                    String amount = session.getAttribute("amount").toString();
                %>

                <%
                    if ("00".equals(request.getParameter("vnp_TransactionStatus"))) {
                %>
                <%= "Success<br>" %>
                <%
                } else {
                %>
                <%= "Transaction Failed<br>" %>
                <%
                    }
                } else {
                %>
                <%= "Invalid Secure Hash<br>" %>
                <%
                    }
                %>
        </label>
        </div>
    </div>
    <p>&nbsp;</p>
    <footer class="footer">
        <p>&copy; VNPAY 2020</p>
    </footer>
</div>
</body>
</html>
