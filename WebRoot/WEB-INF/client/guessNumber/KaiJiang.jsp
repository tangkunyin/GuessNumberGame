<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.vision.game.bean.KactivityUser" %>
<%@ page import="com.vision.game.service.KactivityUserService" %>
<%@ page import="com.vision.game.utils.SpringUtil" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />

<title>用户填完-开奖了</title>
<style type="text/css">
body { margin: 0px; padding: 0px; text-align: center; font-family: Arial, Helvetica, sans-serif; font-size: 14px; color: #FFF; }
img {border:none}
#main { margin-left: auto; margin-right: auto; width: 320px; text-align: center; position: relative; background-image: url(${pageContext.request.contextPath}/resources/common/img/beforegamebg.jpg); background-repeat: no-repeat; height: 480px; }
#yhts{ float: left; height: 80px; width: 320px; position: relative; margin-top: 17px; margin-bottom: 10px; color: #FFF; background-color: #1D264D; }
#top{ height: 30px; width: 300px; margin-right: 10px; margin-left: 10px; margin-top: 10px; font-weight: bold; font-size: 18px; float: left; position: relative; line-height: 30px; }
#bottom{ height: 30px; width: 300px; margin-right: 10px; margin-left: 10px; text-align: center; font-size: 17px; font-weight: bold; float: left; position: relative; line-height: 30px; color: #FFF; }
#boL{ float: left; height: 30px; width: auto; color: #E4E4E4; padding-left: 10px; }
#boR{ float: right; height: 30px; width: auto; position: relative; text-align: left; font-size: 20px; color: #F00; margin-right: 20px; }
#systs{ height: 30px; width: 300px; float: left; margin-right: 10px; margin-left: 10px; color: #FFF; line-height: 30px; font-size: 18px; font-weight: bold; margin-bottom: 5px; position: relative; }
#result{ float: right; height: 338px; width: 320px; position: relative; overflow:scroll; overflow-x:hidden; overflow-y:auto; }
.activities{ float: left; height: 143px; width: 320px; position: relative; border-top-width: 2px; border-top-style: solid; border-top-color: #525252; margin-top: 8px; }
.answer{ float: left; height: 30px; width: 223px; position: relative; background-image: url(${pageContext.request.contextPath}/resources/guessNumber/images/your-an-bg.png); margin-right: 48.5px; margin-left: 48.5px; line-height: 30px; font-size: 16px; font-weight: bold; margin-top: 8px; }
.acUser{ float: left; width: 320px; position: relative; height: 55px; margin-bottom: 8px; margin-top: 8px; }
.user{ height: 55px; width: 145px; margin-right: auto; margin-left: auto; position: relative; }
.user .userImg{ float: left; height: 45px; width: 45px; position: relative; }
.user .userInfo{ float: right; height: 55px; width: 100px; position: relative; }
.userInfo .sex{ float: left; height: 16px; width: 16px; position: relative; margin-left: 5px; margin-top: 5px; }
.userInfo .nickname{ float: right; height: 15px; width: 70px; text-align: left; position: relative; margin-top: 5px; font-size: 12px; font-weight: bold; overflow: hidden; }
.userInfo .uanwser{ height: 21px; width: 40px; text-align: center; float: left; position: relative; margin-top: 4px; line-height: 20px; font-size: 14px; font-weight: bold; color: #FFF; margin-left: 10px; background-image: url(${pageContext.request.contextPath}/resources/guessNumber/images/answer-gb.png); }
.prizes{ float: left; height: 30px; width: 300px; margin-right: 10px; margin-left: 10px; position: relative; line-height: 30px; font-size: 17px; font-weight: bold; }
</style>
</head>
<body>
<div id="main">
	<!--  用户提示：正在开奖，或者您为能参加本活动，以及刷新按钮 -->
		<div id="yhts">
			<!-- 用户不为空 -->
			<c:if test="${sessionScope.currentUser!=null}">
				<div id="top">您猜的号码是：<span style="color:red;">${sessionScope.currentUser.yourAnswer}</span>&nbsp;&nbsp;系统摇奖中...</div>
				<!-- cuser is winner -->
				<c:if test="${sessionScope.currentUser.isWinner=='y'}"><div id="bottom"><span style="color:red;">恭喜您已中奖，请速到前台领奖</span></div></c:if>
				<!-- cuser is not winner -->
				<c:if test="${sessionScope.currentUser.isWinner!='y'}"><div id="bottom">真遗憾，您未中奖，请再接再厉</div></c:if>
			</c:if>
			<!-- 用户为空 -->
			<c:if test="${sessionScope.currentUser==null}">
				<div id="top">啊喔，活动已经结束了。您未能参加本次活动！</div>	
				<div id="bottom">
					<div id="boL">开奖时段不能再提交</div>
					<div id="boR">更多活动</div>
				</div>
			</c:if>
		</div>
  <!--  系统提示：本次开奖多少名额 -->
  <div id="systs">本次已经开奖，共开${sessionScope.currentGameInfo.winNum}个大奖</div>
  <!-- 摇奖结果 -->
<div id="result">  
<%
	KactivityUserService acUserService=(KactivityUserService) SpringUtil.getBean("kacUserService");
	
	@SuppressWarnings("unchecked")
	List<String> answers=(List<String>)request.getAttribute("answers");
	
	@SuppressWarnings("unchecked")
	List<String> prizes=(List<String>)request.getAttribute("prizes");
		
	for(int i=1;;i++){
		List<KactivityUser> users=acUserService.findAcWinerByTH(i);
		if(users.size()==0){
			break;
		}else{
			if(users.size()==1){
				%>
				<div class="activities">
				<div class="answer">第<%out.print(i);%>次摇奖结果&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%out.print(answers.get(i-1));%></div>
					<div class="acUser">
						<div class="user">
							<div class="userImg"><img alt="头像" width="45px" height="45px" src="<%out.print(users.get(0).getHeadpicpath());%>" /></div>
							<div class="userInfo">
								<!-- 性别判断 -->
								<%if(users.get(0).getSex()==0){%>	
									<div class="sex" style="background-image: url(${pageContext.request.contextPath}/resources/guessNumber/images/male-bg.png)"></div>
								<%}else{%>		
									<div class="sex" style="background-image: url(${pageContext.request.contextPath}/resources/guessNumber/images/female-bg.png)"></div>
								<%}%>
								<div class="nickname"><%out.print(users.get(0).getNickname());%></div>
								<div class="uanwser"><%out.print(users.get(0).getYourAnswer());%></div>
							</div>
						</div>
					</div>      
					<div class="prizes">恭喜他获得<%out.print(prizes.get(i-1));%></div>    
			</div>
			<%
				continue;
			}else if(users.size()==2){
			%>
				<div class="activities">
				<div class="answer">第<%out.print(i);%>次摇奖结果&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%out.print(answers.get(i-1));%></div>
					<div class="acUser">
							<div class="user" style="float: left; margin-left: 10px;">
								<div class="userImg"><img alt="头像" width="45px" height="45px" src="<%out.print(users.get(0).getHeadpicpath());%>" /></div>
								<div class="userInfo">
								<%if(users.get(0).getSex()==0){%>	
									<div class="sex" style="background-image: url(${pageContext.request.contextPath}/resources/guessNumber/images/male-bg.png)"></div>
								<%}else{%>		
									<div class="sex" style="background-image: url(${pageContext.request.contextPath}/resources/guessNumber/images/female-bg.png)"></div>
								<%}%>
									<div class="nickname"><%out.print(users.get(0).getNickname());%></div>
									<div class="uanwser"><%out.print(users.get(0).getYourAnswer());%></div>
								</div>
							</div>
							<div class="user" style="float: left; margin-left: 10px;">
								<div class="userImg"><img alt="头像" width="45px" height="45px" src="<%out.print(users.get(1).getHeadpicpath());%>" /></div>
								<div class="userInfo">
									<%if(users.get(1).getSex()==0){%>	
										<div class="sex" style="background-image: url(${pageContext.request.contextPath}/resources/guessNumber/images/male-bg.png)"></div>
									<%}else{%>		
										<div class="sex" style="background-image: url(${pageContext.request.contextPath}/resources/guessNumber/images/female-bg.png)"></div>
									<%}%>
									<div class="nickname"><%out.print(users.get(1).getNickname());%></div>
									<div class="uanwser"><%out.print(users.get(1).getYourAnswer());%></div>
								</div>
							</div>
					</div>      
					<div class="prizes">恭喜他们共同获得<%out.print(prizes.get(i-1));%></div>
			</div>
			<%
				continue;
			}else{
				break;
			}	
		}
	}
%>
</div>
</div>
</body>
</html>