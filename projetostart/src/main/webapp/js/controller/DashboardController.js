var DashboardController = function($rootScope, $scope, DashboardService) {
	
	DashboardService.get({id: $rootScope.user.id}, function(d) {
		
		scope.selectedProject = null;
		$scope.dataproject = new kendo.data.DataSource({
			data: d
		});
		
	});
	
	$scope.onChange = function(e) {		
		var valueID = this.element.val();		
        console.log("startDate " + valueID);
        console.log("endDate " + valueID);
	}
	
};
DashboardController.$inject = ['$rootScope', '$scope', 'DashboardService'];
