$().ready(function(){
	showWinners();
	lreselectFunction();
	rreselectFunction();
	doubleOkFunction();
	lockUserStatus();
	nextButt();
});
//页面加载完毕之后
function showWinners(){
	$.post("tv.ktv?action=InitiaWinner",null,function(data){
			if(data=="nobody#nobody"){
				userPromot();
			}else{
				var str=data.split("#");
				var luser=eval("("+str[0]+")");
				var ruser=eval("("+str[1]+")");
				
				$("#luserImg").attr("src",luser.user[0].headpicpath);
				$("#lnickName").text(luser.user[0].nickName);
				$("#lsex").text(luser.user[0].sex);
				$("#lanswer").text(luser.user[0].yourAnswer);
				
				$("#ruserImg").attr("src",ruser.user[0].headpicpath);
				$("#rnickName").text(ruser.user[0].nickName);
				$("#rsex").text(ruser.user[0].sex);
				$("#ranswer").text(ruser.user[0].yourAnswer);
			}
		},"text");
}

//左侧重新选择
function lreselectFunction(){
	$("#lreselectButt").unbind("click");
	$("#lreselectButt").bind("click",function(){
		$.post("tv.ktv?action=changeWinner",null,function(people){
			if(people=="nobody"){
				userPromot();
			}else{
				$("#luserImg").attr("src",people.user[0].headpicpath);
				$("#lnickName").text(people.user[0].nickName);
				$("#lsex").text(people.user[0].sex)
				$("#lanswer").text(people.user[0].yourAnswer)
			}
		},"json");
	});
}
//右侧重新选择
function rreselectFunction(){
	$("#rreselectButt").unbind("click");
	$("#rreselectButt").bind("click",function(){
		$.post("tv.ktv?action=changeWinner",null,function(people){
			if(people=="nobody"){
				userPromot();
			}else{
			$("#ruserImg").attr("src",people.user[0].headpicpath);
				$("#rnickName").text(people.user[0].nickName);
				$("#rsex").text(people.user[0].sex)
				$("#ranswer").text(people.user[0].yourAnswer)
			}
		},"json");
	});
}
//提示信息
function userPromot(){
	$("#promot").text("没有候选人员了...");
	$("#lreselectButt").attr("disabled","disabled");
	$("#rreselectButt").attr("disabled","disabled");
}
//显示到TV
function doubleOkFunction(){
	$("#doubleOkButt").unbind("click");
	$("#doubleOkButt").bind("click",function(){
		//取得当前的俩用户的答案，返回给服务器
		var lanswer=$("#lanswer").text();
		var ranswer=$("#ranswer").text();
		$.post("tv.ktv?action=showWinnersOnTV&answers="+lanswer+","+ranswer,null,function(data){
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
		if(promot=="用户更换成功！" || promot=="没有候选人员了..."){
			$.post("tv.ktv?action=setWinnerOrderEmpty");
			$("#promot").text("获奖者已提交，请进入下一轮！");
			$("#lreselectButt").attr("disabled","disabled");
			$("#rreselectButt").attr("disabled","disabled");
			$("#doubleOkButt").attr("disabled","disabled");
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
		var lanswer=$("#lanswer").text();
		var ranswer=$("#ranswer").text();
		$.post("tv.ktv?action=InitiaInputNums&luanswer="+lanswer+"&ruanswer="+ranswer,null,function(isok){
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