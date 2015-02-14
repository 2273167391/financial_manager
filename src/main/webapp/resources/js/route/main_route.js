mainApp.config(['$routeProvider',function($routeProvider){
	$routeProvider.when('/',{
		templateUrl:'welcome.html'
	}).when('/user/:name/user_info.html',{
		templateUrl:'user/user_info.html'
	}).when('/account/:name/account_list.html',{
		templateUrl:'account/account_list.html'
	}).when('/account/:name/account_list.html',{
		templateUrl:'account/account_list.html'
	}).when('/account/type/type_list.html',{
		templateUrl:'account/type/type_list.html'
	}).when("/account/add_account.html",{
		templateUrl:'account/add_account.html'
	}).when("/statistics/year_statis.html",{
		templateUrl:"statistics/year_statis.html"
	}).when("/statistics/month_statis.html",{
		templateUrl:"statistics/month_statis.html"
	}).when("/statistics/type_statis.html",{
		templateUrl:"statistics/type_statis.html"
	}).when("/sys/role/role_list.html",{
		templateUrl:"sys/role/role_list.html"
	}).when("/sys/auth/auth_list.html",{
		templateUrl:"sys/auth/auth_list.html"
	}).when("/user/update_pwd.html",{
		templateUrl:"user/update_pwd.html"
	}).when("/user/user_list.html",{
		templateUrl:"user/user_list.html"
	}).when("/user/update_user.html",{
		templateUrl:"user/update_user.html"
	}).when("/sys/region/region_manager.html",{
		templateUrl:"sys/region/region_manager.html"
	}).when("/user/update_self_info.html",{
		templateUrl:"user/update_self_info.html"
	});
}]);