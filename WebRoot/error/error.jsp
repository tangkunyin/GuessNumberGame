<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>请求资源未找到...</title>
</head>
<body>
<h1>${indexError}</h1>
<h2>访问路径不存在或资源不存在 - 点击返回首页</h2>
	<hr>
	<c:choose>
		<c:when test="${indexError==null}">
			<a href="${pageContext.request.contextPath}/">返回首页</a>
		</c:when>
		<c:otherwise>
			<a href="http://www.myktv.com">到K友看看</a>
		</c:otherwise>
	</c:choose>
</body>
</html>