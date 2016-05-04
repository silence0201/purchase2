<%--
  Created by IntelliJ IDEA.
  User: silence
  Date: 16/4/26
  Time: 上午10:59
  总经理对整个公司最近申请信息查看
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>部门经理->申请查看</title>
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
            <p>王XX（
                <a href="">3个未处理</a>
                ）
            </p>
        </div>
        <div class="web_palce">
            <article class="place">
                <a   class="user_status" href="#">部门经理</a>
                <div class="place_driver"></div>
                <a  class="current" href="#">申请查看</a>
            </article>
        </div>
    </section>
    <%--Function--%>
    <%@include file="../common/divManagerFun.jsp"%>
    <%--main--%>
    <section id="main">
        <div id = "main_div">
            <header class="notice">
                <h3>申请列表</h3>
            </header>
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
        </div>
    </section>
</body>
</html>
