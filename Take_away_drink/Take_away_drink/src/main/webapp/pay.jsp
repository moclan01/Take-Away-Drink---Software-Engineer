<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 5/30/2024
  Time: 12:57 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>Title</title>

    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
    <link href="https://pay.vnpay.vn/lib/vnpay/vnpay.css" rel="stylesheet" />
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

</head>
<style>
    body {
        width: 100%;
        height:100%;
        display: grid;
        justify-content: center;
        margin: 0px;

    }

    #container {

        margin-top: 30px;
        width: 700px;
        height: 100%;
        display:grid;
        border-radius: 10px;
        padding: 10px 20px;
        box-shadow: 0 4px 8px rgba(33, 38, 44, .16);
    }

    #selector_header {
        display: flex;
        justify-content: space-between;
        align-items: baseline;
    }

    #selector_body {

        width: 100%;
        display: grid;
        grid-template-rows: auto auto;
        row-gap: 30px;
    }

    #selector_body .payment_option:first-child {
        border-radius: 10px 10px 0px 0px;
        border: 1px solid black;
    }

    #selector_body .payment_option {

        border-radius:  0px 0px 10px 10px;
        border: 1px solid black;
    }

    .script_payment {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin: 20px;
        margin-bottom: 0px;
    ;
    }

    p {
        font-size: 16px;
    }

    i {
        font-size: 40px;
        cursor: pointer;
    }

    .payment_box {
        width: 90%;
        margin-left: 3.5%;
        border: 1px solid black;
        margin-bottom: 10px;
        padding: 10px;
        border-radius: 10px;

    }
    .payment_box:hover{
        background-color: rgba(0, 0, 0, 0.1);
        box-shadow: 0 4px 8px rgba(33, 38, 44, .16);
    }

    button {
        cursor: pointer;
    }


</style>
<body>
<div id="container">


    <div id="selector_body">
        <form  action="ajaxServlet"
               id="frmCreateOrder1" method="post">
            <input type="hidden" name = "price" value="${priceTotal}">
        <div class="mb-3">
            <label for="fullName" class="form-label"><i class="fa-solid fa-user"></i>
            </label> <input placeholder="Họ và tên" type="text" class="form-control"
                            id="fullName" name="fullName" required="required" >
        </div>
        <div class="mb-3">
            <label for="phone" class="form-label"><i class="fa-solid fa-phone"></i>
            </label> <input placeholder="Số điện thoại" type="text" class="form-control"
                            id="phone" name="phone" required="required" >
        </div>
        <div class="mb-3">
            <label for="address" class="form-label"><i class="fas fa-map-marker-alt"></i>
            </label> <input placeholder="Địa chỉ" type="text" class="form-control"
                            id="address" name="address" required="required" >
        </div>
        <h2>Pay once in advance, not automatically renewed.</h2>

        <div id="paymment_options">



                <div class="payment_option">

                    <div class="script_payment">
                        <div>
                            <h2>${priceTotal}₫ </h2>

                        </div>
                        <button type="submit" class="payment_box" id="pay_1">
                            <p>Pay with VNPay</p>
                            <i></i>
                        </button>
                    </div>
                </div>



        </div>
        </form>
    </div>
</div>
<script src="https://pay.vnpay.vn/lib/vnpay/vnpay.min.js"></script>
<script type="text/javascript">
    $("#frmCreateOrder1").submit(function() {
        var postData = $("#frmCreateOrder1").serialize();
        var submitUrl = $("#frmCreateOrder1").attr("action");
        $.ajax({
            type : "POST",
            url : submitUrl,
            data : postData,
            dataType : 'JSON',
            success : function(x) {
                if (x.code === '00') {
                    if (window.vnpay) {
                        vnpay.open({
                            width : 768,
                            height : 600,
                            url : x.data
                        });
                    } else {
                        location.href = x.data;
                    }
                    return false;
                } else {
                    alert(x.Message);
                }
            }
        });
        return false;
    });


</script>
<script type="text/javascript">
    function showPaymentMethod(id, iconElement) {
        var payment_box = document.getElementById(id);
        if (payment_box.style.display == "grid") {
            payment_box.style.display = "none";
            iconElement.classList.add("bi-arrow-down-square");
            iconElement.classList.remove("bi-arrow-up-square");
        } else {
            payment_box.style.display = "grid";
            iconElement.classList.remove("bi-arrow-down-square");
            iconElement.classList.add("bi-arrow-up-square");
        }
    }
</script>
</body>


</html>
