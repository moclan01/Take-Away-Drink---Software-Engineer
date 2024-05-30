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
    <script src="https://www.google.com/recaptcha/api.js"></script>

    <title>Giỏ hàng</title>
    <style>
        <%@include file="css/cart.css" %>
    </style>
</head>
<body>

<jsp:useBean id="product" class="DB.DAOProduct"></jsp:useBean>
<jsp:useBean id="toppinglist" class="DB.DAOCartDetailTopping"></jsp:useBean>
<jsp:useBean id="cartdetail" class="DB.DAOCartDetail"></jsp:useBean>
<input type="hidden" name="makhachhang" value="${user.username}"/>
<input type="hidden" name="maorder" value="${order.getMaOrder()}"/>

<div>
    <table id="cart-table">
        <thead>
        <tr class="product-row-title">
            <th>Hình ảnh</th>
            <th>Tên</th>
            <th>Size</th>
            <th>Giá</th>
            <th>Số lượng</th>
            <th>Topping</th>
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
                <td>  <div class="price-value_${item.getIdcartdetail()}">${item.getPrice()}</div></td>
                <td>
                    <div class="quantity">
                        <button onclick="decreasement('${item.getIdcartdetail()}')">-</button>
                        <div class="quantity-value_${item.getIdcartdetail()}">${item.getQuantity()}</div>
                        <button onclick="increasement('${item.getIdcartdetail()}')">+</button>
                    </div>
                </td>
                <td>
                    <div class="topping" >
                        <p>${toppinglist.listToppingToString(item.getIdcartdetail())}</p>
                    </div>
                </td>

                <td>
                    <button class="btn-dlt"  onclick="deleteCartDetail('${item.getIdcartdetail()}')">Xóa</button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<div class="btn-pay">
    <a href="UpgradeSelectorController?price=${cartdetail.totalPrice(order.getMaOrder())}">
        <button class="inner_button">Buy</button>
    </a>
</div>
</body>

<script>
    var test = false;


    function updateValue(idcartdetail) {

        var quantityElement = document.querySelector('.quantity-value_' + idcartdetail);
        var quantity = parseInt(quantityElement.textContent || quantityElement.innerText);

        $.ajax({
            url: "SetQuantityInCart",
            type: "get",
            data: {
                idcartdetail: idcartdetail,
                quantity: quantity
            },
            success: function(reponse) {
                $('.quantity-value_' + idcartdetail).html(reponse);

            },
            error: function(xhr, status, error) {
                // Xử lý lỗi (nếu có)
                console.error("Lỗi: " + error);
            }
        });

    }
    function updateValue2(idcartdetail) {

        var quantityElement = document.querySelector('.quantity-value_' + idcartdetail);
        var quantity = parseInt(quantityElement.textContent || quantityElement.innerText);

        $.ajax({
            url: "SetPriceInCart",
            type: "get",
            data: {
                idcartdetail: idcartdetail,
                quantity: quantity
            },
            success: function(reponse) {

                $('.price-value_' + idcartdetail).html(reponse);
            },
            error: function(xhr, status, error) {
                // Xử lý lỗi (nếu có)
                console.error("Lỗi: " + error);
            }
        });

    }

    function increasement(idcartdetail) {

        test = true;
        var quantityElement = document.querySelector('.quantity-value_' + idcartdetail);
        var quantity = parseInt(quantityElement.textContent || quantityElement.innerText);
        var result = quantity + 1;
        var resultStr = result.toString();


        $('.quantity-value_' + idcartdetail).html(resultStr);
        updateValue(idcartdetail);
        updateValue2(idcartdetail);


    }
    function decreasement(idcartdetail){

        test = true;
        var quantityElement = document.querySelector('.quantity-value_' + idcartdetail);

        var quantity = parseInt(quantityElement.textContent || quantityElement.innerText);
        var result = quantity - 1;

        if(result < 0){
            result = 0
        }
        var resultStr = result.toString();

        $('.quantity-value_' + idcartdetail).html(resultStr);
        updateValue(idcartdetail);
        updateValue2(idcartdetail)

    }
    function deleteCartDetail(idcartdetail){

        console.log('.product-row-' + idcartdetail);
        $.ajax({
            url: "DeleteCartDetailInCart",
            type: "get",
            data: {
                idcartdetail: idcartdetail
            },
            success: function(reponse) {
                $('#product-row-' + idcartdetail).remove();
                console.log("Success delete");

            },
            error: function(xhr, status, error) {
                // Xử lý lỗi (nếu có)
                console.error("Lỗi: " + error);
            }
        });
    }
</script>


</html>
