<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: silence
  Date: 16/5/13
  Time: 下午8:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>部门经理->申请查看</title>
    <link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../css/common.css">
    <link rel="stylesheet" type="text/css" href="../css/manager.css">
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
            <a   class="user_status" href="#">部门经理</a>
            <div class="place_driver"></div>
            <a  class="current" href="#">审核查看</a>
        </article>
    </div>
</section>
<%--Function--%>
<%@include file="../common/manager.jsp"%>
<%--main--%>
<section id="main">
    <div id = "main_div">
        <header class="notice">
            <h3>订单列表</h3>
        </header>
        <table class="table table-striped table-hover table-bordered">
            <tr>
                <td>订单编号</td>
                <td>商品</td>
                <td>数量</td>
                <td>花费</td>
                <td>采购人</td>
                <td>采购时间</td>
                <td>采购状态</td>
                <td>&nbsp;</td>
            </tr>

            <c:forEach items="${ requestScope.orders }" var="arder" varStatus="status">
                <c:if test="${ status.index < 15}">
                    <tr>
                        <td>${ arder.orderId }</td>
                        <td>${ arder.provideritem.item.itemName}</td>
                        <td>${ arder.plan.number}</td>
                        <td>${ arder.totalCost }</td>
                        <td>${ arder.orderMan.userName}</td>
                        <td>${ arder.orderTime}</td>
                        <td>${ arder.orderStatus }</td>
                        <td><a href="managerOrderInfo.action?orderID=${ arder.orderId}">详情</a></td>
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

    <div id="side_div2">
        <header id="curRecord">
            <h3>订单统计</h3>
        </header>
        <br />
        <p style="font-size: 1.8em;text-align: center;">公司订单统计</p>
        <hr />
        <br />
        <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
        <label style="font-size: 1.3em">总订单次数:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
        <input type="text" readonly="readonly" value="${ requestScope.allCount}">
        <br /><br />
        <span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
        <label style="font-size: 1.3em">月累计订单次数:&nbsp;&nbsp;</label>
        <input type="text" readonly="readonly" value="${ requestScope.count}" />
        <br /><br />
        <span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
        <label style="font-size: 1.3em">月累计订单金额:&nbsp;&nbsp;</label>
        <input type="text" readonly="readonly" value="${ requestScope.sum }">
        <br />
    </div>
</section>
</body>
</html>

