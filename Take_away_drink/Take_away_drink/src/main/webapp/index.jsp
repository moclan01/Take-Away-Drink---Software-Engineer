<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Trang chủ</title>
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

    <style><%@include file="css/index.css"%></style>
</head>
<body>
<jsp:useBean id="sanpham" class="DB.DAOProduct">
</jsp:useBean>
<jsp:useBean id="hang" class="DB.DAOType">
</jsp:useBean>
<jsp:include page="component/header.jsp"></jsp:include>


<div class="card-car">
    <div class="picture">
        <img src="//bizweb.dktcdn.net/100/350/449/themes/894786/assets/slider_1.jpg?1676262438991"
             style="width: 100%; margin-bottom: 10px">
    </div>
    <div class="container">

        <div class="title">
            <h2>
                <span>Các loại sản phẩm</span>
            </h2>
        </div>
        <hr style="width:100%; size:5px; align:center; color: red">
        <div class="car-item">
            <div id="myCarousel" class="carousel slide" data-ride="carousel">
                <!-- Indicators -->
                <ul class="carousel-indicators">
                    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                    <li data-target="#myCarousel" data-slide-to="1"></li>
                    <li data-target="#myCarousel" data-slide-to="2"></li>
                </ul>
                <!-- Slides -->
                <div class="carousel-inner">

                    <div class="carousel-item active">
                        <ul>
                            <li><a href="ListItems.jsp">Tất cả</a></li>
                            <c:forEach var="hangItem" items="${hang.selectAll()}">

                                <li class="car-listHang"><a id="link-item"
                                                            href="index.jsp?mahang=${hangItem.idtype}">
                                        ${hangItem.nametype}</a></li>
                                <c:set var="mahangParam" value="${param.mahang}"/>

                            </c:forEach>

                        </ul>

                        <ul>
                            <c:if test="${empty param.mahang}">
                                <c:set var="mahangParam" value="type1"/>
                            </c:if>
                            <c:forEach var="item" items="${sanpham.getTop3(mahangParam)}">

                                <c:set var="maHang" value="${item.getidtype()}"/>
                                <c:if test="${maHang eq mahangParam}">
                                    <li class="item-li">
                                        <div class="card" style="width: 17rem;">
                                            <div class="card-body">
                                                <a href="Items?idItem=${item.idproduct}"><img class="card-img-top"
                                                                                              src="images/product/${item.srcIMG}"
                                                                                              alt=""></a>
                                                <a class="card-title" href="Items?idItem=${item.idproduct}"
                                                   id="card-title">${item.name}</a>
                                                <p class="cost">
                                                    <fmt:formatNumber value="${item.price}" type="currency"
                                                                      currencySymbol="VND"/>
                                                </p>
                                                <ul class="item-detail">
                                                    <li> ${item.describe} </li>

                                                </ul>
                                            </div>
                                        </div>
                                    </li>


                                </c:if>
                            </c:forEach>

                        </ul>
                    </div>
                    <div class="carousel-item">
                        <ul>
                            <li><a href="ListItems.jsp"> Tất cả</a></li>
                            <c:forEach var="hangItem" items="${hang.selectAll()}">

                                <li class="car-listHang"><a id="link-item"
                                                            href="index.jsp?mahang=${hangItem.idtype}">
                                        ${hangItem.nametype}</a></li>
                                <c:set var="mahangParam" value="${param.mahang}"/>

                            </c:forEach>

                        </ul>

                        <ul>
                            <c:if test="${empty param.mahang}">
                                <c:set var="mahangParam" value="type1"/>
                            </c:if>
                            <c:forEach var="item" items="${sanpham.getBottom3(mahangParam)}">

                                <c:set var="maHang" value="${item.getidtype()}"/>


                                <c:if test="${maHang eq mahangParam}">
                                    <li class="item-li">
                                        <div class="card" style="width: 17rem;">
                                            <div class="card-body">
                                                <a href="Items?idItem=${item.idproduct}"><img class="card-img-top"
                                                                                              src="images/product/${item.srcIMG}"
                                                                                              alt=""></a>
                                                <a class="card-title" href="Items?idItem=${item.idproduct}"
                                                   id="card-title">${item.name}</a>
                                                <p class="cost">
                                                    <fmt:formatNumber value="${item.price}" type="currency"
                                                                      currencySymbol="VND"/>
                                                </p>
                                                <ul class="item-detail">
                                                    <li> ${item.describe} </li>

                                                </ul>
                                            </div>
                                        </div>
                                    </li>


                                </c:if>
                            </c:forEach>

                        </ul>
                    </div>
                </div>
                <!-- Controls -->
                <a class="carousel-control-prev" href="#myCarousel"
                   data-slide="prev"> <span class="carousel-control-prev-icon"></span>
                </a> <a class="carousel-control-next" href="#myCarousel"
                        data-slide="next"> <span class="carousel-control-next-icon"></span>
            </a>
            </div>
        </div>
    </div>
    <section class="section-1">
        <section class="section_sync">
            <div class="container">
                <div class="row">
                    <div class="row-detail">
                        <div class="row-title">
                            <h2 class="h2">
                                <span>Trao đổi với chúng tôi</span>
                            </h2>
                        </div>
                        <div class="des_sync">
                            <p>
                                Với phương châm: “Đồng hành cùng lớn mạnh” và với mức giá dịch
                                vụ cạnh tranh, chúng tôi tin tưởng và rất hận hạnh được trở
                                thành đối tác mới và lâu dài của Quý khách. Dàn xe đời mới,
                                chất lượng cao của các hãng xe có thương hiệu toàn cầu chắc
                                chắn sẽ làm hài lòng Quý khách hàng <br> Chúng tôi chuyên
                                phục vụ cho các nhu cầu mua xe như tư vấn chọn lựa dựa trên đặc
                                điểm cá nhân và nhu cầu sử dụng của khách hàng, cung cấp thông
                                tin chi tiết về các tính năng kỹ thuật, an toàn và hiệu suất
                                của xe. Đồng thời, chúng tôi cam kết đem đến trải nghiệm mua xe
                                thoải mái và tin cậy bằng cách hỗ trợ trong quá trình kiểm tra
                                chất lượng xe và thương lượng giá để đảm bảo sự hài lòng tuyệt
                                đối cho khách hàng.
                            </p>
                        </div>
                        <a class="btn_main" href="Contact.jsp" title="Trao đổi ngay">Trao đổi
                            ngay</a>
                    </div>
                </div>
            </div>
        </section>
    </section>
    <section class="section-2">
        <section class="section_band">
            <div class="container">
                <div class="wrap_brand">
                    <div class="img-car">
                        <ul class="img-car-detail">
                            <%--								<li class="img-car-items"><img --%>
                            <%--									src="img/logo/logoBMW.jpg"--%>
                            <%--									alt="" width="240" height="125"></li>--%>
                            <%--								<li class="img-car-items" ><img--%>
                            <%--									src="img/logo/logoHyndai.jpg" --%>
                            <%--									alt="" width="150" height="120"></li>--%>
                            <%--								<li class="img-car-items"><img--%>
                            <%--									src="img/logo/logoMer.jpg"--%>
                            <%--									alt="" width="200" height="120"></li>--%>
                            <%--								<li class="img-car-items"><img--%>
                            <%--									src="img/logo/logoFord.jpg"--%>
                            <%--									alt="" width="200" height="120"></li>--%>

                        </ul>
                    </div>
                </div>
            </div>
        </section>
    </section>
    <script
            src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script
            src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script
            src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <!-- footer -->
    <%@include file="component/footer.jsp" %>
</body>
</html>