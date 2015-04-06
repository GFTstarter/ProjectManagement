var ExpensesController = function($rootScope, $scope, $location, ExpensesService) {
	
	$scope.init = function(){
		$scope.expenses.idUser = $rootScope.user.id;
	}
		
	$scope.expenses = new ExpensesService();
	$scope.submitExpensesForm = function(isValid){
		if(isValid){
			$scope.save = function() {
				$scope.expenses.$save(function() {
					$location.path('#!/');					
				});
			};
		}		
	};	
	
	
	ResourceService.get({id: $rootScope.user.id}, function(r) {
		
		$scope.selectedResource = null;
		$scope.resources = new kendo.data.DataSource({
			data: r
		});
		
	});

	$scope.onChange = function(e) {		
		var valueID = this.element.val();		
	    console.log("idResource: " + valueID);		
	}


	
};
ExpensesController.$inject = ['$rootScope', '$scope', '$location', 'ExpensesService'];



