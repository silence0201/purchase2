<%--
  Created by IntelliJ IDEA.
  User: silence
  Date: 16/5/18
  Time: 下午2:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>库管员->出库记录</title>
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
                <a  class="current" href="#">出库记录</a>
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
                <h3>出库信息</h3>
            </header>
            <table class="table table-striped table-hover table-bordered">
                <tr>
                    <td>出库单编号</td>
                    <td>申请单号</td>
                    <td>申请人</td>
                    <td>物品</td>
                    <td>数量</td>
                    <td>出库时间</td>
                    <td>&nbsp;</td>
                </tr>

                <c:forEach items="${requestScope.exports}" var="export" varStatus="status">
                    <c:if test="${status.index < 15}">
                        <tr>
                            <td>${export.exportId}</td>
                            <td>${export.request.requestId}</td>
                            <td>${export.request.requestMan.userName}</td>
                            <td>${export.request.item.itemName}</td>
                            <td>${export.request.number}</td>
                            <td>${export.exportTime}</td>
                            <td><a href="exportInfo.action?exportID=${export.exportId}">详情</a></td>
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
                <h3>出库统计</h3>
            </header>
            <br />
            <p style="font-size: 1.8em;text-align: center;">出库统计</p>
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
