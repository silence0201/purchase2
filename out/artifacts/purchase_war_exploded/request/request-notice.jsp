<%--
  Created by IntelliJ IDEA.
  User: silence
  Date: 16/4/25
  Time: 下午4:08
  需求申请员代办事项页面
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>申请员->通知</title>
    <link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../css/common.css">
    <link rel="stylesheet" type="text/css" href="../css/request.css">
</head>
<body>
    <%--firsthead--%>
    <%@ include file="../common/head.jsp" %>

    <!--second header-->
    <section id="second_bar">
        <div class="user">
            <p>${ sessionScope.user.userName }（
                <a href="requestNotice.action">${ sessionScope.count }个未处理</a>
                ）
            </p>
        </div>
        <div class="web_palce">
            <article class="place">
                <a   class="user_status" href="requestNotice.action">需求部门</a>
                <div class="place_driver"></div>
                <a  class="current" href="requestNotice.action">待办事项</a>
            </article>
        </div>
    </section>

    <%--function--%>
    <%@ include file="../common/requestFun.jsp" %>

    <%--main--%>
    <section id="main">
        <%--主要功能--%>
        <div id = "main_div">
            <header class="notice">
                <h3>通知</h3>
            </header>
            <div class="noticeContext">
                <c:if test="${ sessionScope.count ==0 }">
                    <p class="alert-info noticeCon" >你没有要处理的通知</p>
                </c:if>
                <c:if test="${ requestScope.notices.size() != 0 }">
                    <h3>&nbsp;&nbsp;已到货</h3>
                </c:if>
                <c:forEach items="${ requestScope.notices }" var="notice">
                    <p class="alert-info noticeCon">你有订单号为<a href="requestInfo.action?requestID=${ notice }">${ notice }</a>的订单已经到货</p>
                </c:forEach>

                <c:if test="${ requestScope.passes.size() != 0 }">
                    <h3>&nbsp;&nbsp;通过</h3>
                </c:if>
                <c:forEach items="${ requestScope.passes }" var="pass">
                    <p class="alert-success noticeCon">你有订单号为<a href="requestInfo.action?requestID=${ pass }">${ pass }</a>的订单已经通过</p>
                </c:forEach>

                <c:if test="${ requestScope.refuses.size() != 0 }">
                    <h3>&nbsp;&nbsp;拒绝</h3>
                </c:if>
                <c:forEach items="${ requestScope.refuses }" var="refuse">
                    <p class="alert-warning noticeCon">你有订单号为<a href="requestInfo.action?requestID=${ refuse }">${ refuse }</a>的订单已经被拒绝</p>
                </c:forEach>
            </div>
        </div>

        <%--时间--%>
        <div id="side_div1">
            <header id="time">
                <h3>时间</h3>
            </header>
            <%@include file="../common/calendar.jsp"%>
        </div>

        <%--额外功能--%>
        <div id="side_div2">
            <header id="curRecord">
                <h3>最近申请状态</h3>
            </header>
            <table class="table table-striped table-hover table-bordered">
                    <tr>
                        <td><b>需求单编号</b></td>
                        <td><b>物品名称</b></td>
                        <td><b>数量</b></td>
                        <td><b>状态</b></td>
                    </tr>

                    <c:forEach items="${ requestScope.requests }" var="request" varStatus="status">
                        <c:if test="${ status.index < 5 }">
                            <tr>
                                <td>${ request.getRequestId() }</td>
                                <td>${ request.getItem().getItemName() }</td>
                                <td>${ request.getNumber() }</td>
                                <td>${ request.getRequestStatus() }</td>
                            </tr>
                        </c:if>
                    </c:forEach>
            </table>
        </div>
    </section>
</body>
</html>
