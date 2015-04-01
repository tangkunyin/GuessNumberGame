<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>游戏管理页面</title>
<style type="text/css">
body { text-align: center; }
img {border:none;}
#centerBox{ width: 855px; margin-right: auto; margin-left: auto; min-height: 250px; }
textarea{resize:none}
p{margin-top:3px; margin-bottom:2px; padding:0;}
#pager{ height: 30px; width: 615px; padding-left:245px; margin-right: auto; margin-left: auto; margin-top: -20px; text-align:center;}
table{text-align:left;}
</style>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/common/css/Pager.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/js/jquery.pager.js"></script>
<script type="text/javascript" language="javascript">
		$(document).ready(function() {
			alertThis()
			//加载背景图
			//页面载入时初始化
			$("#pager").pager({ pagenumber: ${currentPage}, pagecount: ${totalPages}, buttonClickCallback: PageClick });
		})

		//单页分页导航数字是执行异步加载数据
        PageClick = function(pageclickednumber) {
           	$("#pager").pager({ pagenumber: pageclickednumber, pagecount: ${totalPages}, buttonClickCallback: PageClick });
        	$.post("${pageContext.request.contextPath}/ManageGame.ktv?action=ShowGames&pagenumber="+pageclickednumber,null,function(data){
            		$("#result").html(data);
			},"html");
        }
		
		//prizes
		function alertThis(){
			$("#prizes").unbind("click");
			$("#prizes").bind("click",function(){
				var value=$("#prizes").val();
				var prizes=new Array();
				prizes=value.split(",");
				//alert("共"+prizes.length+"个奖品！");
				for(var i=0;i<prizes.length;i++){
					if(prizes[i]!=""){
						alert("第"+(i+1)+"个奖品是："+prizes[i]);
					}
				}
			});
		}
</script>
</head>
<body id="result">
<div id="centerBox">
		<c:forEach var="item" items="${games}" begin="${beginIndex}" end="${endIndex}">
	<table width="850" border="1">
		<tr style="background:#666; color:#FFF">
    	<td>第<span style="font-weight:bold">${item.activityId}</span>项活动&nbsp;&nbsp;共<span style="font-weight:bold">${totals}</span>项活动</td>
        <td>本活动已有<span style="color:red; font-weight:bold;">${item.acCrrentPeople}</span>人参加</td>
    </tr>
  <tr>
    <td width="425" height="32">活动名称：<input type="text" size="40pc" id="acName" name="acName" value="${item.acName}"></td>
    <td>
    	活动状态：&nbsp;&nbsp;
    	<c:if test="${item.acValid==2}"><span style="background-color: yellow;">活动未开始</span></c:if>
		<c:if test="${item.acValid==1}"><span style="background-color:  #77FF3C;">活动进行中</span></c:if>
    	<c:if test="${item.acValid==0}"><span style="background-color: red;">活动已过期</span></c:if>
    </td>
  </tr>
  <tr>
  	<!-- 活动时间 -->
    <td>
    	<p>开始时间：<input type="text"  id="acStartDate" size="40pc" readonly="readonly" value="日期：${item.acStartDate}&nbsp;&nbsp;&nbsp;&nbsp;当日时间：${item.acStartTime}" /></p>
    	<p>结束时间：<input type="text" id="acEndTime" size="40pc" readonly="readonly" value="日期：${item.acStartDate}&nbsp;&nbsp;&nbsp;&nbsp;当日时间：${item.acEndTime}"/></p>
    	<p>开奖时间：<input type="text" id="acCloseTime" size="40pc" readonly="readonly" value="日期：${item.acStartDate}&nbsp;&nbsp;&nbsp;&nbsp;当日时间：${item.acCloseTime}"/></p>
    </td>
    <td>
    	活动规则：<textarea cols="47" rows="3" id="acRule" name="acRule">${item.acRule}</textarea>
    </td>
  </tr>
  <tr>
    <td>
    	奖品是<input type="text" id="prizes" size="50pc" readonly="readonly" value="${item.prize1},${item.prize2},${item.prize3},${item.prize4},${item.prize5},${item.prize6},${item.prize7},${item.prize8},${item.prize9},${item.prize10}" />
    </td>
    <td style="text-align: center;">
   	<a href="${pageContext.request.contextPath}/${item.acPicAddress}" title="活动介绍图" target="_blank">展示</a>&nbsp;&nbsp;&nbsp;&nbsp;
    <a href="${pageContext.request.contextPath}/ManageGame.ktv?action=ShowUpdateGame&id=${item.activityId}">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;
    <a href="${pageContext.request.contextPath}/${item.qrcodeImg}" title="预览活动" target="_blank">二维码</a>&nbsp;&nbsp;&nbsp;&nbsp;
    <a href="${pageContext.request.contextPath}/ManageGame.ktv?action=DelGames&id=${item.activityId}">删除</a>
    </td>
  </tr>
</table><br/>
	</c:forEach>
</div>
<div id="pager"></div>
</body>
</html>