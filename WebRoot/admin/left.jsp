<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>left</title>
<style type="text/css">
body { background-color: #CCC; }
#mylinks{ margin: 0px; padding: 0px; height: auto; width: auto; }
ul{ list-style: square; }
li{ height: auto; width: auto; margin-top: 15px; }
.title {color:#fff; font-size:26px; font-weight:bold;}
#mylinks a:link,#mylinks a:visited { color: #009; text-decoration: none; }
#mylinks a:hover,#mylinks a:active { color: #F00; text-decoration: underline; }
</style>
</head>
<body>
	<div id="mylinks">
    	<ul>
        	<li class="title">用户设置</li>
        	<li><a href="javascript:" target="rightFram">注册申请</a></li>
            <li><a href="javascript:" target="rightFram">密码修改</a></li>
            <li><a href="javascript:" target="rightFram">用户管理</a></li>
            <li><a href="javascript:" target="rightFram">角色管理</a></li>
            <li class="title">模版设置</li>
            <li><a href="${pageContext.request.contextPath}/ManageTemp.ktv?action=ShowPage" target="rightFram" title="管理已有模版信息">模版管理</a></li>
            <li><a href="${pageContext.request.contextPath}/admin/addTemp.jsp" target="rightFram" title="新建一个模版">新增模版</a></li>
            <li class="title">活动管理</li>
            <li><a href="${pageContext.request.contextPath}/ManageGame.ktv?action=GetTemp" target="rightFram">新增活动</a></li>
            <li><a href="${pageContext.request.contextPath}/ManageGame.ktv?action=ShowGames" target="rightFram" >管理活动</a></li>
            <li class="title">其他设置</li>
            <li><a href="javascript:" target="rightFram">保留链接</a></li>
            <li><a href="javascript:" target="rightFram">保留链接</a></li>
            <li><a href="javascript:" target="rightFram">保留链接</a></li>
      </ul>
</div>
</body>
</html>