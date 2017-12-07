<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%
    String path = request.getContextPath();
    String basepath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/"+path+"/";
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basepath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<h3>登录页面</h3>
<form action="uc/login" method="post">
用户名:<input type= "text" value="zhangsan" name = "username"/><br>
密码:<input type="password" value="123" name = "password"/><br>
<input type="submit" value="登录"/>
</form>
还没有账号请 <a href="uc/temp?req=add">注册</a>
</body>
</html>