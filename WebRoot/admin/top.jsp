<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>top</title>
<style type="text/css">
body { background-color: #F2F2F2; }
#top{ padding: 0px; height: auto; width: 1000px; margin-right: auto; margin-left: auto; text-align: right; }
#topwords{ color: #000; font-size: 20px; font-style: italic; line-height: 150px; }
</style>
</head>
<body>
	<div id="top">
        <span id="topwords">欢迎您，<span style="color:red">${sessionScope.currentUser.userName}</span>。
       	<c:if test="${sessionScope.addr!=null }">
       		来自，${sessionScope.addr.country},${sessionScope.addr.region},${sessionScope.addr.city},${sessionScope.addr.isp}网络。
       	</c:if>
       	<c:if test="${sessionScope.addr==null }">IP地址：<%=request.getRemoteAddr() %></c:if>
        &nbsp;&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/Login.ktv?method=AdminLoginOut" target="_top" title="退出后台">退出登录</a>
        </span>
    </div>
</body>
</html>