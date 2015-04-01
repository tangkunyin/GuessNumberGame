/*
 *  add template js
 *  author:tangkunyin
 *  date:2013-05-24
 */
$().ready(function(){
	resetFunction();
	subttOK();
	checUploadFile();
});

/*
 * check forms
 * all the input values can not be empty
 */
function  chectForm(){
	var tempName=$.trim($("#tempName").val());
	var acRule=$.trim($("#acRule").val());
	var fileContent=$.trim( $("#acPicAddress").val());
	var flag=true;
	
	if(tempName==""){
		alert("请填写模版名称！");
		$("#tempName").focus();
		flag=false;
		return flag;
	}else{
		//检查模版名称是否重复
		$.post("/game/checkDataValidity.ktv?method=CheckTempNames&name="+tempName,null,function(data){
			if(data=="no"){
				alert("模版名称不允许重复，请更换！");
				$("#acName").val("");
				flag=false;
				return flag;
			}
		},"text")
	}
	if(acRule==""){
		alert("请为活动填写规则！");
		$("#acRule").focus();
		flag=false;
		return flag;
	}
	if(fileContent==""){
		alert("请选择文件并上传！");
		flag=false;
		return flag;
	}
	return flag;
}
//subutton submit
function subttOK(){
	$("#subtt").unbind("click");
	$("#subtt").bind("click",function(){
		var isTrue=chectForm();
		if(isTrue){
			$("#myform").submit();
		}
	});
}
//uploadTextCheck
function checUploadFile(){
	$("#subttfile").unbind("click");
	$("#subttfile").bind("click",function(){
		var fileContent=$.trim( $("#acPicAddress").val());
		if(fileContent!=""){
			$('#uploadForm').ajaxSubmit(function(data) { 
                if(data!="请上传合法的图片类型，支持：png|gif|jpg|jpeg"){
                	alert("上传成功！");
	                //添加图片链接
	                $("#showAcPic").attr("src",data);
                }else{
                	$("#acPicAddress").val("");
                	$("#uploadts").html(data);
                }
            }); 
		}else{
			alert("请选择要上传的图片文件！");
			return false;
		}
	});
}

//reset function
function resetFunction(){
	$("#resetButt").unbind("click");
	$("#resetButt").bind("click",function(){
		$("#tempName").val("");
		$("#acRule").val("");
		$("#acPicAddress").val("");
	});
}
