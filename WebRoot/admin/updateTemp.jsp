<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>模版修改</title>
<style type="text/css">
body { text-align: center; }
img {border:none;}
#main{ width: 1024px; margin-right: auto; margin-left: auto; margin-top:120px; height: 500px; }
textarea{resize:none;}
</style>
</head>
<body>
<div id="main">	
  <form action="ManageTemp.ktv?action=UpdateTemp" method="post">
  <div  style="display: none;">
  	<input name="acTempId" type="text" value="${temple.acTempId}" />
  	<input name="acPicAddress" type="text" value="${temple.acPicAddress}" />
  </div>
	<table width="1024" border="1">
		<tr style="background:#666; color:#FFF">
    	<td>第<span style="font-weight:bold">${temple.acTempId}个模版</span></td>
        <td><span style="font-weight:bold; color:red;">您正在修改这个模版，请谨慎操作</span></td>
    </tr>
  <tr>
    <td width="420" height="32">模版名称：<input type="text" id="tempName" name="tempName" value="${temple.tempName}" style="width:19pc"></td>
    <td width="580">
    	模版状态：&nbsp;&nbsp;
		<c:if test="${temple.tempValid==1}">
			启用模版<input name="tempValid" type="radio" value="1" checked="checked" />&nbsp;&nbsp;
 			禁用模版<input name="tempValid" type="radio" value="0" />
		</c:if>
    	<c:if test="${temple.tempValid==0}">
    		启用模版<input name="tempValid" type="radio" value="1" />&nbsp;&nbsp;
 			禁用模版<input name="tempValid" type="radio" value="0" checked="checked" />
    	</c:if>
    </td>
  </tr>
  <tr>
    <td>
    	活动规则：<textarea cols="35" rows="1" id="acRule" name="acRule">${temple.acRule}</textarea>
    </td>
    <td><input type="submit" value="确定修改"/>&nbsp;&nbsp;&nbsp;&nbsp;<input type="button"" value="取消修改" onclick="javascript:window.history.back(-1);"></td>
  </tr>
</table>
</form>
</div>
</body>
</html>