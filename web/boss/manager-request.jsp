<%--
  Created by IntelliJ IDEA.
  User: silence
  Date: 16/5/17
  Time: 下午7:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>部门经理->申请查看</title>
    <link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../css/common.css">
    <link rel="stylesheet" type="text/css" href="../css/manager.css">
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
                <a   class="user_status" href="#">部门经理</a>
                <div class="place_driver"></div>
                <a  class="current" href="#">申请详情</a>
            </article>
        </div>
    </section>

    <%--Function--%>
    <%@include file="../common/manager.jsp"%>

    <%--main--%>
    <section id="main">
        <div id = "main_div">
            <header class="notice">
                <h3>需求单列表</h3>
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
                <s:if test="request.auditor != null">
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
            <h3>&nbsp;&nbsp;理由</h3>
            <div class="jumbotron">
                <p>&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="request.reason" /> </p>
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
                <h3>申请统计</h3>
            </header>
            <br />
            <p style="font-size: 1.8em;text-align: center;">公司申请统计</p>
            <hr />
            <br />
            <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
            <label style="font-size: 1.3em">总申请次数:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
            <input type="text" readonly="readonly" value="${ requestScope.allCount}">
            <br /><br />
            <span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
            <label style="font-size: 1.3em">月累计申请次数:&nbsp;&nbsp;</label>
            <input type="text" readonly="readonly" value="${ requestScope.count}" />
            <br /><br />
            <span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
            <label style="font-size: 1.3em">月累计申请金额:&nbsp;&nbsp;</label>
            <input type="text" readonly="readonly" value="${ requestScope.sum }">
            <br />
        </div>
    </section>

</body>
</html>
