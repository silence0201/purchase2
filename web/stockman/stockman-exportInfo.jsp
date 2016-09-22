<%--
  Created by IntelliJ IDEA.
  User: silence
  Date: 16/5/18
  Time: 下午3:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>库管员->出库详情</title>
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
            <a   class="user_status" href="stockStatus.action">库管员</a>
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
            <h3>出库单详情</h3>
        </header>
        <br />
        <h4>&nbsp;&nbsp;出库单编号:${requestScope.export.exportId}</h4>
        <h4>&nbsp;&nbsp;处理人：${requestScope.export.stockMan.userName}</h4>
        <hr>
        <table class="table table-striped table-hover table-bordered">
            <tr>
                <td class="active">出库单编号</td>
                <td>${requestScope.export.exportId}</td>
                <td class="active">需求单编号</td>
                <td>${requestScope.export.request.requestId}</td>
            </tr>

            <tr>
                <td class="active">申请人</td>
                <td>${requestScope.export.request.requestMan.userName}</td>
                <td class="active">申请时间</td>
                <td>${requestScope.export.request.requestTime}</td>
            </tr>

            <tr>
                <td class="active">审核人</td>
                <td>${requestScope.export.request.auditor.userName}</td>
                <td class="active">审核时间</td>
                <td>${requestScope.export.request.auditTime}</td>
            </tr>

            <tr>
                <td class="active">物品</td>
                <td>${requestScope.export.request.item.itemName}</td>
                <td class="active">数量</td>
                <td>${requestScope.export.request.number}</td>
            </tr>

            <tr>
                <td class="active">单价</td>
                <td>${requestScope.export.request.item.avePrice}</td>
                <td class="active">总金额</td>
                <td>${requestScope.export.request.totalCost}</td>
            </tr>


        </table>
        <hr />
        <h3>&nbsp;&nbsp;处理理由</h3>
        <div class="jumbotron">
            <p>&nbsp;&nbsp;&nbsp;&nbsp;${requestScope.export.request.reason} </p>
        </div>
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
