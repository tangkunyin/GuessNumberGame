<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<title>欢迎K游戏管理 - 登录首页</title>
<!--[if lt IE 9]>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/js/css3-mediaqueries.js"></script>
<![endif]-->
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/js/respond.src.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/js/adminHtml.js"></script>
<link rel="stylesheet" media="screen and (min-width: 641px)" href="${pageContext.request.contextPath}/resources/common/css/admin-desktop.css" />
<link rel="stylesheet" media="screen and (min-width: 320px) and (max-width: 640px)" href="${pageContext.request.contextPath}/resources/common/css/admin-mobile.css" />
</head>
<body>
 <c:choose>
 	<c:when test="${sessionScope.currentUser!=null}">
 		<c:if test="${sessionScope.currentUser.role==1}">
 			<c:redirect url="/admin/main.jsp" />
 		</c:if>
 		<c:if test="${sessionScope.currentUser.role==3}">
 			<c:redirect url="/admin/KJInput.jsp" />
 		</c:if>
 	</c:when>
 	<c:otherwise>
 		<div id="msg"><c:out value="${errorMsg}" /></div>
	<div id="login">
    	<form action="${pageContext.request.contextPath}/Login.ktv?method=AdminLogin" id="mylogin" method="post">
       	  <p>用户名：<input type="text"  name="userName" id="uname"/></p>
          <p>密&nbsp;&nbsp;码：<input type="password" name="repassword" id="pword"/></p>
          <p style="text-align:center;">身&nbsp;&nbsp;份：
          	<select name="role" id="role">
				<option value="" selected>==请选登录身份==</option>
				<option value="3">摇奖录入</option>
				<option value="2">主管编辑</option>
				<option value="1">系统管理员</option>
			</select>
          </p>
            <p><input type="reset" value="重置" />&nbsp;&nbsp;
          &nbsp;&nbsp;<input type="button" id="subtt" value="登录" /></p>
        </form>
    </div>
 	</c:otherwise>
 </c:choose>
</body>
</html>