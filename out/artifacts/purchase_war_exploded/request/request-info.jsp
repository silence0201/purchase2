<%--
  Created by IntelliJ IDEA.
  User: silence
  Date: 16/4/25
  Time: 下午7:45
  申请单详情页面
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>采购员->需求详情</title>
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
                <a href="requestNotice.action">${ sessionScope.count }个未处理</a>
                ）
            </p>
        </div>
        <div class="web_palce">
            <article class="place">
                <a   class="user_status" href="requestNotice.action">需求部门</a>
                <div class="place_driver"></div>
                <a  class="user_status" href="requestNotice.action">待办事项</a>
                <div class="place_driver"></div>
                <a  class="current" href="#">需求详情</a>
            </article>
        </div>
    </section>

    <%--function--%>
    <%@ include file="../common/requestFun.jsp" %>

    <%--main--%>
    <section id="main">
        <%--主要功能--%>
        <div id = "main_div">
            <header class="buyInfo">
                <h3>需求详情</h3>
            </header>
            <br />
            <h4>&nbsp;&nbsp;申请的申请单号是:<s:property value="request.requestId"/> </h4>
            <h4>&nbsp;&nbsp;处理结果：<s:property value="request.requestStatus"/></h4>
            <hr>
            <h4>详细信息:</h4>
            <table class="table table-bordered">
                <tr>
                    <td class="active">申请人</td>
                    <td><s:property value="request.requestMan.userName"/></td>
                    <td class="active">申请时间</td>
                    <td><s:property value="request.requestTime"/> </td>
                </tr>
                <s:if test="request.auditor !=null">
                    <tr>
                        <td class="active">审核人</td>
                        <td><s:property value="request.auditor.userName"/> </td>
                        <td class="active">审核时间</td>
                        <td><s:property value="request.auditTime"/> </td>
                    </tr>
                </s:if>
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
            <h3>&nbsp;&nbsp;处理理由</h3>
            <div class="jumbotron">
                <p>&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="request.reason" /> </p>
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
                <h3>最近申请状态</h3>
            </header>
            <table class="table table-striped table-hover table-bordered">
                <tr>
                    <td><b>需求单编号</b></td>
                    <td><b>物品名称</b></td>
                    <td><b>数量</b></td>
                    <td><b>状态</b></td>
                </tr>
                <s:iterator value="#request.requests" var="r" status="status">
                    <s:if test="#status.count < 6">
                        <tr>
                            <td><s:property value="#r.requestId"/> </td>
                            <td><s:property value="#r.item.itemName"/></td>
                            <td><s:property value="#r.number"/> </td>
                            <td><s:property value="#r.requestStatus"/> </td>
                        </tr>
                    </s:if>
                </s:iterator>
            </table>
        </div>
    </section>
</body>
</html>
