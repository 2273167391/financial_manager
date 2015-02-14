var userService=angular.module('com.tenghu.fiancial.user.service',[]);
userService.factory("userService",["$http","$q",function($http,$q){
	return {
		//获取用户信息
		getUsersInfo:function(userId){
			return requestUtil.getRequest($http, $q, 'user/get_user_info/'+userId);
		},
		//获取用户列表
		getUserList:function(page){
			return requestUtil.postRequest($http, $q, page, 'user/list');
		},
		//删除用户
		deleteUser:function(userId){
			return requestUtil.getRequest($http, $q, 'user/delete/'+userId);
		}
	};
}]);