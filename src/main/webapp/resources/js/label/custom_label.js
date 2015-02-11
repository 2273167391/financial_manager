/**
 * 自定义html标签Js文件
 */
var customLabelApp=angular.module('com.tenghu.fiancial.custom_label',[]);
/**
 * 自定义查看账目信息标签
 */
customLabelApp.directive("account",function(){
	return {
		scope:{accountInfo:"=accountInfo"},
		restrict:"EA",
		templateUrl:"account/account_info.html",
		replace:true
	};
});
/**
 * 修改账目类型标签
 */
customLabelApp.directive("updateAccountType",["accountService",function(accountService){
	return {
		scope:{
			accountType:"=accountType",
			getpagedatas:"&",
			pageIndex:"="
		},
		restrict:"EA",
		priority:100,
		templateUrl:"account/type/update_account_type.html",
		replace:true,
		controller:function($scope,$element,$attrs){
			$scope.updateAccountType=function(accountType){
				if(accountType.typeName==""){
					dialog.prompt({iconType:8,msg:"类型名称不能为空！"});
					return;
				}
				var index=layer.load(0);//加载层
				accountService.updateAccountType(accountType).then(function(data){
					//关闭加载框
					layer.close(index);
					if(data.result==1){
						layer.close($scope.pageIndex);//关闭修改弹窗
						layer.msg(data.message,1,1,function(){
							$scope.getpagedatas();//刷新
						});
					}else{
						layer.msg(data.message,1);
					}
				});
			};
		}
	};
}]);

/**
 * 添加账目类型标签
 */
customLabelApp.directive("addAccountType",function(){
	return {
		restrict:"EA",
		templateUrl:"account/type/add_account_type.html",
		replace:true
	};
});
/**
 * 角色权限标签
 */
customLabelApp.directive("roleAuth",function(){
	return {
		restrict:"EA",
		templateUrl:"sys/role/role_auth.html",
		replace:true
	};
});

/**
 * 添加角色
 */
customLabelApp.directive("addRole",["sysService",function(sysService){
	return {
		scope:{
			roleInfo:"=roleInfo",
			getpagedatas:"&"
		},
		restrict:"EA",
		templateUrl:"sys/role/add_role.html",
		replace:true,
		controller:function($scope,$element,$attrs){
			//权限
			$scope.authority=function(aIds){
				var index=layer.load(0);//加载框
				sysService.getAuthorityAll().then(function(data){
					var zTreeObj=$("#authTree").authorityTree(data,aIds);//权限树
					layer.close(index);
					index=dialog.pageLayer({title:"所有权限",dom:"#authorityAll"});
					$scope.$emit("authBoxIndex",index);//注册冒泡事件
				});
			};
			//添加
			$scope.addRole=function(role){
				if(role.roleName==""||role.roleName==null){
					layer.msg("角色名称不能为空",1,8);
				}else if(role.authIds==""||role.authIds==null){
					layer.msg("请选择权限",1,8);
				}else{
					var index=layer.load(0);//加载框
					sysService.addRole(role).then(function(data){
						layer.close(index);
						if(data.result==1){
							layer.msg(data.message,1,1,function(){
								layer.closeAll();
								$scope.getpagedatas();
							});
						}else{
							layer.msg(data.message,1,8);
						}
					});
				}
			};
			//关闭页面弹窗
			$scope.close=function(){
				layer.closeAll();
			};
		}
	};
}]);
/**
 * 修改角色
 */
