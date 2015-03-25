App.config(['$routeProvider', '$locationProvider', '$httpProvider', function($routeProvider, $locationProvider, $httpProvider) {
		
		$routeProvider.when('/login', {templateUrl: 'views/login.html', controller: LoginController});
		
		$routeProvider.when('/home', {templateUrl: 'views/home.html', controller: HomeController});
			
		$routeProvider.when('/addProject', 
						   {templateUrl: 'views/addProject.html', 
						   controller: 'ProjectController'});
		
		$routeProvider.otherwise({redirectTo:'/home'});
		
		$locationProvider.hashPrefix('!');
		
	    $httpProvider.responseInterceptors.push(interceptor);
	    
}]);