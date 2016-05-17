<%--
  Created by IntelliJ IDEA.
  User: silence
  Date: 16/4/26
  Time: 上午9:55
  部门经理待办事项
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>部门经理->通知</title>
    <link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../css/common.css">
    <link rel="stylesheet" type="text/css" href="../css/divManager.css">
</head>
<body>
    <%--firsthead--%>
    <%@ include file="../common/head.jsp" %>

    <%--secondhead--%>
    <section id="second_bar">
        <div class="user">
            <p> ${ sessionScope.user.userName }（
                <a href="">${ sessionScope.count }个未处理</a>
                ）
            </p>
        </div>
        <div class="web_palce">
            <article class="place">
                <a   class="user_status" href="divManagerNotice.action">部门经理</a>
                <div class="place_driver"></div>
                <a  class="current" href="#">待办事项</a>
            </article>
        </div>
    </section>
    <%--Function--%>
    <%@include file="../common/divManagerFun.jsp"%>
    <%--main--%>
    <section id="main">
        <div id = "main_div">
            <header class="notice">
                <h3>通知</h3>
            </header>
            <div class="noticeContext">
                <c:if test="${ sessionScope.count == 0 }">
                    <p class="alert-info noticeCon" >你没有需要审核的需求单</p>
                </c:if>
                <c:if test="${ sessionScope.count != 0 }">
                    <h3>&nbsp;&nbsp;需审核</h3>
                    <c:forEach items="${ requestScope.checkNotices }" var="checkNotice">
                        <p class="alert-info noticeCon">你有订单号为<a href="divManagerInitHandle.action?requestID=${ checkNotice }">${ checkNotice }</a>的需求需要审核</p>
                    </c:forEach>
                </c:if>
            </div>
        </div>
        <div id="side_div1">
            <header id="time">
                <h3>日历</h3>
            </header>
            <%@include file="../common/calendar.jsp"%>
        </div>

        <div id="side_div2">
            <header id="curRecord">
                <h3>最近审核记录</h3>
            </header>
            <table class="table table-striped table-hover table-bordered">
                <tr>
                    <td><b>需求单编号</b></td>
                    <td><b>物品</b></td>
                    <td><b>数量</b></td>
                    <td><b>审批时间</b></td>
                </tr>
                <c:forEach items="${ requestScope.checkList }" var="check" varStatus="status">
                    <c:if test="${ status.index < 8 }">
                        <tr>
                            <td>${ check.getRequestId() }</td>
                            <td>${ check.getItem().getItemName() }</td>
                            <td>${ check.getNumber() }</td>
                            <td>${ check.getAuditTime() }</td>
                        </tr>
                    </c:if>
                </c:forEach>
            </table>
        </div>
    </section>
</body>
</html>
