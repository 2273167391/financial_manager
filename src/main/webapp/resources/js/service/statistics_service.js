var statisticsService=angular.module('com.tenghu.fiancial.statistics.service',[]);
statisticsService.factory("statisticsService",["$http","$q",function($http,$q){
	return {
		//年统计
		yearStatistics:function(year,status,typeId){
			return requestUtil.getRequest($http, $q, "statistics/year/"+year+"/"+status+"/"+typeId);
		},//月统计
		monthStatistics:function(month,status,typeId){
			return requestUtil.getRequest($http, $q, "statistics/month/"+month+"/"+status+"/"+typeId);
		},//分类统计
		typeStatistics:function(month,status){
			return requestUtil.getRequest($http, $q, "statistics/type/"+month+"/"+status);
		}
		
	};
}]);

