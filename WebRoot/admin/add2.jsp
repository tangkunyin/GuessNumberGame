<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>add2</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
body { margin: 0px; padding: 0px; text-align: center; font-family: Arial, Helvetica, sans-serif; font-size: 14px; }
img {border:none}
#main{ margin-left: auto; margin-right: auto; width: 750px; height: 620px; }
#qrimg{ width:260px; height:260px; margin-top:130px; float: right; position: relative; }
#texts{ width:370px; height:480px; margin-left: auto; margin-right: auto; margin-top:15px; float: left; position: relative; }
#buttArea{ width: 100%; height: 100px; margin-top: 20px; float: left; position: relative; }
</style>
</head>
<body>
<c:choose>
	<c:when test="${sessionScope.gameInfo==null}">
		<h2 style="color: red;">请勿重复提交</h2>
	</c:when>
	<c:otherwise>
		<div id="main">
  <!-- 二维码及预览 -->
  	<div id="qrimg"><img alt="二维码" src="${pageContext.request.contextPath}/${sessionScope.gameInfo.qrcodeImg}" width="260px" height="260px"/></div>
  	<div id="texts">
    	<h2>操作说明：</h2>
      <p>将用手机上的摄像头对准二维码图像，uc浏览器将会直接解码并打开里面的网址：</p>
    	<a href="http://jingyan.baidu.com/article/851fbc3706c56f3e1e15ab7f.html" target="_blank" title="使用指导"><img src="${pageContext.request.contextPath}/resources/common/img/qrdemo.gif" width="285" height="301"></a>
        <p style="color:#F00">注意：没有摄像头的手机不支持此程序。</p>
  </div>
  	<div id="buttArea">
  		<img src="${pageContext.request.contextPath}/resources/common/img/add2_button.gif" border="0" usemap="#Map5" />
        <map name="Map5">
          <area shape="rect" coords="23,15,184,67" href="${pageContext.request.contextPath}/addGame.ktv?method=cancelAddGuessNumber">
          <area shape="rect" coords="203,15,364,67" href="${pageContext.request.contextPath}/addGame.ktv?method=addGuessNumber">
      </map>
  </div>
</div>
	</c:otherwise>
</c:choose>
</body>
</html>