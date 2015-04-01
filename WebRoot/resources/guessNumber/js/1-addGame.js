$().ready(function(){
	subttOK();
	cancelButt();
});

/**
 * 判断输入框为，控制表单提交
 * @author {tangkunyin} 
 * @since {2013-06-08} 
 */

function checkForm(){
	var ktvName=$.trim($("#ktvName").val());
	var acName=$.trim($("#acName").val());
	var acRule=$.trim($("#acRule").val());
	
	var acStartDate=$.trim($("#acStartDate").val());
	var acStartTime=$.trim($("#acStartTime").val());
	var acEndTime=$.trim($("#acEndTime").val());
	var acCloseTime=$.trim($("#acCloseTime").val());
	
	var flag=true;
	if(ktvName==""){
		alert("请填写KTV名称！");
		$("#ktvName").focus();
		flag=false;
		return false;
	}
	if(ktvName=="[查无此项,请输入正确关键字]"){
		alert("请输入正确ktv名称");
		flag=false;
		//滞空
		$("#ktvName").val("");
		$("#ktvName").focus();
		return false;
	}
	
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
	if(!checkPrizes()){
		$("#prize1").focus();
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
	return flag;
}

//奖品验证
function checkPrizes(){
	var prize1=$.trim($("#prize1").val());
	if(prize1=="第一个奖品"){prize1=""}
	
	var prize2=$.trim($("#prize2").val());
	if(prize2=="第二个奖品"){prize2=""}
	
	var prize3=$.trim($("#prize3").val());
	if(prize3=="第三个奖品"){prize3=""}
	
	var prize4=$.trim($("#prize4").val());
	if(prize4=="第四个奖品"){prize4=""}
	
	var prize5=$.trim($("#prize5").val());
	if(prize5=="第五个奖品"){prize5=""}
	
	var prize6=$.trim($("#prize6").val());
	if(prize6=="第六个奖品"){prize6=""}
	
	var prize7=$.trim($("#prize7").val());
	if(prize7=="第七个奖品"){prize7=""}
	
	var prize8=$.trim($("#prize8").val());
	if(prize8=="第八个奖品"){prize8=""}
	
	var prize9=$.trim($("#prize9").val());
	if(prize9=="第九个奖品"){prize9=""}
	
	var prize10=$.trim($("#prize10").val());
	if(prize10=="第十个奖品"){prize10=""}
	
	var flag=true;
	var prizes=new Array(prize1,prize2,prize3,prize4,prize5,prize6,prize7,prize8,prize9,prize10);
	var count=0;
	for(var i=0;i<10;i++){
		if(prizes[i]==""){
			count++;
		}
	}
	if(count>7){
		alert("至少为活动设置三个奖品！！");
		flag=false;
	}
	$("#prs").html(10-count);
	return flag;	
}

function clearPrizeText(){
	if($.trim($("#prize1").val())=="第一个奖品"){
		$("#prize1").removeAttr("name");
	}
	if($.trim($("#prize2").val())=="第二个奖品"){
		$("#prize2").removeAttr("name");
	}
	if($.trim($("#prize3").val())=="第三个奖品"){
		$("#prize3").removeAttr("name");
	}
	if($.trim($("#prize4").val())=="第四个奖品"){
		$("#prize4").removeAttr("name");
	}
	if($.trim($("#prize5").val())=="第五个奖品"){
		$("#prize5").removeAttr("name");
	}
	if($.trim($("#prize6").val())=="第六个奖品"){
		$("#prize6").removeAttr("name");
	}
	if($.trim($("#prize7").val())=="第七个奖品"){
		$("#prize7").removeAttr("name");
	}
	if($.trim($("#prize8").val())=="第八个奖品"){
		$("#prize8").removeAttr("name");
	}
	if($.trim($("#prize9").val())=="第九个奖品"){
		$("#prize9").removeAttr("name");
	}
	if($.trim($("#prize10").val())=="第十个奖品"){
		$("#prize10").removeAttr("name");
	}
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
					//执行奖品清空操作
					clearPrizeText();
					var prs=$("#prs").html();
					$("#myform").attr("action","addGame.ktv?method=addGuessNumberForm&prs="+prs);
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
//cancel buttom
function cancelButt(){
	$("#cancel").unbind("click");
	$("#cancel").bind("click",function(){
		var a=confirm("您确定要取消吗？");
		if(a){
			//返回前一页
			window.history.back(-1);
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

/**
 * Ajax 模糊匹配 
 * @author {tangkunyin} 
 * @see {www.hadooper.org} 
 */
$(function() {
		function log( message ) {
			$("#ktvName").val(message);
		}
		$( "#ktvName" ).autocomplete({
			source: function( request, response ) {
			$.ajax({
					url: "KeyWordsAction.ktv?searchContent="+$.trim($("#ktvName").val()),
					dataType: "json",
					contentType: "application/x-www-form-urlencoded; charset=UTF-8",
					data: {
						featureClass: "P",
						style: "full",
						name_startsWith: request.term
					},
					success: function( data ) {
						response( $.map( data, function( item ) {
							return {
								label: item
							}
						}));
					}
				});
			},
			minLength: 1,
			select: function( event, ui ) {
				log( ui.item ? ui.item.label :this.value);
			},
		});
	});