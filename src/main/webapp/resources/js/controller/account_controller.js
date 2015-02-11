var accountApp=angular.module('com.tenghu.fiancial.account.app',[]);
//账目控制器
accountApp.controller("accountController",["$scope","$location","accountService",function($scope,$location,accountService){
	//开始时间
	$scope.getStartTime=function(){
		laydate(dialog.date({elem:"#start"}));
	};
	//结束时间
	$scope.getEndTime=function(){
		laydate(dialog.date({elem:"#end"}));
	};
	
	$scope.showRecords=null;//显示的数据
	$scope.pageObject=new PageDataModel();//实例化数据模型
	$scope.datamodel=null;//数据模型
	$scope.page=new PageParam().pageReqModel;//分页参数模型
	var path=$location.path();//获取请求路径
	$scope.getPageDatas=function(){
		var url="";
		//收入
		if(path.indexOf("income")>0){
			url="account/income/list";
		//支出
		}else if(path.indexOf("expenditure")>0){
			url="account/expenditure/list";
		}
		//加载框
		var index=layer.load(0);
		//获取数据
		accountService.getAccountList($scope.page,url).then(function(responseData){
			//关闭加载框
			layer.close(index);
			$scope.pageObject.resetModel(responseData);//重置分页
			$scope.showRecords=$scope.pageObject.paginationDataModel.showRecords;//设置显示数据
			$scope.datamodel=$scope.pageObject.paginationDataModel;
			
		});
	};
	//获取第一个页面
	$scope.getFirstPage=function(){
		$scope.page.currentPage=1;
		$scope.getPageDatas();
	};
	$scope.getFirstPage();
	
	//删除账目
	$scope.deleteAccount=function(aId){
		dialog.confirm({
			title:'删除提示',
			msg:'确定要删除该条账目么？',
			msgIcon:5,
			determine:function(){
				accountService.deleteAccount(aId).then(function(data){
					if(data.result==1){
						layer.msg(data.message,1,1,function(){
							$scope.getPageDatas();//刷新
						});
					}else{
						layer.msg(data.message,1,8);
					}
				});
			}
		});		
	};
	
	//查看账目
	$scope.seeAccount=function(aId){
		$scope.accountInfo=null;
		var index=layer.load(0);
		accountService.getAccountInfo(aId).then(function(accountInfo){
			//关闭加载框
			layer.close(index);
			$scope.accountInfo=accountInfo;
			dialog.pageLayer({title:"账目信息",dom:".account_info"});
		});
	};
	
	//搜索
	$scope.search=function(){
		if($("#start").val()>$("#end").val()){
			dialog.prompt({iconType:0,msg:"开始时间不能大于结束时间！"});
			return;
		}else{
			$scope.page.paramters["startTime"]=$("#start").val();
			$scope.page.paramters["endTime"]=$("#end").val();
		}
		
		if($("#min_money").val()!=""||$("#max_money").val()!=""){
			if(isNaN($("#min_money").val())||isNaN($("#max_money").val())){
				dialog.prompt({iconType:0,msg:"请输入有效的金额！"});
				return;
			}else if(parseFloat($("#min_money").val())>parseFloat($("#max_money").val())){
				dialog.prompt({iconType:0,msg:"最小金额不能大于最大金额！"});
				return;
			}else{
				$scope.page.paramters['minMoney']=$("#min_money").val();
				$scope.page.paramters["maxMoney"]=$("#max_money").val();
			}
		}
		$scope.getPageDatas();
	};
}]);

//账目类型控制器
accountApp.controller("typeController",["$scope","accountService",function($scope,accountService){
	//显示的数据
	$scope.showRecords=null;
	//实例化数据模型
	$scope.pageObject=new PageDataModel();
	//数据模型
	$scope.datamodel=null;
	//分页参数模型
	$scope.page=new PageParam().pageReqModel;
	
	//获取数据
	$scope.getPageDatas=function(){
		accountService.getAccountTypePageList($scope.page).then(function(responseData){
			//重置分页
			$scope.pageObject.resetModel(responseData);
			//设置显示的数据
			$scope.showRecords=$scope.pageObject.paginationDataModel.showRecords;
			//设置数据模型
			$scope.datamodel=$scope.pageObject.paginationDataModel;
		});
	};
	
	//获取第一个页面
	$scope.getFirstPage=function(){
		$scope.page.currentPage=1;
		$scope.getPageDatas();
	};
	$scope.getFirstPage();
	
	//修改账目类型弹窗
	$scope.updateAccountTypeBox=function(atId){
		$scope.accountType=null;
		$scope.pageIndex=0;
		var index=layer.load(0);
		accountService.getAccountTypeInfo(atId).then(function(accountTypeInfo){
			layer.close(index);
			$scope.accountType=accountTypeInfo;
			$scope.pageIndex=dialog.pageLayer({title:"修改账目类型",dom:"#updateT"});
		});
	};
	
	//添加账目类型弹窗
	var pageIndex=0;
	$scope.addAccountTypeBox=function(){
		if($scope.accountType!=null)
			$scope.accountType=null;
		pageIndex=dialog.pageLayer({title:"添加账目类型",dom:"#addT"});
	};
	
	//添加账目类型
	$scope.addAccountType=function(accountType){
		if(accountType==null||accountType==""){
			dialog.prompt({iconType:8,msg:"类型名称不能为空"});
			return;
		}
		var index=layer.load(0);//加载框
		accountService.addAccountType(accountType).then(function(data){
			layer.close(index);//关闭加载框
			if(data.result==1){
				layer.close(pageIndex);
				layer.msg(data.message,1,1,function(){
					$scope.getFirstPage();//刷新
				});
			}else{
				layer.msg(data.message,1);
			}
		});
	};
	
	//删除账目类型
	$scope.deleteAccountType=function(atId){
		dialog.confirm({
			title:"删除提示",
			msg:"确定要删除该账目类型么？",
			msgIcon:5,
			determine:function(){
				accountService.deleteAccountType(atId).then(function(data){
					if(data.result==1){
						layer.msg(data.message,1,1);
						$scope.getFirstPage();//刷新
					}else{
						layer.msg(data.message,1,8);
					}
				});
			}
		});
	};
}]);
/**
 * 添加账目控制器
 */
accountApp.controller("addAccountController",["$scope","accountService",function($scope,accountService){
	//获取日期控件
	$scope.getDate=function(){
		laydate(dialog.date({elem:'#date',format:'YYYY-MM-DD hh:mm:ss',istime:true,festival:true}));
	};
	
	//获取所有账目类型
	accountService.getAccountTypeList().then(function(data){
		$scope.accountTypes=data;
	});
}]);