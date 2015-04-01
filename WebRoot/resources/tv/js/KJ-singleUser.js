$().ready(function(){
	reselectFunction();
	singleOkFunction();
	lockUserStatus();
	nextButt();
});
//重新选择
function reselectFunction(){
	$("#reselectButt").unbind("click");
	$("#reselectButt").bind("click",function(){
		window.location="tv.ktv?action=ShowDoubleWinners";
	});
}
//显示到TV
function singleOkFunction(){
	$("#singleOkButt").unbind("click");
	$("#singleOkButt").bind("click",function(){
		$.post("tv.ktv?action=setWinnerOrderEmpty");
		//取得当前的俩用户的答案，返回给服务器
		var uanswer=$("#uanswer").text();
		$.post("tv.ktv?action=showWinnersOnTV&uanswer="+uanswer,null,function(data){
			if(data=="ok"){
				$("#promot").text("用户更换成功！");
			}else{
				$("#promot").text("服务器未收到数据，请重试！");
			}
		},"text");
	});
}

//锁定用户
function lockUserStatus(){
	$("#LockUserButt").unbind("click");
	$("#LockUserButt").bind("click",function(){
		//将临时获奖用户名单清空
		var promot=$("#promot").text();
		if(promot=="用户更换成功！"){
			$.post("tv.ktv?action=setWinnerOrderEmpty");
			$("#promot").text("获奖者已提交，请进入下一轮！");
			$("#reselectButt").attr("disabled","disabled");
			$("#singleOkButt").attr("disabled","disabled");
			$("#LockUserButt").attr("disabled","disabled");
			$("#next").css("display","block");
		}else{
			$("#promot").text("请确保用户已在TV端显示再锁定用户");
			return false;
		}
	});
}

//下一步按钮
function nextButt(){
	$("#next").unbind("click");
	$("#next").bind("click",function(){
		var uanswer=$("#uanswer").text();
		$.post("tv.ktv?action=InitiaInputNums&uanswer="+uanswer,null,function(isok){
			if(isok=="next"){
				window.location="admin/KJInput-gw.jsp";
			}else if(isok=="end"){
				alert("开奖结束");
				window.location="/game/Login.ktv?method=GameOver";
			}else{
				alert("系统错误");
				window.location="/game/Login.ktv?method=GameOver";
			}
		},"text");
	});
}