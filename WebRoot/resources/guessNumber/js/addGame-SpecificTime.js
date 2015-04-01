/**
 * 具体时间指定
 * author:tangkunyin
 * see:www.hadooper.org
 */
$().ready(function(){
	addacGameStartTime();
	addGameAcEndTime();
	addGameacCloseTime();
});
//acGameStartTime
function showacGameStartTime(){
		var hour=$("#acStartTimeh").val();
		var min=$("#acStartTimemin").val();
 		$("#acStartTime").val(hour+":"+min+":00");
}
function addacGameStartTime(){
		var hourSelect="<select name='acStartTimeh' id='acStartTimeh'></select>时";
		var minSelect="<select name='acStartTimemin' id='acStartTimemin'></select>分";

		$("#acStartTime").after(hourSelect+minSelect+"&nbsp;");
	
		//先赋值
		for(var i=0;i<=23;i++){
			if(i<10){
				$("#acStartTimeh").append("<option value='0"+i+"'>0"+i+"</option>");
			}else{
				$("#acStartTimeh").append("<option value='"+i+"'>"+i+"</option>");
			}
		}
		
		for(var i=0;i<=59;i++){
			if(i<10){
				$("#acStartTimemin").append("<option value='0"+i+"'>0"+i+"</option>");
			}else{
				$("#acStartTimemin").append("<option value='"+i+"'>"+i+"</option>");
			}
		}
		
		//绑定事件
		$("#acStartTimeh").unbind("change");
		$("#acStartTimeh").bind("change",function(){
			showacGameStartTime();
		});

		$("#acStartTimemin").unbind("change");
		$("#acStartTimemin").bind("change",function(){
			showacGameStartTime();
		});
}
//addGameAcEndTime
function showGameAcEndTime(){
		var hour=$("#acEndTimeh").val();
		var min=$("#acEndTimemin").val();
 		$("#acEndTime").val(hour+":"+min+":00");
}
function addGameAcEndTime(){
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
			showGameAcEndTime();
		});

		$("#acEndTimemin").unbind("change");
		$("#acEndTimemin").bind("change",function(){
			showGameAcEndTime();
		});
}
//
//addGameacCloseTime
function showGameacCloseTime(){
		var hour=$("#acCloseTimeh").val();
		var min=$("#acCloseTimemin").val();
 		$("#acCloseTime").val(hour+":"+min+":00");
}
function addGameacCloseTime(){
		var hourSelect="<select name='acCloseTimeh' id='acCloseTimeh'></select>时";
		var minSelect="<select name='acCloseTimemin' id='acCloseTimemin'></select>分";

		$("#acCloseTime").after(hourSelect+minSelect+"&nbsp;");
	
		//先赋值
		for(var i=0;i<=23;i++){
			if(i<10){
				$("#acCloseTimeh").append("<option value='0"+i+"'>0"+i+"</option>");
			}else{
				$("#acCloseTimeh").append("<option value='"+i+"'>"+i+"</option>");
			}
		}
		
		for(var i=0;i<=59;i++){
			if(i<10){
				$("#acCloseTimemin").append("<option value='0"+i+"'>0"+i+"</option>");
			}else{
				$("#acCloseTimemin").append("<option value='"+i+"'>"+i+"</option>");
			}
		}
		
		//绑定事件
		$("#acCloseTimeh").unbind("change");
		$("#acCloseTimeh").bind("change",function(){
			showGameacCloseTime();
		});

		$("#acCloseTimemin").unbind("change");
		$("#acCloseTimemin").bind("change",function(){
			showGameacCloseTime();
		});
}