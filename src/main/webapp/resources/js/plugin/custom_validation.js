//验证金额
jQuery.validator.addMethod("isMoney",function(value,element){
	if(value>0){return true;}
},"金额必须大于0");