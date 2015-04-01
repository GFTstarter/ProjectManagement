var AbsenceController = function($rootScope, $scope, $location, AbsenceService) {
	
	$scope.init = function(){
		$scope.absence.idUser = $rootScope.user.id;
	}
		
	$scope.absence = new AbsenceService();
	$scope.submitAbsenceForm = function(isValid){
		if(isValid){
			$scope.save = function() {
				$scope.absence.$save(function() {
					$location.path('#!/');					
				});
			};
		}		
	};	
};
AbsenceController.$inject = ['$rootScope', '$scope', '$location', 'AbsenceService'];


