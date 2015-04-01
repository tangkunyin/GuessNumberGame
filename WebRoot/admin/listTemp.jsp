<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>增加游戏页</title>
<style type="text/css">
body { text-align: center; margin: 0px; padding: 0px; }
#centerBox{ padding: 0px; height: 820px; width: 1000px; margin-right: auto; margin-left: auto; }
#centerBox ul{ list-style: none; margin: 0px; padding: 0px; }
#centerBox li{ float: left; text-overflow:ellipsis; overflow:hidden; height: 400px; width: 240px; display: block; margin-top: 10px; margin-right: 5px; margin-left: 5px; background-repeat: no-repeat; background-position: center center; }
#centerBox li a{ margin: 0px; padding: 0px; height: 400px; width: 240px; display: block; text-decoration: none; line-height: 45px; font-size: 16px; }
#centerBox li a:hover { background-color: #DBDBDB; background:rgba(99, 55, 11, 0.3); -moz-opacity:0.3; filter:alpha(opacity=30); text-decoration: none; color:red;}
.words{ display: block; height: auto; width: 100%; }
#pager{ height: 30px; width: 400px; margin-top: -40px; margin-right: auto; margin-left: auto; text-align:center;}
</style>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/common/css/Pager.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/js/jquery.pager.js"></script>
<script type="text/javascript" language="javascript">
		$(document).ready(function() {
			//加载背景图
			$(".noData").css("background-image","url(${pageContext.request.contextPath}/resources/common/img/nogamepic.gif)");
			//页面载入时初始化
			$("#pager").pager({ pagenumber: ${currentPage}, pagecount: ${totalPages}, buttonClickCallback: PageClick });
		})

		//单页分页导航数字是执行异步加载数据
        PageClick = function(pageclickednumber) {
           	$("#pager").pager({ pagenumber: pageclickednumber, pagecount: ${totalPages}, buttonClickCallback: PageClick });
        	$.post("${pageContext.request.contextPath}/ManageGame.ktv?action=GetTemp&pagenumber="+pageclickednumber,null,function(data){
            		$("#result").html(data);
			},"html");
        }
</script>
</head>
<body id="result">
<div id="centerBox">
	<c:choose>
		<c:when test="${totals==0}">
			<h2>没有预定义模版，请先<a href="${pageContext.request.contextPath}/admin/addTemp.jsp">添加模版</a></h2>
		</c:when>
		<c:otherwise>
		<ul>
			<c:forEach var="item" items="${KacTemplaites}" begin="${beginIndex}" end="${endIndex}">
				<c:choose>
					<c:when test="${item.tempValid==0}">
					<li class="BgImg">
						<a href="javascript:alert('该模版被禁用，请联系开发商')" title="无效模版">
							<img width="240px" height="400px" src="${pageContext.request.contextPath}/${item.acPicAddress}"/>
						</a>
					</li>
					</c:when>
					<c:otherwise>
					<li class="BgImg">
    					<a href="${pageContext.request.contextPath}/ManageGame.ktv?action=showForm&id=${item.acTempId}" title="点击进行下一步操作">
    						<img width="240px" height="400px" src="${pageContext.request.contextPath}/${item.acPicAddress}"/>
    					</a>
    				</li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:forEach begin="${totals}" end="${endIndex}">
				<li class="noData"><a href="${pageContext.request.contextPath}/admin/addTemp.jsp" title="点击添加模版"></a></li>
			</c:forEach>
    	</ul>
		</c:otherwise>
	</c:choose>
</div>
<div id="pager"></div>
</body>
</html>