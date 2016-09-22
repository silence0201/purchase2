<%--
  Created by IntelliJ IDEA.
  User: silence
  Date: 16/5/19
  Time: 下午7:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>库管员->登记</title>
    <link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../css/common.css">
    <link rel="stylesheet" type="text/css" href="../css/stock.css">
</head>
<body>
<%--firsthead--%>
<%@ include file="../common/head.jsp" %>

<%--secondhead--%>
<section id="second_bar">
    <div class="user">
        <p>${ sessionScope.user.userName }</p>
    </div>
    <div class="web_palce">
        <article class="place">
            <a   class="user_status" href="stockStatus.action">库管员</a>
            <div class="place_driver"></div>
            <c:if test="${requestScope.r == null && requestScope.o == null}">
                <a  class="current" href="#">登记信息</a>
            </c:if>
            <c:if test="${requestScope.r != null}">
                <a  class="current" href="#">出库登记</a>
            </c:if>
            <c:if test="${requestScope.o != null}">
                <a  class="current" href="#">入库登记</a>
            </c:if>
        </article>
    </div>
</section>
<%--Function--%>
<%@include file="../common/stockFun.jsp"%>

<%--main--%>
<section id="main">
    <%--主要功能区--%>
    <div id = "main_div">
        <header class="storeStatus">
            <c:if test="${requestScope.r == null && requestScope.o == null}">
                <h3>登记信息</h3>
            </c:if>
            <c:if test="${requestScope.r != null}">
                <h3>出库信息</h3>
            </c:if>
            <c:if test="${requestScope.o != null}">
                <h3>入库信息</h3>
            </c:if>
        </header>
        <c:if test="${requestScope.r == null && requestScope.o == null}">
            <%--出库登记--%>
            <h4>&nbsp;&nbsp;出库登记:</h4>
            <form action="initExport.action">
                <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                <label for="requestID">需求编号: &nbsp;&nbsp;&nbsp;&nbsp;</label>
                <input type="text" id="requestID" name="requestID">
                <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                <input type="submit">
                <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                <input type="reset" value="清空">
            </form>
            <hr />
            <%--入库登记--%>
            <h4>&nbsp;&nbsp;入库登记:</h4>
            <form action="initImport.action">
                <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                <label for="orderID">订单编号: &nbsp;&nbsp;&nbsp;&nbsp;</label>
                <input type="text" id="orderID" name="orderID">
                <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                <input type="submit">
                <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                <input type="reset" value="清空">
            </form>
        </c:if>

        <%--出库信息确认--%>
        <c:if test="${requestScope.r != null}">
            <c:if test="${requestScope.r.item !=null}">

                <h4>&nbsp;&nbsp;申请单编号:${requestScope.r.requestId}</h4>
                <h4>&nbsp;&nbsp;申请单状态:${requestScope.r.requestStatus}</h4>
                <hr />
                <h4>&nbsp;&nbsp;请核对申请单信息:</h4>
                <table class="table table-bordered">
                    <tr>
                        <td class="active">申请人</td>
                        <td>${ requestScope.r.requestMan.userName}</td>
                        <td class="active">申请时间</td>
                        <td>${requestScope.r.requestTime}</td>
                    </tr>
                    <tr>
                        <td class="active">商品</td>
                        <td>${ requestScope.r.item.itemName}</td>
                        <td class="active">单价</td>
                        <td>${requestScope.r.item.avePrice}</td>
                    </tr>
                    <tr>
                        <td class="active">数量</td>
                        <td>${requestScope.r.number}</td>
                        <td class="active">总金额</td>
                        <td>${requestScope.r.totalCost}</td>
                    </tr>
                </table>
                <c:if test="${requestScope.r.requestStatus == '到货' || requestScope.r.requestStatus == '有货'}">
                    <form action="addExport.action">
                        <input value="${requestScope.r.requestId}" name="requestID" hidden="hidden" />
                        <span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
                        <input type="submit" value="确认">
                    </form>
                </c:if>
                <c:if test="${requestScope.r.requestStatus != '到货' && requestScope.r.requestStatus != '有货' }">
                    <p class="alert-info noticeCon" >请核查申请单的状态</p>
                </c:if>
            </c:if>
            <c:if test="${requestScope.r.item ==null}" >
                <p class="alert-info noticeCon" >请输入正确的申请单编号</p>
            </c:if>
        </c:if>

        <%--入库信息确认--%>
        <c:if test="${requestScope.o != null}">
            <c:if test="${requestScope.o.plan !=null}">

                <h4>&nbsp;&nbsp;订单编号:${requestScope.o.orderId}</h4>
                <h4>&nbsp;&nbsp;订单状态:${requestScope.o.orderStatus}</h4>
                <hr />
                <h4>&nbsp;&nbsp;请核对订单信息:</h4>

                <table class="table table-bordered">
                    <tr>
                        <td class="active">采购人</td>
                        <td>${ requestScope.o.orderMan.userName}</td>
                        <td class="active">采购时间</td>
                        <td>${requestScope.o.orderTime}</td>
                    </tr>
                    <tr>
                        <td class="active">商品</td>
                        <td>${ requestScope.o.provideritem.item.itemName}</td>
                        <td class="active">单价</td>
                        <td>${requestScope.o.provideritem.price}</td>
                    </tr>
                    <tr>
                        <td class="active">数量</td>
                        <td>${requestScope.o.plan.number}</td>
                        <td class="active">总金额</td>
                        <td>${requestScope.o.totalCost}</td>
                    </tr>
                </table>
                <c:if test="${requestScope.o.orderStatus == '采购中'}">
                    <form action="addImport.action">
                        <input value="${requestScope.o.orderId}" name="orderID" hidden="hidden" />
                        <span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
                        <input type="submit" value="确认">
                    </form>
                </c:if>
                <c:if test="${requestScope.o.orderStatus != '采购中' }">
                    <p class="alert-info noticeCon" >请核查申请单的状态</p>
                </c:if>
            </c:if>
            <c:if test="${requestScope.o.plan ==null}" >
                <p class="alert-info noticeCon" >请输入正确的申请单编号</p>
            </c:if>
        </c:if>

    </div>
    <div id="side_div1">
        <header id="time">
            <h3>时间</h3>
        </header>
        <%@include file="../common/calendar.jsp"%>
    </div>
    <%--额外功能--%>
    <div id="side_div2">
        <header id="curInfo">
            <h3>最近记录</h3>
        </header>
        <%--没有进行登记--%>
        <c:if test="${requestScope.r == null && requestScope.o == null}">
            <br />
            <p style="font-size: 1.8em;text-align: center;">月出入库统计</p>
            <hr />
            <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
            <label style="font-size: 1.3em">月入库次数:&nbsp;&nbsp;</label>
            <input type="text" readonly="readonly" value="${ requestScope.countOfImport }" />
            <br /><br /><br />
            <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
            <label style="font-size: 1.3em">月出库次数:&nbsp;&nbsp;</label>
            <input type="text" readonly="readonly" value="${requestScope.countOfExport}">
            <br /><br />
        </c:if>

        <%--显示最近出入记录--%>
        <c:if test="${ requestScope.r != null }">
            <table class="table table-striped table-hover table-bordered">
                <tr>
                    <td>需求编号</td>
                    <td>商品</td>
                    <td>数量</td>
                    <td>出库库时间</td>
                </tr>

                <c:forEach items="${ requestScope.exports }" var="export" varStatus="status">
                    <c:if test="${ status.index < 5}">
                        <tr>
                            <td>${ export.getRequest().getRequestId() }</td>
                            <td>${ export.getRequest().getItem().getItemName() }</td>
                            <td>${ export.getRequest().getNumber()}</td>
                            <td>${ export.getExportTime() }</td>
                        </tr>
                    </c:if>
                </c:forEach>
            </table>
        </c:if>
        <%--显示最近入库记录--%>
        <c:if test="${requestScope.o.plan !=null}">
            <table class="table table-striped table-hover table-bordered">
                <tr>
                    <td>订单编号</td>
                    <td>商品</td>
                    <td>数量</td>
                    <td>入库时间</td>
                </tr>
                <c:forEach items="${ requestScope.imports }" var="impart" varStatus="status">
                    <c:if test="${ status.index < 5 }">
                        <tr>
                            <td>${ impart.getOrder().getOrderId() }</td>
                            <td>${ impart.getOrder().getPlan().getItem().getItemName() }</td>
                            <td>${ impart.getOrder().getPlan().getNumber() }</td>
                            <td>${ impart.getImportTime()}</td>
                        </tr>
                    </c:if>
                </c:forEach>
            </table>
        </c:if>
    </div>
</section>
</body>
</html>
