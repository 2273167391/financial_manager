$(document).ready(function(){
	document.onselectstart=new Function('event.returnValue=false;');
	setTimeout(function(){$(".menu_item_title").menu_plugin();}, 500);
	//设置时间
	setInterval(function(){$("#currentTime").current_time();},1000);
});

(function($){
	$.fn.extend({
		menu_plugin:function(options){
			//默认参数
			var defaults={
				"parent_menu_obj":"content_menu_item",
				"menu_list":"menu_list",
				"menu":"content_menu"
			};
			jQuery.extend(defaults,options);
			var menu=$("."+defaults.menu);
			//获取菜单高度
			var mHeight=menu.height();
			//获取菜单标题高度
			var tHeight=$(this).length*$(this).height();
			//设置子菜单高度
			$("."+defaults.menu_list).height(mHeight-tHeight-$(this).length*2);
			$(this).on('click',function(){
				//关闭当前点击菜单之前的菜单
				$(this).parent().prevAll("."+defaults.parent_menu_obj).children("."+defaults.menu_list).slideUp();
				//关闭或显示当前菜单
				$(this).siblings("."+defaults.menu_list).slideToggle();
				//关闭当前点击菜单之后的菜单
				$(this).parent().nextAll("."+defaults.parent_menu_obj).children("."+defaults.menu_list).hide();
			});
		},
		current_time:function(){
			var date=new Date();//创建时间对象
			//获取年份
			var year=date.getFullYear();
			//获取月份
			var month=date.getMonth()+1;
			//获取天
			var day=date.getDate();
			//获取时间
			var hour=date.getHours();
			//获取分钟
			var minutes=date.getMinutes();
			//获取秒
			var seconds=date.getSeconds();
			if(month<10){
				month="0"+month;
			}
			if(day<10){
				day="0"+day;
			}
			if(hour<10){
				hour="0"+hour;
			}
			if(minutes<10){
				minutes="0"+minutes;
			}
			if(seconds<10){
				seconds="0"+seconds;
			}
			$(this).text(year+"-"+month+"-"+day+"  "+hour+":"+minutes+":"+seconds);
		},
		//角色权限树
		roleAuthZtree:function(data){
			var setting,zTreeObj=null;
			//设置zTree参数
			setting={
					edit:{
						enable:true,//超链接失效
						showRemoveBtn:false,//不显示删除图标
						showRenameBtn:false//不现实编辑图标
					}
			};
			zTreeObj=$.fn.zTree.init($(this),setting,data);//获取数对象
			//默认全部展开
			zTreeObj.expandAll(true);
		},
		//权限树
		authorityTree:function(data,authIds){
			var self=this;
			var setting,zTreeObj=null;
			//设置参数
			setting={
					check:{
						enable:true,
						chkStyle:'checkbox',
						autoCheckTrigger:true
					}
			};
			zTreeObj=$.fn.zTree.init($(this),setting,data);//获取树对象
			zTreeObj.expandAll(true);//展开节点
			//获取所有节点
			var nodes=zTreeObj.getNodes();
			//默认选中节点
			self.autoCheckNode(zTreeObj, nodes, authIds==null?new Array():authIds.split(","));
			//获取选中节点的id
			var selectedNodes=zTreeObj.getSelectedNodes();
			return zTreeObj;//返回树对象
		},
		//地区树
		regionTree:function(data){
			var self=this;
			//设置参数
			var setting={};
			var zTreeObj=$.fn.zTree.init($(this),setting,data);
			return zTreeObj;
		},
		//自动选择节点
		autoCheckNode:function(zTreeObj,nodes,authIds){
			for(var i=0;i<nodes.length;i++){
				for(var j=0;j<authIds.length;j++){
					if(nodes[i].id==authIds[j]){
						//选中节点
						zTreeObj.checkNode(nodes[i],true,true);
					}
				}
				this.autoCheckNode(zTreeObj, nodes[i].children, authIds);
			}
		},
	});
})(jQuery);