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
};
ExpensesController.$inject = ['$rootScope', '$scope', '$location', 'ExpensesService'];


