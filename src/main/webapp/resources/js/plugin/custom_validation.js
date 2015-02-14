//验证金额
jQuery.validator.addMethod("isMoney",function(value,element){
	if(value>0){return true;}
},"金额必须大于0");

//验证电话号码
jQuery.validator.addMethod("isPhone",function(value,element){
	var regPhone=/^1[3|4|5|7|8|9](\d{9})$/;
	if(value==null||value=="")
		return true;
	return regPhone.test(value);
},"手机号码格式错误");
