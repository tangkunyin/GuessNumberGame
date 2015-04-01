/* 
 *  game controler function
 *  @author tangkunyin
 *  @since 2013-05-20
 *  --------------------
 *  All alow data format
 *  1. 3 length;
 *  2. must be numbers
 *  3. the number can not be equal
 */
$().ready(function(){
	delButt();
	check();
	setInEmpty();
	getNumbers();
});
function check(){
	$("#subtt").unbind("click");
	$("#subtt").bind("click",function(){
		var input=$.trim($("#num").html());
		if(input==""){
			$("#num").html("输入不能为空");
		}else{
			//执行数据格式合法性检查
			if(checkData(input)){
				//提交的数据不能用重复
				$.post("GuessNum.ktv?action=equalJudge&input="+input,null,function(data){
					if(data=="ok"){
						window.location.href="GuessNum.ktv?action=submitData&input="+input;
					}else{
						$("#num").html("该数字已有用户提交");
					}
				},"text");
			}
		}
	});
}
//set input area empty
function setInEmpty(){
	$("#num").unbind("click");
	$("#num").bind("click",function(){
		$("#num").html("");
	});
}
//judge data
function checkData(data){
	if(data.length==3){
		return true;
	}else{
		$("#num").html("数据长度不正确！");
		return false;
	}
	//由于不是<input>框，因此用户只能通过点击输入，由此不需要判断数字有效性
	/*else{
		regex  /^\d+$/g		if(!/^\d+$/g.test(data)){
			$("#num").html("请输入有效的三位数字！");
			$("#num").focus();
			return false;
		}else{
			return true;
		}
	}*/
}
/*
 * get numbers
 */
function getNumbers(){
	//分别获取每个按键上的数字
	$("#k1").unbind("click");
	$("#k1").bind("click",function(){
		var _1=1;
		checkThisButt(_1);
	});
	$("#k2").unbind("click");
	$("#k2").bind("click",function(){
		var _2=2;
		checkThisButt(_2);
	});
	$("#k3").unbind("click");
	$("#k3").bind("click",function(){
		var _3=3;
		checkThisButt(_3);
	});
	$("#k4").unbind("click");
	$("#k4").bind("click",function(){
		var _4=4;
		checkThisButt(_4);
	});
	$("#k5").unbind("click");
	$("#k5").bind("click",function(){
		var _5=5;
		checkThisButt(_5);
	});
	$("#k6").unbind("click");
	$("#k6").bind("click",function(){
		var _6=6;
		checkThisButt(_6);
	});
	$("#k7").unbind("click");
	$("#k7").bind("click",function(){
		var _7=7;
		checkThisButt(_7);
	});
	$("#k8").unbind("click");
	$("#k8").bind("click",function(){
		var _8=8;
		checkThisButt(_8);
	});
	$("#k9").unbind("click");
	$("#k9").bind("click",function(){
		var _9=9;
		checkThisButt(_9);
	});
	$("#k0").unbind("click");
	$("#k0").bind("click",function(){
		var _0=0;
		checkThisButt(_0);
	});
}
//点击0-9之间的数字时，每点击一次，判断一次长度
checkThisButt=function(number){
	//如果非数字，则将输入框清空
	if(!/^\d+$/g.test($("#num").html())){
		$("#num").html("");
	}
	//文本框的maxlength限制不了js的输入
	if($("#num").html().length<3){
		$("#num").html($("#num").html()+number);
	}
};
/*
 * delete button function
 */
function delButt(){
	$("#delete").unbind("click");
	$("#delete").bind("click",function(){
		var temp=$.trim($("#num").html());
		var newData=temp.substring(0,temp.length-1);
		$("#num").html(newData);
	});
}