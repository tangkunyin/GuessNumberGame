/* 
 *  game controler function
 *  @author tangkunyin
 *  @since 2013-04-11
 *  --------------------
 *  All alow data format
 *  1. 3 length;
 *  2. must be numbers
 */
$().ready(function(){
	check();
	showRules();
	resetButt();
	delButt();
	getNumbers();
});
function check(){
	$("#subButt").unbind("click");
	$("#subButt").bind("click",function(){
		var input=$.trim($("#num").val());
		if(input==""){
			//break submit
			$(".rules").css("display","none");
			$("#prompt").html("<h2 style='color:red;'>输入不能为空！</h2>");
			return false;
		}else{
			//执行数据格式合法性检查
			if(checkData(input)){
				//通过ajax异步发送数据请求服务器
				$.post("doGame?input="+input,null,function(data){
					$(".rules").css("display","none");
					$("#prompt").html(data);
				},"html");
			}
		}
	});
}
//show rules
function showRules(){
	$("#num").unbind("click");
	$("#num").bind("click",function(){
		$(".rules").css("display","block");
		$("#prompt").html("");
	});
}
//judge data
function checkData(data){
	if(data.length!=3){
		$(".rules").css("display","none");
		$("#prompt").html("<h2 style='color:red;'>输入数据长度不正确！</h2>");
		return false;
	}else{
		//regex  /^\d+$/g
		if(!/^\d+$/g.test(data)){
			$(".rules").css("display","none");
			$("#prompt").html("<h2 style='color:red;'>请输入有效的三位数字！</h2>");
			return false;
		}else{
			return true;
		}
	}
}
/*
 * reset button function
 */
function resetButt(){
	$("#reset").unbind("click");
	$("#reset").bind("click",function(){
		//set input empty
		$("#num").val("");
	});
}
/*
 * delete button function
 */
function delButt(){
	$("#delete").unbind("click");
	$("#delete").bind("click",function(){
		var temp=$.trim($("#num").val());
		var newData=temp.substring(0,temp.length-1);
		$("#num").val(newData);
	});
}
/*
 * get numbers
 */
function getNumbers(){
	//分别获取每个按键上的数字
	$("#0").unbind("click");
	$("#0").bind("click",function(){
		var _0=this.value;
		checkThisButt(_0);
	});
	$("#1").unbind("click");
	$("#1").bind("click",function(){
		var _1=this.value;
		checkThisButt(_1);
	});
	$("#2").unbind("click");
	$("#2").bind("click",function(){
		var _2=this.value;
		checkThisButt(_2);
	});
	$("#3").unbind("click");
	$("#3").bind("click",function(){
		var _3=this.value;
		checkThisButt(_3);
	});
	$("#4").unbind("click");
	$("#4").bind("click",function(){
		var _4=this.value;
		checkThisButt(_4);
	});
	$("#5").unbind("click");
	$("#5").bind("click",function(){
		var _5=this.value;
		checkThisButt(_5);
	});
	$("#6").unbind("click");
	$("#6").bind("click",function(){
		var _6=this.value;
		checkThisButt(_6);
	});
	$("#7").unbind("click");
	$("#7").bind("click",function(){
		var _7=this.value;
		checkThisButt(_7);
	});
	$("#8").unbind("click");
	$("#8").bind("click",function(){
		var _8=this.value;
		checkThisButt(_8);
	});
	$("#9").unbind("click");
	$("#9").bind("click",function(){
		var _9=this.value;
		checkThisButt(_9);
	});
}
//点击0-9之间的数字时，每点击一次，判断一次长度
checkThisButt=function(number){
	if($("#num").val().length<3){
		$("#num").val($("#num").val()+number);
	}
};