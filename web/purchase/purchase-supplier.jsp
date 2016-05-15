
<%--
  Created by IntelliJ IDEA.
  User: silence
  Date: 16/4/25
  Time: 下午8:37
  供应商列表页面
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>采购员->供应商管理</title>
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
                <a  class="current" href="#">供应商</a>
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
            <table class="table table-striped table-hover table-bordered">
                <tr>
                    <td>编号</td>
                    <td>供应商名称</td>
                    <td>联系人</td>
                    <td>联系电话</td>
                    <td>详细地址</td>
                    <td>&nbsp;</td>
                </tr>

                <c:forEach items="${ requestScope.suppliers }" var="provider" varStatus="status">
                    <c:if test="${ status.index < 15}">
                        <tr>
                            <td>${ provider.getProviderId() }</td>
                            <td>${ provider.getProviderName() }</td>
                            <td>${ provider.getContant()}</td>
                            <td>${ provider.getTele() }</td>
                            <td>${ provider.getProvinces().concat(provider.getAddress()) }</td>
                            <td><a href="">详情</a>&nbsp;<a href="">修改</a>
                                &nbsp;<a href="">删除</a></td>
                        </tr>
                    </c:if>
                </c:forEach>
            </table>
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
