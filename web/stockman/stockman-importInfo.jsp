<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: silence
  Date: 16/5/18
  Time: 下午4:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>库管员->入库详情</title>
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
            <a  class="current" href="#">入库详情</a>
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
            <h3>入库详情</h3>
        </header>
        <br />
        <h4>&nbsp;&nbsp;入库单编号:${requestScope.impart.importId}</h4>
        <h4>&nbsp;&nbsp;处理人：${requestScope.impart.stockMan.userName}</h4>
        <table class="table table-striped table-hover table-bordered">
            <tr>
                <td class="active">出库单编号</td>
                <td>${requestScope.impart.importId}</td>
                <td class="active">订单编号</td>
                <td>${requestScope.impart.order.orderId}</td>
            </tr>

            <tr>
                <td class="active">采购人</td>
                <td>${requestScope.impart.stockMan.userName}</td>
                <td class="active">采购时间</td>
                <td>${requestScope.impart.importTime}</td>
            </tr>

            <tr>
                <td class="active">商品</td>
                <td>${requestScope.impart.order.plan.item.itemName}</td>
                <td class="active">数量</td>
                <td>${requestScope.impart.order.plan.number}</td>
            </tr>

            <tr>
                <td class="active">单价</td>
                <td>${requestScope.impart.order.plan.item.avePrice}</td>
                <td class="active">金额</td>
                <td>${requestScope.impart.order.plan.totalCost}</td>
            </tr>
        </table>

        <hr />
        <h4>&nbsp;&nbsp;需求单信息</h4>
        <table class="table table-striped table-hover table-bordered">
            <tr>
                <td>需求编号</td>
                <td>申请人</td>
                <td>申请时间</td>
                <td>申请部门</td>
                <td>申请数量</td>
            </tr>
            <c:forEach items="${requestScope.impart.order.plan.requests}" var="r" varStatus="status">

                <c:if test="${status.index < 5}">
                    <tr>
                        <td>${r.requestId}</td>
                        <td>${r.requestMan.userName}</td>
                        <td>${r.requestTime}</td>
                        <td>${r.requestMan.position.split("申请员")[0]}</td>
                        <td>${r.number}</td>
                    </tr>
                </c:if>
            </c:forEach>
        </table>

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
            <h3>入库统计</h3>
        </header>
        <br />
        <p style="font-size: 1.8em;text-align: center;">入库统计</p>
        <hr />
        <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
        <label style="font-size: 1.3em">总出库次数:&nbsp;&nbsp;</label>
        <input type="text" readonly="readonly" value="${ requestScope.allCount }" />
        <br /><br /><br />
        <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
        <label style="font-size: 1.3em">月出库次数:&nbsp;&nbsp;</label>
        <input type="text" readonly="readonly" value="${requestScope.count}">
        <br /><br />
    </div>
</section>
</body>
</html>
