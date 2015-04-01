<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width; initial-scale=1.0">
<title>啊哈，出错咯...</title>
<style type="text/css">
body { margin: 0px; padding: 0px; text-align: center; color: #fff}
#mainBox{ width: 320px; height: 400px; margin-right: auto; margin-left: auto; background-image: url(${pageContext.request.contextPath}/resources/common/img/beforegamebg.jpg); padding-top: 80px; }
</style>
</head>
<body>
	<div id="mainBox">
   	  <h2>${gameError}</h2>
        <h2><a href="${pageContext.request.contextPath}/GuessNum.ktv?action=moreGameInfo" >查看更多活动信息</a></h2>
        <hr/>
        <h2 onclick="window.history.go(-1)">返回上一步</h2>
    </div>
</body>
</html>