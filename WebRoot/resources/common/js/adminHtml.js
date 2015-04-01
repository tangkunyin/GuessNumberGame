/*
 *  Empty value can not be submited
 *  author:tangkunyin(www.hadooper.org)
 */
$().ready(function(){
	//Do focus
	$("#uname").focus();
	checkLogin();
});
function checkLogin(){
	$("#subtt").unbind("click");
	$("#subtt").bind("click",function(){
		var name=$.trim($("#uname").val());
		var pasd=$.trim($("#pword").val());
		var role=$.trim($("#role").val());
		if(name!="" && pasd!="" &&role!=""){
			$("#mylogin").submit();
		}else{
			if(name==""){
			 	alert("请输入登录名");
				$("#uname").focus();
				return false;
			}
			if(pasd==""){
				alert("请输入密码");
				$("#pword").focus();
				return false;
			}
			if(role==""){
				alert("请选择登录身份");
				return false;
			}
		}
	});
}