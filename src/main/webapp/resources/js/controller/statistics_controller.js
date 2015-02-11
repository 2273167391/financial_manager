var statisticsApp=angular.module('com.tenghu.fiancial.statistics.app',[]);
/**
 * 按年统计
 */
statisticsApp.controller("yearStatisController",["$scope","statisticsService","accountService",function($scope,statisticsService,accountService){
	$scope.year=new Date().getFullYear();//默认2015年
	$scope.status=1;//默认收入
	$scope.typeId=0;
	//获取所有账目类型
	accountService.getAccountTypeList().then(function(data){
		$scope.accountTypes=data;
	});

	//获取年份
	$scope.getYear=function(){
		var year=new Date().getFullYear()-3;
		var years=[];
		for(var i=year;i<=year+10;i++){years.push(i);}
		return years;
	};
	
	//统计
	$scope.statistics=function(){
		if($scope.year==""){dialog.prompt({iconType:0,msg:"请选择年份！"});return;}
		statisticsService.yearStatistics($scope.year,$scope.status,$scope.typeId).then(function(result){
			
			var str=$scope.typeId==0?"所有":$(".typeId").find("option:selected").text();
			//报表
			statistics.barChart({
				elem:"chart-container",
				title:$scope.status==1?$scope.year+"年"+str+"收入":$scope.year+"年"+str+"支出",
				subTitle:"个人财务管理",
				x:"月份",
				y:"金额",
				type:"year",
				data:result
			});
		});
		
	};
	//调用统计
	$scope.statistics();
}]);

/**
 * 按月统计
 */
statisticsApp.controller("monthStatisticsController",["$scope","statisticsService","accountService",function($scope,statisticsService,accountService){
	var date=new Date();
	$scope.month=date.getFullYear()+"-"+date.getMonth()+1;//初始化
	$("#month").val($scope.month);
	$scope.status=1;//默认收入
	$scope.typeId=0;
	//获取月份
	$scope.getMonth=function(){
		laydate(dialog.date({elem:"#month",format:"YYYY-MM"}));
	};
	
	//获取所有账目类型
	accountService.getAccountTypeList().then(function(data){
		$scope.accountTypes=data;
	});

	
	//统计
	$scope.statistics=function(){
		var month=$("#month").val();
		if(month==""){dialog.prompt({iconType:0,msg:"请选择月份！"});return;}
		//统计查询
		statisticsService.monthStatistics(month,$scope.status,$scope.typeId).then(function(result){
			var str=$scope.typeId==0?"所有":$(".typeId").find("option:selected").text();
			//报表
			statistics.barChart({
				elem:"chart-container",
				title:$scope.status==1?month+"月"+str+"收入":month+"支"+str+"出月",
				subTitle:"个人财务管理",
				x:"日期/天",
				y:"金额",
				type:"month",
				month:month,
				data:result
			});
		});
	};
	
	$scope.statistics();
}]);

/**
 * 分类统计
 */
statisticsApp.controller("typeStatisticsController",["$scope","statisticsService",function($scope,statisticsService){
	var date=new Date();
	$scope.month=date.getFullYear()+"-"+date.getMonth()+1;//初始化
	$("#month").val($scope.month);
	$scope.status=1;//默认收入
	//获取月份
	$scope.getMonth=function(){
		laydate(dialog.date({elem:"#month",format:"YYYY-MM"}));
	};
	
	//统计
	$scope.statistics=function(){
		var month=$("#month").val();
		if(month==""){dialog.prompt({iconType:0,msg:"请选择月份！"});return;}
		//统计查询
		statisticsService.typeStatistics(month,$scope.status).then(function(result){
			//报表
			statistics.pieChart({
				elem:"chart-container",
				title:$scope.status==1?month+"月收入":month+"月支出",
				subTitle:"个人财务管理",
				data:result
			});
		});
	};
	$scope.statistics();
	
}]);

