<%--
  Created by IntelliJ IDEA.
  User: silence
  Date: 16/4/25
  Time: 下午3:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>欢迎登陆采购管理系统</title>
    <%--加载样式表--%>
    <link href="css/login.css" rel="stylesheet" type="text/css">
</head>
<body>
    <div class="main-login">
        <%--Logo--%>
        <div class="login-logo"></div>
        <%--登陆页面--%>
        <div class="login-content">
            <form action="" method="post" id="login-form" name="login-form">
                <div class="login-info">
                    <span class="user">&nbsp;</span>
                    <input name="userID" id="userID" type="text" value="" class="login-input" />
                </div>
                <div class="login-info">
                    <span class="pwd">&nbsp;</span>
                    <input name="password" id="password" type="password"  value="" class="login-input" />
                </div>
                <div class="login-oper">
                    <input id="remember_me" type="checkbox" />
                    记住密码
                </div>
                <div class="login-oper">
                    <input id="login" type="button" value="登 录" class="login-btn" onclick=""/>
                    <input name="" type="reset" value="重 置" class="login-reset"/>
                </div>
            </form>
        </div>
        <div class="bottom">技术支持：********有限公司&nbsp;&nbsp;&nbsp;&nbsp;</div>
    </div>
</body>
</html>
