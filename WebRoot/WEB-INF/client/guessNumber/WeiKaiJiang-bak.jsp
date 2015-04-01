<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>用户已经填完-还未开奖</title>
<style type="text/css">
body { margin: 0px; padding: 0px; text-align: center; font-family: Arial, Helvetica, sans-serif; font-size: 14px; }
img {border:none}
#main { margin-left: auto; margin-right: auto; width: 640px; min-height: 960px; text-align: center; position: relative; background-image: url(${pageContext.request.contextPath}/resources/common/img/weikaibg.gif); background-attachment: scroll; background-repeat: no-repeat; background-position: center top; }
#nav{ float: left; width: 100%; height: 89px; background-color: #333; color: #FFF; }
#lefttime{ float: left; height: 279px; width: 640px; }
#top{ height: 45px; width: 580px; margin-right: auto; margin-left: auto; margin-top: 55px; line-height: 45px; font-weight: bold; font-size: 28px; }
#yourAnswer{ height: 53px; width: 385px; margin-right: auto; margin-left: auto; margin-top: 28px; line-height: 53px; text-align: right; font-size: 30px; font-weight: bold; padding-right: 55px; }
.acuser{ padding: 0px; float: left; height: 100px; width: 310px; margin-top: 10px; margin-bottom: 0px; margin-left: 10px; position: relative; }
.user{ float: left; height: 100px; width: 300px; }
.user .userImg{ float: left; height: 100px; width: 100px;}
.user .userInfo{ float: right; height: 100px; width: 195px; }
.userInfo .sex{ float: left; height: 30px; width: 30px; margin-top: 10px;}
.userInfo .nickname{ float: right; height: 30px; width: 160px; margin-top: 10px; font-weight: bold; font-size: 18px; line-height: 30px; text-align: left; padding-left: 5px; }
.userInfo .anwser{ height: 35px; width: 85px; background-color: #A5E1F8; text-align: center; font-size: 28px; line-height: 35px; font-weight: bold; margin-top: 15px; float: left; margin-left: 20px; }
</style>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/js/goBack.js" charset="utf-8"></script>
</head>
<body>
<div id="main">
  <div id="nav"><img src="${pageContext.request.contextPath}/resources/guessNumber/images/navbg.png" border="0" usemap="#Map"/>
    <map name="Map">
      <area shape="rect" coords="36,16,95,73" href="javascript:" id="goback" class="mylink">
      <area shape="rect" coords="506,11,627,80" href="${pageContext.request.contextPath}/GuessNum.ktv?action=moreGameInfo" class="mylink">
    </map>
  </div>
  <div id="lefttime">
  	<div id="top">
  		<c:if test="${KJTime==null}"><span style="color:red">该活动暂时无人参加</span></c:if>
  		<c:if test="${KJTime!=null}">开奖时间&nbsp;&nbsp;<span id="kjTime">${KJTime}</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;还剩 <span id="timer" style="color:red"></span></c:if>
  	</div>
    <div id="yourAnswer">${yourAnswer}</div>
    <!-- 定义隐藏表单，当开奖时间到了之后，把当前用户信息提交给服务器 -->
    <div style="display: none;">
    	<form action="${pageContext.request.contextPath}/GuessNum.ktv?action=showWinner" id="currentUserFOrm" method="post">
    		<input name="yourAnswer" id="yourAnswer" value="${yourAnswer}"/>
    	</form>
    </div>
  </div>
  <span style="display:none;" id="acCloseTime">${remainTimes}</span>
  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/js/timeRemains.js"></script>
  <c:choose>
  	<c:when test="${allAcUsers==null}">
  		<h2>该活动暂时无人参加，请返回</h2>
  	</c:when>
  	<c:otherwise>
  	<!--头像和各自的性别，答案-->
  		<c:forEach var="item" items="${allAcUsers}">
  	<div class="acuser">
  	<div class="user">
  	<div class="userImg"><img alt="头像" width="100px" height="100px" src="${item.headpicpath}" /></div>
    <div class="userInfo">
    	<c:if test="${item.sex==0}"><div class="sex" style="background-image: url(${pageContext.request.contextPath}/resources/common/img/genderImg.gif); background-position: left;"></div></c:if>
    	<c:if test="${item.sex==1}"><div class="sex" style="background-image: url(${pageContext.request.contextPath}/resources/common/img/genderImg.gif); background-position: right;"></div></c:if>
        <div class="nickname">${item.nickname}</div>
        <div class="anwser">${item.yourAnswer}</div>
    </div>
  </div>
  </div>
  </c:forEach>
  	</c:otherwise>
  </c:choose>
</div>
</body>
</html>