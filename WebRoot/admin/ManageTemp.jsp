<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>模版管理 - 后台模版修改页</title>
<style type="text/css">
body { text-align: center; }
img {border:none;}
#main{ width: 1024px; margin-right: auto; margin-left: auto; height: 645px; }
textarea{resize:none}
#pager{ height: 30px; width: 400px; margin-right: auto; margin-left: auto; margin-top: -40px; text-align:center;}
</style>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/common/css/Pager.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/js/jquery.pager.js"></script>
<script type="text/javascript" language="javascript">
		$(document).ready(function() {
			//页面载入时初始化
			$("#pager").pager({ pagenumber: ${currentPage}, pagecount: ${totalPages}, buttonClickCallback: PageClick });
        });

        //单页分页导航数字是执行异步加载数据
        PageClick = function(pageclickednumber) {
           	$("#pager").pager({ pagenumber: pageclickednumber, pagecount: ${totalPages}, buttonClickCallback: PageClick });
        	$.post("${pageContext.request.contextPath}/ManageTemp.ktv?action=ShowPage&pagenumber="+pageclickednumber,null,function(data){
            		$("#result").html(data);
			},"html");
        }
</script>
</head>
<body id="result">
<div id="main">
 <c:choose>
 	<c:when test="${totals==0}">
 		<h2>没有预定义模版，请先<a href="${pageContext.request.contextPath}/admin/addTemp.jsp">添加模版</a></h2>
 	</c:when>
 	<c:otherwise>
 		<c:forEach var="item" items="${KacTemplaites}" begin="${beginIndex}" end="${endIndex}">
	<table width="1024" border="1">
		<tr style="background:#666; color:#FFF">
    	<td>第<span style="font-weight:bold">${item.acTempId}</span>号模版</td>
        <td>共<span style="font-weight:bold">${totals}</span>个模版</td>
    </tr>
  <tr>
    <td width="292" height="32">模版名称：<input type="text" id="tempName" name="tempName" value="${item.tempName}" disabled="disabled"></td>
    <td width="716">
    	模版状态：&nbsp;&nbsp;
		<c:if test="${item.tempValid==1}"><span style="background-color: #77FF3C;">未禁用</span></c:if>
    	<c:if test="${item.tempValid==0}"><span style="background-color: red;">已禁用</span></c:if>
    	<!-- 模版使用次数 -->
    	&nbsp;&nbsp;&nbsp;&nbsp;本模版已被使用<span style="color:red; font-weight:bold;">${item.tempUsedTimes}</span>次
    	&nbsp;&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/${item.acPicAddress}" title="查看大图" target="_blank">模版展示图</a>
    </td>
  </tr>
  <tr>
    <td>
    	活动规则：<textarea cols="15" rows="1" id="acRule" name="acRule">${item.acRule}</textarea>
    </td>
    <td>操作：&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/ManageTemp.ktv?action=showUpdateTemp&id=${item.acTempId}">修改此模板</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/ManageTemp.ktv?action=deleteTemp&id=${item.acTempId}">删除此模板</a></td>
  </tr>
</table><br/>
	</c:forEach>
 	</c:otherwise>
 </c:choose>
</div>
<!-- 分页开始 -->
<div id="pager"></div>
</body>
</html>