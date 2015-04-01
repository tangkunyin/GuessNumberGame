<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台首页-K友网后台游戏设置</title>
</head>
	<frameset rows="100,*" frameborder="no" border="0" framespacing="0">
       <frame src="${pageContext.request.contextPath}/admin/top.jsp"  name="topFram" scrolling="no" noresize="noresize"/>
       <frameset cols="250,*" frameborder="no" border="0" framespacing="0">
       		<frame src="${pageContext.request.contextPath}/admin/left.jsp" name="leftFram" scrolling="no" noresize="noresize"/>
            <frame src="${pageContext.request.contextPath}/admin/right.jsp" name="rightFram" scrolling="yes" noresize="noresize" />
       </frameset>
    </frameset>
</html>