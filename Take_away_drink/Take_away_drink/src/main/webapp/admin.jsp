<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 6/4/2024
  Time: 8:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
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
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script
            src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script
            src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <%
        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                + request.getContextPath();
    %>

    <style><%@include file="css/admin.css"%></style>

</head>
<body>
<jsp:include page="component/header.jsp"></jsp:include>
<c:set var="role" value="${user.role}" />
<c:if test="${role == 'admin' }">
    <div class="container">

        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-6">
                        <h2>
                            Manage <b>Product</b>
                        </h2>
                    </div>
                    <div class="col-sm-6">
                        <a href="#addEmployeeModal" class="btn btn-success"
                           data-toggle="modal"> <i class="material-icons">&#xE147;</i> <span>Add
								New Product</span></a>
                    </div>
                </div>
            </div>
            <table class="table table-striped table-hover">
                <thead>
                <tr>

                    <th>ID</th>
                    <th>Name</th>
                    <th>Img</th>
                    <th>Price</th>
                    <th>Describe</th>
                    <th>Actions</th>

                </tr>
                </thead>
                <tbody>
                <c:forEach items="${listP}" var="o">
                    <tr>

                        <td>${o.idproduct}</td>
                        <td>${o.name}</td>
                        <td><img class="product_img"
                                 src="images/product/${o.srcIMG}"></td>
                        <td><fmt:formatNumber value="${o.price}" type="currency"
                                              currencySymbol="VND" /></td>
                        <td>${o.describe}</td>

                        <td>

                            <a
                                    href="admin?pid=${o.idproduct}&hanhDong=DELETE"
                                    class="delete" data-toggle="modal"><i class="material-icons"
                                                                          data-toggle="tooltip" title="Delete">&#xE872;</i></a></td>
                    </tr>
                    </tr>
                </c:forEach>

                </tbody>
            </table>


        </div>



    </div>



    <div id="addEmployeeModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content" style="width: 552px">

                <form method="post" class="form-group"
                      enctype="multipart/form-data">
                    <input type="hidden" name="hanhDong" value="ADD">
                    <div class="modal-header">
                        <h4 class="modal-title">Add Product</h4>
                        <button type="button" class="close" data-dismiss="modal"
                                aria-hidden="true">&times;</button>
                    </div>
                    <div class="modal-body row">

                        <div class="col-md-6">

                            <div class="form-group mb-2">
                                <label for="first name">Name</label> <input type="text" id="first name"
                                                                                    class="form-control" name="name" size="50" />
                            </div>

                            <div class="form-group mb-2">
                                <label for="first name">Price</label> <input
                                    id="first name"
                                    type="text" class="form-control" name="price"
                                    size="50" />
                            </div>




                        </div>
                        <div class="col-md-6">
                            <div class="form-group mb-2">
                                <label for="first name">Describe</label> <input type="text" id="first name"
                                                                             class="form-control" name="describe" size="50" />
                            </div>

                            <div class="form-group mb-2">
                                <label for="first name">Type</label> <input type="text" id="first name"
                                                                                class="form-control" name="type" size="50" />
                            </div>



                            <div class="form-group mb-2">
                               <label for="Profile Photo">Profile Photo:</label>
                                <input id="Profile Photo"
                                        type="file" name="photo" size="50" />
                            </div>

                        </div>

                    </div>

                    <div class="modal-footer">
                        <input type="button" class="btn btn-default" data-dismiss="modal"
                               value="Cancel"> <input type="submit"
                                                      class="btn btn-success" value="Add">
                    </div>

                </form>




            </div>
        </div>
    </div>
</c:if>

</body>
</html>
