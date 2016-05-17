<%--
  Created by IntelliJ IDEA.
  User: silence
  Date: 16/5/15
  Time: 下午8:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>申请员->申请修改</title>
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
                <a   class="user_status" href="requestNotice.action">需求部门</a>
                <div class="place_driver"></div>
                <a  class="user_status" href="requestList.action">表单查询</a>
                <div class="place_driver"></div>
                <a  class="current" href="#">申请修改</a>
            </article>
        </div>
    </section>

    <%--function--%>
    <%@ include file="../common/requestFun.jsp" %>

    <%--main--%>
    <section id="main">
        <%--主要功能--%>
        <div id = "main_div">
            <br />
            <h4>&nbsp;&nbsp;申请的申请单号是:<s:property value="request.requestId"/> </h4>
            <h4>&nbsp;&nbsp;处理结果：<s:property value="request.requestStatus"/></h4>
            <hr />
            <form action="requestModify.action">
                <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                <label for="requestID">商品名称:</label>
                <span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
                <input id="requestID" name="requestID" type="text" value="${ requestScope.requestID }" readonly="readonly" hidden="hidden"/>
                <s:property value="request.item.itemName"/>
                <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                <label for="number">数量</label>
                <span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
                <input id="number" name="number" type="text" value="${ requestScope.number }" />
                <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                <input type="submit" value="修改">
                <hr />
                <h4>详细信息:</h4>
                <table class="table table-bordered">
                    <tr>
                        <td class="active">申请人</td>
                        <td><s:property value="request.requestMan.userName"/></td>
                        <td class="active">申请时间</td>
                        <td><s:property value="request.requestTime"/> </td>
                    </tr>
                    <tr>
                        <td class="active">申请物品</td>
                        <td><s:property value="request.item.itemName"/> </td>
                        <td class="active">申请数量</td>
                        <td><s:property value="request.number"/> </td>
                    </tr>
                    <tr>
                        <td class="active">物品单价</td>
                        <td><s:property value="request.item.avePrice"/> </td>
                        <td class="active">总金额</td>
                        <td><s:property value="request.totalCost"/> </td>
                    </tr>
                </table>
                <hr />
                <h3>&nbsp;&nbsp;申请理由</h3>
                <div class="jumbotron">
                    <p><s:property value="request.reason" /> </p>
                </div>
            </form>
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

                <tr>
                    <td>申请编号</td>
                    <td>物品</td>
                    <td>数量</td>
                    <td>通过时间</td>
                    <td>审核人</td>
                </tr>
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

            </table>
        </div>
    </section>

</body>
</html>
