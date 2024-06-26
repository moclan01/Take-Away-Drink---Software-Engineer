<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 5/5/2024
  Time: 5:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT"
            crossorigin="anonymous">
    <script
            src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"
            integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3"
            crossorigin="anonymous"></script>
    <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.min.js"
            integrity="sha384-7VPbUDkoPSGFnVtYi0QogXtr74QeVeeIs99Qfg5YCF+TidwNdjvaKZX19NZ/e6oz"
            crossorigin="anonymous"></script>
    <style><%@include file="css/signin.css"%></style>
    <style>
        .red {
            color: red;
        }
    </style>
</head>
<body>
<main class="form-signin w-100 m-auto">
    <form class="text-center" action="LoginController" method="GET">
        <h1 class="h3 mb-3 fw-normal">ĐĂNG NHẬP</h1>
        <%
            String baoLoi = request.getAttribute("baoLoi")+"";
            if(baoLoi.equals("null")){
                baoLoi = "";
            }
        %>
        <div class="text-center"><span class="red"><%=baoLoi %></span></div>

        <div class="form-floating">
            <input type="text" class="form-control" id="username"
                   placeholder="Tên đăng nhập" name="username" > <label for="username">Username</label>
        </div>
        <div class="form-floating">
            <input type="password" class="form-control" id="password"
                   placeholder="Mật khẩu" name="password"> <label for="password">Mật khẩu</label>
        </div>
        <button class="w-100 btn btn-lg btn-primary" type="submit">Đăng nhập</button>
        <a href="sign_up.jsp" style="text-decoration: none;">Đăng ký tài khoản mới</a> </br>
        <a href="index.jsp" style="text-decoration: none;">Trở về trang chủ</a>
        </br>
    </form>
</main>
</body>

</html>
