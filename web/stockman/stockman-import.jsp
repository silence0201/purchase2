<%--
  Created by IntelliJ IDEA.
  User: silence
  Date: 16/4/26
  Time: 上午9:31
  库管员进行入库登记页面
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>库管员->入库登记</title>
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
            <p>${ sessionScope.user.userName }</p>
        </div>
        <div class="web_palce">
            <article class="place">
                <a   class="user_status" href="#">库管员</a>
                <div class="place_driver"></div>
                <a  class="current" href="#">入库登记</a>
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
                <h3>入库信息</h3>
            </header>
            <br /><br />
            <form action="">
                <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                <label for="orderID">订单编号: &nbsp;&nbsp;&nbsp;&nbsp;</label>
                <input type="text" id="orderID" name="orderID">
                <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                <input type="submit">
                <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                <input type="reset" value="清空">
            </form>

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
                <h3>最近记录</h3>
            </header>
            <table class="table table-striped table-hover table-bordered">
                <thead>
                    <tr>
                        <td>订单编号</td>
                        <td>商品</td>
                        <td>数量</td>
                        <td>入库时间</td>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${ requestScope.imports }" var="impart" varStatus="status">
                        <c:if test="${ status.index < 5 }">
                            <tr>
                                <td>${ impart.getOrder().getOrderId() }</td>
                                <td>${ impart.getOrder().getPlan().getItem().getItemName() }</td>
                                <td>${ impart.getOrder().getPlan().getNumber() }</td>
                                <td>${ impart.getImportTime()}</td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </section>

</body>
</html>