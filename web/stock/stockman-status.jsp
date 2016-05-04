<%--
  Created by IntelliJ IDEA.
  User: silence
  Date: 16/4/26
  Time: 上午8:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>库管员->库存状态</title>
    <link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../css/common.css">
    <link rel="stylesheet" type="text/css" href="../css/stock.css">
</head>
<body>
    <%--firsthead--%>
    <%@ include file="../common/head.jsp" %>

    <%--secondhead--%>
    <section id="second_bar">
        <div class="user">
            <p>宋XX（
                <a href="">3个未处理</a>
                ）
            </p>
        </div>
        <div class="web_palce">
            <article class="place">
                <a   class="user_status" href="#">库管员</a>
                <div class="place_driver"></div>
                <a  class="current" href="#">库存状态</a>
            </article>
        </div>
    </section>
    <%--Function--%>
    <%@include file="../common/stockFun.jsp"%>

    <%--main--%>
    <section id="main">
        <%--主要功能区--%>
        <div id = "main_div">
            <header class="storeStatus">
                <h3>库存状态</h3>
            </header>
        </div>
        <div id="side_div1">
             <header id="time">
                <h3>时间</h3>
             </header>
             <%@include file="../common/calendar.jsp"%>
        </div>
         <%--额外功能--%>
        <div id="side_div2">
            <header id="curInfo">
                <h3>统计</h3>
            </header>
        </div>
    </section>

</body>
</html>
