<%--
  Created by IntelliJ IDEA.
  User: silence
  Date: 16/5/14
  Time: 下午4:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>采购员->审核查看</title>
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
            <table class="table table-striped table-hover table-bordered">
                <thead>
                    <tr>
                        <td>需求单编号</td>
                        <td>申请人</td>
                        <td>物品名称</td>
                        <td>数量</td>
                        <td>金额</td>
                        <td>状态</td>
                        <td>审核时间</td>
                    </tr>
                </thead>

                <tbody>
                    <c:forEach items="${ requestScope.checkList }" var="check" varStatus="status">
                        <c:if test="${ status.index < 15}">
                            <tr>
                                <td>${ check.getRequestId() }</td>
                                <td>${ check.getRequestMan().getUserName() }</td>
                                <td>${ check.getItem().getItemName() }</td>
                                <td>${ check.getNumber() }</td>
                                <td>${ check.getTotalCost() }</td>
                                <td>${ check.getRequestStatus() }</td>
                                <td>${ check.getAuditTime() }</td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </tbody>
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
