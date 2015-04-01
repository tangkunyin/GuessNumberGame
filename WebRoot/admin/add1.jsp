<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>1</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/common/css/jquery.ui.autocomplete.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/common/css/jquery.ui.theme.css">
<script src="${pageContext.request.contextPath}/resources/common/js/jquery-1.9.1.js"></script>
<script src="${pageContext.request.contextPath}/resources/common/js/jquery.ui.core.js"></script>
<script src="${pageContext.request.contextPath}/resources/common/js/jquery.ui.widget.js"></script>
<script src="${pageContext.request.contextPath}/resources/common/js/jquery.ui.position.js"></script>
<script src="${pageContext.request.contextPath}/resources/common/js/jquery.ui.menu.js"></script>
<script src="${pageContext.request.contextPath}/resources/common/js/jquery.ui.autocomplete.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/js/ShowCalendar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/guessNumber/js/addGame-SpecificTime.js"></script>
<script src="${pageContext.request.contextPath}/resources/guessNumber/js/1-addGame.js"></script>
<style type="text/css">
.ui-autocomplete-loading {background: white url('${pageContext.request.contextPath}/resources/common/img/ui-anim_basic_16x16.gif') right center no-repeat;}
body { text-align: center; margin: 0px; padding: 0px; background-color: #F2F2F2; }
img {border:none;}
h2,h3{ padding-left: 15px; padding-right: 15px; }
p{ padding: 0px; margin-top: 0px; margin-right: 0px; margin-bottom: 20px; margin-left: 0px; font-size: 18px; }
input{ width: 13pc; height: 22px; }
textarea{ width: 34pc; resize:none; }
#mainBox{ padding: 0px; height: 600px; width: 1024px; margin-top: 30px; margin-right: auto; margin-left: auto; }
#leftBox{ float: left; height: 480px; width: 320px; position: relative; }
#left_top { height: 89px; width: 100%; color: #FFF; line-height: 85px; font-size: 38px; font-weight: bold; }
#left_bottom{ height: 679px; width: 100%; text-align: left; color: #666; }
#rightBox{ float: right; height: 480px; width: 684px; position: relative; }
#topBox{ width: 100%; position: relative; height: 370px; margin-top: 7px; margin-bottom: 7px; text-align: left; }
.prize{ width: 105px; color: #999; margin-left: 5px; margin-right: 5px; margin-bottom: 5px;  }
.timeBOx{ width: 6pc; }
#info{ height: 90px; width: 100%; position: relative; }
#BottomBox{ height: 100px; width: 100%; margin-top: 7px; position: relative; float: left; }
</style>
</head>
<body>
	<div id="mainBox">
		<div id="leftBox"><img src="${pageContext.request.contextPath}/${temp.acPicAddress}" width="320" height="480"/></div>
        <div id="rightBox">
       	  <div id="topBox">
       	  <!-- 此处form的action转移到js中 -->
          	<form id="myform" method="post">
                <p>KTV名称：<input name="ktvName" id="ktvName" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;活动名称：<input name="acName" id="acName" value="${temp.tempName}"/></p>
                <p>活动规则：<textarea rows="1" id="acRule" name="acRule">${temp.acRule}</textarea></p>
                <p style="text-align:center">活动奖品：</p>
                <p style="text-align:center">
                	<input name="prize1" id="prize1" class="prize" value="第一个奖品" onfocus="this.value=''"/>
                	<input name="prize2" id="prize2" class="prize" value="第二个奖品" onfocus="this.value=''"/>
                    <input name="prize3" id="prize3" class="prize" value="第三个奖品" onfocus="this.value=''"/>
                    <input name="prize4" id="prize4" class="prize" value="第四个奖品" onfocus="this.value=''"/>
                    <input name="prize5" id="prize5" class="prize" value="第五个奖品" onfocus="this.value=''"/>
                    <input name="prize6" id="prize6" class="prize" value="第六个奖品" onfocus="this.value=''"/>
                    <input name="prize7" id="prize7" class="prize" value="第七个奖品" onfocus="this.value=''"/>
                    <input name="prize8" id="prize8" class="prize" value="第八个奖品" onfocus="this.value=''"/>
                    <input name="prize9" id="prize9" class="prize" value="第九个奖品" onfocus="this.value=''"/>
                    <input name="prize10" id="prize10" class="prize" value="第十个奖品" onfocus="this.value=''"/>
              </p>
                <p style="text-align:center">开始日期：<input name="acStartDate" id="acStartDate" class="timeBOx" readonly="readonly" onclick="new Calendar().show(this.form.acStartDate);"/>&nbsp;&nbsp;&nbsp;&nbsp;具体时间：<input name="acStartTime" id="acStartTime" class="timeBOx"  readonly="readonly"/></p>
       	   		<p style="text-align:center">结束日期：<input id="acEndDate" class="timeBOx" readonly="readonly"/>&nbsp;&nbsp;&nbsp;&nbsp;具体时间：<input name="acEndTime" id="acEndTime" class="timeBOx"  readonly="readonly"/></p>
                <p style="text-align:center">开奖结束：<input id="acCloseDate" class="timeBOx" readonly="readonly"/>&nbsp;&nbsp;&nbsp;&nbsp;具体时间：<input name="acCloseTime" id="acCloseTime" class="timeBOx"  readonly="readonly"/></p>
                <!-- 隐藏域，传递picAddress -->
                <input type="text" name="acPicAddress" value="${temp.acPicAddress}" style="display:none;"/>
                <!-- 隐藏域，传递奖品个数 -->
                <span style="display: none;" id="prs"></span>
            </form>
          </div>
          <div id="info">
            <p><span style="font-weight:bold">操作提示:</span>结束日期和开奖结束不用填写。您只需要将其余的空格填满即可。注意：具体时间请注意格式：<span style="color:#F00; font-weight:bold;">24:00:00</span>(请用24小时制，具体到秒)</p>
          </div>
      </div>
      <div id="BottomBox">
       	    <img src="${pageContext.request.contextPath}/resources/common/img/add_button.gif" width="384" height="80" border="0" usemap="#Map" />
        <map name="Map" id="Map">
              <area shape="rect" coords="22,14,183,68" id="cancel" href="javascript:" />
              <area shape="rect" coords="203,15,363,67" id="subtt" href="javascript:" />
            </map>
            </div>
    </div>
</body>
</html>