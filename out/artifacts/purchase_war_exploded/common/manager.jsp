<%--
  Created by IntelliJ IDEA.
  User: silence
  Date: 16/4/26
  Time: 上午10:51
  总经理公共功能部分
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<aside id="side_bar">
    <div>
        <p id="alt">请选择</p>
        <hr>
    </div>
    <div class="function">
        <div id="notify"><a href="manager-notice.jsp">待办事项</a></div>
        <div id="query"><a href="manager-query.jsp">申请查看</a></div>
        <div id="work"><a href="manager-work.jsp">审核查看</a></div>
        <div id="exit"><a href="">退出登录</a></div>
    </div>
    <%@ include file="foot.jsp" %>
</aside>
