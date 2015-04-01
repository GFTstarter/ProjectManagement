var TravelController = function($rootScope, $scope, $location, TravelService) {
	
	$scope.init = function(){
		$scope.travel.idUser = $rootScope.user.id;
	}
		
	$scope.travel = new TravelService();
	$scope.submitTravelForm = function(isValid){
		if(isValid){
			$scope.save = function() {
				$scope.travel.$save(function() {
					$location.path('#!/');					
				});
			};
		}		
	};	
};
TravelController.$inject = ['$rootScope', '$scope', '$location', 'TravelService'];


