/**
 * 请求工具类
 */
var requestUtil={
	//Get请求
	getRequest:function($http,$q,url){
		var deferred=$q.defer();
		$http({
			url:url,
			method:'GET',
			cache:false,
			headers:{'Content-Type':'application/x-www-form-urlencoded;charset=utf-8'}
		}).success(function(data,status,headers,config){
			deferred.resolve(data);
		}).error(function(data,status,headers,config){
			deferred.reject(data);
		});
		return deferred.promise;
	},
	//Post请求
	postRequest:function($http,$q,obj,url){
		var deferred=$q.defer();
		var data=$.param(obj);
		$http({
			url:url,
			method:'POST',
			cache:false,
			data:data,
			headers:{'Content-Type':'application/x-www-form-urlencoded;charset=utf-8'}
		}).success(function(data,status,headers,config){
			deferred.resolve(data);
		}).error(function(data,status,headers,config){
			deferred.reject(data);
		});
		return deferred.promise;
	}
};