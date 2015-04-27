services.factory('ActivitySheetService', function($resource) {
	return $resource('rest/activitySheet/:id', {id: '@id'});
});