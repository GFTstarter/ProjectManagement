services.factory('AbsenceService', function($resource) {
	return $resource('rest/absence/:id', {id: '@id'});
});