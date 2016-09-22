<%--
  Created by IntelliJ IDEA.
  User: silence
  Date: 16/4/25
  Time: 下午11:05
  增加供应商的信息
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>采购员->增加商品</title>
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
                <a  class="current" href="#">添加商品</a>
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
                <h3>商品信息</h3>
            </header>
            <div class="form">
                <h3 style="text-align: center;">请输入物品信息</h3>
                <hr />
                <table class="table table-bordered">
                    <tr>
                        <td colspan="4">当前供应商ID:${requestScope.provider.providerId}</td>
                    </tr>
                    <tr>
                        <td class="active">供应商名称</td>
                        <td>${requestScope.provider.providerName}</td>
                        <td class="active">省份</td>
                        <td>${requestScope.provider.provinces}</td>
                    </tr>
                </table>
                <br>
                <hr />
                <form action="purchaseProviderItemAdd.action">
                    <input type="text" value="${requestScope.provider.providerId}" name="providerID" hidden="hidden">
                    <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                    <label for="itemName">物品名称</label>
                    <input type="text" id="itemName" name="itemName"/><br /><br />
                    <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                    <label for="price">物品单价</label>
                    <input type="text" id="price" name="price">
                    <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                    <label for="quality">物品质量&nbsp;</label>
                    <input type="text" id="quality" name="quality">
                    <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                    <br />
                    <hr>
                    <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                    <input type="submit" value="提交">
                    <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
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
                <h3>商品信息</h3>
            </header>
            <br />
            <p style="font-size: 1.8em;text-align: center;">质量统计</p>
            <hr />
            <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
            <label style="font-size: 1.3em">A质量个数:&nbsp;&nbsp;</label>
            <input type="text" readonly="readonly" value="${ requestScope.countA}">
            <br /><br />
            <span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
            <label style="font-size: 1.3em">B质量个数:&nbsp;&nbsp;</label>
            <input type="text" readonly="readonly" value="${ requestScope.countB}" />
            <br /><br />
            <span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
            <label style="font-size: 1.3em">C质量个数:&nbsp;&nbsp;</label>
            <input type="text" readonly="readonly" value="${ requestScope.countC }">
            <br />
        </div>

    </section>
</body>
</html>
