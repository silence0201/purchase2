<%--
  Created by IntelliJ IDEA.
  User: silence
  Date: 16/5/15
  Time: 下午4:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>申请员->申请结果</title>
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
                <a   class="user_status" href="requestRequest.action">需求申请</a>
                <div class="place_driver"></div>
                <a   class="current" href="#">申请结果</a>
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
                <h3>申请结果</h3>
            </header>
            <br />
            <s:if test="request == null">
                <p class="alert-warning noticeCon" >添加失败请和系统管理员联系</p>
            </s:if>
            <s:else>
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
                    <p>&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="request.reason" /> </p>
                </div>
            </s:else>

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
                <h3>统计数据</h3>
            </header>
            <br />
            <p style="font-size: 1.8em;text-align: center;">月申请统计</p>
            <hr />
            <span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
            <label style="font-size: 1.3em">月累计申请次数:&nbsp;&nbsp;</label>
            <input type="text" readonly="readonly" value="${ requestScope.countOfTime }" />
            <br /><br />
            <span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
            <label style="font-size: 1.3em">月累计申请金额:&nbsp;&nbsp;</label>
            <input type="text" readonly="readonly" value="${requestScope.countOfMoney}">
            <br /><br />
            <span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
            <label style="font-size: 1.3em">本月剩余金额:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
            <input type="text" readonly="readonly" value="${10000 - requestScope.countOfMoney}">
            <br />
        </div>
    </section>
</body>
</html>
