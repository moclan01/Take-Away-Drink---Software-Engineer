<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<style><%@include file="/css/left.css"%></style>
<jsp:useBean id="sanpham" class="DB.DAOProduct">
	</jsp:useBean>
<jsp:useBean id="hang" class="DB.DAOType">
</jsp:useBean>
<div class="col-lg-3" >
	<div class="list-group" style="position: fixed; width: 280px " >

	<div class="nav-item dropdown" style="height: 0px;margin-bottom: 40px"><a
						class="nav-link"  role="button"
						 aria-expanded="false" id="xuatxu" href="ListItems.jsp" style="border: solid 1px #ebebeb;"> Tất cả sản phẩm </a>
						</div>

	<div class="nav-item dropdown" style="height: 0px;margin-bottom: 40px"><a
						class="nav-link dropdown-toggle"  role="button"
						data-bs-toggle="dropdown" aria-expanded="false" id="xuatxu" style="border: solid 1px #ebebeb;"> Loại sản phẩm </a>
						<ul class="dropdown-menu">
							<c:forEach var="item" items="${hang.selectAll()}">
							<p>
								<li><a class="link-item" href="ListItems.jsp?mahang=${item.idtype}">${item.getNametype()}</a></li>
							</p>
							
						</c:forEach>
						</ul></div>
	</div>
</div>
