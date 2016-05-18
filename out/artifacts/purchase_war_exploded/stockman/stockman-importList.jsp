<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: silence
  Date: 16/5/18
  Time: 下午3:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>库管员->入库列表</title>
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
                <a  class="current" href="#">入库列表</a>
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
                <h3>入库列表</h3>
            </header>

            <table class="table table-striped table-hover table-bordered">
                <tr>
                    <td>入库单编号</td>
                    <td>订单编号</td>
                    <td>采购人</td>
                    <td>商品</td>
                    <td>数量</td>
                    <td>入库时间</td>
                    <td>&nbsp;</td>
                </tr>

                <c:forEach items="${requestScope.imports}" varStatus="status" var="impart">
                    <c:if test="${status.index<15}">
                        <tr>
                            <td>${impart.importId}</td>
                            <td>${impart.order.orderId}</td>
                            <td>${impart.order.orderMan.userName}</td>
                            <td>${impart.order.plan.item.itemName}</td>
                            <td>${impart.order.plan.totalCost}</td>
                            <td>${impart.importTime}</td>
                            <td><a href="importInfo.action?importID=${impart.importId}">详情</a></td>
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
