<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>

<%
    String path = request.getContextPath();
    String basepath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/" + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <base href="<%=basepath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>注册</title>
</head>
<body>
<h3>注册页面</h3>
<form action="uc/add" enctype="multipart/form-data" method="post">
    用户名:<input type="text" name="username"/><br>
    密码:<input type="password" name="password"/><br>
    真实姓名:<input type="text" name="realname"/><br>
    <%--注意这里的file组件的name属性值--%>
    上传头像:<input type="file" name="picfile"><br>
    出生日期：<input type="text" name="birthday"/>
    <input type="submit" value="注册"/>
</form>
已有账号请 <a href="uc/temp?req=login">登录</a>

</body>
</html>