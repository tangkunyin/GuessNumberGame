<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<title>欢迎K游戏管理 - 登录首页</title>
<style type="text/css">
html {  -webkit-text-size-adjust：none;
}
body { margin: 0px; padding: 0px; font-family: Verdana, Geneva, sans-serif; background-color: #A4E1FF; text-align: center; }
form{ margin: 0px; padding: 0px; width: 280px; }
p { padding: 0px; margin-top: 0px; margin-right: 0px; margin-bottom: 10px; margin-left: 0px; font-size: 18px; }
#msg { width: 280px; height: auto; margin-top: 100px; margin-right: auto; margin-left: auto; text-align: center; }
#login { padding-top: 15px; height: 120px; width: 280px; margin-right: auto; margin-left: auto; text-align: center; border: 1px solid #6CF; }
#uname { width:120px; height: 20px; font-size: 16px; }
#pword { width:120px; height: 20px; font-size: 16px; }
.butts{ width: 60px; height: 30px; font-size: 15px; }
</style>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/js/adminHtml.js"></script>
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
    <div id="msg">
      <c:out value="${errorMsg}" />
    </div>
    <div id="login">
      <form action="${pageContext.request.contextPath}/Login.ktv?method=AdminLogin" id="mylogin" method="post">
        <p>用户名：
          <input type="text"  name="userName" id="uname"/>
        </p>
        <p>密&nbsp;&nbsp;码：
          <input type="password" name="repassword" id="pword"/>
        </p>
        <p style="text-align:center; display:none;">身&nbsp;&nbsp;份：
          <select name="role" id="role">
            <option value="3" selected>摇奖录入</option>
          </select>
        </p>
        <p>
          <input type="reset" value="重置" class="butts"/>
          &nbsp;&nbsp;
          &nbsp;&nbsp;
          <input type="button" id="subtt" value="登录" class="butts" />
        </p>
      </form>
    </div>
  </c:otherwise>
</c:choose>
</body>
</html>