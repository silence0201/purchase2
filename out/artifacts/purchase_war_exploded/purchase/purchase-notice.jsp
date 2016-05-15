
<%--
  Created by IntelliJ IDEA.
  User: silence
  Date: 16/4/25
  Time: 下午8:04
  采购员代办事项
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>采购员->通知</title>
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
                <a href="purchaseNotice.action">${ sessionScope.count }个未处理</a>
                ）
            </p>
        </div>
        <div class="web_palce">
            <article class="place">
                <a   class="user_status" href="#">采购部门</a>
                <div class="place_driver"></div>
                <a  class="current" href="#">待办事项</a>
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
                <h3>通知</h3>
            </header>
            <div class="noticeContext">
                <c:if test="${ requestScope.count == 0}">
                    <p class="alert-info noticeCon" >你没有要处理的通知</p>
                </c:if>
                <c:if test="${ requestScope.checkNotice.size() != 0}">
                    <h3>&nbsp;&nbsp;需审核</h3>
                    <c:forEach items="${ requestScope.checkNotice }" var="checkNotice">
                        <p class="alert-info noticeCon">你有订单号为<a>${ checkNotice }</a>的需求需要审核</p>
                    </c:forEach>
                </c:if>
                <c:if test="${ requestScope.needPlans.size() != 0}">
                    <h3>&nbsp;&nbsp;需采购</h3>
                    <c:forEach items="${ requestScope.needPlans }" var="needPlan">
                        <p class="alert-success noticeCon">你有订单号为<a>${ needPlan }</a>的计划需要采购</p>
                    </c:forEach>
                </c:if>
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