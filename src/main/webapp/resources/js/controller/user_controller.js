var userApp=angular.module('com.tenghu.fiancial.user.app',[]);
userApp.controller('userInfoController',['$scope','$location','userService',function($scope,$location,userService){
	var uId=0;
	//判断是否是查看个人信息
	$scope.isLookSelfInfo=function(){
		var currentPath=$location.path();
		if(currentPath.indexOf('/user_list')>-1){
			return true;
		}else{
			$location.search("uId="+$scope.user.uId);
			return false;
		}
	};
	//获取用户信息
	userService.getUsersInfo($location.search().uId).then(function(data){
		$scope.user=data;
	});
}]);

/**
 * 用户控制器
 */
userApp.controller("userController",["$scope","$location","userService",function($scope,$location,userService){
	$scope.showRecords=null;//显示的数据
	$scope.pageObject=new PageDataModel();//实例化数据模型
	$scope.datamodel=null;//数据模型
	$scope.page=new PageParam().pageReqModel;//分页参数模型
	
	//获取分页数据
	$scope.getPageDatas=function(){
		userService.getUserList($scope.page).then(function(data){
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
	
	//查看用户
	$scope.seeUser=function(uId){
		$location.path("/user/user_list/user_info.html");
		$location.search("uId="+uId);
	};
	
	//修改用户
	$scope.updateUser=function(uId){
		$location.path("/user/update_user.html");
		$location.search("uId="+uId);
	};
}]);

/**
 * 修改用户控制器
 */
userApp.controller("updateUserController",["$scope","$location","userService","sysService",function($scope,$location,userService,sysService){
	
	//获取地区
	sysService.getRegions().then(function(data){
		$scope.provinces=data;
	});
	//获取所有角色
	sysService.getRoleAll().then(function(data){
		$scope.roles=data;
	});
	//获取用户信息
	userService.getUsersInfo($location.search().uId).then(function(data){
		$scope.user=data;
		$scope.rId=data.role.rId;
		$scope.province=data.province;
		$scope.city=data.city;
		$scope.region=data.region;
	});
	
	
	$scope.getDate=function(){
		laydate(dialog.date({elem:"#date"}));
	};
	
	$scope.citys=null;//城市
	$scope.regions=null;//地区
	//监听省份
	$scope.$watch("province",function(name,oldName){
		$scope.regions=null;//还原地区
		if(name!=null){
			for(var i=0;i<$scope.provinces.length;i++){
				if(name==$scope.provinces[i].name){
					$scope.citys=$scope.provinces[i].children;//设置城市
				}
			}
		}
	});
	//监听城市
	$scope.$watch("city",function(name,oldName){
		if(name!=null){
			for(var i=0;i<$scope.citys.length;i++){
				if(name==$scope.citys[i].name){
					$scope.regions=$scope.citys[i].children;//设置地区
				}
			}
		}
	});
}]);