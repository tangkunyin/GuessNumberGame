<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<title>更多好玩活动</title>
<style type="text/css">
body { margin: 0px; padding: 0px; text-align: center; font-family: Arial, Helvetica, sans-serif; font-size: 14px; }
img {border:none}
#main { margin-left: auto; margin-right: auto; width: 320px; height: 480px; text-align: center; background-image: url(${pageContext.request.contextPath}/resources/common/img/beforegamebg.jpg); }
.content{ padding: 0px; height: 126px; width: 292px; margin-top: 15px; margin-left: 14px; background-image: url(${pageContext.request.contextPath}/resources/common/img/beforeshowbg.png); font-family: "黑体", "宋体"; margin-bottom: 5px; background-repeat: no-repeat; float: left; position: relative; }
.actime{ height: 35px; width: 100%; line-height: 35px; font-size: 22px; float: left; position: relative; overflow: hidden; margin-top: 1px; font-weight: bold; color: #FFF; }
.actitle{ height: 30px; width: 100%; line-height: 30px; float: left; position: relative; overflow: hidden; font-size: 18px; font-weight: bold; color: #FFD0D1; }
.acprize{ width: 100%; height: 25px; line-height: 25px; font-size: 18px; float: left; overflow: hidden; position: relative; font-weight: bold; color: #FFFFFE; }
.acts{ line-height: 28px; height: 28px; width: 100%; float: left; overflow: hidden; position: relative; color: #D5D4D4; font-size: 18px; font-weight: bold; }
#bottom{}
</style>
</head>
<body>
<div id="main">
  <c:forEach var="item" items="${games}">
  	<div class="content" onclick="javascript:window.history.go(-1);">
    	<div class="actime">本${item.week}&nbsp;${item.acStartTime}-${item.acEndTime}</div>
         <div class="actitle">${item.acName}</div>
         <div class="acprize">奖品是：${item.prize1}</div>
         <div class="acts">欢迎大家来参加</div>
    </div>
  </c:forEach>
</div>
</body>
</html>