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
        <div id="notify"><a href="divManagerNotice.action">待办事项</a></div>
        <div id="query"><a href="divManagerList.action">需求查看</a></div>
        <div id="work"><a href="divManagerWork.action">业务查看</a></div>
        <div id="exit"><a href="divManagerLogout.action">退出登录</a></div>
    </div>
    <%@ include file="foot.jsp" %>
</aside>
