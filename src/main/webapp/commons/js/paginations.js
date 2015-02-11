angular.module("com.tenghu.fiancial.paginations",[]).directive("pagination",function(){
	return {
		scope:{
			showRecords:"=showRecords",
			getpagedatas:"&",
			datamodel:"=",
			page:"="
		},
		restrict:"EA",
		templateUrl:"commons/paginations.html",
		replace:true,
		link:function(scope,element,attrs){},
		controller:function($scope,$element,$attrs){
			//分页过滤
			$scope.pageFilter=function(c){
				return c.p<=$scope.datamodel.totalPage;
			};
			
			//获取数据
			$scope.getPageData=function($event,currentPage){
				//判断当前点击的按钮下是否有disabled样式，有酒不往下执行
				if($($event.srcElement?$event.srcElement:$event.target).closest("li").hasClass('disabled'))return;
				//设置当前页
				$scope.page.currentPage=currentPage;
				//调用分页
				$scope.getpagedatas();
			};
			
			//设置每页显示的行数
			$scope.setPageSize=function(){
				//设置显示的行数
				$scope.page.pageSize=$scope.pageSize;
				$scope.page.currentPage=1;
				//调用分页
				$scope.getpagedatas();
			};
			
			//跳转页
			$scope.jumpPage=function(){
				//获取跳转也
				var pageNum=$scope.pageNum;
				if(isNaN(pageNum)){
					$(this).promptBox({iconType:0,msg:"跳转页面只能输入数字！"});
				}else if(pageNum<1){
					$scope.pageNum=pageNum=1;
				}else if(pageNum>$scope.datamodel.totalPage){
					$scope.pageNum=pageNum=$scope.datamodel.totalPage;
				}
				//设置当前页
				$scope.page.currentPage=pageNum;
				//调用分页
				$scope.getpagedatas();
			};
			
			//判断是否是当前页
			$scope.isCurrentPage=function(currentPage){
				return $scope.datamodel.currentPage==currentPage;
			};
			
		}
	};
});

/**
 * 分页参数
 * @returns {PageParam}
 */
function PageParam(){
		this.pageReqModel={
			currentPage:1,
			pageSize:10,
			paramters:{}
		};
}

/**
 * 分页数据模型
 */
function PageDataModel(){
	this.defaultPageNum=5;//默认分页按钮个数
	this.choicePageSize=[10,30,50];//选择没有显示的行数
	
	//分页按钮模型
	this.getPageBarModel=function(){
		var pModel=[];
		for(var i=1;i<=this.defaultPageNum;i++){
			pModel.push({p:i});
		}
		return pModel;
	};
	
	//分页数据模型
	this.paginationDataModel={
			currentPage:1,//当前页
			pageSize:0,//每页显示的行数
			totalCount:0,//总记录数
			totalPage:0,//总页数
			showRecords:[],//显示的数据
			choicePageSize:this.choicePageSize,
			pageBarModel:this.getPageBarModel()//分页按钮个数
	};
	
	//重置数据模型
	this.resetModel=function(responseData){
		//重置分页按钮
		this.resetNavBar(responseData.currentPage, responseData.totalPage);
		this.paginationDataModel=$.extend(this.paginationDataModel,responseData);
	};
	
	//重置分页按钮
	this.resetNavBar=function(currentPage,totalPage){
		var nvaSize=this.paginationDataModel.pageBarModel.length;//获取分页按钮个数
		if(currentPage==1){
			this.paginationDataModel.pageBarModel=this.getPageBarModel();
		}else if(currentPage>this.paginationDataModel.pageBarModel[nvaSize-1].p){
			for(var i=0;i<nvaSize;i++){
				this.paginationDataModel.pageBarModel[i].p=currentPage++;
			}
		}else if(currentPage<this.paginationDataModel.pageBarModel[0].p){
			for(var i=this.paginationDataModel.pageBarModel.length-1;i>=0;i--){
				this.paginationDataModel.pageBarModel[i].p=currentPage--;
			}
		}
	};
}