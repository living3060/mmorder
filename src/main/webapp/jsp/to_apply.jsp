<%--
  Created by IntelliJ IDEA.
  User: itit
  Date: 2019/9/9
  Time: 2:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Title</title>
    <meta charset="UTF-8" name="viewport" content="initial-scale=1,maximum-scale=3,minimum-scale=1,user-scalable=no">
</head>
<body>
<form id="apply" action="/api/applyOrder" method="post" style="text-align: center">
    <input type="hidden" value="${gameId}" name="gameId" style="width: 100px"/>
    <input type="hidden" value="${gameAccountId}" name="gameAccountId"/>
    <br>
    <input type="hidden" value="${gameOrderNo}" name="gameOrderNo"/>
    <input type="number" min="100" step="200" name="amount" style="width: 100px;margin-bottom: 10px"/>
    <br>
    <select  name="type">
        <option value="alipay">支付宝二维码</option>
        <option value="weixin">微信二维码</option>
    </select>
    <br>
    <h2/>
    <button id="btn-sub">确定</button>
</form>
<script type="text/javascript" src="/static/js/jquery-3.4.1.min.js"></script>

<script>
    $(document).ready(function(){
        $("#btn-sub").click(function () {
            $("#apply").submit();
        });
    });
</script>
</body>
</html>
