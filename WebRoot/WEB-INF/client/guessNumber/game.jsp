<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<title>猜数字赢大奖</title>
<style type="text/css">
body { margin: 0px; padding: 0px; text-align: center; font-family: Arial, Helvetica, sans-serif; font-size: 14px; }
img {border:none}
#main { margin-left: auto; margin-right: auto; width: 320px; height: 480px; text-align: center; position: relative; background-image: url(${pageContext.request.contextPath}/resources/guessNumber/images/game-input-bg.png); }
#show { width: 320px; height: 200px; position: relative; float: left; background-repeat: no-repeat; }
#rule{ width:300px; height:70px; text-align: left; color: #CCCAD1; position: relative; text-overflow:ellipsis; margin-top: 40px; float: right; padding-right: 10px; padding-left: 10px; }
#moreGameInfo{ float: left; height: 30px; width: 120px; position: relative; margin-top: 15px; margin-left: 32px; display: block; }
#SeeMoreAcUser{ float: right; height: 30px; width: 120px; position: relative; margin-top: 15px; display: block; margin-right: 32px; }
#myinput{ float: right; height: 63px; width: 320px; text-align: center; position: relative; }
.num{ float: left; margin-left: 28px; width: 198px; height: 44px; margin-top: 9px; border-top-width: 0px; border-right-width: 0px; border-bottom-width: 0px; border-left-width: 0px; border-top-style: none; border-right-style: none; border-bottom-style: none; border-left-style: none; font-size: 20px; font-weight: bold; font-family: Arial, Helvetica, sans-serif; text-align: center; color: #FFF; line-height: 43px; position: relative; background-image: url(${pageContext.request.contextPath}/resources/guessNumber/images/inputText.png); }
#keybord { width: 290px; height: 114px; position: relative; margin: 0px; float: left; padding-top: 10px; padding-right: 15px; padding-left: 15px; }
.mykey{ display: block; height: 48px; width: 48px; float: left; }
#subarea{ float: left; height: 77px; width: 320px; position: relative; margin-top: 16px; }
#subtt{ display: block; float: left; height: 38px; width: 176px; position: relative; margin-left: 71px; }
#delete{ float: right; height: 37px; width: 50px; margin-top: 10px; display: block; position: relative; margin-right: 30px; background-image: url(${pageContext.request.contextPath}/resources/guessNumber/images/delbutt.png); }
</style>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/guessNumber/js/controlKey.js" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/js/goBack.js" charset="utf-8"></script>
</head>
<body>
<div id="main">
  <div id="show">
    <div id="rule">${sessionScope.game.acRule}</div>
   	<a id="moreGameInfo" href="${pageContext.request.contextPath}/GuessNum.ktv?action=moreGameInfo"></a>
    <a id="SeeMoreAcUser" href="${pageContext.request.contextPath}/GuessNum.ktv?action=SeeMoreAcUser"></a>
  </div>
<div id="myinput">
   	  <div id="num"  class="num">请输入您猜的数</div>
    <div id="delete"></div>
  </div>
  <div id="keybord">
    <span id="k1" class="mykey" style="margin-right:10px; background-image:url(${pageContext.request.contextPath}/resources/guessNumber/images/1.png)"></span>
    <span id="k2" class="mykey" style="margin-right:10px; background-image:url(${pageContext.request.contextPath}/resources/guessNumber/images/2.png)"></span>
    <span id="k3" class="mykey" style="margin-right:10px; background-image:url(${pageContext.request.contextPath}/resources/guessNumber/images/3.png)"></span>
    <span id="k4" class="mykey" style="margin-right:10px; background-image:url(${pageContext.request.contextPath}/resources/guessNumber/images/4.png)"></span>
    <span id="k5" class="mykey" style="margin-right:10px; background-image:url(${pageContext.request.contextPath}/resources/guessNumber/images/5.png)"></span>
    <span id="k6" class="mykey" style="margin-top:18px; margin-right:10px; background-image:url(${pageContext.request.contextPath}/resources/guessNumber/images/6.png)"></span>
    <span id="k7" class="mykey" style="margin-top:18px; margin-right:10px; background-image:url(${pageContext.request.contextPath}/resources/guessNumber/images/7.png)"></span>
    <span id="k8" class="mykey" style="margin-top:18px; margin-right:10px; background-image:url(${pageContext.request.contextPath}/resources/guessNumber/images/8.png)"></span>
    <span id="k9" class="mykey" style="margin-top:18px; margin-right:10px; background-image:url(${pageContext.request.contextPath}/resources/guessNumber/images/9.png)"></span>
    <span id="k0" class="mykey" style="margin-top:18px; margin-right:10px; background-image:url(${pageContext.request.contextPath}/resources/guessNumber/images/0.png)"></span>
  </div>
    <div id="subarea"><span id="subtt"></span></div> 
</div>
</body>
</html>