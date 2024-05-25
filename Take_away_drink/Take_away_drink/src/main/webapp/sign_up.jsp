<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 5/5/2024
  Time: 5:56 PM
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
    <form class="text-center" action="SignUpController" method="GET">
        <h1 class="h3 mb-3 fw-normal">ĐĂNG KÝ</h1>
        <%
            String baoLoi = request.getAttribute("baoLoi")+"";
            if(baoLoi.equals("null")){
                baoLoi = "";
            }
        %>
        <div class="text-center"><span class="red"><%=baoLoi %></span></div>

        <div class="form-floating">
            <input type="text" class="form-control" id="username"
                   placeholder="Username" name="username" oninput="validateUsername()"> <label for="username">Username</label>
            <span id="usernameError" class="red"></span>

        </div>
        <div class="form-floating">
            <input type="email" class="form-control" id="email"
                   placeholder="Email" name="email"> <label for="email">Email</label>

        </div>
        <div class="form-floating">
            <input type="password" class="form-control" id="password"
                   placeholder="Password" name="password" oninput="validatePassword()"> <label for="password">Mật khẩu</label>
            <span id="passwordError" class="red"></span>
            <p onclick="showPassword()">Show Password</p>
        </div>
        <button class="w-100 btn btn-lg btn-primary" type="submit">Đăng ký</button>
        </br>
    </form>
</main>
</body>
<script>
    var booleanTest ;
    var booleanCheck;
    function showPassword() {
        var passwordField = document.getElementById("password");
        if (passwordField.type === "password") {
            passwordField.type = "text";
        } else {
            passwordField.type = "password";
        }
    }
    function  validatePassword() {
        var passwordError = document.getElementById("passwordError");

        var password = document.getElementById("password");
        var passwordString = password.value;
        var hasUppercase = /[A-Z]/.test(passwordString);
        var numberPattern = /\d/;
        var regex = /[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]/;

        if (passwordString.length <8) {
            passwordError.textContent = "Độ dài phải hơn 8 kí tự";
        }else if(passwordString.length >16){
            passwordError.textContent ="Độ dài phải ít hơn 16 kí tự";
        }
        else {
            if (!hasUppercase) {
                passwordError.textContent = "Mật khẩu phải chứa ít nhất một kí tự viết hoa.";
            } else {
                if (!regex.test(passwordString)) {
                    passwordError.textContent = "Mật khẩu phải chứa 1 kí tự đặc biệt";
                } else {
                    if (!numberPattern.test(passwordString)) {
                        passwordError.textContent = "Mật khẩu phải chứa 1 số";

                    } else {
                        passwordError.textContent = "";

                    }


                }
            }


        }
    }
    function validateUsername() {
        var number=0;
        var username = document.getElementById("username");
        var usernameString = username.value;
        var usernameError = document.getElementById("usernameError");


        var containsSpecialChars = /[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]/.test(usernameString);
        var containsWhitespace = /\s/.test(usernameString);


        if (!containsSpecialChars && !containsWhitespace) {
            usernameError.textContent = "";
        } else if (containsSpecialChars){
            usernameError.textContent = "Username không được chứa kí tự đặc biệt";
        }else if(containsWhitespace){
            usernameError.textContent = "Username không được chứa khoảng trắng";
        }



    }
</script>
</html>
