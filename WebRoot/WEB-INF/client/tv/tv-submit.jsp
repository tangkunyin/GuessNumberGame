<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>幸运数抽大奖-提交页面</title>
<style type="text/css">
body,div { text-align: center; margin: 0px; padding: 0px; }
img{border:none;}
#mainBox{ text-align: center; padding: 0px; height: 1080px; width: 1920px; margin-right: auto; margin-left: auto; }
#leftBox{ float: left; height: 100%; width: 625px; position: relative; }
#rightBox{ float: right; height: 100%; width: 1295px; position: relative; }
#ltop { height: 120px; width: 100%; float: left; position: relative; background-image: url(${pageContext.request.contextPath}/resources/tv/images/ltop.jpg); }
#priseBg{ float: left; height: 615px; width: 100%; position: relative; background-image: url(${pageContext.request.contextPath}/resources/tv/images/priseBg.png); }
#priseBanner{ height: 93px; width: 100%; position: relative; background-image: url(${pageContext.request.contextPath}/resources/tv/images/priseBanner.jpg); }
#prise{ height: 444px; width: 100%; position: relative; }
#priseName{ height: 81px; width: 100%; position: relative; background-image: url(${pageContext.request.contextPath}/resources/tv/images/priseName.jpg); }
#downloadBg{ background-image: url(${pageContext.request.contextPath}/resources/tv/images/downloadBg.jpg); float: left; height: 342px; width: 100%; position: relative; }
#rtop{ background-image: url(${pageContext.request.contextPath}/resources/tv/images/rtop.jpg); float: right; height: 120px; width: 100%; position: relative; }
#submitBg{ background-image: url(${pageContext.request.contextPath}/resources/tv/images/gamebg.png); float: right; height: 870px; width: 1205px; position: relative; overflow: hidden; padding-top: 90px; padding-left: 90px;}
.userSubmit{ float: left; height: 124px; width: 422px; position: relative; background-image: url(${pageContext.request.contextPath}/resources/tv/images/tj-bg.png); margin-right: 175px; margin-bottom: 90px; }
.userImg{ float: left; height: 100px; width: 100px; margin-top: 5px; margin-left: 5px; position: relative; }
.nickName{ float: right; height: 35px; width: 275px; margin-top: 12px; position: relative; text-align: left; color: #E4E4E4; font-weight: bold; font-size: 24px; line-height: 35px; }
.number{ float: right; height: 50px; width: 275px; margin-top: 15px; position: relative; color: #CCC; font-weight: bold; font-size: 42px; line-height: 50px; }
</style>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/tv/js/tv-submit.js"></script>
<script type="text/javascript">
	setTimeout("setRemainsTime()",1000);
	function setRemainsTime(){
		//设置倒计时，到了特定时间后，跳转页面
		var rt,time_end,time_now;
		var StartTime="${acEndTime}";
		time_end=new Date(StartTime); //截至时间
		time_end=time_end.getTime();
		time_now=new Date();//开始时间
		time_now=time_now.getTime();
		var time_distance=time_end-time_now;
		if(time_distance>0){
			setTimeout("setRemainsTime()",1000);
		}else{
			clearTimeout(rt);
			//跳转
			window.location="${pageContext.request.contextPath}/Index";
		}
	}
	//定义ajax异步取数据  12秒请求一次
	$().ready(function(){
		getSubmitInfo();
	});
	function getSubmitInfo(){
			$.post("tv.ktv?action=ShowSubmits",null,function(data){
				document.charset="utf-8";
				document.getElementById('body').innerHTML=data;
			},"html")
			setTimeout("getSubmitInfo()",12000);
	}
</script>
</head>
<body  id="body">
<div id="mainBox">
  <div id="leftBox">
    	<div id="ltop"></div>
    <div id="priseBg">
        	<div id="priseBanner"></div>
	        <div id="prise"><img src="${pageContext.request.contextPath}/resources/tv/images/prise.png" width="477" height="409" /></div>
	        <div id="priseName"></div>
        </div>
        <div id="downloadBg"></div>
    </div>
  <div id="rightBox">
   	<div id="rtop"></div>
    <div id="submitBg">
     <c:forEach begin="0" end="3" varStatus="i">
     	<div class="Subdata">
     		<c:forEach var="item" items="${user}" begin="${2*(i.index)}" end="${2*(i.index)+1}">
	    		<div class="userSubmit">
	   		  		<div class="userImg"><img src="${item.headpicpath}" width="115" height="115"/></div>
	                <div class="nickName">${item.nickname}</div>
	                <div class="number">${item.yourAnswer}</div>
	   		  	</div>
    		</c:forEach>
     	</div>
     </c:forEach>
    </div>
  </div>
</div>
</body>
<!-- 结束 -->
</html>