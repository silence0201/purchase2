<%--
  Created by IntelliJ IDEA.
  User: silence
  Date: 16/4/26
  Time: 上午9:18
   采购部门功能公用部分
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<aside id="side_bar">
    <div>
        <p id="alt">请选择</p>
        <hr>
    </div>
    <div class="function">
        <div id="status"><a href="stockman-status.jsp">库存状态</a></div>
        <div id="import"><a href="stockman-import.jsp">入库登记</a></div>
        <div id="export"><a href="stockman-export.jsp">出库登记</a></div>
        <div id="exit"><a href="#">退出登录</a></div>
    </div>
    <%@ include file="foot.jsp" %>
</aside>
