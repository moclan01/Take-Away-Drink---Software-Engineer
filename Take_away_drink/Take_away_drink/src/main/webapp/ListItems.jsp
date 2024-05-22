<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sản phẩm</title>
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
	<style><%@include file="css/listItems.css"%></style>

</head>
<body>
	<jsp:useBean id="sanpham" class="DB.DAOProduct">
	</jsp:useBean>
	<jsp:useBean id="hang" class="DB.DAOType">
	</jsp:useBean>
	<jsp:include page="component/header.jsp"></jsp:include>

	<jsp:useBean id="topping" class="DB.DAOTopping">
	</jsp:useBean>


	<div class="container mt-4">
		<div class="row">
			<!-- Menu left -->
			<jsp:include page="component/left.jsp"></jsp:include>
			<!-- End Menu left -->

			<!-- Slider and Products -->
			<div class="col-lg-9" style="width: 60%">
				<!-- Slider -->
				<div id="carouselExampleIndicators" class="carousel slide mb-4"
					data-bs-ride="true">
					<div class="carousel-indicators"></div>

				</div>
				<!-- Products -->
				<div class="row">
					<c:forEach var="item" items="${sanpham.selectAll()}">
						<c:set var="maHang" value="${item.getidtype()}" />
						<c:set var="mahangParam" value="${param.mahang}" />
						<c:if test="${empty param.mahang}">
							<div class="col-lg-4 col-md-6 mb-4">

								<div id="${item.idproduct}" class="card h-100" onclick="openPopup(this.id)">

									<a href="#"><img class="card-img-top" src="" alt=""></a>
									<div class="card-body">
										<a href="#"><img id="card-img" class="card-img-top" src="images/product/${item.srcIMG}" alt=""></a>
										<p class="card-title">
											<a href="Items?idItem=${item.idproduct}"  class="name-item">${item.name}</a>
										</p>
										<p class="cost">
											<fmt:formatNumber value="${item.price}" type="currency"
												currencySymbol="VND" />
										</p>

									</div>
									<div class="card-footer">
										<ul class="item-detail">
											<li> ${item.describe} </li>

										</ul>
									</div>
								</div>

							</div>
						</c:if>
						<c:if test="${maHang eq mahangParam}">
							<div class="col-lg-4 col-md-6 mb-4">
								<div class="card h-100">
									<a href="#"><img class="card-img-top" src="" alt=""></a>
									<div class="card-body">
									<a href="#"><img id="card-img" class="card-img-top" src="images/product/${item.srcIMG}" alt=""></a>
										<p class="card-title">
											<a href="Items?idItem=${item.idproduct}"  class="name-item">${item.name}</a>
										</p>
										<p class="cost">
											<fmt:formatNumber value="${item.price}" type="currency"
												currencySymbol="VND" />
										</p>

									</div>
									<div class="card-footer">
										<ul class="item-detail">
											<li> ${item.describe} </li>
										</ul>
									</div>
								</div>

							</div>
						</c:if>

					</c:forEach>
				</div>
				<!-- End Products -->
			</div>
			<!-- End Slider and Products -->
		</div>
	</div>
	<div id="myModal" class="modal">

		<!-- Modal content -->
		<div class="modal-content">
			<span class="close" onclick="closePopup()" >&times;</span>
			<div class="modal-detail"></div>
			<p id="result"></p>



		</div>
	</div>

	<%@include file="component/footer.jsp" %>

</body>
<script>
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
				 updateValue(idproduct)
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
				seValue: selectedValues

			},
			success: function(reponse) {
				$('.price-product').html(reponse);
			},
			error: function(xhr, status, error) {
				// Xử lý lỗi (nếu có)
				console.error("Lỗi: " + error);
			}
		});

		document.getElementById("result").innerHTML = "Selected gender: " + selectedSize;
	}
	function increasement() {
		var quantityElement = document.querySelector('.detail-quantity');
		var quantity = parseInt(quantityElement.textContent || quantityElement.innerText);
		var result = quantity + 1;
		var resultStr = result.toString();

		$('.detail-quantity').html(resultStr);
	}
	function decreasement(){
		var quantityElement = document.querySelector('.detail-quantity');
		var quantity = parseInt(quantityElement.textContent || quantityElement.innerText);
		var result = quantity - 1;

		if(result < 0){
			result = 0
		}
		$('.detail-quantity').html(result);
	}
	function selectDefaultRadioButton() {
		$('#m').prop('checked', true);

	}

	function addCartController(idproduct){
		var form = document.getElementById('myForm');
		var checkboxes = form.querySelectorAll('input[name="topping"]:checked');
		var selectedValues = [];
		checkboxes.forEach((checkbox) => {
			selectedValues.push(checkbox.value);
		});
		var selectedSize = document.querySelector('input[name="size"]:checked').value;
		$.ajax({
			url: "AddProductToCartController",
			type: "get",
			data: {
				idproduct: idproduct,
				idsize: selectedSize,
				seValue: selectedValues

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