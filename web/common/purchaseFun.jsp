<%--
  Created by IntelliJ IDEA.
  User: silence
  Date: 16/4/25
  Time: 下午8:02
  采购员的功能选择
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<aside id="side_bar">
    <div>
        <p id="alt">请选择</p>
        <hr>
    </div>
    <div class="function">
        <div id="notify"><a href="purchase-notice.jsp">待办事项</a></div>
        <div id="query"><a href="purchase-list.jsp">订单查看</a></div>
        <div id="support"><a href="purchase-supplier.jsp">供应商管理</a></div>
        <div id="exit" ><a href="">退出登录</a></div>
    </div>
    <%@ include file="foot.jsp" %>
</aside>

