var ExpensesController = function($rootScope, $scope, $location, ExpensesService) {
	
	
	ExpensesService.getting.get(function(r) {
		
		console.log(r);
		
		$scope.expensesdata = new kendo.data.DataSource({
			data: r
		});
		
		$scope.expenses.idResource = null;
	});
	
	
	$scope.expenses = new ExpensesService.posting();
	$scope.submitExpensesForm = function(isValid){
		if(isValid){
			$scope.save = function() {
				$scope.expenses.$save(function() {
					$location.path('#!/');					
				});
			};
		}		
	};	
};

ExpensesController.$inject = ['$rootScope', '$scope', '$location', 'ExpensesService'];


