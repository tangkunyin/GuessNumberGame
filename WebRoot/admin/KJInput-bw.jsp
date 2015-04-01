<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<title>摇奖号码-录入</title>
<style type="text/css">
html { -webkit-text-size-adjust:none;}
body { text-align: center; margin: 0px; padding: 0px; }
#mainBox{ height: 480px; width: 320px; margin-right: auto; margin-left: auto; padding: 0px; text-align: center; background-color: #6CF; }
#header{ height: auto; width: 100%; font-size: 18px; background-color: #333; color: #FFF; padding-top: 10px; float: left; position: relative; }
#main{ height: 180px; width: 100%; float: left; position: relative; font-size: 26px; }
#bottom { height: 30px; width: 100%; line-height: 30px; background-color: #000; color: #FFF; float: left; position: relative; }
#bottom a{ font-size: 17px; font-weight: bold; color: #FFF; text-decoration: none; }
#screen{ height: 45px; width: 100%; margin-top: 50px; position: relative; float: left; margin-bottom: 20px; font-size: 22px; line-height: 45px; font-weight: bold; color: #F00; }
#bnum{ width:66px; height: 30px; text-align:center; font-size:21px; font-weight: bold; color: red; }
#previous{ height: 175px; width: 100%; float: left; position: relative; text-align: center; }
#preButt{ font-size: 24px; text-decoration: underline; color: #F00; font-weight: bold; }
#nextButt{font-size: 24px; text-decoration: underline; color: #F00; font-weight: bold;}
#subbt { width:70px; height: 45px; font-size: 18px; }
</style>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/tv/js/yaojiang-submit.js"></script>
</head>
<body>
<div id="mainBox">
	<div id="header">
   	  登录成功，来自<span style="font-weight:bold"><c:out value="${sessionScope.ktvName}" /></span>的管理员
        <p>请为本活动设置开奖答案</p>
    </div>
    <div id="main">
      <div id="screen">请输入百位数：</div>
   	  <p><input name="num" id="bnum" maxlength="1">&nbsp;&nbsp;<input type="button" value="完成" id="bwsubbt"/></p>
    </div>
     <!--  百位数字输入完毕后，提示进入下一步 -->
    <div id="previous">
		  <a id="preButt">上一步</a><br/><br/><br/>
		  <a id="nextButt" style="display: none;">下一步</a>
  	</div>
<div id="bottom">
    	<a href="">修改密码</a> -- <a href="${pageContext.request.contextPath}/Login.ktv?method=AdminLoginOut">退出登录</a>
    </div>
</div>
</body>
</html>