customLabelApp.directive("updateRole",["sysService",function(sysService){
	return {
		scope:{
			roleInfo:"=roleInfo",
			getpagedatas:"&"
		},
		restrict:"EA",
		templateUrl:"sys/role/update_role.html",
		replace:true,
		controller:function($scope,$element,$attrs){

			//权限
			$scope.authority=function(aIds){
				var index=layer.load(0);//加载框
				sysService.getAuthorityAll().then(function(data){
					var zTreeObj=$("#authTree").authorityTree(data,aIds);//权限树
					layer.close(index);
					index=dialog.pageLayer({title:"所有权限",dom:"#authorityAll"});
					$scope.$emit("authBoxIndex",index);//注册冒泡事件
				});
			};
			
			//修改
			$scope.updateRole=function(role){
				if(role.roleName==""){
					layer.msg("角色名称不能为空",1,8);
				}else if(role.authIds==""){
					layer.msg("请选择权限",1,8);
				}else{
					var index=layer.load(0);//加载框
					sysService.updateRole(role).then(function(data){
						layer.close(index);
						if(data.result==1){
							layer.msg(data.message,1,1,function(){
								layer.closeAll();
								$scope.getpagedatas();
							});
						}else{
							layer.msg(data.message,1,8);
						}
					});
				}
			};
			
			//关闭页面弹窗
			$scope.close=function(){
				layer.closeAll();
			};
		}
	};
}]);
/**
 * 权限标签
 */
customLabelApp.directive("authority",function(){
	return {
		restrict:"EA",
		templateUrl:"sys/role/authority_all.html",
		replace:true,
		controller:function($scope,$element,$attrs){
			var authBoxIndex=0;
			//关闭页面弹窗
			$scope.close=function(){
				layer.close(authBoxIndex);
			};
			//确定
			$scope.determine=function(){
				var zTreeObj=$.fn.zTree.getZTreeObj("authTree");//获取权限树对象
				var nodes=zTreeObj.getCheckedNodes(true);//获取所有勾选的节点
				var authIds="";
				//遍历勾选的节点
				for(var i=0;i<nodes.length;i++){
					authIds+=nodes[i].id+",";
				}
				if(authIds==""){
					layer.msg("请选择需要的权限",1,8);
				}else{
					layer.close(authBoxIndex);//关闭窗口
					$scope.roleInfo.authIds=authIds.substring(0,authIds.length-1);//设置选中的权限
				}
			};
			
			//监听事件
			$scope.$on("authBoxIndex",function(event,msg){
				authBoxIndex=msg;
			});
		}
	};
});
/**
 * 添加权限标签
 */
customLabelApp.directive("addAuthority",["sysService",function(sysService){
	return {
		scope:{
			parentAuth:"=parentAuth",
			getpagedatas:"&"
		},
		restrict:"EA",
		replace:true,
		templateUrl:"sys/auth/add_auth.html",
		controller:function($scope,$element,$attrs){
			//添加权限
			$scope.addAuth=function(auth){
				if(auth.authName==null){
					layer.msg("权限名称不能为空",1,8);
				}else{
					var index=layer.load(0);//加载框
					sysService.addAuth(auth,$scope.parentAuth.authId).then(function(data){
						layer.close(index);
						if(data.result==1){
							layer.msg(data.message,1,1,function(){
								$scope.getpagedatas();
								layer.closeAll();
							});
						}else{
							layer.msg(data.message,1,8);
						}
					});
				}
			};
			
			//关闭弹窗
			$scope.close=function(){
				layer.closeAll();
			};
		}
	};
}]);

/**
 * 修改权限
 */
customLabelApp.directive("updateAuthority",["sysService",function(sysService){
	return {
		scope:{
			authInfo:"=authInfo",
			getpagedatas:"&"
		},
		restrict:"EA",
		templateUrl:"sys/auth/update_auth.html",
		controller:function($scope,$element,$attrs){
			//修改权限
			$scope.updateAuth=function(authInfo){
				$scope.auth=new Object();//实例化权限对象
				if(authInfo.authName==null){
					layer.msg("权限名称不能为空",1,8);
				}else{
					var index=layer.load(0);//加载框
					$scope.auth.authName=authInfo.authName;//权限名
					$scope.auth.url=authInfo.url;//权限url
					$scope.auth.authId=authInfo.authId;//权限id
					sysService.updateAuth($scope.auth).then(function(data){
						layer.close(index);
						if(data.result==1){
							layer.msg(data.message,1,1,function(){
								$scope.getpagedatas();
								layer.closeAll();
							});
						}else{
							layer.msg(data.message,1,8);
						}
					});
				}
			};
			
			//关闭弹窗
			$scope.close=function(){
				layer.closeAll();
			};
		}
	};
}]);

