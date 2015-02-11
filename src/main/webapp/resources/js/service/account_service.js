var accountService=angular.module('com.tenghu.fiancial.account.service',[]);
accountService.factory('accountService',["$http","$q",function($http,$q){
	return {
		//获取账目列表
		getAccountList:function(page,url){
			return requestUtil.postRequest($http, $q, page,url);
		},
		//删除账目
		deleteAccount:function(aId){
			return requestUtil.getRequest($http, $q, 'account/delete/'+aId);
		},
		//获取账目信息
		getAccountInfo:function(aId){
			return requestUtil.getRequest($http, $q, 'account/info/'+aId);
		},
		//获取账目类型列表
		getAccountTypePageList:function(page){
			return requestUtil.postRequest($http, $q, page, 'account/type/list');
		},
		//获取账目类型信息
		getAccountTypeInfo:function(atId){
			return requestUtil.getRequest($http, $q, 'account/type/'+atId);
		},
		//添加账目类型
		addAccountType:function(accountType){
			return requestUtil.postRequest($http, $q, accountType, 'account/type/add');
		},
		//修改账目类型
		updateAccountType:function(accountType){
			accountType.createTime=null;
			return requestUtil.postRequest($http, $q, accountType, 'account/type/edit');
		},
		//删除账目类型
		deleteAccountType:function(atId){
			return requestUtil.getRequest($http, $q, 'account/type/delete/'+atId);
		},
		//获取所有账目类型
		getAccountTypeList:function(){
			return requestUtil.getRequest($http, $q, 'account/type/all/list');
		}
	};
}]);