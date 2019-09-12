<%--
  Created by IntelliJ IDEA.
  User: itit
  Date: 2019/9/6
  Time: 5:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>hello</title>
    <meta charset="UTF-8" name="viewport" content="initial-scale=1,maximum-scale=3,minimum-scale=1,user-scalable=no">
</head>
<body>
<div style="text-align: center">
    <span id="title">请在五分钟内支付，超时支付无法到账</span>
    <br>
    <span>金额</span>
    <span id="amout" style="color: red">${order.amount}</span>
    <br>订单号
    <span id="orderNo" style="color: red">${order.orderNo}</span>
    <br>
    <span id="timeOutSpan"></span>
    <span id="cancelDiv"   style="display:none;color: red">已经超过五分钟无法支付，请从新下单</span>
    <br>
    <div id="img" style="display:inline-block;max-width: 200px;max-height: 200px;">
        <img src="/boss/public/attach/${order.attachId}/" style="width: 100%">
    </div>
</div>
<script type="text/javascript" src="/static/js/jquery-3.4.1.min.js"></script>
<script>
    function autoTime() {
        alert('xxx')
    }

    $(document).ready(function () {
        var lessTime = ${lessTime}
        $("#btn-sub").click(function () {
            $("#apply").submit();
        });
        var inter = setInterval(function () {
            lessTime = lessTime - 1;
            if (lessTime <= 0) {
                $("#timeOutSpan").css("display","none")
                $("#title").css("display","none")
                $("#cancelDiv").css("display","inline-block")
                $('#img').css("display", "none")
                window.clearInterval(inter);
            }
            $("#timeOutSpan").html(lessTime);
        }, 1000)

    });
</script>
</body>
</html>
