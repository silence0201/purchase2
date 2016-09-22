<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: silence
  Date: 16/4/25
  Time: 下午9:09
  供应商的详细信息
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>采购员->供应商信息</title>
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
                <a  class="current" href="#">供应商信息</a>
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
                <h3>供应商信息</h3>
            </header>
            <h4>&nbsp;&nbsp;供应商编号:${requestScope.provider.providerId} </h4>
            <h4>&nbsp;&nbsp;所属省份：${requestScope.provider.provinces}</h4>
            <hr />
            <table class="table table-bordered">
                <tr>
                    <td class="active">供应商编号</td>
                    <td>${requestScope.provider.providerId}</td>
                    <td class="active">供应商名称</td>
                    <td>${requestScope.provider.providerName}</td>
                </tr>

                <tr>
                    <td class="active">联系人</td>
                    <td>${requestScope.provider.contant}</td>
                    <td class="active">联系方式</td>
                    <td>${requestScope.provider.tele}</td>
                </tr>
                <tr>
                    <td class="active">详细地址</td>
                    <td colspan="3">${requestScope.provider.getProvinces().concat(requestScope.provider.getAddress())}</td>
                </tr>
            </table>
            <hr />
            <h4>&nbsp;&nbsp;供应商品信息 </h4>
            <table class="table table-striped table-hover table-bordered">
                <tr>

                    <td>编号</td>
                    <td>商品名称</td>
                    <td>库存</td>
                    <td>价格</td>
                    <td>质量</td>
                    <td>&nbsp;</td>
                </tr>
            <c:forEach items="${requestScope.providerItems}" var="providerItem" varStatus="status">
                <c:if test="${ providerItem.status.equals('有效') }">
                    <tr>
                        <td>${providerItem.item.itemId}</td>
                        <td>${providerItem.item.itemName}</td>
                        <td>${providerItem.item.inventory}</td>
                        <td>${providerItem.price}</td>
                        <td>${providerItem.quality}</td>
                        <td><a href="purchaseInitProviderItemModify.action?providerItemID=${providerItem.providerItemId}">&nbsp;修改</a>&nbsp;
                            <a href="purchaseProviderItemDel.action?providerItemID=${providerItem.providerItemId}">删除</a>
                        </td>
                    </tr>
                </c:if>
            </c:forEach>
            </table>
            <hr />
            <span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
            <button name="addItem"><a href="purchaseInitProviderItemAdd.action?providerID=${requestScope.provider.providerId}">添加商品</a></button>
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
                <h3>商品信息统计</h3>
            </header>
            <br />
            <p style="font-size: 1.8em;text-align: center;">质量统计</p>
            <hr />
            <br />
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
