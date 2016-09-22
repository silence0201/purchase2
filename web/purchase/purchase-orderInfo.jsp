<%--
  Created by IntelliJ IDEA.
  User: silence
  Date: 16/5/18
  Time: 下午9:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>采购员->订单信息</title>
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
            <a  class="current" href="#">计划详情</a>
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
            <h3>计划信息</h3>
        </header>
        <br />
        <h4>&nbsp;&nbsp;采购生成的订单号:${requestScope.order.orderId} </h4>
        <h4>&nbsp;&nbsp;采购状态：${requestScope.order.orderStatus}</h4>
        <hr>
        <h4>详细信息:</h4>
        <table class="table table-bordered">
            <tr>
                <td class="active">采购物品</td>
                <td>${requestScope.order.plan.item.itemName}</td>
                <td class="active">采购数量</td>
                <td>${requestScope.order.plan.number}</td>
            </tr>
            <tr>
                <td class="active">物品单价</td>
                <td>${requestScope.order.provideritem.price}</td>
                <td class="active">总金额</td>
                <td>${requestScope.order.totalCost}</td>
            </tr>
            <tr>
                <td class="active">采购公司</td>
                <td>${requestScope.order.provideritem.provider.providerName}</td>
                <td class="active">省份</td>
                <td>${requestScope.order.provideritem.provider.provinces}</td>
            </tr>
            <tr>
                <td class="active">联系人</td>
                <td>${requestScope.order.provideritem.provider.contant}</td>
                <td class="active">联系方式</td>
                <td>${requestScope.order.provideritem.provider.tele}</td>
            </tr>
            <tr>
                <td class="active">详细地址</td>
                <td colspan="3">${requestScope.order.provideritem.provider.address}</td>
            </tr>
        </table>
        <hr />
        <h4>&nbsp;&nbsp;申请单信息</h4>
        <table class="table table-striped table-hover table-bordered">
            <tr>
                <td>申请人</td>
                <td>申请部门</td>
                <td>商品</td>
                <td>数量</td>
            </tr>
            <c:forEach items="${requestScope.order.plan.requests}" var="r" varStatus="status">
                <c:if test="${status.index < 5}">
                    <tr>
                        <td>${r.requestMan.userName}</td>
                        <td>${r.requestMan.position.split("申请员")[0]}</td>
                        <td>${r.item.itemName}</td>
                        <td>${r.number}</td>
                    </tr>
                </c:if>
            </c:forEach>
        </table>

        <form action="purchaseNotice.action">
            <span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
            <input type="submit" value="返回">
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
        <header id="count">
            <h3>最近记录</h3>
        </header>
        <c:if test="${ requestScope.checkList.size() != 0}">
            <h4>&nbsp;&nbsp;审核</h4>
            <table class="table table-striped table-hover table-bordered">
                <tr>
                    <td>需求单编号</td>
                    <td>商品</td>
                    <td>数量</td>
                    <td>申请时间</td>
                </tr>

                <c:forEach items="${requestScope.checkList}" var="check" varStatus="status">
                    <c:if test="${ status.index < 2}">
                        <tr>
                            <td>${ check.getRequestId() }</td>
                            <td>${ check.getItem().getItemName() }</td>
                            <td>${ check.getNumber() }</td>
                            <td>${ check.getRequestTime() }</td>
                        </tr>
                    </c:if>
                </c:forEach>
            </table>
        </c:if>

        <c:if test="${ requestScope.orderList.size() != 0}">
            <h4>&nbsp;&nbsp;采购</h4>
            <table class="table table-striped table-hover table-bordered">
                <tr>
                    <td>订单编号</td>
                    <td>商品</td>
                    <td>数量</td>
                    <td>采购时间</td>
                </tr>

                <c:forEach items="${requestScope.orderList}" var="arder" varStatus="status">
                    <c:if test="${ status.index < 2}">
                        <tr>
                            <td>${ arder.getOrderId() }</td>
                            <td>${ arder.getPlan().getItem().getItemName()}</td>
                            <td>${ arder.getPlan().getNumber() }</td>
                            <td>${ arder.getOrderTime() }</td>
                        </tr>
                    </c:if>
                </c:forEach>
            </table>
        </c:if>
    </div>
</section>
</body>
</html>
