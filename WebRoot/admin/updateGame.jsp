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
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/js/ShowCalendar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/guessNumber/js/addGame-SpecificTime.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/js/updateGame.js"></script>
</head>
<body id="result">
<div id="centerBox">
		<form action="ManageGame.ktv?action=UpdateGame" method="post" id="myform">
	<table width="850" border="1">
		<caption style="color:red; font-size:26px; margin-bottom:10px;">您将要修改第${gameInfo.activityId}项活动。请慎重操作！</caption>
  <tr>
    <td width="425" height="32">活动名称：<input type="text" size="40pc" id="acName" name="acName" value="${gameInfo.acName}"></td>
    <td>
    	活动状态：&nbsp;&nbsp;
    	<c:if test="${gameInfo.acValid==2}">
	    	<span style="background-color: yellow;">未开始</span><input name="acValid" type="radio" value="2" checked="checked" />
	    	&nbsp;进行中<input name="acValid" type="radio" value="1" />
	    	&nbsp;已过期<input name="acValid" type="radio" value="0" />
    	</c:if>
		<c:if test="${gameInfo.acValid==1}">
			未开始<input name="acValid" type="radio" value="2" />
	    	&nbsp;<span style="background-color:  #77FF3C;">进行中</span><input name="acValid" type="radio" value="1" checked="checked" />
	    	&nbsp;已过期<input name="acValid" type="radio" value="0" />
		</c:if>
    	<c:if test="${gameInfo.acValid==0}">
    		未开始<input name="acValid" type="radio" value="2" />
	    	&nbsp;进行中<input name="acValid" type="radio" value="1" />
	    	&nbsp;<span style="background-color: red;">已过期</span><input name="acValid" type="radio" value="0" />
    	</c:if>
    </td>
  </tr>
  <tr>
  	<!-- 活动时间 -->
    <td>
    	<p>开始时间：<input type="text"  name="acStartDate" id="acStartDate" size="10pc"  value="${gameInfo.acStartDate}" onclick="new Calendar().show(this.form.acStartDate);"/>&nbsp;<input type="text"  name="acStartTime" id="acStartTime" size="8pc" readonly="readonly" value="${gameInfo.acStartTime}" /></p>
    	<p>结束时间：<input type="text" id="acEndDate"   size="10pc" readonly="readonly" value="${gameInfo.acStartDate}"/>&nbsp;<input type="text"  name="acEndTime" id="acEndTime" size="8pc" readonly="readonly" value="${gameInfo.acEndTime}" /></p>
    	<p>开奖时间：<input type="text" id="acCloseDate"  size="10pc" readonly="readonly" value="${gameInfo.acStartDate}"/>&nbsp;<input type="text"  name="acCloseTime" id="acCloseTime" size="8pc" readonly="readonly" value="${gameInfo.acCloseTime}" /></p>
    </td>
    <td>
    	活动规则：<textarea cols="47" rows="3" id="acRule" name="acRule">${gameInfo.acRule}</textarea>
    </td>
  </tr>
  <tr>
    <td>
    	<p>奖品一：<input type="text" name="prize1" id="prize1" size="15pc"  value="${gameInfo.prize1}"/>&nbsp;奖品二：<input type="text" name="prize2" id="prize2" size="15pc"  value="${gameInfo.prize2}"/></p>
    	<p>奖品三：<input type="text" name="prize3" id="prize3" size="15pc"  value="${gameInfo.prize3}"/>&nbsp;奖品四：<input type="text" name="prize4" size="15pc"  value="${gameInfo.prize4}"/></p>
    	<p>奖品五：<input type="text" name="prize5" size="15pc"  value="${gameInfo.prize5}"/>&nbsp;奖品六：<input type="text" name="prize6" size="15pc"  value="${gameInfo.prize6}"/></p>
    	<p>奖品七：<input type="text" name="prize7" size="15pc"  value="${gameInfo.prize7}"/>&nbsp;奖品八：<input type="text" name="prize8" size="15pc"  value="${gameInfo.prize8}"/></p>
    	<p>奖品九：<input type="text" name="prize9" size="15pc"  value="${gameInfo.prize9}"/>&nbsp;奖品十：<input type="text" name="prize10" size="15pc"  value="${gameInfo.prize10}"/></p>
    </td>
    <td style="text-align: center;">
		<input type="text" name="activityId" value="${gameInfo.activityId}" style="display: none;" />
		<input type="button"" value="确定修改" id="subtt" />&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="取消修改" onclick="javascript:window.history.back(-1);" />
    </td>
  </tr>
</table><br/>
</form>
</div>
</body>
</html>