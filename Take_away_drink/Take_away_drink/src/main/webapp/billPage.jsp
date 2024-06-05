<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 6/5/2024
  Time: 10:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css"
            rel="stylesheet">
    <script
            src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js"
            integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT"
            crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <title>Title</title>
    <style>
        <%@include file="css/cart.css" %>
    </style>
</head>
<body>
<jsp:include page="component/header.jsp"></jsp:include>

<div class="container-cart">
    <table id="cart-table">
        <thead>
        <tr class="product-row-title">
            <th>Tên</th>
            <th>Size</th>
            <th>Giá</th>
            <th>Số lượng</th>
           <th>Thời gian</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${billDetails}">
            <tr id="product-row-${item.getIdbilldetail()}" class="product-row">

                <td>
                    <p>${item.getNameProduct()}</p>
                </td>
                <td>${item.getNameSize()}</td>
                <td>
                    <div >${item.getPrice()}</div>
                </td>
                <td>
                    <div class="quantity">
                        <div >${item.getQuantity()}</div>
                    </div>
                </td>
                <td>
                    <div >${item.getDate()}</div>
                </td>

            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
