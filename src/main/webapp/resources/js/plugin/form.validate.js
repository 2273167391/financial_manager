(function($){
	$.fn.extend({
		//登录验证
		loginForm:function(options){
			var settings={
				"prompt_box_class":"triangle_prompt_box",
				"redirection_url":"main.html",
				"username_error_info":"用户名不能为空",
				"pwd_error_info":"密码不能为空",
				"code_error_info":"验证码不能为空"
			};
			jQuery.extend(settings,options);
			$(this).validate({
				//按键抬起时验证
				onkeyup:function(
						element){$(element).valid();
						if($(element).val()!=''){
							$(element).siblings("."+settings.prompt_box_class).hide();
						}else{
							$(element).siblings("."+settings.prompt_box_class).show();
						}
				},
				//获取焦点时提示框隐藏
				onfocusin:function(element){
					$(element).siblings("."+settings.prompt_box_class).hide();
				},
				//失去焦点时验证并显示提示框
				onfocusout:function(element){
					$(element).valid();
					if($(element).val()==""){
						$(element).siblings("."+settings.prompt_box_class).show();
					}
				},
				errorPlacement:function(error,element){
					//设置错误信息位置
					error.appendTo(element.siblings("."+settings.prompt_box_class).show());
				},
				rules:{
					username:"required",
					password:"required",
					code:"required"
				},messages:{
					username:settings.username_error_info,
					password:settings.pwd_error_info,
					code:settings.code_error_info
				},submitHandler:function(form){
					//加载框
					var index=layer.load('正在登录，请稍后......');
					$.ajax({
						url:$(form).attr("action"),
						data:$(form).serialize(),
						dataType:'json',
						type:'POST',
						success:function(data){
							//关闭加载框
							layer.close(index);
							if(data.result==1){
								layer.msg(data.message,1,1,function(){
									location.href=settings.redirection_url;
								});
							}else{
								layer.msg(data.message,1);
							}
						}
					});
				}
			});
		},
		//添加账目验证
		addAccountForm:function(options){
			$(this).validate({
				errorPlacement:function(error,element){
					error.appendTo(element.parent().next("td"));
				},
				rules:{
					"accountName":"required",
					"typeId":"required",
					"status":"required",
					"money":{
						isMoney:true,
						required:true
					},
					"createTime":"required"
				},
				messages:{
					"accountName":"账目名称不能为空",
					"typeId":"请选择用途",
					"status":"请选择类型",
					"money":{
						required:"金额不能为空"
					},
					"createTime":"日期不能为空"
				},submitHandler:function(form){
					var index=layer.load(0);
					$.ajax({
						url:$(form).attr("action"),
						type:'POST',
						data:$(form).serialize(),
						dataType:'json',
						success:function(data){
							//关闭加载框
							layer.close(index);
							if(data.result==1){
								layer.msg(data.message,1,1,function(){
									if($("#status").val()==1){
										location.href="#/account/income/account_list.html";
									}else{
										location.href="#/account/expenditure/account_list.html";
									}
								});
							}else{
								layer.msg(data.message,1);
							}
						}
					});
				}
			});
		},
		//修改密码验证
		updatePwdForm:function(options){
			$(this).validate({
				errorPlacement:function(error,element){
					error.appendTo(element.parent().next("td"));
				},
				rules:{
					oldPwd:"required",
					newPwd:{
						required:true,
						minlength:6
					},
					againPwd:{
						equalTo:"#pwd"
					}
				},
				messages:{
					oldPwd:"旧密码不能为空",
					newPwd:{
						required:"新密码不能为空",
						minlength:"密码长度不能小于{0}字符 "
					},
					againPwd:{
						equalTo:"两次密码出入不一致"
					}
				},
				submitHandler:function(form){
					var index=layer.load(0);
					$.ajax({
						url:$(form).attr("action"),
						type:"POST",
						dataType:'JSON',
						data:$(form).serialize(),
						success:function(data){
							//关闭加载框
							layer.close(index);
							if(data.result==1){
								layer.msg(data.message,1,1,function(){
									location.href="main.html";
								});
							}else{
								layer.msg(data.message,1);
							}
						}
					});
				}
			});
		},
		//验证修改用户
		updateUserForm:function(options){
			var settings={
					"redirection_url":"#/user/user_list.html"
				};
				jQuery.extend(settings,options);
			$(this).validate({
				errorPlacement:function(error,element){
					error.appendTo(element.parent().next("td"));
				},
				rules:{
					trueName:"required",
					rId:"required",
					phone:{
						isPhone:true
					},
					email:{
						email:true
					}
				},messages:{
					trueName:"真实姓名不能为空",
					rId:"请选择角色",
					email:{
						email:"电子邮箱格式错误"
					}
				},submitHandler:function(form){
					var index=layer.load(0);
					$.ajax({
						url:$(form).attr("action"),
						type:'POST',
						dataType:'JSON',
						data:$(form).serialize(),
						success:function(data){
							//关闭加载框
							layer.close(index);
							if(data.result==1){
								layer.msg(data.message,1,1,function(){
									location.href=settings.redirection_url;
								});
							}else{
								layer.msg(data.message,1);
							}
						}
					});
				}
			});
		},
		//注册验证
		registerForm:function(options){
			var settings={
				"prompt_box_class":"triangle_prompt_box",
				"redirection_url":"index.html"
			};
			jQuery.extend(settings,options);
			$(this).validate({
				//按键抬起时验证
				onkeyup:function(
						element){$(element).valid();
						if($(element).val()!=''){
							$(element).siblings("."+settings.prompt_box_class).hide();
						}else{
							$(element).siblings("."+settings.prompt_box_class).show();
						}
				},
				//获取焦点时提示框隐藏
				onfocusin:function(element){
					$(element).siblings("."+settings.prompt_box_class).hide();
				},
				//失去焦点时验证并显示提示框
				onfocusout:function(element){
					$(element).valid();
					if($(element).val()==""){
						$(element).siblings("."+settings.prompt_box_class).show();
					}
				},
				errorPlacement:function(error,element){
					//设置错误信息位置
					error.appendTo(element.siblings("."+settings.prompt_box_class).show());
				},
				rules:{
					userName:"required",
					password:"required",
					conPwd:{
						equalTo:"#pwd"
					},
					trueName:"required",
					code:"required"
				},messages:{
					userName:"用户名不能为空",
					password:"密码不能为空",
					conPwd:{
						equalTo:"两次密码输入不一致"
					},
					trueName:"真实姓名不能为空",
					code:"验证码不能为空"
				},submitHandler:function(form){
					//加载框
					var index=layer.load('注册中，请稍后......');
					$.ajax({
						url:$(form).attr("action"),
						data:$(form).serialize(),
						dataType:'json',
						type:'POST',
						success:function(data){
							//关闭加载框
							layer.close(index);
							if(data.result==1){
								layer.msg(data.message,1,1,function(){
									location.href=settings.redirection_url;
								});
							}else{
								layer.msg(data.message,1);
							}
						}
					});
				}
			});
		}
	});
})(jQuery);