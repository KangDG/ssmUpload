<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false" %>
<%
    String path = request.getContextPath();
    String basepath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/" + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <base href="<%=basepath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>欢迎</title>
</head>
<body>
<h3>登录成功,欢迎${sessionScope.user.realname}登录....</h3>
<img src="upload/${sessionScope.user.picpath}" width="100" height="100" border="1">
<a href="uc/downloadfile?picname=${sessionScope.user.picpath }">下载头像</a>
</body>
</html>