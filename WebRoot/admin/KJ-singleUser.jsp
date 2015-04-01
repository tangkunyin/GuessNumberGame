<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<title>摇奖号码-录入--单一用户</title>
<style type="text/css">
body { text-align: center; margin: 0px; padding: 0px; }
#mainBox{ height: 480px; width: 320px; margin-right: auto; margin-left: auto; padding: 0px; text-align: center; background-color: #6CF; }
#header{ height: auto; width: 100%; font-size: 18px; background-color: #333; color: #FFF; padding-top: 15px; float: left; position: relative; }
#main{ height: auto; width: 100%; float: left; position: relative; font-size: 26px; }
#screen{ height: auto; width: 100%; margin-top: 10px; position: relative; float: left; margin-bottom: 5px; font-size: 22px; font-weight: bold; color: #F00; line-height: 30px; }
#num{width:100px; height: 30px; text-align:center; font-size:21px; font-weight: bold; color: red;}
#subbt {width:60px; height: 30px; font-size: 16px; }
#promot{ height: auto; width: 100%; float: left; position: relative; line-height: 30px; color: #F00; font-size: 16px; font-weight: bold; }
#winnerPreview { float: left; height: 250px; width: 100%; position: relative; text-align: center; }
#user{ width: 300px; background-color: #FFF; float: left; position: relative; height: 110px; margin-top: 10px; border: 2px dashed #666; margin-left: 8px; }
#userImg{ height: 80px; width: 80px; float: left; position: relative; }
#userInfo{ width: 200px; float: right; height: 80px; position: relative; }
#nickName{ width: 100%; overflow: hidden; float: right; position: relative; margin-bottom: 5px; font-size: 18px; color: #333; height: 25px; line-height: 25px; }
#userSex{ float: right; height: 25px; width: 100%; position: relative; margin-bottom: 5px; line-height: 25px; }
#answer{ float: right; width: 100%; position: relative; font-size: 18px; height: 20px; line-height: 20px; font-weight: bold; color: #000; }
#userForm{ float: left; height: auto; width: 296px; position: relative; margin-top: 4px; margin-left: 2px; border-top-width: 1px; border-top-style: solid; border-top-color: #F00; color: red; }
#confirm{ float: left; width: 100%; position: relative; height: auto; margin-top: 10px; }
#bottom { height: auto; width: 100%; line-height: 30px; background-color: #000; color: #FFF; float: left; position: relative; margin-top: 8px; }
a{ font-size: 17px; font-weight: bold; color: #FFF; text-decoration: none; }
.butts{ width: 80px; height: 30px; font-size: 14px; text-align: center; }
#next{ float: left; height: 35px; width: 180px; position: relative; background-color: #3C0; margin-left: 70px; margin-top: 15px; font-size: 26px; line-height: 35px; font-weight: bold; }
</style>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/tv/js/KJ-singleUser.js"></script>
</head>
<body>
<div id="mainBox">
	<div id="header">
   	  登录成功，来自<span style="font-weight:bold"><c:out value="${sessionScope.ktvName}" /></span>的管理员
        <p>请为本活动选择获奖用户</p>
    </div>
    <div id="main">
      <div id="screen">摇奖号码为：</div>
   	  <p><input name="num" id="num" value="${rightAnswer}" disabled="disabled"></p>
    </div>
    <div id="winnerPreview">
    <!-- 提示信息 -->
    <div id="promot">获奖用户已产生，请予以通知</div>
   	  <!-- 标准用户 -->
      <div id="user">
      	<div id="userImg"><img src="${specialUser.headpicpath}" width="80px" height="80px"/></div>
        <div id="userInfo">
        	<div id="nickName">昵称：<span style="background-color:#F63">${specialUser.nickname}</span></div>
<c:if test="${specialUser.sex==0}"><div id="userSex">性别：<span style="background-color:#F63">男</span></div></c:if>
<c:if test="${specialUser.sex==1}"><div id="userSex">性别：<span style="background-color:#F63">女</span></div></c:if>
<div id="answer">答案：<span style="background-color:#F63" id="uanswer">${specialUser.yourAnswer}</span></div>
        </div>
        <div id="userForm">特殊中奖用户</div>
      </div>
      
      <div id="confirm">
      	<input type="button" value="重新选择" id="reselectButt" class="butts"/>&nbsp;&nbsp;
      	&nbsp;&nbsp;<input type="button" value="显示到TV" id="singleOkButt" class="butts"/>
        &nbsp;&nbsp;<input type="button" value="锁定用户" id="LockUserButt" class="butts"/>
      </div>
      
      <!-- 隐藏域 -->
      <div id="next" style="display:none;">继续开奖</div>
    </div>
    
<div id="bottom">
    	<a href="">修改密码</a> -- <a href="${pageContext.request.contextPath}/Login.ktv?method=AdminLoginOut">退出登录</a>
    </div>
</div>
</body>
</html>