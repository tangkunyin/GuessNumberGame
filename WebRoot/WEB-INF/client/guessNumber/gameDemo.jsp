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
<script type="text/javascript">
$().ready(function(){
	delButt();
	check();
	setInEmpty();
	getNumbers();
});
function check(){
	$("#subtt").unbind("click");
	$("#subtt").bind("click",function(){
		var input=$.trim($("#num").html());
		if(input==""){
			$("#num").html("输入不能为空");
		}else{
			//执行数据格式合法性检查
			if(checkData(input)){
				//提交的数据不能用重复
				$.post("GuessNum.ktv?action=equalJudge&input="+input,null,function(data){
					if(data=="ok"){
						$("#num").html("数字"+input+"测试成功");
					}else{
						$("#num").html("该数字已有用户提交");
					}
				},"text");
			}
		}
	});
}
//set input area empty
function setInEmpty(){
	$("#num").unbind("click");
	$("#num").bind("click",function(){
		$("#num").html("");
	});
}
//judge data
function checkData(data){
	if(data.length==3){
		return true;
	}else{
		$("#num").html("数据长度不正确！");
		return false;
	}
	//由于不是<input>框，因此用户只能通过点击输入，由此不需要判断数字有效性
	/*else{
		regex  /^\d+$/g		if(!/^\d+$/g.test(data)){
			$("#num").html("请输入有效的三位数字！");
			$("#num").focus();
			return false;
		}else{
			return true;
		}
	}*/
}
/*
 * get numbers
 */
function getNumbers(){
	//分别获取每个按键上的数字
	$("#k1").unbind("click");
	$("#k1").bind("click",function(){
		var _1=1;
		checkThisButt(_1);
	});
	$("#k2").unbind("click");
	$("#k2").bind("click",function(){
		var _2=2;
		checkThisButt(_2);
	});
	$("#k3").unbind("click");
	$("#k3").bind("click",function(){
		var _3=3;
		checkThisButt(_3);
	});
	$("#k4").unbind("click");
	$("#k4").bind("click",function(){
		var _4=4;
		checkThisButt(_4);
	});
	$("#k5").unbind("click");
	$("#k5").bind("click",function(){
		var _5=5;
		checkThisButt(_5);
	});
	$("#k6").unbind("click");
	$("#k6").bind("click",function(){
		var _6=6;
		checkThisButt(_6);
	});
	$("#k7").unbind("click");
	$("#k7").bind("click",function(){
		var _7=7;
		checkThisButt(_7);
	});
	$("#k8").unbind("click");
	$("#k8").bind("click",function(){
		var _8=8;
		checkThisButt(_8);
	});
	$("#k9").unbind("click");
	$("#k9").bind("click",function(){
		var _9=9;
		checkThisButt(_9);
	});
	$("#k0").unbind("click");
	$("#k0").bind("click",function(){
		var _0=0;
		checkThisButt(_0);
	});
}
//点击0-9之间的数字时，每点击一次，判断一次长度
checkThisButt=function(number){
	//如果非数字，则将输入框清空
	if(!/^\d+$/g.test($("#num").html())){
		$("#num").html("");
	}
	//文本框的maxlength限制不了js的输入
	if($("#num").html().length<3){
		$("#num").html($("#num").html()+number);
	}
};
/*
 * delete button function
 */
function delButt(){
	$("#delete").unbind("click");
	$("#delete").bind("click",function(){
		var temp=$.trim($("#num").html());
		var newData=temp.substring(0,temp.length-1);
		$("#num").html(newData);
	});
}
</script>
</head>
<body>
<div id="main">
  <div id="nav"><img src="${pageContext.request.contextPath}/resources/guessNumber/images/navbg.png" border="0" usemap="#Map"/>
    <map name="Map">
      <area shape="rect" coords="36,16,95,73" href="javascript:" class="mylink">
      <area shape="rect" coords="506,11,627,80" href="${pageContext.request.contextPath}/GuessNum.ktv?action=moreGameInfo" class="mylink">
    </map>
  </div>
  <div id="show"><img src="${pageContext.request.contextPath}/resources/guessNumber/images/showEmptybg.png" border="0" usemap="#Map2"/>
    <div id="rule"><h2 style="padding-left: 35px; padding-right: 35px; text-overflow:ellipsis; overflow:hidden;">活动规则...</h2></div>
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