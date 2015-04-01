$(function(){
	msgmove();
	$(".brodcast").hover(function(){
			$(this).attr("name","hovered"); //鼠标经过设置ul的name值为"hovered"
		},function(){
			$(this).removeAttr("name");
		});
	});
function msgmove(){ 
	var isIE=!!window.ActiveXObject;
	var isIE6=isIE&&!window.XMLHttpRequest;
	if($(".brodcast").attr("name") != "hovered"){
		//判断ul的name属性是否为"hovered"，决定下面代码块是否运行，以实现鼠标经过暂停滚动。
		var height = $(".brodcast:last").height();
		if(isIE6){
			//判断IE6，jQuery的animate动画和opacity透明在一起使用在IE6中会出现中文重影现象，
			//所以在ie6中实行透明的禁用。
			$(".brodcast:last").css({"height":0});
		}else{ 
			$(".brodcast:last").css({"opacity":0,"height":0});
			//列表最后的li透明和高度设置为0 }
			$(".brodcast:first").before($(".brodcast:last"));
			//把列表最后的li提升到顶部，实现有限列表项无限循环滚动显示
			$(".brodcast:first").animate({"height":height},300);
			//列表顶部的li高度逐渐变高以把下面的li推下去 if(!isIE6){
			$(".brodcast:first").animate({"opacity":"1"},300);
			//在非IE6浏览器中设置透明淡入效果
		}
}
//设置5秒滚动一次
setTimeout("msgmove()",3000);
}