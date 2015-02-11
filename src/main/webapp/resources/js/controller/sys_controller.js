var sysApp=angular.module('com.tenghu.fiancial.sys.app',[]);
//角色控制器
sysApp.controller("roleController",["$scope","sysService",function($scope,sysService){
	
	$scope.showRecords=null;//显示的数据
	$scope.pageObject=new PageDataModel();//实例化数据模型
	$scope.datamodel=null;//数据模型
	$scope.page=new PageParam().pageReqModel;//分页参数模型
	
	//获取分页数据
	$scope.getPageDatas=function(){
		sysService.getRoleList($scope.page).then(function(data){
			$scope.pageObject.resetModel(data);//重置数据
			$scope.datamodel=$scope.pageObject.paginationDataModel;//设置数据模型
			$scope.showRecords=$scope.pageObject.paginationDataModel.showRecords;//设置显示的数据
		});
	};
	
	//获取第一页
	$scope.getFirstPage=function(){
		$scope.page.currentPage=1;
		$scope.getPageDatas();
	};
	//获取第一页
	$scope.getFirstPage();
	
	//查看权限
	$scope.seeAuth=function(rId,authIds){
		var index=layer.load(0);//加载框
		//获取角色权限
		sysService.getRoleAuth(rId).then(function(result){
			$("#tree").roleAuthZtree(result);//调用角色权限树
			//关闭加载框
			layer.close(index);
			dialog.pageLayer({title:"角色权限",dom:"#roleAuth"});
		});
	};
	
	//获取修改角色页面
	$scope.getUpdateRolePage=function(rId){
		var index=layer.load(0);//加载框
		$scope.roleInfo=null;
		sysService.getRoleInfo(rId).then(function(roleInfo){
			$scope.roleInfo=roleInfo;
			//关闭加载框
			layer.close(index);
			dialog.pageLayer({title:"修改角色",dom:"#updateRole"});
		});
	};
	
	//添加角色
	$scope.addRoleBox=function(){
		$scope.roleInfo=new Object();
		dialog.pageLayer({title:"添加角色",dom:"#addRole"});
	};
	
	//删除角色
	$scope.deleteRole=function(rId){
		dialog.confirm({
			title:'删除提示',
			msg:'确定要删除该角色么？',
			msgIcon:5,
			determine:function(){
				//加载框
				var index=layer.load(0);
				sysService.deleteRole(rId).then(function(data){
					layer.close(index);
					if(data.result==1){
						layer.msg(data.message,1,1,function(){
							$scope.getFirstPage();
						});
					}else{
						layer.msg(data.message,1,8);
					}
				});
			}
		});	
	};
}]);
/**
 * 权限控制器
 */
sysApp.controller("authController",["$scope","sysService",function($scope,sysService){
	$scope.showRecords=null;//显示的数据
	$scope.pageObject=new PageDataModel();//实例化数据模型
	$scope.datamodel=null;//数据模型
	$scope.page=new PageParam().pageReqModel;//分页参数模型
	
	//获取分页数据
	$scope.getPageDatas=function(){
		sysService.getAuthList($scope.page).then(function(data){
			$scope.pageObject.resetModel(data);//重置数据
			$scope.datamodel=$scope.pageObject.paginationDataModel;//设置数据模型
			$scope.showRecords=$scope.pageObject.paginationDataModel.showRecords;//设置显示的数据
		});
	};
	
	//获取第一页
	$scope.getFirstPage=function(){
		$scope.page.currentPage=1;
		$scope.getPageDatas();
	};
	//获取第一页
	$scope.getFirstPage();
	
	//添加权限页
	$scope.addAuthPage=function(auth){
		$scope.authority=new Object();//实例化权限
		$scope.parentAuth=auth;//父级权限
		dialog.pageLayer({title:"添加权限",dom:"#addAuth"});
	};
	
	//修改权限页
	$scope.updateAuthPage=function(auth){
		$scope.authInfo=auth;//权限信息
		dialog.pageLayer({title:"修改权限",dom:"#updateAuth"});
	};
	
	//删除权限
	$scope.deleteAuth=function(authId){
		dialog.confirm({
			title:'删除提示',
			msg:'确定要删除该权限么？',
			msgIcon:5,
			determine:function(){
				//加载框
				var index=layer.load(0);
				sysService.deleteAuth(authId).then(function(data){
					layer.close(index);
					if(data.result==1){
						layer.msg(data.message,1,1,function(){
							$scope.getFirstPage();
						});
					}else{
						layer.msg(data.message,1,8);
					}
				});
			}
		});	
	};
	
}]);