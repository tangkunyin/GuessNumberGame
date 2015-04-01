<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>幸运数抽大奖 -K友广播中</title>
<style type="text/css">
body { text-align: center; margin: 0px; padding: 0px; }
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
#gbBg{ background-image: url(${pageContext.request.contextPath}/resources/tv/images/gbBg.jpg); float: right; height: 960px; width: 100%; position: relative; overflow: hidden; }
.brodcast{ height: 215px; width: 1260px; overflow: hidden; margin-left: 10px; padding: 5px; float: right; position: relative; margin-top: 8px; }
.userImg{ float: left; position: relative; }
.userInfo{ float: left; height: 210px; width: 800px; margin-left: 10px; position: relative; }
.nickName{ height: 35px; width: 100%; line-height: 35px; font-family: "黑体", "宋体"; font-size: 28px; font-weight: bold; color: #FFF; text-align: left; }
.castInfo{ height: auto; width: 100%; font-family: "黑体", "宋体"; font-size: 30px; font-weight: bold; color: #FFF; text-align: left; margin-top: 5px; overflow: hidden; position: relative; }
.fileUrl{ float: left; height: 210px; width: 210px; margin-left: 10px; position: relative; }
.typeImg{ float: right; height: 36px; width: 67px; position: relative;  }
</style>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/tv/js/tv-gb.js"></script>
<script type="text/javascript">
	setTimeout("setRemainsTime()",1000);
	function setRemainsTime(){
		//设置倒计时，到了特定时间后，跳转页面
		var rt,time_end,time_now;
		var StartTime="${acStartTime}";
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
	//广播消息处理
	$().ready(function(){
		getBroadCastInfo();
	});
	function getBroadCastInfo(){
		//异步获取服务器数据，12秒一次。3秒滚动一次
		$.post("tv.ktv?action=ShowBroadCast",null,function(data){
			document.charset="utf-8";
			document.getElementById('body').innerHTML=data;
		},"html")
		setTimeout("getBroadCastInfo()",12000);
	}
</script>
</head>
<body id="body">
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
        <div id="gbBg">
			<c:choose>
				<c:when test="${BroadcastInfo==null}">
					<h1>暂无广播</h1>
				</c:when>
				<c:otherwise>
					<c:forEach var="item" items="${BroadcastInfo}">
						<div class="brodcast">
							<div class="userImg"><img src="${item.userHeadPic}" width="100px" height="100px"/></div>
				            <div class="userInfo">
				              <div class="nickName">${item.nickName}</div>
				              <div class="castInfo">${item.textContent}</div>
				            </div>
				            <c:if test="${item.fileUrl!=null}"><div class="fileUrl"><img src="${item.fileUrl}" width="210px" height="210px"/></div></c:if>
				            <c:if test="${item.type==3}"><div class="typeImg" style="background-image: url(${pageContext.request.contextPath}/images1/type.png); background-position: 0px 79px;"></div></c:if>
				            <c:if test="${item.type==2}"><div class="typeImg" style="background-image: url(${pageContext.request.contextPath}/images1/type.png);"></div></c:if>
				            <c:if test="${item.type==1}"><div class="typeImg" style="background-image: url(${pageContext.request.contextPath}/images1/type.png); background-position: 0px 36px;"></div></c:if>
				            <c:if test="${item.type==0}"><div class="typeImg" style="background-image: url(${pageContext.request.contextPath}/images1/type.png); background-position: 0px 79px;"></div></c:if>
						</div>
					</c:forEach>
				</c:otherwise>
			</c:choose>
        </div>
    </div>
</div>
</body>
<!-- 结束 -->
</html>