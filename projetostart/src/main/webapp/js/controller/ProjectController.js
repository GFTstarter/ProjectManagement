var ProjectController = function($rootScope, $scope, $location, ProjectService) {
	
	$scope.init = function(){
		$scope.project.idUser = $rootScope.user.id;
	}
		
	$scope.project = new ProjectService();
	$scope.submitProjectForm = function(isValid){
		if(isValid){
			$scope.save = function() {
				$scope.project.$save(function() {
					$location.path('#!/');					
				});
			};
		}		
	};	
};
ProjectController.$inject = ['$rootScope', '$scope', '$location', 'ProjectService'];


