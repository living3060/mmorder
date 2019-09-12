<%--
  Created by IntelliJ IDEA.
  User: itit
  Date: 2019/9/6
  Time: 5:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>hello</title>
</head>
<body>
<span id="title">请在五分钟内支付，超时支付无法到账</span>
<span id="amout">金额${order.amount}</span>

<div>
    <img src="/boss/public/attach/${order.attachId}">
</div>
<div><span id="timeOutSpan"></span></div>
<script type="text/javascript" src="/static/js/jquery-3.4.1.min.js"></script>
<script>
    $(document).ready(function(){
        var
        $("#btn-sub").click(function () {
            $("#apply").submit();
        });
        setTimeout("autoTime")
        function autoTime() {
            $("#timeOutSpan").submit();
        }
    });
</script>
</body>
</html>
