<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<title>摇奖号码-录入--双用户</title>
<style type="text/css">
body{ text-align: center; margin: 0px; padding: 0px; }
#mainBox{ height: 480px; width: 320px; margin-right: auto; margin-left: auto; padding: 0px; text-align: center; background-color: #6CF; }
#header{ height: auto; width: 100%; font-size: 18px; background-color: #333; color: #FFF; padding-top: 10px; float: left; position: relative; }
#main{ height: 85px; width: 100%; float: left; position: relative; font-size: 24px; }
#screen{ height: auto; width: 100%; margin-top: 10px; position: relative; float: left; margin-bottom: 5px; font-size: 22px; font-weight: bold; color: #F00; line-height: 30px; }
#num{width:100px; height: 30px; text-align:center; font-size:21px; font-weight: bold; color: red;}
#subbt {width:60px; height: 30px; font-size: 16px; }
#promot{ height: auto; width: 100%; float: left; position: relative; line-height: 30px; color: #F00; font-size: 16px; font-weight: bold; }
#winnerPreview { float: left; height: auto; width: 100%; position: relative; text-align: center; margin: 0px; padding: 0px; }
#leftUser{ float: left; height: 155px; width: 155px; margin-top: 8px; position: relative; background-color: #44C2FF; margin-left: 2px; }
#rightUser{ float: right; height: 155px; width: 155px; position: relative; margin-top: 8px; background-color: #44C2FF; }
.userImg{ float: left; height: 80px; width: 80px; position: relative; }
.userInfo{ float: right; height: 80px; width: 70px; position: relative; }
.nickName{ width: 100%; overflow: hidden; float: right; position: relative; text-align: left; height: 19px; font-size: 14px; }
.userSex{ float: right; width: 100%; position: relative; height: auto; margin-top: 8px; text-align: left; }
.answer{ float: right; width: 100%; margin-top: 8px; font-size: 18px; font-weight: bold; position: relative; text-align: left; }
.userForm{ float: left; height: auto; width: 155px; color: #F00; font-size: 18px; margin-top: 10px; position: relative; border-top-width: 1px; border-right-width: 1px; border-bottom-width: 1px; border-left-width: 1px; border-top-style: solid; border-top-color: #F00; }
.resetForm{ float: left; height: auto; width: 155px; margin-top: 10px; }
#confirm{ float: left; height: auto; width: 100%; text-align: center; position: relative; margin-top: 12px; }
#bottom { height: auto; width: 100%; background-color: #000; color: #FFF; float: left; position: relative; line-height: 30px; margin-top: 15px; }
a{ font-size: 17px; font-weight: bold; color: #FFF; text-decoration: none; }
.butts{ width: 80px; height: 28px; text-align: center; font-size: 14px; }
#next{ float: left; height: auto; width: 160px; position: relative; background-color: #3C0; margin-top: 15px; font-size: 26px; margin-left: 80px; }
</style>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/tv/js/KJ-doubleUser.js"></script>
</head>
<body>
<div id="mainBox">
	<div id="header">
   	  登录成功，来自<span style="font-weight:bold"><c:out value="${sessionScope.ktvName}" /></span>的管理员
        <p style="margin-top: 8px; margin-bottom: 10px; padding: 0px;">请为本活动选择获奖用户</p>
    </div>
    <div id="main">
      <div id="screen">摇奖号码为：</div>
   	  <p style="margin: 0px; padding: 0px;"><input name="num" id="num" value="${rightAnswer}" disabled="disabled" /></p>
    </div>
    <div id="winnerPreview">
    <!-- 提示信息 -->
    <div id="promot">获奖用户已产生，请予以通知</div>
   	  <!-- 两个用户 -->
     <div id="leftUser">
            <div class="userImg"><img id="luserImg" width="80px" height="80px"/></div>
            <div class="userInfo">
              	<div class="nickName"><span style="background-color:#F63;" id="lnickName"></span></div>
               	<div class="userSex"><span style="background-color:#F63" id="lsex"></span></div>
                <div class="answer"><span style="background-color:#F63" id="lanswer"></span></div>
      		</div>
            <div class="userForm">一般中奖用户</div>
    		<div class="resetForm">
              <input type="button" value="重新选择" id="lreselectButt" class="butts"/>
        	</div>
      </div>
      <div id="rightUser">
      		 <div class="userImg"><img id="ruserImg" width="80px" height="80px"/></div>
            <div class="userInfo">
              	<div class="nickName"><span style="background-color:#F63"  id="rnickName"></span></div>
                <div class="userSex"><span style="background-color:#F63" id="rsex"></span></div>
                <div class="answer"><span style="background-color:#F63" id="ranswer"></span></div>
      		</div>
            <div class="userForm">一般中奖用户</div>
    		<div class="resetForm">
              <input type="button" value="重新选择" id="rreselectButt" class="butts"/>
        	</div>
      </div>
      <div id="confirm">
        <input type="button" value="显示到TV" id="doubleOkButt" class="butts"/>
        &nbsp;&nbsp;
        &nbsp;&nbsp;
        <input type="button" value="锁定用户" id="LockUserButt" class="butts"/>
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