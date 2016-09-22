<%--
  Created by IntelliJ IDEA.
  User: silence
  Date: 16/4/25
  Time: 下午6:13
  需求申请员采购申请页面
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>申请员->需求申请</title>
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
                <a   class="current" href="requestRequest.action">需求申请</a>
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
                <h3>需求信息</h3>
            </header>
            <br /><br />
            <form action="requestAdd.action">
                <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                <label for="item">商品名称:</label>
                <span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
                <select id="item" name="item" style="width:160px;">
                    <c:forEach items="${ requestScope.items }" var="item">
                        <c:if test="${item.avePrice > 0}">
                            <option value="${ item.getItemId() }">${ item.getItemName() }</option>
                        </c:if>
                    </c:forEach>
                </select>

                <br /><br />
                <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                <label for="number" >数量 :</label>
                <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                <input type="text" id="number" name="number">

                <br /><br /><br /><br />
                <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                申请理由：
                <br /><br />
                <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                <textarea rows="12" cols="80%" style="margin-left: 39px;" name="reason" id="reason" ></textarea>
                <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                <br /><br />
                <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                <input type="submit" value="添加">
                <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                <input type="reset" value="清空">
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
            <br />
        </div>
    </section>
</body>
</html>
