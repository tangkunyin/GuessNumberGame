<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.vision.game.utils.GameOrderUtil" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="refresh" content="180;url=${pageContext.request.contextPath}" />
<title>电视开奖时</title>
<style type="text/css">
body,div { text-align: center; margin: 0px; padding: 0px; }
img{border:none;}
#mainBox{ text-align: center; padding: 0px; height: 1080px; width: 1920px; margin-right: auto; margin-left: auto; }
#leftBox{ float: left; height: 100%; width: 346px; position: relative; }
#rightBox{ float: right; height: 100%; width: 1574px; position: relative; }
#ltop { height: 120px; width: 100%; float: left; position: relative; background-image: url(${pageContext.request.contextPath}/resources/tv/images/tvkj-lt.png); }
#lbottom{ height: 960px; width: 100%; float: left; position: relative; background-image: url(${pageContext.request.contextPath}/resources/tv/images/tvkj-lb.png); }
#rtop{ background-image: url(${pageContext.request.contextPath}/resources/tv/images/tvkj-rt.png); float: right; height: 120px; width: 100%; position: relative; }
#tvKJBg{ background-image: url(${pageContext.request.contextPath}/resources/tv/images/tvkj-bg.jpg); float: right; height: 960px; width: 100%; position: relative; overflow: hidden; margin: 0px; padding: 0px; }
#KaiJnum{ color: #FFF; font-size: 72px; font-weight: bold; text-align: center; width: 1000px; height: auto; line-height: 80px; float: left; position: relative; margin-left: 290px; margin-top: 150px; letter-spacing: 50px; }
#numbers{ float: left; height: 269px; width: 1000px; position: relative; margin-top: 100px; margin-left: 285px; }
#bw{ float: left; height: 100%; width: 269px; position: relative; margin-left: 48.25px;  }
#sw{ float: left; height: 100%; width: 269px; position: relative; margin-left: 48.25px;}
#gw{ float: right; height: 100%; width: 269px; position: relative; margin-right: 48.25px; }
#acUserNum{ float: right; width: auto; height: 60px; position: relative; text-align: left; margin-top: 200px; margin-right: 100px; font-size: 36px; font-weight: bold; color: #FFF; line-height: 60px; letter-spacing: 10px; }
</style>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/js/jquery-1.9.1.js"></script>
<script type="text/javascript">
	$().ready(function(){
		$.post("tv.ktv?action=ShowTotalUsersNum",null,function(size){
			$("#userNum").text(size);
		},"json");
	});

	setTimeout("getNumberInfo()",2000);
	function getNumberInfo(){
	 	 //异步获取服务器数据，12秒一次。3秒滚动一次。返回的数据必须分开来
		$.post("tv.ktv?action=ShowKJNumber",null,function(number){
			if(number[0]==null){
				$(".gw").attr("src","${pageContext.request.contextPath}/resources/tv/images/null.png");
			}else{
				$(".gw").attr("src","${pageContext.request.contextPath}/resources/tv/images/"+number[0]+".png");
			}
			if(number[1]==null){
				$(".sw").attr("src","${pageContext.request.contextPath}/resources/tv/images/null.png");
			}else{
				$(".sw").attr("src","${pageContext.request.contextPath}/resources/tv/images/"+number[1]+".png");
			}
			if(number[2]==null){
				$(".bw").attr("src","${pageContext.request.contextPath}/resources/tv/images/null.png");
			}else{
				$(".bw").attr("src","${pageContext.request.contextPath}/resources/tv/images/"+number[2]+".png");
			}
		},"json");
		//触发了一个动作就是算出中奖用户
	 	getWinners();
		setTimeout("getNumberInfo()",2000);
	}
	//算出谁是中奖用户
	function getWinners(){
		$.post("tv.ktv?action=getWinners",null,function(data){
			if(data=="" || data==null){
				return false;
			}else{
				if(data=="nobody"){
					window.location="tvshow/finished-1.jsp";
				}else{
					window.location="tvshow/finished-2.jsp";
				}
			}
		},"text");
	}
</script>
</head>

<body>
<div id="mainBox">
  <div id="leftBox">
    	<div id="ltop"></div>
   		<div id="lbottom"></div>
  </div>
  <div id="rightBox">
   	<div id="rtop"></div>
    <div id="tvKJBg">
      <div id="KaiJnum">第<%=GameOrderUtil.getGameOrder() %>个大奖</div>
      <div id="numbers">
        <!-- 百位数 -->
        <div id="bw"><img class="bw"/></div>
        <!-- 十位数 -->
        <div id="sw"><img class="sw"/></div>
        <!--个位数-->
        <div id="gw"><img class="gw"/></div>
      </div>
      <div id="acUserNum">共<span id="userNum"></span>个参与者</div>
    </div>
  </div>
</div>
</body>
</html>