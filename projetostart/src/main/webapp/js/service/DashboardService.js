services.factory('DashboardService', function($resource) {
	return $resource('rest/project/:id', {id: '@id'});
});