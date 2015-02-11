var mainService=angular.module('com.tenghu.fiancial.main.service',[]);
mainService.factory("mainService",["$http","$q",function($http,$q){
	return {
		//获取Session中的用户
		getSessionUsers:function(){
			return requestUtil.getRequest($http, $q, 'main/get_current_user');
		},
		//获取菜单
		getMenu:function(){
			return requestUtil.getRequest($http, $q, 'main/get_menu');
		}
	};
}]);