var ActivitySheetController = function($rootScope, $scope, ActivitySheetService) {
	
	ActivitySheetService.get({id: $rootScope.user.id}, function(d) {
		
		scope.selectedProject = null;
		$scope.dateProject = new kendo.data.DataSource({
			data: d
		});
		
	});
	
	$scope.onChange = function(e) {		
		var valueID = this.element.val();		
        console.log("hours " + valueID);
        console.log("month " + valueID);
	}
	
};
ActivitySheetController.$inject = ['$rootScope', '$scope', 'ActivitySheetService'];
