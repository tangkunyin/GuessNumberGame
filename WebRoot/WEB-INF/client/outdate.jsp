<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<title>啊喔~活动已过期</title>
<style type="text/css">
body { margin: 0px; padding: 0px; text-align: center; font-family: Arial, Helvetica, sans-serif; font-size: 14px; }
img {border:none}
#main { margin-left: auto; margin-right: auto; width: 320px; height: 480px; text-align: center; background-image: url(${pageContext.request.contextPath}/resources/common/img/beforegamebg.jpg); }
.content{ padding: 0px; height: 126px; width: 292px; margin-top: 15px; margin-left: 14px; background-color:#fff; backfont-family: "黑体", "宋体"; margin-bottom: 5px; background-repeat: no-repeat; float: left; position: relative;}
.actime{ height: 35px; width: 100%; line-height: 35px; font-size: 22px; float: left; position: relative; overflow: hidden; margin-top: 1px; font-weight: bold; color: red; }
.actitle{ height: auto; width: 100%; float: left; position: relative;}
.acprize{ margin-top: 10px; width: 100%; height: auto; font-size: 18px; float: left; position: relative; font-weight: bold;}
a {text-decoration: none; color: #000;}
</style>
</head>
<body>
<div id="main">
  	<div class="content">
  		<div class="actime">亲，您怎么才来啊...</div>
  		<div class="actitle"><img src="${pageContext.request.contextPath}/resources/common/img/outdate-smile.gif"/></div>
  		<div class="acprize"><a href="${pageContext.request.contextPath}/GuessNum.ktv?action=moreGameInfo">看看更多活动</a></div>
  	</div>
</div>
</body>
</html>