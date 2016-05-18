<%--
  Created by IntelliJ IDEA.
  User: silence
  Date: 16/5/17
  Time: 下午7:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>部门经理->订单详情</title>
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
            <h3>订单信息</h3>
        </header>
        <h4>&nbsp;&nbsp;订单编号是:${requestScope.arder.orderId} </h4>
        <h4>&nbsp;&nbsp;订单状态：${requestScope.arder.orderStatus}</h4>
        <table class="table table-bordered">
            <tr>
                <td class="active">采购人</td>
                <td>${requestScope.arder.orderMan.userName}</td>
                <td class="active">采购时间</td>
                <td>${requestScope.arder.orderTime}</td>
            </tr>
            <tr>
                <td class="active">计划单编号</td>
                <td>${ requestScope.arder.plan.planId}</td>
                <td class="active">计划生成时间</td>
                <td>${requestScope.arder.plan.number }</td>
            </tr>
            <tr>
                <td class="active">商品</td>
                <td>${requestScope.arder.plan.item.itemName }</td>
                <td class="active">单价</td>
                <td>${requestScope.arder.plan.item.avePrice }</td>
            </tr>
            <tr>
                <td class="active">数量</td>
                <td>${ requestScope.arder.plan.number }</td>
                <td class="active">总额</td>
                <td>${requestScope.arder.plan.totalCost }</td>
            </tr>
        </table>

        <hr />
        <h4>&nbsp;&nbsp;申请人详情</h4>
        <table class="table table-striped table-hover table-bordered">
            <tr>
                <td>申请人</td>
                <td>申请部门</td>
                <td>商品</td>
                <td>数量</td>
            </tr>

            <c:forEach items="${ requestScope.arder.plan.requests }" var="request" varStatus="status">
                <c:if test="${status.index < 5}">
                    <tr>
                        <td>${request.requestMan.userName}</td>
                        <td>${request.requestMan.position.split("申请员")[0]}</td>
                        <td>${request.item.itemName}</td>
                        <td>${request.number}</td>
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
