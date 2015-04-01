$().ready(function(){
	subttOK();
});

/**
 * 判断输入框为，控制表单提交
 * @author {tangkunyin} 
 * @since {2013-06-08} 
 */

function checkForm(){
	var acName=$.trim($("#acName").val());
	var acRule=$.trim($("#acRule").val());
	
	var acStartDate=$.trim($("#acStartDate").val());
	var acStartTime=$.trim($("#acStartTime").val());
	var acEndTime=$.trim($("#acEndTime").val());
	var acCloseTime=$.trim($("#acCloseTime").val());
	
	var prize1=$.trim($("#prize1").val());
	var prize2=$.trim($("#prize2").val());
	var prize3=$.trim($("#prize3").val());
	
	var flag=true;
	
	if(acName==""){
		alert("请填写活动名称！");
		$("#acName").focus();
		flag=false;
		return false;
	}
	
	if(acRule==""){
		alert("请填写活动规则！");
		$("#acRule").focus();
		flag=false;
		return false;
	}
	
	//活动开始时间检验	
	if(acStartDate==""){
		alert("请选输入开始日期！");
		$("#acStartDate").focus();
		flag=false;
		return false;
	}else{
		//将开始日期复制到截至和关闭框
		$("#acEndDate").val(acStartDate);
		$("#acCloseDate").val(acStartDate);
	}
	
	if(acStartTime==""){
		alert("请选输入开始时间！");
		$("#acStartTime").focus();
		flag=false;
		return false;
	}
	if(acEndTime==""){
		alert("请选输入结束时间！");
		$("#acEndTime").focus();
		flag=false;
		return false;
	}
	if(acCloseTime==""){
		alert("请选输入开奖结束时间！");
		$("#acCloseTime").focus();
		flag=false;
		return false;
	}
	if(prize1==""){
		alert("请设置第一个奖品！");
		$("#prize1").focus();
		flag=false;
		return false;
	}
	if(prize2==""){
		alert("请设置第二个奖品！");
		$("#prize2").focus();
		flag=false;
		return false;
	}
	if(prize3==""){
		alert("请设置第三个奖品！");
		$("#prize3").focus();
		flag=false;
		return false;
	}
	return flag;
}


//subutton submit
function subttOK(){
	$("#subtt").unbind("click");
	$("#subtt").bind("click",function(){
		if(true==checkForm()){
			var acStartDate=$.trim($("#acStartDate").val());
			var acStartTime=$.trim($("#acStartTime").val());
			var acEndTime=$.trim($("#acEndTime").val());
			var acCloseTime=$.trim($("#acCloseTime").val());
			//日期合法性校验，比较时间先后顺序
			$.post("checkDataValidity.ktv?method=CheckAcDate&StartDate="+acStartDate+"&StartTime="+acStartTime+"&EndTime="+acEndTime+"&CloseTime="+acCloseTime,null,function(data){
				if(data==""){
					$("#myform").submit();
				}else{
					alert(data);
					$("#acStartTime").val("");
					$("#acEndTime").val("");
					$("#acCloseTime").val("");
					$("#acStartTime").focus();
					return false;
				}
			},"text")
		}else{
			return false;
		}
	});
}
/**
 * 显示日历。
 * @author {tangkunyin} 
 * @since {2013-06-08}  
 */
function showCalenders(){
	$("#acStartDate").unbind("click");
	$("#acStartDate").bind("click",function(){
		return new Calendar().show(this.name);
	});
}