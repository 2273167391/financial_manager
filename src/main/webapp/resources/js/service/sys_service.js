var sysServiceApp=angular.module("com.tenghu.fiancial.sys.service",[]);
sysServiceApp.factory("sysService",["$http","$q",function($http,$q){
	return {
		//获取角色列表
		getRoleList:function(page){
			return requestUtil.postRequest($http, $q, page, 'role/list');
		},
		//获取角色权限
		getRoleAuth:function(rId){
			return requestUtil.getRequest($http, $q, 'role/auth/'+rId);
		},
		//获取所有角色
		getRoleAll:function(){
			return requestUtil.getRequest($http, $q, 'role/all');
		},
		//获取角色
		getRoleInfo:function(rId){
			return requestUtil.getRequest($http, $q, 'role/info/'+rId);
		},
		//获取所有权限
		getAuthorityAll:function(){
			return requestUtil.getRequest($http, $q, 'auth/all');
		},
		//添加角色
		addRole:function(role){
			return requestUtil.postRequest($http, $q, role, 'role/add');
		},
		//修改角色
		updateRole:function(role){
			role.createTime=null;
			return requestUtil.postRequest($http, $q, role, 'role/edit');
		},
		//删除角色
		deleteRole:function(rId){
			return requestUtil.getRequest($http, $q, 'role/delete/'+rId);
		},
		//获取权限列表
		getAuthList:function(page){
			return requestUtil.postRequest($http, $q, page, 'auth/list');
		},
		//添加权限
		addAuth:function(auth,parentId){
			return requestUtil.postRequest($http, $q, auth, 'auth/add/'+parentId);
		},
		//删除权限
		deleteAuth:function(authId){
			return requestUtil.getRequest($http, $q, 'auth/delete/'+authId);
		},
		//修改权限
		updateAuth:function(auth){
			return requestUtil.postRequest($http, $q, auth, 'auth/update');
		},
		//获取地区
		getRegions:function(){
			return requestUtil.getRequest($http, $q, "regions/data");
		}
	};
}]);