<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: silence
  Date: 16/4/25
  Time: 下午7:34
  显示申请单列表页面
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>申请员->表单查看</title>
    <link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../css/common.css">
    <link rel="stylesheet" type="text/css" href="../css/request.css">
</head>
<body>
    <%--firsthead--%>
    <%@ include file="../common/head.jsp" %>

    <%--secondhead--%>
    <section id="second_bar">
        <div class="user">
            <p>${ sessionScope.user.userName }（
                <a href="requestNotice.action">${ sessionScope.count } 个未处理</a>
                ）
            </p>
        </div>
        <div class="web_palce">
            <article class="place">
                <a   class="user_status" href="#">需求部门</a>
                <div class="place_driver"></div>
                <a  class="current" href="#">表单查询</a>
            </article>
        </div>
    </section>

    <%--function--%>
    <%@ include file="../common/requestFun.jsp" %>

    <%--main--%>
    <section id="main">
        <%--主要功能--%>
        <div id = "main_div">
            <header class="">
                <h3>需求记录</h3>
            </header>
            <table class="table table-striped table-hover table-bordered">
                <thead>
                <tr>
                    <td>需求单编号</td>
                    <td>物品名称</td>
                    <td>数量</td>
                    <td>金额</td>
                    <td>状态</td>
                    <td>申请时间</td>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${ requestScope.requests }" varStatus="status" var="request">
                    <c:if test="${ status.index <20 }">
                        <tr>
                            <td>${ request.getRequestId()}</td>
                            <td>${ request.getItem().getItemName() }</td>
                            <td>${ request.getNumber() }</td>
                            <td>${ request.getTotalCost() }</td>
                            <td>${ request.getRequestStatus() }</td>
                            <td>${ request.getRequestTime() }</td>
                            <td><a>修改</a>&nbsp;&nbsp;<a>删除</a></td>
                        </tr>
                    </c:if>
                </c:forEach>
                </tbody>
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
            <header id="count">
                <h3>通过记录</h3>
            </header>
            <table class="table table-striped table-hover table-bordered">
                <thead>
                    <tr>
                        <td>申请编号</td>
                        <td>物品</td>
                        <td>数量</td>
                        <td>通过时间</td>
                        <td>审核人</td>
                    </tr>
                </thead>
                <tbody>
                <c:set var="num" value="0" scope="page" />
                    <c:forEach items="${ requestScope.requests }" var="reques" varStatus="status">
                        <c:if test="${ num < 5 && reques.getRequestStatus() == '通过'}">
                            <tr>
                                <td>${ reques.getRequestId()}</td>
                                <td>${ reques.getItem().getItemName() }</td>
                                <td>${ reques.getNumber() }</td>
                                <td>${ reques.getAuditTime() }</td>
                                <td>${ reques.getAuditor().getUserName() }</td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </section>
</body>
</html>
