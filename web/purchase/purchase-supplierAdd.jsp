<%--
  Created by IntelliJ IDEA.
  User: silence
  Date: 16/5/19
  Time: 下午9:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>采购员->增加供应商</title>
    <link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../css/common.css">
    <link rel="stylesheet" type="text/css" href="../css/purchase.css">
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
                <a   class="user_status" href="#">采购部门</a>
                <div class="place_driver"></div>
                <a  class="current" href="#">增加供应商</a>
            </article>
        </div>
    </section>
    <%--function--%>
    <%@ include file="../common/purchaseFun.jsp" %>
    <%--main--%>
    <section id="main">
        <%--主要功能--%>
        <div id = "main_div">
            <header class="buyInfo">
                <h3>供应商列表</h3>
            </header>
            <div class="form">
                <h3 style="text-align: center;">请添加供应商信息</h3>
                <hr />
                <form action="purchaseProviderAdd.action">
                    <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                    <label for="providerName">供应商名称&nbsp;</label>
                    <input type="text" id="providerName" name="providerName">
                    <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                    <label for="provinces">供应商省份</label>
                    <input type="text" id="provinces" name="provinces">
                    <br /><br />
                    <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                    <label for="contant">联系人&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
                    <input type="text" id="contant" name="contant">
                    <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                    <label for="tele">联系电话&nbsp;&nbsp;&nbsp;&nbsp;</label>
                    <input type="text" id="tele" name="tele">
                    <br /><br /><hr />
                    <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                    详细地址：
                    <br /><br />
                    <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                    <textarea rows="8" cols="80%"  style="margin-left: 39px;" name="address" id="address"></textarea>
                    <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                    <br />
                    <hr />
                    <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                    <input type="submit" value="修改">
                    <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                    <input type="reset" value="重置">
                </form>
            </div>
        </div>
        <div id="side_div1">
            <header id="time">
                <h3>日历</h3>
            </header>
            <%@include file="../common/calendar.jsp"%>
        </div>
        <%--额外功能--%>
        <div id="side_div2">
            <header id="count">
                <h3>供应商统计</h3>
            </header>
            <table class="table table-striped table-hover table-bordered">
                <tr>
                    <td>省份</td>
                    <td>数量</td>
                </tr>

                <c:forEach items="${ requestScope.classal }" var="provider">
                    <tr>
                        <td>${provider.key}</td>
                        <td>${ provider.value}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </section>
</body>
</html>
