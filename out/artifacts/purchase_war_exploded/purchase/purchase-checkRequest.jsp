<%--
  Created by IntelliJ IDEA.
  User: silence
  Date: 16/4/25
  Time: 下午8:32
  采购员需求审核页面
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>采购员->需求详情</title>
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
                <a  class="current" href="#">需求审核</a>
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
                <h3>需求信息</h3>
            </header>

            <br />
            <h4>&nbsp;&nbsp;申请的申请单号是:${requestScope.check.requestId} </h4>
            <h4>&nbsp;&nbsp;处理结果：${requestScope.check.requestStatus}</h4>
            <hr>
            <h4>详细信息:</h4>
            <table class="table table-bordered">
                <tr>
                    <td class="active">申请人</td>
                    <td>${requestScope.check.requestMan.userName}</td>
                    <td class="active">申请时间</td>
                    <td>${requestScope.check.requestTime}</td>
                </tr>
                <tr>
                    <td class="active">申请物品</td>
                    <td>${requestScope.check.item.itemName}</td>
                    <td class="active">申请数量</td>
                    <td>${requestScope.check.number}</td>
                </tr>
                <tr>
                    <td class="active">物品单价</td>
                    <td>${requestScope.check.item.avePrice}</td>
                    <td class="active">总金额</td>
                    <td>${requestScope.check.totalCost}</td>
                </tr>
                <tr>
                    <td class="active">理由</td>
                    <td colspan="3">${ requestScope.check.reason }</td>
                </tr>
            </table>

            <hr/>
            <br>
            <h4>&nbsp;处理结果:</h4>
            <br />
            <form action="purchaseRequestHandle.action">
                <input name="requestID" value="${requestScope.check.requestId}" hidden="hidden">
                <label>&nbsp;&nbsp;&nbsp;处理意见:&nbsp;&nbsp;&nbsp;</label>
                <input type="radio" name="status" value="通过"  checked/>通过
                <input type="radio" name="status" value="拒绝" />拒绝<br />
                <label>&nbsp;&nbsp;&nbsp;处理理由:&nbsp;&nbsp;&nbsp;</label><br />
                <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                <textarea name="reason" rows="5" cols="80"></textarea><br /><br />
                <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                <input type="submit" value="提交">
                <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                <input type="reset" value="清空">
            </form>
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
                <h3>最近采购记录</h3>
            </header>
            <c:if test="${ requestScope.checkList.size() != 0}">
                <h4>&nbsp;&nbsp;审核</h4>
                <table class="table table-striped table-hover table-bordered">
                    <tr>
                        <td>需求单编号</td>
                        <td>商品</td>
                        <td>数量</td>
                        <td>申请时间</td>
                    </tr>

                    <c:forEach items="${requestScope.checkList}" var="check" varStatus="status">
                        <c:if test="${ status.index < 2}">
                            <tr>
                                <td>${ check.getRequestId() }</td>
                                <td>${ check.getItem().getItemName() }</td>
                                <td>${ check.getNumber() }</td>
                                <td>${ check.getRequestTime() }</td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </table>
            </c:if>

            <c:if test="${ requestScope.orderList.size() != 0}">
                <h4>&nbsp;&nbsp;采购</h4>
                <table class="table table-striped table-hover table-bordered">
                    <tr>
                        <td>订单编号</td>
                        <td>商品</td>
                        <td>数量</td>
                        <td>采购时间</td>
                    </tr>

                    <c:forEach items="${requestScope.orderList}" var="arder" varStatus="status">
                        <c:if test="${ status.index < 2}">
                            <tr>
                                <td>${ arder.getOrderId() }</td>
                                <td>${ arder.getPlan().getItem().getItemName()}</td>
                                <td>${ arder.getPlan().getNumber() }</td>
                                <td>${ arder.getOrderTime() }</td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </table>
            </c:if>
        </div>
    </section>
</body>
</html>
