App.config(['$routeProvider', '$locationProvider', '$httpProvider', function($routeProvider, $locationProvider, $httpProvider) {
		
		$routeProvider.when('/login', {templateUrl: 'views/login.html', controller: LoginController});
		
		$routeProvider.when('/home', {templateUrl: 'views/home.html', controller: HomeController});
			
		$routeProvider.when('/addProject', 
						   {templateUrl: 'views/addProject.html', 
						   controller: 'ProjectController'});
		
		$routeProvider.when('/addAbsence', 
				   {templateUrl: 'views/addAbsence.html', 
				   controller: 'AbsenceController'});
		
		$routeProvider.when('/addExpenses', 
				   {templateUrl: 'views/addExpenses.html', 
				   controller: 'ExpensesController'});
		
		$routeProvider.otherwise({redirectTo:'/home'});
		
		$locationProvider.hashPrefix('!');
		
	    $httpProvider.responseInterceptors.push(interceptor);
	    
}]);