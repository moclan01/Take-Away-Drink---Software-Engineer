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
        <img  src="images/imgIndex/img_index1.png"
             style="width: 60%; margin-bottom: 10px;height: 500px; margin-left: 20%">
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
                                        <div  class="card" style="width: 17rem;">
                                            <div id="${item.idproduct}" class="card-body"  onclick="openPopup(this.id)">
                                               <img class="card-img-top"  src="images/product/${item.srcIMG}"
                                                                                              alt="">
                                                <div class="card-title"
                                                   id="card-title">${item.name}</div>
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
                                        <div  class="card" style="width: 17rem;" >
                                            <div id="${item.idproduct}" class="card-body" onclick="openPopup(this.id)">
                                               <img class="card-img-top"  src="images/product/${item.srcIMG}" alt="">
                                                <div class="card-title"
                                                   id="card-title">${item.name}</div>
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
                                <span>Đôi lời của chúng tôi</span>
                            </h2>
                        </div>
                        <div class="des_sync">
                            <p>
                                Luôn tâm huyết với việc khai thác nguồn nông sản Việt Nam để tạo ra những ly thức uống tươi ngon,
                                an toàn và giàu giá trị dinh dưỡng.
                                Mang trong mình lòng đam mê và khát vọng xây dựng một thương hiệu trà sữa thuần Việt, mang đậm hương vị quê hương.
                               Xuu Tea tin rằng thưởng thức một ly trà sữa được pha chế từ trà Mộc Châu, trân châu từ sắn dây Nghệ An hay mứt dâu tằm từ Đà Lạt sẽ là những trải nghiệm hoàn toàn khác biệt và tuyệt vời nhất cho những khách hàng của mình.
                            </p>
                        </div>

                    </div>
                </div>
            </div>
        </section>
    </section>

    <div id="myModal" class="modal">

        <!-- Modal content -->
        <div class="modal-content">
            <span class="close" onclick="closePopup()" >&times;</span>
            <div class="modal-detail"></div>
            <p id="result"></p>



        </div>
    </div>
    <script
            src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script
            src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script
            src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <!-- footer -->
    <%@include file="component/footer.jsp" %>
</body>
<script>

    var test = false;

    var modal = document.getElementById("myModal");
    function openPopup(idproduct){
        var form = document.getElementById('myForm');

        $.ajax({
            url: "AddPopupController",
            type: "get",
            data: {
                searchInput: idproduct

            },
            success: function(data) {
                var content = document.querySelector(".modal-detail");
                content.innerHTML = data;
                selectDefaultRadioButton();
                updateValue(idproduct);
                changePriceWithQuantity(idproduct)
            },
            error: function(xhr, status, error) {
                // Xử lý lỗi (nếu có)
                console.error("Lỗi: " + error);
            }
        });
        modal.style.display = "block";


    }
    function closePopup(){
        modal.style.display = "none";
    }

    function updateValue(idproduct) {
        var quantityElement = document.querySelector('.detail-quantity');
        var quantity = parseInt(quantityElement.textContent || quantityElement.innerText);
        var form = document.getElementById('myForm');
        var checkboxes = form.querySelectorAll('input[name="topping"]:checked');
        var selectedValues = [];
        checkboxes.forEach((checkbox) => {
            selectedValues.push(checkbox.value);
        });
        var selectedSize = document.querySelector('input[name="size"]:checked').value;
        $.ajax({
            url: "SetPriceController",
            type: "get",
            data: {
                idproduct: idproduct,
                idsize: selectedSize,
                seValue: selectedValues,
                quantity: quantity

            },
            success: function(reponse) {
                $('.price-product').html(reponse);
            },
            error: function(xhr, status, error) {
                // Xử lý lỗi (nếu có)
                console.error("Lỗi: " + error);
            }
        });

    }
    function increasement(idproduct) {

        test = true;
        var quantityElement = document.querySelector('.detail-quantity');
        var quantity = parseInt(quantityElement.textContent || quantityElement.innerText);
        var result = quantity + 1;



        var resultStr = result.toString();
        $('.detail-quantity').html(resultStr);
        updateValue(idproduct)

    }
    function decreasement(idproduct){

        test = true;
        var quantityElement = document.querySelector('.detail-quantity');
        var quantity = parseInt(quantityElement.textContent || quantityElement.innerText);
        var result = quantity - 1;

        if(result < 0){
            result = 0
        }
        var resultStr = result.toString();
        $('.detail-quantity').html(resultStr);
        updateValue(idproduct)

    }
    function selectDefaultRadioButton() {
        $('#m').prop('checked', true);

    }

    function changePriceWithQuantity(idproduct){
        var quantityElement = document.querySelector('.detail-quantity');
        var quantity = parseInt(quantityElement.textContent || quantityElement.innerText);

       if(test == true){
           $.ajax({
               url: "SetPriceWithQuantity",
               type: "get",
               data: {
                   idproduct: idproduct,
                   quantity: quantity

               },
               success: function(reponse) {
                   $('.price-product').html(reponse);

               },
               error: function(xhr, status, error) {
                   // Xử lý lỗi (nếu có)
                   console.error("Lỗi: " + error);
               }
           });

       }

    }
    function addCartController(idproduct){
        var quantityElement = document.querySelector('.detail-quantity');
        var quantity = parseInt(quantityElement.textContent || quantityElement.innerText);

        var form = document.getElementById('myForm');
        var checkboxes = form.querySelectorAll('input[name="topping"]:checked');
        var selectedValues = [];
        checkboxes.forEach((checkbox) => {
            selectedValues.push(checkbox.value);
        });
        var selectedSize = document.querySelector('input[name="size"]:checked').value;
        $.ajax({
            url: "AddProductToCartController",
            type: "GET",
            data: {
                idproduct: idproduct,
                idsize: selectedSize,
                seValue: selectedValues,
                quantity: quantity

            },
            success: function(reponse) {
                console.log("Success")
            },
            error: function(xhr, status, error) {
                // Xử lý lỗi (nếu có)
                console.error("Lỗi: " + error);
            }
        });
    }
    function selectDefaultCheckbox() {
        var defaultCheckbox = document.getElementById('topping1');
        if (defaultCheckbox) {
            defaultCheckbox.checked = true;
        }
    }

</script>
</html>