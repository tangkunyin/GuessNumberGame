<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<title>已提交用户-还未开奖</title>
<style type="text/css">
body { margin: 0px; padding: 0px; text-align: center;}
img {border:none}
#main { margin-left: auto; margin-right: auto; width: 320px; height: 480px; text-align: center; background-image: url(${pageContext.request.contextPath}/resources/guessNumber/images/weikj-bg.png); padding: 0px; background-repeat: no-repeat; }
#lefttime{ float: left; height: 80px; width: 320px; position: relative; margin-top: 17px; margin-bottom: 40px; }
#top{ height: 30px; width: 320px; margin-top: 10px; font-weight: bold; font-size: 16px; float: left; position: relative; line-height: 30px; color: #FFF; }
#yourAnswer{ height: 30px; width: 223px; margin-right: 48.5px; margin-left: 48.5px; text-align: left; font-size: 16px; font-weight: bold; float: left; position: relative; line-height: 30px; color: #FFF; background-image: url(${pageContext.request.contextPath}/resources/guessNumber/images/your-an-bg.png); }
#yourAnswerL{ float: left; height: 30px; width: 150px; color: #E4E4E4; text-align: center;}
#yourAnswerR{ float: right; height: 30px; width: 55px; position: relative; text-align: left; font-size: 17px; }
#acusr{ float: right; height: 343px; width: 320px; position: relative; overflow:scroll;overflow-x:hidden;overflow-y:auto;}
.user{ float: left; height: 65px; width: 145px; position: relative; margin-left: 10px; margin-top: 10px; }
.user .userImg{ float: left; height: 45px; width: 45px; position: relative; }
.user .userInfo{ float: right; height: 65px; width: 100px; position: relative; }
.userInfo .sex{ float: left; height: 16px; width: 16px; position: relative; margin-left: 5px; margin-top: 5px; }
.userInfo .nickname{ float: right; height: 20px; width: 70px; text-align: left; position: relative; overflow: hidden; line-height: 20px; margin-top: 5px; font-size: 12px; color: #FFF; font-weight: bold; }
.userInfo .anwser{ height: 21px; width: 40px; text-align: center; float: left; position: relative; margin-top: 4px; line-height: 21px; font-size: 14px; font-weight: bold; color: #FFF; margin-left: 10px; background-image: url(${pageContext.request.contextPath}/resources/guessNumber/images/answer-gb.png); }
</style>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/js/jquery-1.9.1.js"></script>
</head>
<body>
<div id="main">
 <div id="lefttime">
  	<div id="top">
  		<c:if test="${KJTime==null}"><span style="color:red">该活动暂时无人参加</span></c:if>
  		<c:if test="${KJTime!=null}">开奖时间&nbsp;<span id="kjTime">${KJTime}</span>&nbsp;&nbsp;还剩 <span id="timer" style="color:red"></span></c:if>
  	</div>
    <div id="yourAnswer">
   	  	<c:if test="${yourAnswer==null}">
   	  		<div id="yourAnswerL">你还没有提交数字</div>
        	<div id="yourAnswerR" onclick="window.history.go(-1)"><span style="color: red">提交&gt;</span></div>
   	  	</c:if>
   	  	<c:if test="${yourAnswer!=null}">
   	  		<div id="yourAnswerL" style="padding-left: 5px;">你猜的数字是</div>
       		<div id="yourAnswerR">${yourAnswer}</div>
   	  	</c:if>
    </div>
     <!-- 定义隐藏表单，当开奖时间到了之后，把当前用户信息提交给服务器 -->
    <div style="display: none;">
    	<form action="${pageContext.request.contextPath}/GuessNum.ktv?action=showWinner" id="currentUserFOrm" method="post">
    		<input name="yourAnswer" id="yourAnswer" value="${yourAnswer}"/>
    	</form>
    </div>
  </div>
  <span style="display:none;" id="acCloseTime">${remainTimes}</span>
  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/js/timeRemains.js"></script>
  <!-- 用户显示，过多之后，就滚动或隐藏 -->
  <div id="acusr">
  <c:choose>
  	<c:when test="${allAcUsers==null}">
  		<h2>该活动暂时无人参加，请返回</h2>
  	</c:when>
  	<c:otherwise>
  	<!--头像和各自的性别，答案-->
  		<c:forEach var="item" items="${allAcUsers}">
    <div class="user">
      <div class="userImg"><img alt="头像" width="45px" height="45px" src="${item.headpicpath}" /></div>
      <div class="userInfo">
       <c:if test="${item.sex==0}"><div class="sex" style="background-image: url(${pageContext.request.contextPath}/resources/guessNumber/images/male-bg.png)"></div></c:if>
    	<c:if test="${item.sex==1}"><div class="sex" style="background-image: url(${pageContext.request.contextPath}/resources/guessNumber/images/female-bg.png)"></div></c:if>
        <div class="nickname">${item.nickname}</div>
        <div class="anwser">${item.yourAnswer}</div>
      </div>
    </div>
     </c:forEach>
  	</c:otherwise>
  </c:choose>
  </div>
</div>
</body>
</html>