/**
 * 具体时间指定
 */
$().ready(function(){
	//addTemp.jsp：acEndTime	 acEndTime2	
	addTempacEndTime();
	addTempacEndTime2();
});
//======================addTemp.jsp======================acEndTime
function showacEndTime(){
		var hour=$("#acEndTimeh").val();
		var min=$("#acEndTimemin").val();
 		$("#acEndTime").val(hour+":"+min+":00");
}
function addTempacEndTime(){
		var hourSelect="<select name='acEndTimeh' id='acEndTimeh'></select>时";
		var minSelect="<select name='acEndTimemin' id='acEndTimemin'></select>分";

		$("#acEndTime").after(hourSelect+minSelect+"&nbsp;");
	
		//先赋值
		for(var i=0;i<=23;i++){
			if(i<10){
				$("#acEndTimeh").append("<option value='0"+i+"'>0"+i+"</option>");
			}else{
				$("#acEndTimeh").append("<option value='"+i+"'>"+i+"</option>");
			}
		}
		
		for(var i=0;i<=59;i++){
			if(i<10){
				$("#acEndTimemin").append("<option value='0"+i+"'>0"+i+"</option>");
			}else{
				$("#acEndTimemin").append("<option value='"+i+"'>"+i+"</option>");
			}
		}
		
		//绑定事件
		$("#acEndTimeh").unbind("change");
		$("#acEndTimeh").bind("change",function(){
			showacEndTime();
		});

		$("#acEndTimemin").unbind("change");
		$("#acEndTimemin").bind("change",function(){
			showacEndTime();
		});
}
//======================addTemp.jsp======================acEndTime2
function showacEndTime2(){
		var hour=$("#acEndTime2h").val();
		var min=$("#acEndTime2min").val();
 		$("#acEndTime2").val(hour+":"+min+":00");
}
function addTempacEndTime2(){
		var hourSelect="<select name='acEndTime2h' id='acEndTime2h'></select>时";
		var minSelect="<select name='acEndTime2min' id='acEndTime2min'></select>分";

		$("#acEndTime2").after(hourSelect+minSelect+"&nbsp;");
	
		//先赋值
		for(var i=0;i<=23;i++){
			if(i<10){
				$("#acEndTime2h").append("<option value='0"+i+"'>0"+i+"</option>");
			}else{
				$("#acEndTime2h").append("<option value='"+i+"'>"+i+"</option>");
			}
		}
		
		for(var i=0;i<=59;i++){
			if(i<10){
				$("#acEndTime2min").append("<option value='0"+i+"'>0"+i+"</option>");
			}else{
				$("#acEndTime2min").append("<option value='"+i+"'>"+i+"</option>");
			}
		}
		
		//绑定事件
		$("#acEndTime2h").unbind("change");
		$("#acEndTime2h").bind("change",function(){
			showacEndTime2();
		});

		$("#acEndTime2min").unbind("change");
		$("#acEndTime2min").bind("change",function(){
			showacEndTime2();
		});
}