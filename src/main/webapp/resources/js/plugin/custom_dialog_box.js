var dialog={
	//无标题提示框
	prompt:function(options){
		var setting={
			iconType:1,
			msg:''
		};
		jQuery.extend(setting,options);
		
		var index=$.layer({
			type:0,
			title:false,
			time:2,
			shift:'top',
			shadeClose:true,
			dialog:{
				type:setting.iconType,
				msg:setting.msg
			}
		});
		return index;
	},
	//页面层
	pageLayer:function(options){
		var settings={
				title:"",
				width:"400px",
				height:"auto",
				dom:""
		};
		jQuery.extend(settings,options);
		//页面层弹窗
		var index=$.layer({
			type:1,
			title:settings.title,
			closeBtn:[1,true],
			area:[settings.width,settings.height],//页面层的宽高
			shade:[0.5,'#000'],//遮罩的透明度及颜色
			shadeClose:true,//点击遮罩关闭页面弹层
			shift:'top',//从顶部进入
			page:{
				dom:settings.dom
			},success:function(){
				layer.close(1);
			}
		});
		return index;
	},
	//对话框提示框
	confirm:function(options){
		//默认参数
		var settings={
			title:'退出提示',
			msg:'',
			msgIcon:4,
			determine:function(){}//确认操作
		};		
		jQuery.extend(settings,options);
		var index=$.layer({
			type:0,
			title:settings.title,
			closeBtn:false,
			dialog:{
				type:settings.msgIcon,
				btns:2,
				area:['auto','auto'],
				msg:settings.msg,
				btn:['确定','取消'],
				yes:function(){settings.determine();},
				no:function(){layer.close();}
			}
		});
		return index;
	},
	//日期控件
	date:function(options){
		var setting={elem:'#start',format:'YYYY-MM-DD'};
		jQuery.extend(setting,options);
		laydate.skin("molv");
		laydate.reset();
		return setting;
	}
};