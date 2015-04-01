<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="refresh" content="10;url=${pageContext.request.contextPath}/GuessNum.ktv?action=showWinner">
<title>用户填完-开奖了</title>
<style type="text/css">
body { margin: 0px; padding: 0px; text-align: center; font-family: Arial, Helvetica, sans-serif; font-size: 14px; background-color: #F2F2F2;}
img {border:none}
#main { margin-left: auto; margin-right: auto; width: 640px; min-height: 960px; text-align: center; position: relative; background-attachment: scroll; background-image: url(${pageContext.request.contextPath}/resources/guessNumber/images/kaij_bg.gif); background-repeat: no-repeat; background-position: center top; }
#nav{ float: left; width: 100%; height: 89px; color: #FFF; }
#yhts{ float: left; height: 95px; width: 580px; margin-top: 25px; margin-left: 30px; }
#yhts p{ margin: 0px; padding: 0px; font-size: 24px; line-height: 45px; font-weight: bold; }
#systs{ height: 50px; width: 600px; float: left; margin-right: 20px; margin-left: 20px; color: #666; line-height: 50px; font-size: 36px; font-weight: bold; margin-top: 5px; margin-bottom: 5px; }
.result{ width: 640px; float: left; margin-top: 5px; height: auto; margin-bottom: 5px; }
.answer{ float: left; height: 56px; width: 412px; margin-right: 99px; margin-left: 99px; margin-top: 15px; background-image: url(${pageContext.request.contextPath}/resources/guessNumber/images/kj_nav.png); line-height: 56px; font-size: 28px; font-weight: bold; padding-left: 30px; }
.acuser{ padding: 0px; height: 100px; width: 600px; margin-top: 10px; margin-bottom: 0px; margin-right: 20px; margin-left: 20px; float: left; }
.user{ height: 100px; width: 300px; padding: 0px; margin-right: auto; margin-left: auto;}
.user .userImg{ float: left; height: 100px; width: 100px; }
.user .userInfo{ float: right; height: 100px; width: 192px; margin-left: 8px; overflow: hidden; }
.userInfo .sex{ float: left; height: 30px; width: 30px; margin-top: 10px;}
.userInfo .nickname{ float: right; height: 30px; width: 155px; margin-top: 10px; font-size: 18px; line-height: 30px; font-weight: bold; text-align: left; }
.userInfo .yanwser{ float: left; height: 50px; width: 110px; margin-top: 5px; background-color: #A5E1F8; text-align: center; font-size: 28px; line-height: 50px; font-weight: bold; margin-left: 20px; }
.prizes{ float: left; height: auto; width: 100%; margin-top: 10px; font-size: 32px; text-align: center; font-weight: bold; color: #333; }
.mylink area {blr:expression(this.onFocus=this.blur())}
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
  <!--  用户提示：正在开奖，或者您为能参加本活动，以及刷新按钮 -->
  <div id="yhts">
  	<c:if test="${sessionScope.currentUser!=null}">
	  	<p>您猜的号码是：<span style="color:red;">${sessionScope.currentUser.yourAnswer}</span>  系统正在摇奖中，请稍候...</p>
  	</c:if>
  	<c:if test="${sessionScope.currentUser.isWinner=='y'}"><p><span style="color:red;">恭喜您已中奖，请速到前台领奖</span></p></c:if>
  	<c:if test="${sessionScope.currentUser.isWinner=='n'}"><p>真遗憾，您未中奖，请再接再厉</p></c:if>
    <c:if test="${sessionScope.currentUser==null}">
    	<p>您未能参加本次活动</p>
    	<p>请您继续关注我们</p>
    </c:if>
  </div>
  <!--  系统提示：本次开奖多少个大奖，根据奖品数量显示，最多10个 -->
  <div id="systs">本次已经开奖，共开${sessionScope.currentGameInfo.winNum}个大奖</div>
  <!-- 摇奖结果 -->
<c:if test="${rightwinnerNums!=0}">
  	<c:forEach var="prize" items="${prizes}" varStatus="prizeStatus" end="${totalPrize}">
		<c:forEach var="user" items="${rightwinners}" varStatus="userStatus">								
				<c:if test="${user.yourAnswer==answers[prizeStatus.index]}">
					<div class="result">
						<div class="answer">
							第${prizeStatus.count}次摇奖结果&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${answers[prizeStatus.index]}
						</div>
						<!--头像和各自的性别，答案-->
						<div class="acuser">						
							<div class="user">
								<div class="userImg"><img width="100px" height="100px" src="${user.headpicpath}" alt="头像"></div>
								<div class="userInfo">
									<c:if test="${user.sex==0}"><div class="sex" style="background-image: url(${pageContext.request.contextPath}/resources/common/img/genderImg.gif); background-position: left;"></div></c:if>
									<c:if test="${user.sex==1}"><div class="sex" style="background-image: url(${pageContext.request.contextPath}/resources/common/img/genderImg.gif); background-position: right;"></div></c:if>
									<div class="nickname">${user.nickname}</div>
									<div class="yanwser">${user.yourAnswer}</div>
								</div>
							</div>																						
						</div>
						<!--奖品-->	
						<div class="prizes">
							恭喜他获得${prizes[prizeStatus.index]}						
						</div>
					</div>
				</c:if>		
		</c:forEach>
	</c:forEach>
</c:if>


<c:choose>
	<c:when test="${rightwinnerNums==(totalPrize+1)}"></c:when>
	<c:otherwise>
		<c:forEach var="prize" items="${prizes}" varStatus="prizeStatus" begin="${rightwinnerNums}" end="${totalPrize}">
			<div class="result">
				<div class="answer">
				第${rightwinnerNums+1}次摇奖结果&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${answers[prizeStatus.index]}
				</div>
				<!--头像和各自的性别，答案-->
				<div class="acuser">
					<c:forEach var="user" items="${notrightwinnerList}" varStatus="userStatus">
						<div class="user" style="float:left;">
							<div class="userImg"><img width="100px" height="100px" src="${user.headpicpath}" alt="头像"></div>
							<div class="userInfo">
								<c:if test="${user.sex==0}"><div class="sex" style="background-image: url(${pageContext.request.contextPath}/resources/common/img/genderImg.gif); background-position: left;"></div></c:if>
								<c:if test="${user.sex==1}"><div class="sex" style="background-image: url(${pageContext.request.contextPath}/resources/common/img/genderImg.gif); background-position: right;"></div></c:if>
								<div class="nickname">${user.nickname}</div>
								<div class="yanwser">${user.yourAnswer}</div>
							</div>
						</div>	
					</c:forEach>
				</div>
				<!--奖品-->	
				<div class="prizes">									
					恭喜他们共同获得${prizes[prizeStatus.index]}										
				</div>
			</div>
		</c:forEach>
	</c:otherwise>
</c:choose>
</div>
</body>
</html>