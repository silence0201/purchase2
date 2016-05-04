<%--
  Created by IntelliJ IDEA.
  User: silence
  Date: 16/4/26
  Time: 上午10:24
  部门经理公用功能选择.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<aside id="side_bar">
    <div>
        <p id="alt">请选择</p>
        <hr>
    </div>
    <div class="function">
        <div id="notify"><a href="divManager-notice.jsp">待办事项</a></div>
        <div id="query"><a href="divManager-query.jsp">需求查看</a></div>
        <div id="work"><a href="divManager-work.jsp">业务查看</a></div>
        <div id="exit"><a href="#">退出登录</a></div>
    </div>
    <%@ include file="foot.jsp" %>
</aside>
