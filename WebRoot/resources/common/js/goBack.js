/*
 * 通用返回组建
 */
$().ready(function(){
	goback();
	setAreaStyle();
});
function goback(){
	$("#goback").unbind("click");
	$("#goback").bind("click",function(){
			window.history.back(-1);
	});
}

//IE下，去掉热点链接的黑边
function setAreaStyle(){
	$("area").unbind("focus");
	$("area").bind("focus",function(){
		this.focus()=this.blur();
	});
}