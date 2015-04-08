var AbsenceController = function($rootScope, $scope, $location, AbsenceService) {
	
	
	
	AbsenceService.getting.get(function(r) {
		
		$scope.selectedResource = null;
		
		console.log(r);
		
		$scope.absencedata = new kendo.data.DataSource({
			data: r
		});
	});
	
	
	
	
	$scope.absence = new AbsenceService.posting();
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


