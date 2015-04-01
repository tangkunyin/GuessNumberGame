<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>猜数字赢大奖</title>
<style type="text/css">
body { margin: 0px; padding: 0px; text-align: center; font-family: Arial, Helvetica, sans-serif; font-size: 14px; }
img {border:none}
#main { margin-left: auto; margin-right: auto; width: 640px; height: 960px; text-align: center; position: relative; }
.mylink area {blr:expression(this.onFocus=this.blur())}
#nav{ float: left; width: 100%; height: 89px; background-color: #333; color: #FFF; }
#show { width: 640px; height: 309px; position: relative; float: left; }
#rule{ width:100%; height:125px; text-align: left; color: #808080; z-index:5; margin-top: -275px; position: absolute; }
#myinput{ float: left; height: 126px; width: 100%; padding: 0px; background-image: url(${pageContext.request.contextPath}/resources/guessNumber/images/inputbg.png); margin: 0px; text-align: center; }
#myinput .num{ float: left; margin-right: 140px; margin-left: 140px; width: 360px; height: 55px; margin-top: 38px; border-top-width: 0px; border-right-width: 0px; border-bottom-width: 0px; border-left-width: 0px; border-top-style: none; border-right-style: none; border-bottom-style: none; border-left-style: none; font-size: 36px; font-weight: bold; font-family: Arial, Helvetica, sans-serif; text-align: center; color: #666; line-height: 55px;}
#keybord { width: 640px; height: 240px; position: relative; margin: 0px; float: left; padding: 0px; }
#subarea{ margin: 0px; padding: 0px; float: left; height: 196px; width: 640px; }
</style>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/guessNumber/js/controlKey.js" charset="utf-8"></script>
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
  <div id="show"><img src="${pageContext.request.contextPath}/resources/guessNumber/images/showEmptybg.png" border="0" usemap="#Map2"/>
    <div id="rule"><h2 style="padding-left: 35px; padding-right: 35px; text-overflow:ellipsis; overflow:hidden;">${sessionScope.game.acRule}</h2></div>
    <map name="Map2">
      <area shape="rect" coords="57,183,298,245" href="${pageContext.request.contextPath}/GuessNum.ktv?action=moreGameInfo" class="mylink">
      <area shape="rect" coords="335,182,578,246" href="${pageContext.request.contextPath}/GuessNum.ktv?action=SeeMoreAcUser" class="mylink">
    </map>
  </div>
    <div id="myinput">
    	<div id="num"  class="num">请输入您猜的数</div>
    </div>
     <div id="keybord">
     	<img src="${pageContext.request.contextPath}/resources/guessNumber/images/keybord.png" border="0" usemap="#Map3"/>
        <map name="Map3">
          <area shape="circle" coords="91,64,42" id="k1" class="mylink" href="javascript:" >
          <area shape="circle" coords="205,65,41" id="k2" class="mylink" href="javascript:">
          <area shape="circle" coords="321,62,42" id="k3" class="mylink" href="javascript:">
          <area shape="circle" coords="433,66,42" id="k4" class="mylink" href="javascript:">
          <area shape="circle" coords="550,65,41" id="k5" class="mylink" href="javascript:">
          <area shape="circle" coords="90,179,43" id="k6" class="mylink" href="javascript:">
          <area shape="circle" coords="206,180,41" id="k7" class="mylink" href="javascript:">
          <area shape="circle" coords="320,182,41" id="k8" class="mylink" href="javascript:">
          <area shape="circle" coords="435,179,41" id="k9" class="mylink" href="javascript:">
          <area shape="circle" coords="550,179,40" id="k0" class="mylink" href="javascript:">
        </map>
     </div>
    <div id="subarea">
    <!-- 提交与回退 -->
    	<img src="${pageContext.request.contextPath}/resources/guessNumber/images/subb.png" border="0" usemap="#Map4"/>
        <map name="Map4">
	      <area shape="rect" coords="77,103,288,178" id="subtt" class="mylink" href="javascript:" title="提交">
	      <area shape="rect" coords="355,102,567,176" id="delete" class="mylink" href="javascript:" title="删除">
   		</map>
    </div> 
</div>
</body>
</html>