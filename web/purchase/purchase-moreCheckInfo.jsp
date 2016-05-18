<%--
  Created by IntelliJ IDEA.
  User: silence
  Date: 16/5/18
  Time: 下午11:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>采购员->审核详情</title>
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
            <a  class="current" href="#">审核查看</a>
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
            <h3>审核信息</h3>
        </header>
        <br />
        <h4>&nbsp;&nbsp;审核的申请单号是:${requestScope.check.requestId}</h4>
        <h4>&nbsp;&nbsp;处理结果：${requestScope.check.requestStatus}</h4>
        <hr>
        <h4>详细信息:</h4>
        <table class="table table-bordered">
            <tr>
                <td class="active">申请人</td>
                <td>${requestScope.check.requestMan.userName}</td>
                <td class="active">申请时间</td>
                <td>${requestScope.check.requestTime}</td>
            </tr>
            <s:if test="request.auditor != null">
                <tr>
                    <td class="active">审核人</td>
                    <td>${requestScope.check.auditor.userName}</td>
                    <td class="active">审核时间</td>
                    <td>${requestScope.check.auditTime}</td>
                </tr>
            </s:if>
            <tr>
                <td class="active">申请物品</td>
                <td>${requestScope.check.item.itemName}</td>
                <td class="active">申请数量</td>
                <td>${requestScope.check.number}</td>
            </tr>
            <tr>
                <td class="active">物品单价</td>
                <td>${requestScope.check.item.avePrice}</td>
                <td class="active">总金额</td>
                <td>${requestScope.check.totalCost}</td>
            </tr>
        </table>
        <hr />
        <h3>&nbsp;&nbsp;理由</h3>
        <div class="jumbotron">
            <p>&nbsp;&nbsp;&nbsp;&nbsp;${requestScope.check.reason}</p>
        </div>
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
            <h3>审核统计</h3>
        </header>
        <br />
        <p style="font-size: 1.8em;text-align: center;">月审核统计</p>
        <hr /><br />
        <span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
        <label style="font-size: 1.3em">&nbsp;&nbsp;&nbsp;累计审核次数:&nbsp;&nbsp;</label>
        <input type="text" readonly="readonly" value="${ requestScope.count }" />
        <br /><br />
        <span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
        <label style="font-size: 1.3em">月累计审核次数:&nbsp;&nbsp;</label>
        <input type="text" readonly="readonly" value="${requestScope.countOfTime}">
        <br /><br />
        <span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
        <label style="font-size: 1.3em">月累计审核金额:&nbsp;&nbsp;</label>
        <input type="text" readonly="readonly" value="${requestScope.sumOfMoney}">
        <br />
    </div>
</section>
</body>
</html>
