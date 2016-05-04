<%--
  Created by IntelliJ IDEA.
  User: silence
  Date: 16/4/26
  Time: 上午10:46
  总经理待办事项
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>总经理->通知</title>
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
            <p>王XX（
                <a href="">3个未处理</a>
                ）
            </p>
        </div>
        <div class="web_palce">
            <article class="place">
                <a   class="user_status" href="#">部门经理</a>
                <div class="place_driver"></div>
                <a  class="current" href="#">待办事项</a>
            </article>
        </div>
    </section>
    <%--Function--%>
    <%@include file="../common/manager.jsp"%>
    <%--main--%>
    <section id="main">
        <div id = "main_div">
            <header class="notice">
                <h3>通知</h3>
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
                <h3>最近记录</h3>
            </header>
        </div>
    </section>
</body>
</html>
