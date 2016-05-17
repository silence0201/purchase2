<%--
  Created by IntelliJ IDEA.
  User: silence
  Date: 16/4/26
  Time: 上午10:38
  对需求单进行审核处理
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>部门经理->审核处理</title>
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
            <p>${ sessionScope.user.userName }（
                <a href="">${ sessionScope.count }个未处理</a>
                ）
            </p>
        </div>
        <div class="web_palce">
            <article class="place">
                <a   class="user_status" href="divManagerNotice.action">部门经理</a>
                <div class="place_driver"></div>
                <a   class="user_status" href="divManagerNotice.action">待办事项</a>
                <div class="place_driver"></div>
                <a  class="current" href="#">审核处理</a>
            </article>
        </div>
    </section>
    <%--Function--%>
    <%@include file="../common/divManagerFun.jsp"%>
    <%--main--%>
    <section id="main">
        <div id="main_div">
            <header class="notice">
                <h3>需求信息</h3>
            </header>
            <h4>&nbsp;&nbsp;处理的申请单号是:${ requestScope.request.requestId } </h4>
            <hr/>
            <h4>&nbsp;详细信息:</h4>
            <table class="table table-bordered">
                <tr>
                    <td class="active">申请人</td>
                    <td>${ requestScope.request.requestMan.userName }</td>
                    <td class="active">申请时间</td>
                    <td>${requestScope.request.requestTime}</td>
                </tr>
                <tr>
                    <td class="active">申请物品</td>
                    <td>${ requestScope.request.item.itemName}</td>
                    <td class="active">申请数量</td>
                    <td>${ requestScope.request.number}</td>
                </tr>
                <tr>
                    <td class="active">物品单价</td>
                    <td>${ requestScope.request.item.avePrice }</td>
                    <td class="active">总金额</td>
                    <td>${ requestScope.request.totalCost }</td>
                </tr>
                <tr>
                    <td class="active">理由</td>
                    <td colspan="3">${ requestScope.request.reason }</td>
                </tr>
            </table>
            <hr/>
            <br>
            <h4>&nbsp;处理结果:</h4>
            <br />
            <form action="divManagerHandle.action">
                <s:textfield value="%{ #request.requestId }" name="requestID" hidden="true"/>
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

        <div id="side_div2">
            <header id="curRecord">
                <h3>最近审核记录</h3>
            </header>
            <table class="table table-striped table-hover table-bordered">
                <tr>
                    <td><b>需求单编号</b></td>
                    <td><b>物品</b></td>
                    <td><b>数量</b></td>
                    <td><b>审批时间</b></td>
                </tr>
                <c:forEach items="${ requestScope.checkList }" var="check" varStatus="status">
                    <c:if test="${ status.index < 8 }">
                        <tr>
                            <td>${ check.getRequestId() }</td>
                            <td>${ check.getItem().getItemName() }</td>
                            <td>${ check.getNumber() }</td>
                            <td>${ check.getAuditTime() }</td>
                        </tr>
                    </c:if>
                </c:forEach>
            </table>
        </div>
    </section>
</body>
</html>