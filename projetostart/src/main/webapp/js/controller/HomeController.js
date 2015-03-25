var HomeController = function($rootScope, $scope, HomeService) {
	
	HomeService.get({id: $rootScope.user.id}, function(r) {
		
		$scope.selectedProject = null;
		$scope.projects = new kendo.data.DataSource({
			data: r
		});
		
	});
	
	$scope.onChange = function(e) {		
		var valueID = this.element.val();		
        console.log("idProject: " + valueID);		
	}
	
};
HomeController.$inject = ['$rootScope', '$scope', 'HomeService'];
