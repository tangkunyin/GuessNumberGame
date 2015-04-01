<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>活动还未开始呢</title>
<style type="text/css">
body { margin: 0px; padding: 0px; text-align: center; font-family: Arial, Helvetica, sans-serif; font-size: 14px; }
img {border:none}
.mylink area {blr:expression(this.onFocus=this.blur())}
#main { margin-left: auto; margin-right: auto; width: 640px; height: auto; text-align: center; position: relative; }
#nav{ float: left; width: 100%; height: 89px; background-color: #333; color: #FFF; }
#show { width: 640px; min-height:960px; position: relative; float: left; background-image: url(${pageContext.request.contextPath}/resources/common/img/beforegamebg.gif); background-attachment: fixed; background-repeat: no-repeat; background-position: center top; }
.content{ padding: 0px; height: 208px; width: 494px; margin-top: 15px; margin-right: auto; margin-left: auto; background-image: url(${pageContext.request.contextPath}/resources/common/img/beforeshowbg.png); font-family: "黑体", "宋体"; margin-bottom: 5px; }
h1,h2{ margin: 0px; padding: 0px; }
.actime{ height: 50px; width: 100%; border-bottom-width: 2px; border-bottom-style: solid; border-bottom-color: #000; line-height: 50px; font-size: 17px; }
.actitle{ height: 50px; width: 100%; line-height: 50px; color: #900; }
.acprize{ width: 100%; height: 40px; line-height: 40px; font-size: 16px; }
.acts{ line-height: 40px; height: 40px; width: 100%; font-size: 16px; color: #999; }
</style>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/js/goBack.js" charset="utf-8"></script>
</head>
<body>
<div id="main">
  <div id="nav"><img src="${pageContext.request.contextPath}/resources/guessNumber/images/beforegamenav.png" border="0" usemap="#Map" />
    <map name="Map" id="Map">
      <area shape="rect" coords="38,17,94,71" href="javascript:" id="goback" class="mylink" />
    </map>
  </div>
  <div id="show">
  <c:forEach var="item" items="${games}">
  	<div class="content">
    	<div class="actime"><h2>本${item.week}&nbsp;${item.acStartTime}-${item.acEndTime}&nbsp;举办</h2></div>
         <div class="actitle"><h1>${item.acName}</h1></div>
         <div class="acprize"><h2>奖品是：${item.prize1}</h2></div>
         <div class="acts"><h2>欢迎大家来参加</h2></div>
    </div>
  </c:forEach>
  </div>
</div>
</body>
</html>