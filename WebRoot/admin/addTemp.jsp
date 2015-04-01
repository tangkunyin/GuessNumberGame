<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>增加游戏模版设置页</title>
<style type="text/css">
#main{ padding: 0px; margin-top: 20px; margin-right: auto; margin-left: auto; height: 550px; width: 820px; text-align: center; }
textarea {resize:none;}
img {border:none;}
#upBox{ float: left; height: 480px; width: 100%; }
#leftBox{ float: left; height: 480px; width: 460px; border-right-width: 2px; border-right-style: dashed; border-right-color: #000; }
.layer{ float: left; height: auto; width: 100%; text-align: left; margin-bottom: 50px; }
#leftBox_up{ height: 400px; width: 100%; float: left; }
#leftBox_down{ float: left; height: 80px; width: 100%; line-height: 80px; }
#rightBox{ float: right; height: 480px; width: 350px; }
#downBox{ line-height: 70px; float: left; height: 70px; width: 100%; }
</style>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/guessNumber/js/addTemp.js"></script>
</head>
<body>
	<div id="main">
    	<div id="upBox">
        	<div id="leftBox">
                <div id="leftBox_up">
                	<form action="${pageContext.request.contextPath}/addTemp.ktv" method="POST" id="myform">
      				<div class="layer">模版名称：<input type="text" size="50" id="tempName" name="tempName" maxlength="30"></div>
      				<div class="layer">活动规则：<textarea cols="35" rows="5" id="acRule" name="acRule"></textarea></div>
      				<div class="layer" style="margin-bottom: 0px;">
                    	启用模版<input name="tempValid" type="radio" value="1" checked="checked" />&nbsp;&nbsp;
    					禁用模版<input name="tempValid" type="radio" value="0" />
    					<p style="color: red;">提示：如果录入信息之后暂不能提供使用，请选择禁用模版！</p>
                    </div>
		      	</form>
                <div class="layer" style="text-align:center">
                	<h2>此处上传的图片用作活动效果展示图，大图会自动压缩比例。最佳大小：320*480</h2>
                </div>
                </div>
                <div id="leftBox_down">
                	 <form action="${pageContext.request.contextPath}/uploadImgFile.ktv" method="POST" id="uploadForm" enctype="multipart/form-data">
        	活动图片：<input type="file" id="acPicAddress" name="acPicAddress" />
        	&nbsp;&nbsp;<input type="button" id="subttfile" name="subttfile" value="上传图片" /><span style="color:red;"><c:out value="${errorMsg}" /></span>
   	  </form>
                </div>
            </div>
            <div id="rightBox"><img id="showAcPic" width="320px" height="480px" /></div>
        </div>
        <div id="downBox">
        	<input type="button" id="resetButt" value="重新填写" />&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" id="subtt" name="subtt" value="增加模版" />
        </div>
</div>
</body>
</html>