<%@ page import="model.Account" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    	<style><%@include file="/css/header.css"%></style>
    
 
 
<!-- Navbar -->
<jsp:useBean id="hang" class="DB.DAOType">
	</jsp:useBean>
	<nav class="navbar navbar-expand-lg bg-light">
		<div class="container-fluid">
			<a class="navbar-brand" href="index.jsp"> <img
				src="images/imgIndex/logo2.png"	 width="150" height="100">
			</a>
		
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item" id="trang-chu"><a class="nav-link active"
						aria-current="page" href="index.jsp">Trang chủ</a></li>

					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" role="button"
						data-bs-toggle="dropdown" aria-expanded="false"> Sản phẩm </a>
						<ul class="dropdown-menu">
							<li><a class="dropdown-item" href="ListItems.jsp">Tất cả sản phẩm</a></li>
							<li><hr class="dropdown-divider"></li>
							<c:forEach var="item" items="${hang.selectAll()}">
							<p>
								<li><a class="dropdown-item" href="ListItems.jsp?mahang=${item.idtype }">${item.getNametype()}</a></li>
							</p>
							
						</c:forEach>
						
						</ul></li>

					</li>
				</ul>
				<form class="d-flex" role="search" style="position: relative;display: block;float: right;">
				
					<%
									Object obj = session.getAttribute("user");
													Account khachHang = null;
													if (obj!=null)
														khachHang = (Account) obj;
													
													if(khachHang==null){
									%>
						<a class="btn btn-primary" style="white-space: nowrap;margin-left: 10px" href="login.jsp">
							Đăng nhập
						</a>
					<%} else { %>
					
					<ul class="navbar-nav me-auto mb-2 mb-lg-0 bg-infor ">
						<li class="nav-item dropdown dropstart"><a
							class="nav-link dropdown-toggle" href="#" role="button"
							data-bs-toggle="dropdown" aria-expanded="false"> ${user.username}</a>
							<ul class="dropdown-menu">
									<c:set var="role" value="${user.role}" />
									<c:if test="${role == 'admin' }">
								<li><a class="dropdown-item" href="admin" class="cart" id="cart">admin</a></li>
								<li><hr class="dropdown-divider"></li>
									</c:if>
								<li><a class="dropdown-item" href="MKh?username=${user.username}" class="cart" id="cart">Giỏ hàng</a></li>
								<li><hr class="dropdown-divider"></li>
								<li><a class="dropdown-item" href="DestroySession">Thoát tài khoản</a></li>
							</ul></li>
					</ul>				
						
					<% } %>
				</form>
			</div>
		</div>
	</nav>
	<!-- End Navbar -->