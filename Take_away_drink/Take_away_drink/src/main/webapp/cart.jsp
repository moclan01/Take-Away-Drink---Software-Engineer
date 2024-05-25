<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Moc Lan
  Date: 5/25/2024
  Time: 12:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <title>Giỏ hàng</title>
</head>
<body>
<jsp:useBean id="product" class="DB.DAOProduct"></jsp:useBean>
<%--<jsp:include page="component/header.jsp"></jsp:include>--%>
<input type="hidden" name="makhachhang" value="${user.username}" />
<input type="hidden" name="maorder" value="${order.getMaOrder()}" />
<c:forEach var="item" items="${gioHang}">
    <div>
        ${item.idcartdetail}
    </div>
</c:forEach>
<%--<%@include file="component/footer.jsp" %>--%>
</body>
</html>
