$().ready(function(){
	MyGWButtClick();
	MySWButtClick();
	MyBWButtClick();
	PreButtFunction();
	NextButtFunction();
});
//获取按钮个位页点击事件
function MyGWButtClick(){
	$("#gwsubbt").unbind("click");
	$("#gwsubbt").bind("click",function(){
		//获取一位数
		var gnum=$.trim($("#gnum").val());
		if(gnum==""){
			$("#gnum").val("");
			$("#gnum").focus();
		}else{
			//判断是不是数字
			if(!/^\d+$/g.test(gnum)){
				$("#gnum").val("");		
			}else{
				$.post("/game/tv.ktv?action=ShowNumbers&gwinput="+gnum,null,function(data){
					if(data=="gwok"){
						window.location="KJInput-sw.jsp";
					}
				},"text");
			}
		}
	});
}

//获取按钮十位页点击事件
function MySWButtClick(){
	$("#swsubbt").unbind("click");
	$("#swsubbt").bind("click",function(){
		var snum=$.trim($("#snum").val());
		if(snum==""){
			$("#snum").val("");
			$("#snum").focus();
		}else{
			if(!/^\d+$/g.test(snum)){
				$("#snum").val("");		
			}else{
				$.post("/game/tv.ktv?action=ShowNumbers&swinput="+snum,null,function(data){
					if(data=="swok"){
						window.location="KJInput-bw.jsp";
					}
				},"text");
			}
		}
	});
}

//获取按钮百位页点击事件
function MyBWButtClick(){
	$("#bwsubbt").unbind("click");
	$("#bwsubbt").bind("click",function(){
		var bnum=$.trim($("#bnum").val());
		if(bnum==""){
			$("#bnum").val("");
			$("#bnum").focus();
		}else{
			if(!/^\d+$/g.test(bnum)){
				$("#bnum").val("");		
			}else{
				$.post("/game/tv.ktv?action=ShowNumbers&bwinput="+bnum,null,function(data){
					if(data=="bwok"){
						$("#nextButt").css("display","block");
					}
				},"text");
			}
		}
	});
}

function PreButtFunction(){
	$("#preButt").unbind("click");
	$("#preButt").bind("click",function(){
		window.history.back(-1);
	});
}

function NextButtFunction(){
	$("#nextButt").unbind("click");
	$("#nextButt").bind("click",function(){
		//此时在请求中奖用户信息。。。
		window.location="/game/tv.ktv?action=ShowWinners";
	});
}