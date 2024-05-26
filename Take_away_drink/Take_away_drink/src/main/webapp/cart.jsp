<%--
  Created by IntelliJ IDEA.
  User: Moc Lan
  Date: 5/25/2024
  Time: 12:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Giỏ hàng</title>
    <style>
        <%@include file="css/cart.css" %>
    </style>
</head>
<body>

<jsp:useBean id="product" class="DB.DAOProduct"></jsp:useBean>
<input type="hidden" name="makhachhang" value="${user.username}"/>
<input type="hidden" name="maorder" value="${order.getMaOrder()}"/>
<div>
    <table class="cart-table">
        <thead>
        <tr class="product-row-title">
            <th>Hình ảnh</th>
            <th>Tên</th>
            <th>Size</th>
            <th>Giá</th>
            <th>Số lượng</th>
            <th>Topping</th>
            <th>Tổng</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${gioHang}">
            <tr id="product-row-${item.getIdcartdetail()}" class="product-row">
                <td>
                    <div class="image-container">
                        <img class="img" src="<c:url value="images/product/${item.getItem().getSrcIMG()}" />">
                    </div>
                </td>
                <td>
                    <p>${item.getItem().getName()}</p>
                </td>
                <td>${item.getSize().getNamesize()}</td>
                <td>${item.getItem().getPrice()}</td>
                <td>
                    <div class="quantity">
                        <button>-</button>
                        <div class="quantity-value">${item.getQuantity()}</div>
                        <button>+</button>
                    </div>
                </td>
                <td>
                    <div class="topping">
                        chưa có
                    </div>
                </td>
                <td>
                    <div class="total-price">
                        chưa tính
                    </div>
                </td>
                <td>
                    <button class="btn-dlt">Xóa</button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
