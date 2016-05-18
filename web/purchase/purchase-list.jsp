<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: silence
  Date: 16/4/25
  Time: 下午8:32
  采购员订单查列表页面
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>采购员->订单查看</title>
    <link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../css/common.css">
    <link rel="stylesheet" type="text/css" href="../css/purchase.css">
</head>
<body>
    <%--firsthead--%>
    <%@ include file="../common/head.jsp" %>
    <%--secondhead--%>
    <section id="second_bar">
        <div class="user">
            <p>${ sessionScope.user.userName }（
                <a href="">${ sessionScope.count }个未处理</a>
                ）
            </p>
        </div>
        <div class="web_palce">
            <article class="place">
                <a   class="user_status" href="#">采购部门</a>
                <div class="place_driver"></div>
                <a  class="current" href="#">订单查看</a>
            </article>
        </div>
    </section>
    <%--function--%>
    <%@ include file="../common/purchaseFun.jsp" %>
    <%--main--%>
    <section id="main">
        <%--主要功能--%>
        <div id = "main_div">
            <header class="buyInfo">
                <h3>订单信息</h3>
            </header>
            <table class="table table-striped table-hover table-bordered">
                    <tr>
                        <td>订单编号</td>
                        <td>计划单编号</td>
                        <td>商品</td>
                        <td>数量</td>
                        <td>花费</td>
                        <td>采购时间</td>
                        <td>采购状态</td>
                        <td>&nbsp;</td>
                    </tr>
                    <c:forEach items="${ requestScope.orderList }" var="arder" varStatus="status">
                        <c:if test="${ status.index < 15}">
                            <tr>
                                <td>${ arder.getOrderId() }</td>
                                <td>${ arder.getPlan().getPlanId() }</td>
                                <td>${ arder.getPlan().getItem().getItemName()}</td>
                                <td>${ arder.getPlan().getItem().getAvePrice()}</td>
                                <td>${ arder.getPlan().getTotalCost() }</td>
                                <td>${ arder.getOrderTime()}</td>
                                <td>${ arder.getOrderStatus() }</td>
                                <td><a href="purchaseMoreOrder.action?orderID=${ arder.getOrderId() }">详情</a></td>
                            </tr>
                        </c:if>
                    </c:forEach>
            </table>
        </div>
        <div id="side_div1">
            <header id="time">
                <h3>日历</h3>
            </header>
            <%@include file="../common/calendar.jsp"%>
        </div>
        <%--额外功能--%>
        <div id="side_div2">
            <header id="count">
                <h3>采购统计</h3>
            </header>
            <br />
            <p style="font-size: 1.8em;text-align: center;">订单统计</p>
            <hr />
            <br />
            <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
            <label style="font-size: 1.3em">总采购次数:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
            <input type="text" readonly="readonly" value="${ requestScope.count}">
            <br /><br />
            <span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
            <label style="font-size: 1.3em">月累计采购次数:&nbsp;&nbsp;</label>
            <input type="text" readonly="readonly" value="${ requestScope.countOfTime}" />
            <br /><br />
            <span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
            <label style="font-size: 1.3em">月累计采购金额:&nbsp;&nbsp;</label>
            <input type="text" readonly="readonly" value="${ requestScope.sumOfMoney }">
            <br />
        </div>
    </section>
</body>
</html>
