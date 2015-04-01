<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.vision.game.utils.GameOrderUtil" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>开奖完成</title>
<style type="text/css">
body{margin:0px; padding:0px; text-align:center;}
img {border:none;}
#mainBox{ text-align: center; height: 1080px; width: 1920px; margin-right: auto; margin-left: auto; }
#top{ float: left; height: 120px; width: 100%; position: relative; background-image: url(${pageContext.request.contextPath}/resources/tv/images-tv/tv-kj-top.jpg); }
#left{ float: left; height: 960px; width: 346px; position: relative; }
#left_top{ background-image: url(${pageContext.request.contextPath}/resources/tv/images-tv/tv-kj-prize1.jpg); float: left; height: 239px; width: 346px; position: relative; }
#left_bottom{ float: left; height: 721px; width: 346px; position: relative; background-image: url(${pageContext.request.contextPath}/resources/tv/images-tv/tv-kj-prize2.jpg); }
#right{ float: right; height: 960px; width: 1574px; position: relative; }
#right_top{ background-image: url(${pageContext.request.contextPath}/resources/tv/images-tv/tv-kj-times.jpg); float: right; height: 245px; width: 1574px; position: relative; }
#yjtimes{ color: #FFF; height: 100%; float: left; width: 575px; position: relative; line-height: 245px; font-size: 60px; }
#results{ float: right; height: 100%; width: 999px; position: relative; }
#bw{ float: left; height: 175px; width: 175px; margin-top: 35px; position: relative; background-image: url(${pageContext.request.contextPath}/resources/tv/images-tv/0.png); margin-left: 90px; }
#sw{ background-image: url(${pageContext.request.contextPath}/resources/tv/images-tv/2.png); float: left; height: 175px; width: 175px; margin-top: 35px; position: relative; margin-left: 50px; }
#gw{ background-image: url(${pageContext.request.contextPath}/resources/tv/images-tv/3.png); float: left; height: 175px; width: 175px; margin-top: 35px; margin-left: 50px; position: relative; }
#right_bottom{ background-image: url(${pageContext.request.contextPath}/resources/tv/images-tv/tv-kj-one.jpg); float: right; height: 715px; width: 1574px; position: relative; }
#user{ float: left; height: 280px; width: 636px; position: relative; margin-left: 545px; margin-top: 215px; clear: right; }
#headpic{ float: left; height: 170px; width: 170px; margin-top: 8.5px; margin-left: 8.5px; position: relative; clear: left; }
#nickname{ float: right; height: 50px; width: 415px; margin-top: 8.5px; position: relative; font-size: 30px; line-height: 50px; font-weight: bold; color: #FFF; letter-spacing: 5px; }
#answer{ float: right; height: 108px; width: 415px; margin-top: 16px; font-size: 72px; line-height: 108px; font-weight: bold; color: #FFF; letter-spacing: 45px; }
#usure{ float: left; height: 72px; width: 191px; position: relative; background-image: url(${pageContext.request.contextPath}/resources/tv/images-tv/wait-yes.png); margin-top: 20px; }
</style>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/js/jquery-1.9.1.js"></script>
<script type="text/javascript">
setTimeout("showWinners()",2000);
function showWinners(){
	$.post("/game/tv.ktv?action=InitiaTVWinner",null,function(data){
			var str=data.split("#");
			if(str[0]=="nobody" && str[1]=="nobody"){
				window.location="/game";
			}else{
				if(str[0]!="nobody" && str[1]=="nobody"){
					//答案初始化 
					var gw=str[2];
					var sw=str[3];
					var bw=str[4];
					$("#abw").attr("src","${pageContext.request.contextPath}/resources/tv/images-tv/"+bw+".png");
					$("#asw").attr("src","${pageContext.request.contextPath}/resources/tv/images-tv/"+sw+".png");
					$("#agw").attr("src","${pageContext.request.contextPath}/resources/tv/images-tv/"+gw+".png");
					
					//用户信息初始化
					var luser=eval("("+str[0]+")");
					
					$("#userImg").attr("src",luser.user[0].headpicpath);
					$("#nickname").text(luser.user[0].nickName);
					$("#answer").text(luser.user[0].yourAnswer);
				}
				//验证是否更换了用户，如果是，则跳转到俩人页面
				if(str[0]!="nobody" && str[1]!="nobody"){
					window.location="finished-2.jsp"
				}
			}
		},"text");
	setTimeout("showWinners()",2000);
}
</script>
</head>
<body>
<div id="mainBox">
     <div id="top"></div>
     <div id="left">
       <div id="left_top"></div>
        <div id="left_bottom"></div>
  </div>
  <div id="right">
    <div id="right_top">
    	<div id="yjtimes">第<%=GameOrderUtil.getGameOrder() %>个大奖结果</div>
        <div id="results">
       	  <div id="bw"><img id="abw" width="175" height="175"/></div>
            <div id="sw"><img id="asw" width="175" height="175"/></div>
            <div id="gw"><img id="agw" width="175" height="175"/></div>
      </div>
    </div>
        <div id="right_bottom">
        	<div id="user">
           	  	<div id="headpic"><img width="170" height="170" id="userImg"/></div>
                <div id="nickname"></div>
                <div id="answer"></div>
                <div id="usure"></div>
          	</div>
        </div>
  </div>
 </div>
</body>
</html>